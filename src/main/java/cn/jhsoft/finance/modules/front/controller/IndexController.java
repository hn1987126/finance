package cn.jhsoft.finance.modules.front.controller;

import cn.jhsoft.finance.common.utils.R;
import cn.jhsoft.finance.common.utils.RedisKeys;
import cn.jhsoft.finance.common.utils.RedisUtils;
import cn.jhsoft.finance.modules.api.annotation.AuthIgnore;
import cn.jhsoft.finance.modules.pm.entity.DeviceEntity;
import cn.jhsoft.finance.modules.pm.service.DeviceService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenyi9 on 2017/8/29.
 */

@RestController
@RequestMapping("/f")
public class IndexController {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 网关接口
     */
    @AuthIgnore
    @PostMapping("/api")
    public R GateWayApi(@RequestBody byte[]bytes) throws Exception {
        // post过来的数据流，去掉前面5个字节,它是  data={json字符串} 这种格式的
        String postStr = new String(bytes, 5, bytes.length-5);
        postStr = URLDecoder.decode(postStr, "utf-8");

        // 整串json
        JsonObject allData = new JsonParser().parse(postStr).getAsJsonObject();
        JsonArray jr = allData.get("data").getAsJsonArray();
        if (allData == null || jr.size() == 0){
            return R.error("数据不合法");
        }
        Iterator it = jr.iterator();
        JsonObject jo;
        String mac, macKey;
        StringBuffer sb = new StringBuffer();
        // 存放json的data数据
        List<JsonObject> list = new ArrayList<>();
        // 批量取redis的key
        Set<String> macKeys = new LinkedHashSet<>();
        while (it.hasNext()) {
            jo = (JsonObject) it.next();
            mac = jo.get("mac").getAsString();
            macKey = mac.substring(0, 8).replaceAll(":", "-");
            jo.addProperty("macKey", macKey);
            macKeys.add(macKey);
            list.add(jo);
        }

        // redis批量获取
        Map<String, String> macFactorys = redisUtils.gets(macKeys);

        // 根据设备ID取设备信息
        String deviceNum = allData.get("id").getAsString();
        DeviceEntity device = redisUtils.get(RedisKeys.DEVICE_KEY+deviceNum, DeviceEntity.class);
        if (device == null){
            device = deviceService.queryObjectByNum(deviceNum);
            if (device != null) {
                redisUtils.set(RedisKeys.DEVICE_KEY + device.getDeviceNum(), device);
            }
        }

        String macFactory;
        for (JsonObject data : list){
            // 排除电脑 Intel Corporate
            macFactory = macFactorys.get(data.get("macKey").getAsString());
            if ("Intel Corporate".equals(macFactory)){
                continue;
            }
            data.addProperty("mac_factory", macFactory == null ? "" : macFactory);
            data.addProperty("id", deviceNum);
            data.addProperty("mmac", allData.get("mmac").getAsString());
            data.addProperty("rate", allData.get("rate").getAsString());
            data.addProperty("time", allData.get("time").getAsString());
            data.addProperty("lat", allData.get("lat").getAsString());
            data.addProperty("lon", allData.get("lon").getAsString());
            data.addProperty("timestamp", System.currentTimeMillis());
            if (device != null) {
                // 所在商场
                data.addProperty("mall", device.getMallName());
                // 所在楼层
                data.addProperty("floor", device.getFloor());
                // 设备标签
                data.addProperty("tags", device.getTags());
            }
            data.remove("macKey");
//            kafkaTemplate.send("test1", data.toString());
            sb.append(data.toString()+"\n");

        }

        // 写文件
        File file = new File("/export/wwwlogs/flume.access.log."+deviceNum);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        FileLock fileLock = null;
        while(true){
            try {
                // 得到锁
                fileLock = fileChannel.tryLock();
                break;
            } catch (Exception e) {
                System.out.println("有其他线程正在操作该文件，当前线程休眠1000毫秒");
                Thread.sleep(1000);
            }
        }

        long fileLength = randomAccessFile.length();
        randomAccessFile.seek(fileLength);
        randomAccessFile.write(sb.toString().getBytes("utf-8"));
        fileLock.release();
        fileChannel.close();
        randomAccessFile.close();
        return R.ok();
    }


    @PostMapping("/api2")
    public void tradeNumNotify(HttpServletRequest servletRequest) {
        String tradeInfos = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
            String nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                tradeInfos += nextLine;
                nextLine = bufferedReader.readLine();
            }
            System.out.println(tradeInfos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

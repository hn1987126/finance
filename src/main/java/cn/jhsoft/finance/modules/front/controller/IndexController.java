package cn.jhsoft.finance.modules.front.controller;

import cn.jhsoft.finance.common.utils.R;
import cn.jhsoft.finance.common.utils.RedisUtils;
import cn.jhsoft.finance.modules.api.annotation.AuthIgnore;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

    /**
     * 网关接口
     */
    @AuthIgnore
    @PostMapping("/api")
    public R GateWayApi(@RequestBody byte[]bytes) throws Exception {
        // post过来的数据流
        String postStr = new String(bytes, 0, bytes.length);
        // 整串json
        JsonObject allData = new JsonParser().parse(postStr).getAsJsonObject();
        JsonArray jr = allData.get("data").getAsJsonArray();
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

        String macFactory;
        for (JsonObject data : list){
            // 排除电脑 Intel Corporate
            macFactory = macFactorys.get(data.get("macKey").getAsString());
            if ("Intel Corporate".equals(macFactory)){
                continue;
            }
            data.addProperty("mac_factory", macFactory);
            data.addProperty("id", allData.get("id").getAsString());
            data.addProperty("mmac", allData.get("mmac").getAsString());
            data.addProperty("rate", allData.get("rate").getAsString());
            data.addProperty("time", allData.get("time").getAsString());
            data.addProperty("lat", allData.get("lat").getAsString());
            data.addProperty("lon", allData.get("lon").getAsString());
            data.remove("macKey");
            sb.append(data.toString()+"\n");
        }

        // 写文件
        FileWriter fw = new FileWriter("/export/wwwlogs/flume.access.log", true);
        fw.write(sb.toString());
        fw.close();
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

package cn.jhsoft.finance.modules.pm.controller;

import java.util.List;
import java.util.Map;
import java.util.Date;

import cn.jhsoft.finance.common.utils.PageUtils;
import cn.jhsoft.finance.common.utils.Query;
import cn.jhsoft.finance.modules.pm.entity.DeviceEntity;
import cn.jhsoft.finance.modules.pm.service.DeviceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jhsoft.finance.common.utils.Constant;
import cn.jhsoft.finance.common.utils.R;
import cn.jhsoft.finance.common.validator.ValidatorUtils;
import cn.jhsoft.finance.common.validator.group.AddGroup;
import cn.jhsoft.finance.common.validator.group.UpdateGroup;
import cn.jhsoft.finance.modules.sys.controller.AbstractController;



/**
 * 设备
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
@RestController
@RequestMapping("/pm/device")
public class DeviceController extends AbstractController {
	@Autowired
	private DeviceService deviceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pm:device:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DeviceEntity> deviceList = deviceService.queryList(query);
		int total = deviceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deviceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("pm:device:info")
	public R info(@PathVariable("id") Integer id){
		DeviceEntity device = deviceService.queryObject(id);
		
		return R.ok().put("device", device);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("pm:device:save")
	public R save(@RequestBody DeviceEntity device){
        ValidatorUtils.validateEntity(device, AddGroup.class);
        device.setCreateTime(new Date());
        device.setCreateAdminid(getUserId());
		deviceService.save(device);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("pm:device:update")
	public R update(@RequestBody DeviceEntity device){
        ValidatorUtils.validateEntity(device, UpdateGroup.class);
		deviceService.update(device);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("pm:device:delete")
	public R delete(@RequestBody Integer[] ids){
		deviceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

package cn.jhsoft.finance.modules.pm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import cn.jhsoft.finance.common.utils.Constant;
import cn.jhsoft.finance.common.utils.R;
import cn.jhsoft.finance.common.validator.ValidatorUtils;
import cn.jhsoft.finance.common.validator.group.AddGroup;
import cn.jhsoft.finance.common.validator.group.UpdateGroup;
import cn.jhsoft.finance.modules.pm.entity.BasicDataEntity;
import cn.jhsoft.finance.modules.pm.service.BasicDataService;
import cn.jhsoft.finance.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * 基础数据
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
@RestController
@RequestMapping("/pm/basicdata")
public class BasicDataController extends AbstractController {
	@Autowired
	private BasicDataService basicDataService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pm:basicdata:list")
	public List<BasicDataEntity> list(){
		Map<String, Object> map = new HashMap<>();
		List<BasicDataEntity> basicDataList = basicDataService.queryList(map);

		return basicDataList;
	}

	/**
	 * 选择基础数据(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("pm:basicdata:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		List<BasicDataEntity> basicDataList = basicDataService.queryList(map);

//		//添加一级数据
//		if(getUserId() == Constant.SUPER_ADMIN){
//			BasicDataEntity root = new BasicDataEntity();
//			root.setId(0L);
//			root.setName("一级数据");
//			root.setParentId(-1L);
//			basicDataList.add(root);
//		}

		return R.ok().put("basicDataList", basicDataList);
	}

	/**
	 * 上级数据Id(管理员则为0)
	 */
	@RequestMapping("/info")
	@RequiresPermissions("pm:basicdata:list")
	public R info(){
		long deptId = 0;
		return R.ok().put("deptId", deptId);
	}

	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("pm:basicdata:info")
	public R info(@PathVariable("id") Long id){
		BasicDataEntity basicData = basicDataService.queryObject(id);
		
		return R.ok().put("basicData", basicData);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("pm:basicdata:save")
	public R save(@RequestBody BasicDataEntity basicData){
        ValidatorUtils.validateEntity(basicData, AddGroup.class);
        basicData.setCreateTime(new Date());
        basicData.setCreateAdminid(getUserId());

        // 判断标识是否已存在
		if (basicDataService.queryBasicDataIdListByEname(basicData.getEname()).size() > 0){
			return R.error("标识名重复，请重新输入");
		}

		basicDataService.save(basicData);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("pm:basicdata:update")
	public R update(@RequestBody BasicDataEntity basicData){
        ValidatorUtils.validateEntity(basicData, UpdateGroup.class);
		if (basicData.getParentId() == basicData.getId()){
			return R.error("自己的上级数据不能是自己");
		}

		// 判断标识是否已存在
		if (!basicDataService.queryBasicDataIdListByEname(basicData.getEname()).contains(basicData.getId())){
			return R.error("标识名重复，请重新输入");
		}

		basicDataService.update(basicData);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("pm:basicdata:delete")
	public R delete(long id){
		//判断是否有子数据
		List<Long> bdList = basicDataService.queryBasicDataIdList(id);
		if(bdList.size() > 0){
			return R.error("请先删除子数据");
		}
		basicDataService.delete(id);
		
		return R.ok();
	}
	
}

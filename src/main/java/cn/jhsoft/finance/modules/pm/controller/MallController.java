package cn.jhsoft.finance.modules.pm.controller;

import java.util.List;
import java.util.Map;
import java.util.Date;

import cn.jhsoft.finance.common.utils.PageUtils;
import cn.jhsoft.finance.common.utils.Query;
import cn.jhsoft.finance.common.utils.R;
import cn.jhsoft.finance.common.validator.ValidatorUtils;
import cn.jhsoft.finance.common.validator.group.AddGroup;
import cn.jhsoft.finance.common.validator.group.UpdateGroup;
import cn.jhsoft.finance.modules.pm.entity.MallEntity;
import cn.jhsoft.finance.modules.pm.service.MallService;
import cn.jhsoft.finance.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * 商场
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
@RestController
@RequestMapping("/pm/mall")
public class MallController extends AbstractController {
	@Autowired
	private MallService mallService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pm:mall:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<MallEntity> mallList = mallService.queryList(query);
		int total = mallService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(mallList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("pm:mall:info")
	public R info(@PathVariable("id") Integer id){
		MallEntity mall = mallService.queryObject(id);
		
		return R.ok().put("mall", mall);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("pm:mall:save")
	public R save(@RequestBody MallEntity mall){
        ValidatorUtils.validateEntity(mall, AddGroup.class);
        mall.setCreateTime(new Date());
        mall.setCreateAdminid(getUserId());
		mallService.save(mall);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("pm:mall:update")
	public R update(@RequestBody MallEntity mall){
        ValidatorUtils.validateEntity(mall, UpdateGroup.class);
		mallService.update(mall);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("pm:mall:delete")
	public R delete(@RequestBody Integer[] ids){
		mallService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

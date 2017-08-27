package cn.jhsoft.finance.modules.pm.controller;

import java.io.*;
import java.util.*;

import cn.jhsoft.finance.common.utils.POIUtils;
import cn.jhsoft.finance.modules.sys.entity.SysUserEntity;
import cn.jhsoft.finance.modules.sys.service.ShiroService;
import cn.jhsoft.finance.modules.sys.service.SysUserService;
import org.apache.commons.fileupload.util.Streams;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jhsoft.finance.modules.pm.entity.WagesEntity;
import cn.jhsoft.finance.modules.pm.service.WagesService;
import cn.jhsoft.finance.common.utils.PageUtils;
import cn.jhsoft.finance.common.utils.Query;
import cn.jhsoft.finance.common.utils.R;
import cn.jhsoft.finance.common.validator.ValidatorUtils;
import cn.jhsoft.finance.common.validator.group.AddGroup;
import cn.jhsoft.finance.common.validator.group.UpdateGroup;
import cn.jhsoft.finance.modules.sys.controller.AbstractController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 工资
 * 
 * @author chenyi
 * @email hn1987@126.com
 * @date 2017-08-25 22:19:42
 */
@RestController
@RequestMapping("/pm/wages")
public class WagesController extends AbstractController {
	@Autowired
	private WagesService wagesService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ShiroService shiroService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pm:wages:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

        // 获取当前用户的权限列表...
        Set<String> userPermissions = shiroService.getUserPermissions(getUserId());
        // 有导入功能，则能看所有人，否则只能看自己
        if(!userPermissions.contains("pm:wages:save")){
            query.put("userId", getUserId());
        }

        List<WagesEntity> wagesList = wagesService.queryList(query);
		int total = wagesService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(wagesList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("pm:wages:info")
	public R info(@PathVariable("id") Long id){
		WagesEntity wages = wagesService.queryObject(id);
        R r = R.ok();
        if (wages != null){
            SysUserEntity sysUserEntity = sysUserService.queryObject(wages.getUserId());
            r.put("userinfo", sysUserEntity);
        }
		
		return r.put("wages", wages);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("pm:wages:save")
	public R save(@RequestParam("file") MultipartFile file){

		Map<String, Object> param = new HashMap<String, Object>();
		List<WagesEntity> allUsers = new ArrayList<WagesEntity>();

		try {
			List<String[]> lists = POIUtils.readExcel(file, 5);
			for(int i = 0;i<lists.size();i++){

				// 放入实体对象中
				WagesEntity wages = new WagesEntity();
				String[] datas = lists.get(i);

				if (datas.length != 56){
					return R.error("格式不对。");
				}

				// 存用户
                SysUserEntity sysUser = sysUserService.queryByUserName(datas[2]);
				if (sysUser == null){
                    sysUser = new SysUserEntity();
                    sysUser.setCreateUserId(getUserId());
                    sysUser.setUsername(datas[2]);
                    sysUser.setRelname(datas[1]);
                    sysUser.setIdNumber(datas[3]);
                    sysUser.setCardNumber(datas[4]);
                    sysUser.setPassword("123456");
                    List<Long> roleIdList = new ArrayList<>();
                    roleIdList.add(2L);
                    sysUser.setRoleIdList(roleIdList);
                    sysUser.setStatus(1);
                    sysUserService.save(sysUser);
                }

                if (sysUser.getUserId() <= 0){
				    return R.error("上传失败");
                }

				// 存财务数据
				int num = 6;
				wages.setUserId(sysUser.getUserId());
				wages.setPayStatus(Integer.valueOf(datas[num-1]));

				wages.setJobWage(Double.valueOf(datas[num++]));
				wages.setBasicWage(Double.valueOf(datas[num++]));
				wages.setLevelWage(Double.valueOf(datas[num++]));
				wages.setBasicsWage(Double.valueOf(datas[num++]));
				wages.setWorkingYearWage(Double.valueOf(datas[num++]));
				wages.setTechLevelWage(Double.valueOf(datas[num++]));
				wages.setTraineeWage(Double.valueOf(datas[num++]));
				wages.setLiveWage(Double.valueOf(datas[num++]));
				wages.setZoneAllowance(Double.valueOf(datas[num++]));
				wages.setRetainAllowance(Double.valueOf(datas[num++]));

				wages.setBonus(Double.valueOf(datas[num++]));
				wages.setHouseSubsidy(Double.valueOf(datas[num++]));
				wages.setLifeSubsidy(Double.valueOf(datas[num++]));
				wages.setSpecialAllowance(Double.valueOf(datas[num++]));
				wages.setTmpAllowance(Double.valueOf(datas[num++]));
				wages.setChildCost(Double.valueOf(datas[num++]));
				wages.setRetireCostWhole(Double.valueOf(datas[num++]));
				wages.setRetireCostZone(Double.valueOf(datas[num++]));
				wages.setRetireCost(Double.valueOf(datas[num++]));
				wages.setCheckYearBonus(Double.valueOf(datas[num++]));

				wages.setOtherBonus(Double.valueOf(datas[num++]));
				wages.setPostBonus(Double.valueOf(datas[num++]));
				wages.setMeritPay(Double.valueOf(datas[num++]));
				wages.setWagePay(Double.valueOf(datas[num++]));
				wages.setLawWagePay(Double.valueOf(datas[num++]));
				wages.setDifference(Double.valueOf(datas[num++]));
				wages.setOtherSubsidy(Double.valueOf(datas[num++]));
				wages.setPensionDiff(Double.valueOf(datas[num++]));
				wages.setBasicSubsidy(Double.valueOf(datas[num++]));
				wages.setTmpSubsidy(Double.valueOf(datas[num++]));

				wages.setShouldWage(Double.valueOf(datas[num++]));
				wages.setPensionBase(Double.valueOf(datas[num++]));
				wages.setTotalAnnuity(Double.valueOf(datas[num++]));
				wages.setCompanyAnnuity(Double.valueOf(datas[num++]));
				wages.setPersonalAnnuity(Double.valueOf(datas[num++]));
				wages.setPersonalSocial(Double.valueOf(datas[num++]));
				wages.setPersonalMedical(Double.valueOf(datas[num++]));
				wages.setPersonalPension(Double.valueOf(datas[num++]));
				wages.setPersonalPensionCase(Double.valueOf(datas[num++]));
				wages.setIncomeTax(Double.valueOf(datas[num++]));

				wages.setAntiTax(Double.valueOf(datas[num++]));
				wages.setOtherDeduction(Double.valueOf(datas[num++]));
				wages.setPersonalFund(Double.valueOf(datas[num++]));
				wages.setReplacePay(Double.valueOf(datas[num++]));
				wages.setActualWages(Double.valueOf(datas[num++]));
				wages.setHousingSubsidy(Double.valueOf(datas[num++]));
				wages.setCompanyFund(Double.valueOf(datas[num++]));
				wages.setCompanySocial(Double.valueOf(datas[num++]));
				wages.setCompanyMedical(Double.valueOf(datas[num++]));
				wages.setCompanyPension(Double.valueOf(datas[num++]));
                wages.setCreateTime(new Date());
                wages.setCreateAdminid(getUserId());

				wagesService.save(wages);
			}
		} catch (IOException e) {
            return R.error(e.getMessage());
		}
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("pm:wages:update")
	public R update(@RequestBody WagesEntity wages){
        ValidatorUtils.validateEntity(wages, UpdateGroup.class);
		wagesService.update(wages);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("pm:wages:delete")
	public R delete(@RequestBody Long[] ids){
		wagesService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

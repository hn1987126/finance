package cn.jhsoft.finance.modules.pm.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 工资
 * 
 * @author chenyi
 * @email hn1987@126.com
 * @date 2017-08-25 22:19:42
 */
public class WagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 
     */
	private Long id;

    /**
     * 员工
     */
	private Long userId;

	/**
	 * 员工姓名
	 */
	private String userRelname;

    /**
     * 职务工资
     */
	private Double jobWage;

    /**
     * 基本工资
     */
	private Double basicWage;

    /**
     * 级别工资
     */
	private Double levelWage;

    /**
     * 基础工资
     */
	private Double basicsWage;

    /**
     * 工龄工资
     */
	private Double workingYearWage;

    /**
     * 技术等级工资
     */
	private Double techLevelWage;

    /**
     * 见习工资
     */
	private Double traineeWage;

    /**
     * 活工资
     */
	private Double liveWage;

    /**
     * 特区津贴
     */
	private Double zoneAllowance;

    /**
     * 保留津贴
     */
	private Double retainAllowance;

    /**
     * 工作性补贴(奖金)
     */
	private Double bonus;

    /**
     * 改革性补贴(房补)
     */
	private Double houseSubsidy;

    /**
     * 生活性补贴(物补)
     */
	private Double lifeSubsidy;

    /**
     * 特岗津贴
     */
	private Double specialAllowance;

    /**
     * 临岗津贴
     */
	private Double tmpAllowance;

    /**
     * 独生子女费
     */
	private Double childCost;

    /**
     * 离退休增资全国
     */
	private Double retireCostWhole;

    /**
     * 离退休增资特区
     */
	private Double retireCostZone;

    /**
     * 离退休补贴经费
     */
	private Double retireCost;

    /**
     * 年度考核奖
     */
	private Double checkYearBonus;

    /**
     * 其他
     */
	private Double otherBonus;

    /**
     * 岗位津贴
     */
	private Double postBonus;

    /**
     * 绩效工资
     */
	private Double meritPay;

    /**
     * 薪级工资
     */
	private Double wagePay;

    /**
     * 执法薪级工资
     */
	private Double lawWagePay;

    /**
     * 套转差额
     */
	private Double difference;

    /**
     * 其他增补
     */
	private Double otherSubsidy;

    /**
     * 养老补差
     */
	private Double pensionDiff;

    /**
     * 基层补贴
     */
	private Double basicSubsidy;

    /**
     * 临时补贴
     */
	private Double tmpSubsidy;

    /**
     * 应发工资
     */
	private Double shouldWage;

    /**
     * 养老基数
     */
	private Double pensionBase;

    /**
     * 年金合计
     */
	private Double totalAnnuity;

    /**
     * 职业年金(单位)
     */
	private Double companyAnnuity;

    /**
     * 职业年金(个人)
     */
	private Double personalAnnuity;

    /**
     * 个人社保合计
     */
	private Double personalSocial;

    /**
     * 个人医疗
     */
	private Double personalMedical;

    /**
     * 个人养老
     */
	private Double personalPension;

    /**
     * 个人统筹医疗
     */
	private Double personalPensionCase;

    /**
     * 所得税
     */
	private Double incomeTax;

    /**
     * 反税
     */
	private Double antiTax;

    /**
     * 其他应扣
     */
	private Double otherDeduction;

    /**
     * 个人公积金
     */
	private Double personalFund;

    /**
     * 代缴
     */
	private Double replacePay;

    /**
     * 实发工资
     */
	private Double actualWages;

    /**
     * 房改补贴
     */
	private Double housingSubsidy;

    /**
     * 单位公积金
     */
	private Double companyFund;

    /**
     * 单位社保合计
     */
	private Double companySocial;

    /**
     * 单位医疗
     */
	private Double companyMedical;

    /**
     * 单位养老
     */
	private Double companyPension;

    /**
     * 支付状态,0未支付,1已支付
     */
	private Integer payStatus;

    /**
     * 添加时间
     */
	private Date createTime;

    /**
     * 添加人
     */
	private Long createAdminid;


	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：员工
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：员工
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：职务工资
	 */
	public void setJobWage(Double jobWage) {
		this.jobWage = jobWage;
	}

	/**
	 * 获取：职务工资
	 */
	public Double getJobWage() {
		return jobWage;
	}

	/**
	 * 设置：基本工资
	 */
	public void setBasicWage(Double basicWage) {
		this.basicWage = basicWage;
	}

	/**
	 * 获取：基本工资
	 */
	public Double getBasicWage() {
		return basicWage;
	}

	/**
	 * 设置：级别工资
	 */
	public void setLevelWage(Double levelWage) {
		this.levelWage = levelWage;
	}

	/**
	 * 获取：级别工资
	 */
	public Double getLevelWage() {
		return levelWage;
	}

	/**
	 * 设置：基础工资
	 */
	public void setBasicsWage(Double basicsWage) {
		this.basicsWage = basicsWage;
	}

	/**
	 * 获取：基础工资
	 */
	public Double getBasicsWage() {
		return basicsWage;
	}

	/**
	 * 设置：工龄工资
	 */
	public void setWorkingYearWage(Double workingYearWage) {
		this.workingYearWage = workingYearWage;
	}

	/**
	 * 获取：工龄工资
	 */
	public Double getWorkingYearWage() {
		return workingYearWage;
	}

	/**
	 * 设置：技术等级工资
	 */
	public void setTechLevelWage(Double techLevelWage) {
		this.techLevelWage = techLevelWage;
	}

	/**
	 * 获取：技术等级工资
	 */
	public Double getTechLevelWage() {
		return techLevelWage;
	}

	/**
	 * 设置：见习工资
	 */
	public void setTraineeWage(Double traineeWage) {
		this.traineeWage = traineeWage;
	}

	/**
	 * 获取：见习工资
	 */
	public Double getTraineeWage() {
		return traineeWage;
	}

	/**
	 * 设置：活工资
	 */
	public void setLiveWage(Double liveWage) {
		this.liveWage = liveWage;
	}

	/**
	 * 获取：活工资
	 */
	public Double getLiveWage() {
		return liveWage;
	}

	/**
	 * 设置：特区津贴
	 */
	public void setZoneAllowance(Double zoneAllowance) {
		this.zoneAllowance = zoneAllowance;
	}

	/**
	 * 获取：特区津贴
	 */
	public Double getZoneAllowance() {
		return zoneAllowance;
	}

	/**
	 * 设置：保留津贴
	 */
	public void setRetainAllowance(Double retainAllowance) {
		this.retainAllowance = retainAllowance;
	}

	/**
	 * 获取：保留津贴
	 */
	public Double getRetainAllowance() {
		return retainAllowance;
	}

	/**
	 * 设置：工作性补贴(奖金)
	 */
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	/**
	 * 获取：工作性补贴(奖金)
	 */
	public Double getBonus() {
		return bonus;
	}

	/**
	 * 设置：改革性补贴(房补)
	 */
	public void setHouseSubsidy(Double houseSubsidy) {
		this.houseSubsidy = houseSubsidy;
	}

	/**
	 * 获取：改革性补贴(房补)
	 */
	public Double getHouseSubsidy() {
		return houseSubsidy;
	}

	/**
	 * 设置：生活性补贴(物补)
	 */
	public void setLifeSubsidy(Double lifeSubsidy) {
		this.lifeSubsidy = lifeSubsidy;
	}

	/**
	 * 获取：生活性补贴(物补)
	 */
	public Double getLifeSubsidy() {
		return lifeSubsidy;
	}

	/**
	 * 设置：特岗津贴
	 */
	public void setSpecialAllowance(Double specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	/**
	 * 获取：特岗津贴
	 */
	public Double getSpecialAllowance() {
		return specialAllowance;
	}

	/**
	 * 设置：临岗津贴
	 */
	public void setTmpAllowance(Double tmpAllowance) {
		this.tmpAllowance = tmpAllowance;
	}

	/**
	 * 获取：临岗津贴
	 */
	public Double getTmpAllowance() {
		return tmpAllowance;
	}

	/**
	 * 设置：独生子女费
	 */
	public void setChildCost(Double childCost) {
		this.childCost = childCost;
	}

	/**
	 * 获取：独生子女费
	 */
	public Double getChildCost() {
		return childCost;
	}

	/**
	 * 设置：离退休增资全国
	 */
	public void setRetireCostWhole(Double retireCostWhole) {
		this.retireCostWhole = retireCostWhole;
	}

	/**
	 * 获取：离退休增资全国
	 */
	public Double getRetireCostWhole() {
		return retireCostWhole;
	}

	/**
	 * 设置：离退休增资特区
	 */
	public void setRetireCostZone(Double retireCostZone) {
		this.retireCostZone = retireCostZone;
	}

	/**
	 * 获取：离退休增资特区
	 */
	public Double getRetireCostZone() {
		return retireCostZone;
	}

	/**
	 * 设置：离退休补贴经费
	 */
	public void setRetireCost(Double retireCost) {
		this.retireCost = retireCost;
	}

	/**
	 * 获取：离退休补贴经费
	 */
	public Double getRetireCost() {
		return retireCost;
	}

	/**
	 * 设置：年度考核奖
	 */
	public void setCheckYearBonus(Double checkYearBonus) {
		this.checkYearBonus = checkYearBonus;
	}

	/**
	 * 获取：年度考核奖
	 */
	public Double getCheckYearBonus() {
		return checkYearBonus;
	}

	/**
	 * 设置：其他
	 */
	public void setOtherBonus(Double otherBonus) {
		this.otherBonus = otherBonus;
	}

	/**
	 * 获取：其他
	 */
	public Double getOtherBonus() {
		return otherBonus;
	}

	/**
	 * 设置：岗位津贴
	 */
	public void setPostBonus(Double postBonus) {
		this.postBonus = postBonus;
	}

	/**
	 * 获取：岗位津贴
	 */
	public Double getPostBonus() {
		return postBonus;
	}

	/**
	 * 设置：绩效工资
	 */
	public void setMeritPay(Double meritPay) {
		this.meritPay = meritPay;
	}

	/**
	 * 获取：绩效工资
	 */
	public Double getMeritPay() {
		return meritPay;
	}

	/**
	 * 设置：薪级工资
	 */
	public void setWagePay(Double wagePay) {
		this.wagePay = wagePay;
	}

	/**
	 * 获取：薪级工资
	 */
	public Double getWagePay() {
		return wagePay;
	}

	/**
	 * 设置：执法薪级工资
	 */
	public void setLawWagePay(Double lawWagePay) {
		this.lawWagePay = lawWagePay;
	}

	/**
	 * 获取：执法薪级工资
	 */
	public Double getLawWagePay() {
		return lawWagePay;
	}

	/**
	 * 设置：套转差额
	 */
	public void setDifference(Double difference) {
		this.difference = difference;
	}

	/**
	 * 获取：套转差额
	 */
	public Double getDifference() {
		return difference;
	}

	/**
	 * 设置：其他增补
	 */
	public void setOtherSubsidy(Double otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	/**
	 * 获取：其他增补
	 */
	public Double getOtherSubsidy() {
		return otherSubsidy;
	}

	/**
	 * 设置：养老补差
	 */
	public void setPensionDiff(Double pensionDiff) {
		this.pensionDiff = pensionDiff;
	}

	/**
	 * 获取：养老补差
	 */
	public Double getPensionDiff() {
		return pensionDiff;
	}

	/**
	 * 设置：基层补贴
	 */
	public void setBasicSubsidy(Double basicSubsidy) {
		this.basicSubsidy = basicSubsidy;
	}

	/**
	 * 获取：基层补贴
	 */
	public Double getBasicSubsidy() {
		return basicSubsidy;
	}

	/**
	 * 设置：临时补贴
	 */
	public void setTmpSubsidy(Double tmpSubsidy) {
		this.tmpSubsidy = tmpSubsidy;
	}

	/**
	 * 获取：临时补贴
	 */
	public Double getTmpSubsidy() {
		return tmpSubsidy;
	}

	/**
	 * 设置：应发工资
	 */
	public void setShouldWage(Double shouldWage) {
		this.shouldWage = shouldWage;
	}

	/**
	 * 获取：应发工资
	 */
	public Double getShouldWage() {
		return shouldWage;
	}

	/**
	 * 设置：养老基数
	 */
	public void setPensionBase(Double pensionBase) {
		this.pensionBase = pensionBase;
	}

	/**
	 * 获取：养老基数
	 */
	public Double getPensionBase() {
		return pensionBase;
	}

	/**
	 * 设置：年金合计
	 */
	public void setTotalAnnuity(Double totalAnnuity) {
		this.totalAnnuity = totalAnnuity;
	}

	/**
	 * 获取：年金合计
	 */
	public Double getTotalAnnuity() {
		return totalAnnuity;
	}

	/**
	 * 设置：职业年金(单位)
	 */
	public void setCompanyAnnuity(Double companyAnnuity) {
		this.companyAnnuity = companyAnnuity;
	}

	/**
	 * 获取：职业年金(单位)
	 */
	public Double getCompanyAnnuity() {
		return companyAnnuity;
	}

	/**
	 * 设置：职业年金(个人)
	 */
	public void setPersonalAnnuity(Double personalAnnuity) {
		this.personalAnnuity = personalAnnuity;
	}

	/**
	 * 获取：职业年金(个人)
	 */
	public Double getPersonalAnnuity() {
		return personalAnnuity;
	}

	/**
	 * 设置：个人社保合计
	 */
	public void setPersonalSocial(Double personalSocial) {
		this.personalSocial = personalSocial;
	}

	/**
	 * 获取：个人社保合计
	 */
	public Double getPersonalSocial() {
		return personalSocial;
	}

	/**
	 * 设置：个人医疗
	 */
	public void setPersonalMedical(Double personalMedical) {
		this.personalMedical = personalMedical;
	}

	/**
	 * 获取：个人医疗
	 */
	public Double getPersonalMedical() {
		return personalMedical;
	}

	/**
	 * 设置：个人养老
	 */
	public void setPersonalPension(Double personalPension) {
		this.personalPension = personalPension;
	}

	/**
	 * 获取：个人养老
	 */
	public Double getPersonalPension() {
		return personalPension;
	}

	/**
	 * 设置：个人统筹医疗
	 */
	public void setPersonalPensionCase(Double personalPensionCase) {
		this.personalPensionCase = personalPensionCase;
	}

	/**
	 * 获取：个人统筹医疗
	 */
	public Double getPersonalPensionCase() {
		return personalPensionCase;
	}

	/**
	 * 设置：所得税
	 */
	public void setIncomeTax(Double incomeTax) {
		this.incomeTax = incomeTax;
	}

	/**
	 * 获取：所得税
	 */
	public Double getIncomeTax() {
		return incomeTax;
	}

	/**
	 * 设置：反税
	 */
	public void setAntiTax(Double antiTax) {
		this.antiTax = antiTax;
	}

	/**
	 * 获取：反税
	 */
	public Double getAntiTax() {
		return antiTax;
	}

	/**
	 * 设置：其他应扣
	 */
	public void setOtherDeduction(Double otherDeduction) {
		this.otherDeduction = otherDeduction;
	}

	/**
	 * 获取：其他应扣
	 */
	public Double getOtherDeduction() {
		return otherDeduction;
	}

	/**
	 * 设置：个人公积金
	 */
	public void setPersonalFund(Double personalFund) {
		this.personalFund = personalFund;
	}

	/**
	 * 获取：个人公积金
	 */
	public Double getPersonalFund() {
		return personalFund;
	}

	/**
	 * 设置：代缴
	 */
	public void setReplacePay(Double replacePay) {
		this.replacePay = replacePay;
	}

	/**
	 * 获取：代缴
	 */
	public Double getReplacePay() {
		return replacePay;
	}

	/**
	 * 设置：实发工资
	 */
	public void setActualWages(Double actualWages) {
		this.actualWages = actualWages;
	}

	/**
	 * 获取：实发工资
	 */
	public Double getActualWages() {
		return actualWages;
	}

	/**
	 * 设置：房改补贴
	 */
	public void setHousingSubsidy(Double housingSubsidy) {
		this.housingSubsidy = housingSubsidy;
	}

	/**
	 * 获取：房改补贴
	 */
	public Double getHousingSubsidy() {
		return housingSubsidy;
	}

	/**
	 * 设置：单位公积金
	 */
	public void setCompanyFund(Double companyFund) {
		this.companyFund = companyFund;
	}

	/**
	 * 获取：单位公积金
	 */
	public Double getCompanyFund() {
		return companyFund;
	}

	/**
	 * 设置：单位社保合计
	 */
	public void setCompanySocial(Double companySocial) {
		this.companySocial = companySocial;
	}

	/**
	 * 获取：单位社保合计
	 */
	public Double getCompanySocial() {
		return companySocial;
	}

	/**
	 * 设置：单位医疗
	 */
	public void setCompanyMedical(Double companyMedical) {
		this.companyMedical = companyMedical;
	}

	/**
	 * 获取：单位医疗
	 */
	public Double getCompanyMedical() {
		return companyMedical;
	}

	/**
	 * 设置：单位养老
	 */
	public void setCompanyPension(Double companyPension) {
		this.companyPension = companyPension;
	}

	/**
	 * 获取：单位养老
	 */
	public Double getCompanyPension() {
		return companyPension;
	}

	/**
	 * 设置：支付状态,0未支付,1已支付
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 获取：支付状态,0未支付,1已支付
	 */
	public Integer getPayStatus() {
		return payStatus;
	}

	/**
	 * 设置：添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：添加人
	 */
	public void setCreateAdminid(Long createAdminid) {
		this.createAdminid = createAdminid;
	}

	/**
	 * 获取：添加人
	 */
	public Long getCreateAdminid() {
		return createAdminid;
	}

	public String getUserRelname() {
		return userRelname;
	}

	public void setUserRelname(String userRelname) {
		this.userRelname = userRelname;
	}
}

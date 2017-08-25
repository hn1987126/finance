package cn.jhsoft.finance.modules.pm.entity;

import cn.jhsoft.finance.common.validator.group.AddGroup;
import cn.jhsoft.finance.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;



/**
 * 基础数据
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
public class BasicDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 
     */
	private Long id;

    /**
     * 父ID，顶级为0
     */
	private Long parentId;

	//上级部门名称
	private String parentName;

    /**
     * 基础数据名称
     */
	@NotBlank(message="数据名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;

	/**
	 * 基础数据标识
	 */
	private String ename;

    /**
     * 排序
     */
	private Integer orderNum;

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
	 * 设置：父ID，顶级为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取：父ID，顶级为0
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 设置：基础数据名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：基础数据名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
}

package cn.jhsoft.finance.modules.pm.entity;

import cn.jhsoft.finance.common.validator.group.AddGroup;
import cn.jhsoft.finance.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;



/**
 * 商场
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
public class MallEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 
     */
	private Integer id;

    /**
     * 商场名称
     */
	@NotBlank(message="商场名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;

    /**
     * 联系人
     */
	private String relname;

    /**
     * 联系电话
     */
	private String tel;

    /**
     * 手机号
     */
	@Pattern(regexp="^$|^((1[3-9][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message="手机号格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String mobile;

    /**
     * 电子邮箱
     */
	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;

    /**
     * 地址
     */
	@NotBlank(message="地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String address;

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
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：商场名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：商场名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：联系人
	 */
	public void setRelname(String relname) {
		this.relname = relname;
	}

	/**
	 * 获取：联系人
	 */
	public String getRelname() {
		return relname;
	}

	/**
	 * 设置：联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 获取：联系电话
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置：电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取：电子邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
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

}

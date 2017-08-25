package cn.jhsoft.finance.modules.pm.entity;

import cn.jhsoft.finance.common.validator.group.AddGroup;
import cn.jhsoft.finance.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;



/**
 * 设备
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
public class DeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 
     */
	private Integer id;

    /**
     * 设备号
     */
	@NotBlank(message="设备号不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String deviceNum;

    /**
     * 设备名称
     */
	@NotBlank(message="设备名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;

    /**
     * 型号
     */
	@NotBlank(message="型号不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String deviceModel;

    /**
     * 设备mac
     */
	@NotBlank(message="mac不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mac;

    /**
     * 网关url
     */
	private String gatewayUrl;

    /**
     * 所在楼层
     */
	private String floor;

    /**
     * 所属分组
     */
	private Integer groupId;

	/**
	 * 分组名称
	 */
	private String groupName;

    /**
     * 所属商场
     */
	private Integer mallId;

	/**
	 * 商场名称
	 */
	private String mallName;

    /**
     * 标签
     */
	private String tags;

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
	 * 设置：设备号
	 */
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	/**
	 * 获取：设备号
	 */
	public String getDeviceNum() {
		return deviceNum;
	}

	/**
	 * 设置：设备名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：设备名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：型号
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	/**
	 * 获取：型号
	 */
	public String getDeviceModel() {
		return deviceModel;
	}

	/**
	 * 设置：设备mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * 获取：设备mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * 设置：网关url
	 */
	public void setGatewayUrl(String gatewayUrl) {
		this.gatewayUrl = gatewayUrl;
	}

	/**
	 * 获取：网关url
	 */
	public String getGatewayUrl() {
		return gatewayUrl;
	}

	/**
	 * 设置：所在楼层
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}

	/**
	 * 获取：所在楼层
	 */
	public String getFloor() {
		return floor;
	}

	/**
	 * 设置：所属分组
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取：所属分组
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * 设置：所属商场
	 */
	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	/**
	 * 获取：所属商场
	 */
	public Integer getMallId() {
		return mallId;
	}

	/**
	 * 设置：标签
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * 获取：标签
	 */
	public String getTags() {
		return tags;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}
}

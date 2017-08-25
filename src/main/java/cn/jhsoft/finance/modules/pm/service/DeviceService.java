package cn.jhsoft.finance.modules.pm.service;


import cn.jhsoft.finance.modules.pm.entity.DeviceEntity;

import java.util.List;
import java.util.Map;

/**
 * 设备
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
public interface DeviceService {
	
	DeviceEntity queryObject(Integer id);
	
	List<DeviceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeviceEntity device);
	
	void update(DeviceEntity device);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}

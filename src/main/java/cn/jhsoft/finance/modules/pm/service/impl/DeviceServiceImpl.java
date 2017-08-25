package cn.jhsoft.finance.modules.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.jhsoft.finance.modules.pm.dao.DeviceDao;
import cn.jhsoft.finance.modules.pm.entity.DeviceEntity;
import cn.jhsoft.finance.modules.pm.service.DeviceService;



@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public DeviceEntity queryObject(Integer id){
		return deviceDao.queryObject(id);
	}
	
	@Override
	public List<DeviceEntity> queryList(Map<String, Object> map){
		return deviceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deviceDao.queryTotal(map);
	}
	
	@Override
	public void save(DeviceEntity device){
		deviceDao.save(device);
	}
	
	@Override
	public void update(DeviceEntity device){
		deviceDao.update(device);
	}
	
	@Override
	public void delete(Integer id){
		deviceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		deviceDao.deleteBatch(ids);
	}
	
}

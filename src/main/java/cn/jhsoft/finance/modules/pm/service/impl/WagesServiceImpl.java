package cn.jhsoft.finance.modules.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.jhsoft.finance.modules.pm.dao.WagesDao;
import cn.jhsoft.finance.modules.pm.entity.WagesEntity;
import cn.jhsoft.finance.modules.pm.service.WagesService;



@Service("wagesService")
public class WagesServiceImpl implements WagesService {
	@Autowired
	private WagesDao wagesDao;
	
	@Override
	public WagesEntity queryObject(Long id){
		return wagesDao.queryObject(id);
	}
	
	@Override
	public List<WagesEntity> queryList(Map<String, Object> map){
		return wagesDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return wagesDao.queryTotal(map);
	}
	
	@Override
	public void save(WagesEntity wages){
		wagesDao.save(wages);
	}
	
	@Override
	public void update(WagesEntity wages){
		wagesDao.update(wages);
	}
	
	@Override
	public void delete(Long id){
		wagesDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		wagesDao.deleteBatch(ids);
	}
	
}

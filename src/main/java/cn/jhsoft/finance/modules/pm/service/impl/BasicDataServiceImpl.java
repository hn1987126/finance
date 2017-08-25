package cn.jhsoft.finance.modules.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.jhsoft.finance.modules.pm.dao.BasicDataDao;
import cn.jhsoft.finance.modules.pm.entity.BasicDataEntity;
import cn.jhsoft.finance.modules.pm.service.BasicDataService;



@Service("basicDataService")
public class BasicDataServiceImpl implements BasicDataService {
	@Autowired
	private BasicDataDao basicDataDao;
	
	@Override
	public BasicDataEntity queryObject(Long id){
		return basicDataDao.queryObject(id);
	}
	
	@Override
	public List<BasicDataEntity> queryList(Map<String, Object> map){
		return basicDataDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return basicDataDao.queryTotal(map);
	}
	
	@Override
	public void save(BasicDataEntity basicData){
		basicDataDao.save(basicData);
	}
	
	@Override
	public void update(BasicDataEntity basicData){
		basicDataDao.update(basicData);
	}
	
	@Override
	public void delete(Long id){
		basicDataDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		basicDataDao.deleteBatch(ids);
	}

	@Override
	public List<Long> queryBasicDataIdList(Long parentId) {
		return basicDataDao.queryBasicDataIdList(parentId);
	}

	@Override
	public List<Long> queryBasicDataIdListByEname(String ename) {
		return basicDataDao.queryBasicDataIdListByEname(ename);
	}

}

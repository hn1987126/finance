package cn.jhsoft.finance.modules.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.jhsoft.finance.modules.pm.dao.MallDao;
import cn.jhsoft.finance.modules.pm.entity.MallEntity;
import cn.jhsoft.finance.modules.pm.service.MallService;



@Service("mallService")
public class MallServiceImpl implements MallService {
	@Autowired
	private MallDao mallDao;
	
	@Override
	public MallEntity queryObject(Integer id){
		return mallDao.queryObject(id);
	}
	
	@Override
	public List<MallEntity> queryList(Map<String, Object> map){
		return mallDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mallDao.queryTotal(map);
	}
	
	@Override
	public void save(MallEntity mall){
		mallDao.save(mall);
	}
	
	@Override
	public void update(MallEntity mall){
		mallDao.update(mall);
	}
	
	@Override
	public void delete(Integer id){
		mallDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mallDao.deleteBatch(ids);
	}
	
}

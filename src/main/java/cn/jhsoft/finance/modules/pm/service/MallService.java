package cn.jhsoft.finance.modules.pm.service;


import cn.jhsoft.finance.modules.pm.entity.MallEntity;

import java.util.List;
import java.util.Map;

/**
 * 商场
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
public interface MallService {
	
	MallEntity queryObject(Integer id);
	
	List<MallEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MallEntity mall);
	
	void update(MallEntity mall);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}

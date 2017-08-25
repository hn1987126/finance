package cn.jhsoft.finance.modules.pm.service;


import cn.jhsoft.finance.modules.pm.entity.BasicDataEntity;

import java.util.List;
import java.util.Map;

/**
 * 基础数据
 * 
 * @author chenyi
 * @email chenyi9@jd.com
 * @date 2017-08-25 11:32:27
 */
public interface BasicDataService {
	
	BasicDataEntity queryObject(Long id);
	
	List<BasicDataEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasicDataEntity basicData);
	
	void update(BasicDataEntity basicData);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<Long> queryBasicDataIdList(Long parentId);

	List<Long> queryBasicDataIdListByEname(String ename);
}

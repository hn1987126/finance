package cn.jhsoft.finance.modules.pm.service;

import cn.jhsoft.finance.modules.pm.entity.WagesEntity;

import java.util.List;
import java.util.Map;

/**
 * 工资
 * 
 * @author chenyi
 * @email hn1987@126.com
 * @date 2017-08-25 22:19:42
 */
public interface WagesService {
	
	WagesEntity queryObject(Long id);
	
	List<WagesEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WagesEntity wages);
	
	void update(WagesEntity wages);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}

package cn.jhsoft.finance.modules.api.dao;

import cn.jhsoft.finance.modules.api.entity.TokenEntity;
import cn.jhsoft.finance.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Token
 * 
 * @author jhsoft
 * @email hn1987@126.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}

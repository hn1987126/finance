package cn.jhsoft.finance.modules.sys.service;

import cn.jhsoft.finance.modules.sys.entity.SysUserTokenEntity;
import cn.jhsoft.finance.common.utils.R;

/**
 * 用户Token
 * 
 * @author jhsoft
 * @email hn1987@126.com
 * @date 2017-03-23 15:22:07
 */
public interface SysUserTokenService {

	SysUserTokenEntity queryByUserId(Long userId);

	SysUserTokenEntity queryByToken(String token);
	
	void save(SysUserTokenEntity token);
	
	void update(SysUserTokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

}

package cn.jhsoft.finance.modules.sys.service;

import cn.jhsoft.finance.modules.sys.entity.SysUserTokenEntity;
import cn.jhsoft.finance.modules.sys.entity.SysUserEntity;

import java.util.Set;

/**
 * shiro相关接口
 * @author jhsoft
 * @email hn1987@126.com
 * @date 2017-06-06 8:49
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}

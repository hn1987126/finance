package cn.jhsoft.finance.modules.api.dao;

import cn.jhsoft.finance.modules.sys.dao.BaseDao;
import cn.jhsoft.finance.modules.api.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}

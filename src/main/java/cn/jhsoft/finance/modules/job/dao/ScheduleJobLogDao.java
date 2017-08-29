package cn.jhsoft.finance.modules.job.dao;

import cn.jhsoft.finance.modules.job.entity.ScheduleJobLogEntity;
import cn.jhsoft.finance.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 * 
 * @author jhsoft
 * @email hn1987@126.com
 * @date 2016年12月1日 下午10:30:02
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}

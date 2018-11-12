package com.example.demo.service;

import com.example.demo.domain.TaskDO;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 */
public interface JobService {
	
	TaskDO get(Long id);
	
	List<TaskDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TaskDO taskScheduleJob);
	
	int update(TaskDO taskScheduleJob);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**规范
	 * @throws SchedulerException
	 */
	void initSchedule() throws SchedulerException;

	/**规范
	 * @param jobId
	 * @param cmd
	 * @throws SchedulerException
	 */
	void changeStatus(Long jobId, String cmd) throws SchedulerException;

	/**规范
	 * @param jobId
	 * @throws SchedulerException
	 */
	void updateCron(Long jobId) throws SchedulerException;
}

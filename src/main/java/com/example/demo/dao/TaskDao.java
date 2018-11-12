package com.example.demo.dao;

import com.example.demo.domain.TaskDO;

import java.util.List;
import java.util.Map;

/**
 * 
 */
public interface TaskDao {

	TaskDO get(Long id);
	
	List<TaskDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(TaskDO task);
	
	int update(TaskDO task);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}

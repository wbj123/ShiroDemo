package com.example.demo.controller;

import com.example.demo.domain.TaskDO;
import com.example.demo.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 */
@Controller
@RequestMapping("/common/job")
public class JobController {
	@Autowired
	private JobService taskScheduleJobService;
	private static Logger logger = LoggerFactory.getLogger(JobController.class);


	@GetMapping()
	String taskScheduleJob() {
		return "common/job/job";
	}

	@ResponseBody
	@GetMapping("/list")
	public List list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
//		Query query = new Query(params);
//		query.put("jobGroup", "hospital_synchronization_"+ Constant.hosId);
		List<TaskDO> taskScheduleJobList = taskScheduleJobService.list(params);
//		int total = taskScheduleJobService.count(query);
//		PageUtils pageUtils = new PageUtils(taskScheduleJobList, total);
		return taskScheduleJobList;
	}

	@GetMapping("/add")
	String add() {
		return "common/job/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		TaskDO job = taskScheduleJobService.get(id);
		model.addAttribute("job", job);
		return "common/job/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@ResponseBody
	public Map info(@PathVariable("id") Long id) {
		TaskDO taskScheduleJob = taskScheduleJobService.get(id);
		Map o = new HashMap(16);
		o.put("taskScheduleJob", taskScheduleJob);
		return o;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public Map save(TaskDO taskScheduleJob) {
		Map map = new HashMap(16);
		if (taskScheduleJobService.save(taskScheduleJob) > 0) {
			map.put("code","0");
			return map;
		}
		map.put("code","500");
		return map;
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@PostMapping("/update")
	public Map update(TaskDO taskScheduleJob) {
		Map map = new HashMap(16);
		if (taskScheduleJobService.update(taskScheduleJob)>0){
			map.put("code","0");
			return map;
		}
		map.put("code","500");
		return map;
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public Map remove(Long id) {
		Map map = new HashMap(16);
		if (taskScheduleJobService.remove(id) > 0) {
			map.put("code","0");
			return map;
		}
		map.put("code","500");
		return map;
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	public Map remove(@RequestParam("ids[]") Long[] ids) {
		Map map = new HashMap(16);
		if (taskScheduleJobService.batchRemove(ids) > 0) {
			map.put("code","0");
			return map;
		}
		map.put("code","500");
		return map;
	}

	/**
	 * 改变任务状态  cmd=start 开始 cmd=stop 停止
	 * @param id
	 * @param cmd
	 * @return
	 */
	@GetMapping(value = "/changeJobStatus")
	@ResponseBody
	public Map changeJobStatus(Long id,String cmd ) {
		Map map = new HashMap(16);
		String label = "停止";
		if ("start".equals(cmd)) {
			label = "启动";
		} else {
			label = "停止";
		}
		try {
			taskScheduleJobService.changeStatus(id, cmd);
			map.put("msg","任务" + label + "成功");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","任务" + label + "失败");
		}
		return map;
	}


	public static String getUUID32(){
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}

}

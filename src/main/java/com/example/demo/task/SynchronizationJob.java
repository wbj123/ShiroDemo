package com.example.demo.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
@Component
public class SynchronizationJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(SynchronizationJob.class);


    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        logger.info("SynchronizationJob定时任务开始 ----> 发起进攻(　 ´-ω ･)▄︻┻┳══━一");
    }

}
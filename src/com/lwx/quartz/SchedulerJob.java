package com.lwx.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SchedulerJob {
	private List<JobDetail> jobList = new ArrayList<JobDetail>();
	private TreeSet<Trigger> triggerList = new TreeSet<Trigger>();
	private Object lockObj = new Object();
	private boolean shutDown = false;
	SchedulerThread thread = new SchedulerThread(triggerList,jobList,lockObj,shutDown);
	
	public void schedulerJob(JobDetail detail, Trigger trigger){
		synchronized (lockObj) {
			jobList.add(detail);
			trigger.setJobKey(detail.getJobName());
			System.out.println(trigger.getJobKey()+"下次执行时间："+trigger.getNextFireTime());
			triggerList.add(trigger);
		}
	}
	
	public void start(){
		thread.start();
	}
	
	public void halt(){
		thread.halt();
	}
	
	
	
	
}

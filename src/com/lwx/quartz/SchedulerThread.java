package com.lwx.quartz;

import java.util.List;
import java.util.TreeSet;

public class SchedulerThread extends Thread{
	private Object lockObj;
	private TreeSet<Trigger> triggerList;
	private List<JobDetail> jobList;
	private boolean shutDown = false;
	public SchedulerThread(TreeSet<Trigger> triggerList,List<JobDetail> jobList,Object lockObj,boolean shutDown) {
		this.triggerList = triggerList;
		this.jobList = jobList;
		this.lockObj = lockObj;
		this.shutDown = shutDown;
	}
	@Override
	public void run() {
		while(!shutDown){
			synchronized (lockObj) {
				try {
					Trigger trigger = triggerList.pollFirst();
					if(trigger==null){
						lockObj.wait(100);
						continue;
					}
					System.out.println(trigger.getJobKey()+"++"+(System.currentTimeMillis()));
					long curr = System.currentTimeMillis();
					System.out.println("当前时间："+curr);
					long nextTime = trigger.getNextFireTime();
					System.out.println(trigger.getJobKey()+"++下次执行时间："+trigger.getNextFireTime());
					while(nextTime>curr&&!shutDown){
						curr = System.currentTimeMillis();
						if(nextTime>curr+1){
							lockObj.wait(nextTime-curr);
						}
						if(!shutDown){
							JobDetail jobDetail = null;
							for(JobDetail d:jobList){
								if(d.getJobName().equals(trigger.getJobKey())){
									 jobDetail = d;
									 break;
								}
							}
							Job job = jobDetail.getClazz().newInstance();
							job.execute(jobDetail.getJobData());
							trigger.reset();
							System.out.println(trigger.getJobKey()+"变更后下下次执行时间："+trigger.getNextFireTime());
							long tempNextTime = trigger.getNextFireTime();
							if(tempNextTime!=-1){
								triggerList.add(trigger);
							}else{
								break;
							}
							curr = System.currentTimeMillis();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void halt(){
		synchronized (lockObj) {
			shutDown = true;
			lockObj.notifyAll();
		}
	}

}

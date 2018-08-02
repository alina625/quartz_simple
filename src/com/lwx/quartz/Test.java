package com.lwx.quartz;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		JobDetail detail1 = new JobDetail(Job.class, "job1");
		detail1.getJobData().put("type", "job1");
		JobDetail detail2 = new JobDetail(Job.class, "job2");
		detail2.getJobData().put("type", "job2");
		Trigger trigger1 = new Trigger();
		long interval1 = 10001;
		trigger1.setInterval(10001);
		trigger1.setNextFireTime(System.currentTimeMillis()+interval1);
		Trigger trigger2 = new Trigger();
		long interval2 = 70001;
		trigger2.setInterval(interval2);
		trigger2.setNextFireTime(System.currentTimeMillis()+interval2);
		SchedulerJob scheduler = new SchedulerJob();
		scheduler.schedulerJob(detail1, trigger1);
		scheduler.schedulerJob(detail2, trigger2);
		scheduler.start();
		Thread.sleep(200001);
		scheduler.halt();
	}
	
	
	
	
	
}

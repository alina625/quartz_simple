package com.lwx.quartz;

public class Trigger implements Comparable<Trigger> {
	private String jobKey;
	private long nextFireTime;
	private long interval;
	
	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}

	public long getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	
	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	@Override
	public int compareTo(Trigger o){
		return (int)(this.nextFireTime-o.getNextFireTime());
	}
	
	public void reset(){
		long t = System.currentTimeMillis()+this.interval;
		setNextFireTime(t);
	}
	
	
}

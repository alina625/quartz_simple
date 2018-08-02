package com.lwx.quartz;

import java.util.HashMap;
import java.util.Map;

public class JobDetail {
	private Class<? extends Job> clazz;
	private String jobName;
	private Map<String, String> jobData;
	public JobDetail() {
		jobData = new HashMap<String, String>();
	}
	public JobDetail(Class<? extends Job> clazz, String jobName) {
		super();
		this.clazz = clazz;
		this.jobName = jobName;
	}
	
	public Class<? extends Job> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends Job> clazz) {
		this.clazz = clazz;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Map<String, String> getJobData() {
		if(jobData==null){
			jobData = new HashMap<String, String>();
		}
		return jobData;
	}
	public void setJobData(Map<String, String> jobData) {
		this.jobData = jobData;
	}
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime*result+((jobName==null)?0:jobName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj){
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(obj instanceof JobDetail){
			JobDetail other = (JobDetail) obj;
			if(jobName==null){
				if(other.jobName!=null)
					return false;
			}else{
				if(other.jobName==null||!jobName.equals(other.jobName))
					return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	
}

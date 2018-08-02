package com.lwx.quartz;

import java.util.Map;

public class Job {
	public void execute(Map<String, String> jobData){
		System.out.println("开始执行");
		System.out.println(jobData.get("type")+"当前时间："+System.currentTimeMillis());
		System.out.println("结束执行");
	}
}

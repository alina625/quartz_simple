package com.lwx.quartz;

import java.util.TreeSet;
 
public class Test2 {
	public static void main(String[] args) {
		TreeSet<Trigger> t = new TreeSet<Trigger>();
		Trigger t1 = new Trigger();
		t1.setInterval(1);
		t1.setJobKey("job1");
		t1.setNextFireTime(1);
		Trigger t2 = new Trigger();
		t2.setInterval(2);
		t2.setJobKey("job2");
		t2.setNextFireTime(2);
		t.add(t1);
		t.add(t2);
		Trigger temp = t.pollFirst();
		System.out.println(temp.getJobKey()+":"+temp.getNextFireTime());
	}
}

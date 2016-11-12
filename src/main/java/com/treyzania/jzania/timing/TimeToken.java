package com.treyzania.jzania.timing;

import java.util.logging.Level;

public class TimeToken {
	
	private Timer timer;
	
	private String name;
	private long start;
	
	protected TimeToken(String name, Timer timer) {
		
		this.timer = timer;
		this.name = name;
		this.start = System.currentTimeMillis();
		
	}
	
	public TimeToken() {
		this.start = System.currentTimeMillis();
	}
	
	public long getCreation() {
		return this.start;
	}
	
	public long getDuration() {
		return System.currentTimeMillis() - this.getCreation();
	}
	
	public void report(Level level, String message) {

		String logMessage = String.format("Timer(%s) @ %s: %s", this.name, this.getDuration(), message);
		this.timer.getLogger().log(level, logMessage);
		
	}
	
	public void report(String message) {
		this.report(this.timer.getLevel(), message);
	}
	
	public void report() {
		this.report("[triggered]");
	}
	
}

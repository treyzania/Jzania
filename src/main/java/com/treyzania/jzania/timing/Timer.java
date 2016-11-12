package com.treyzania.jzania.timing;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer {
	
	private static final Map<String, Timer> maps = new HashMap<>();
	
	private Logger logger;
	private Level level = Level.INFO;
	
	private String initTokenMsg = "";
	
	private Timer() {
		
	}
	
	public TimeToken create(String name) {
		
		if (initTokenMsg != null && !initTokenMsg.isEmpty()) this.logger.log(level, String.format(this.initTokenMsg, name));
		return new TimeToken(name, this);
		
	}
	
	public void setNewTokenMessage(String msg) {
		this.initTokenMsg = msg;
	}
	
	public void setLogger(Logger log) {
		this.logger = log;
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	public void setLevel(Level lvl) {
		this.level = lvl;
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public static Timer getTimer(String name) {
		
		Timer t = maps.get(name);
		
		if (t == null) {
			
			t = new Timer();
			maps.put(name, t);
			
		}
		
		return t;
		
	}
	
	public static Timer getTimer() {
		return getTimer("default");
	}
	
}

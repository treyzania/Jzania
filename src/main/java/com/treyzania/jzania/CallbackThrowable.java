package com.treyzania.jzania;

public class CallbackThrowable {
	
	private String name;
	
	private Runnable runnable;
	private Throwable thrown;
	
	public CallbackThrowable(String name, Runnable r, Throwable t) {
		
		this.name = name;
		this.runnable = r;
		this.thrown = t;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public Runnable getRan() {
		return this.runnable;
	}
	
	public Throwable getThrown() {
		return this.thrown;
	}
	
}

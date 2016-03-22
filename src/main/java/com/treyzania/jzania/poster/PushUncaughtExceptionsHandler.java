package com.treyzania.jzania.poster;

public class PushUncaughtExceptionsHandler implements Thread.UncaughtExceptionHandler {

	private final PostPusher poster;
	
	public PushUncaughtExceptionsHandler(PostPusher pp) {
		this.poster = pp;
	}
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		
		System.out.println("Pushing stack trace for " + e.getMessage() + "...");
		this.poster.post(e);
		
	}
	
}

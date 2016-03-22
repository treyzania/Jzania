package com.treyzania.jzania.poster;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class PusherLogHandler extends Handler {
	
	// private static final String PREFIX = "Pushing log record. Type: ";
	
	private final PostPusher pusher;
	
	public PusherLogHandler(PostPusher pusher) {
		this.pusher = pusher;
	}
	
	@Override
	public void publish(LogRecord record) {
		
		String msg = record.getMessage().toLowerCase();
		Throwable t = record.getThrown();
		Level ll = record.getLevel();
		
		if (t != null || msg.contains("exception") || msg.contains("error") || ll == Level.SEVERE || ll == Level.WARNING) {
			
			if (t != null) {
				this.pusher.post(t);
			} else {
				this.pusher.post(record.getMessage());
			}
			
		}
		
	}
	
	@Override
	public void flush() {
	
	}
	
	@Override
	public void close() throws SecurityException {
	
	}
	
}

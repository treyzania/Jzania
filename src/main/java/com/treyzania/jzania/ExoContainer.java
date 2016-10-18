package com.treyzania.jzania;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Suppport class for helping make sure that exceptions caused by "external"
 * code is caught and reported properly. 
 * 
 * @author treyzania
 */
public class ExoContainer {
	
	private Logger logger;
	private List<Consumer<CallbackThrowable>> hooks;
	
	public ExoContainer(Logger logger) {
		
		this.logger = logger;
		this.hooks = new ArrayList<>();
		
	}
	
	public boolean invoke(String name, Runnable r) {
		
		try {
			r.run();
		} catch (Throwable t) {
			
			this.logger.log(Level.WARNING, "Problem when executing callback " + name  + "!", t);
			
			CallbackThrowable ct = new CallbackThrowable(name, r, t);
			
			this.hooks.forEach(h -> {
				
				try {
					h.accept(ct);
				} catch (Throwable t2) {
					// do nothing?
				}
				
			});
			
			return false;
			
		}
		
		return true;
		
	}
	
	public void addHook(Consumer<CallbackThrowable> callback) {
		this.hooks.add(callback);
	}
	
}

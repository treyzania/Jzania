package com.treyzania.jzania;

import java.util.logging.Logger;

public class Jzania {

	private static boolean inited = false;
	
	private static Logger jzaniaLogger;
	
	public static void init() {
		
		if (inited) return;
		
		jzaniaLogger = Logger.getLogger("Jzania");
		
		// Add more things as necessary.
		
		inited = true;
		jzaniaLogger.info("Jzania initalized.");
		
	}
	
	public static Logger getLogger() {
		return jzaniaLogger;
	}
	
	public static void setLogger(Logger logger) {
		
		init();
		
		jzaniaLogger = logger;
		
	}
	
}

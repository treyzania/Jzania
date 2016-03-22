package com.treyzania.jzania;

import java.util.logging.Logger;

public class Jzania {

	private static boolean inited = false;
	
	private static Logger log;
	
	public static void init() {
		
		if (inited) return;
		
		log = Logger.getLogger("Jzania");
		
		// Add more things as necessary.
		
		inited = true;
		log.info("Jzania initalized.");
		
	}
	
	public static Logger getLogger() {
		return log;
	}
	
}

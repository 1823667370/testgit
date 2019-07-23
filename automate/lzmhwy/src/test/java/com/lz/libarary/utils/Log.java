package com.lz.libarary.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	 public final Class<?> songer;
	 public Logger logger;

	    /**
	     * 
	     * @param songer
	     */
	   public Log(Class<?> songer) {
	        this.songer = songer;
	        this.logger = LogManager.getLogger(this.songer);
	    }


	    public void info(String message) {
	        logger.info(songer.getCanonicalName() + ": " + message);
	    }


	    public void debug(String message) {
	        logger.debug(songer.getCanonicalName() + ": " + message);
	    }

	    
	    public void error(String message) {
	        logger.error(songer.getCanonicalName() + ": " + message);
	    }


	    public void trace(String message) {
	        logger.trace(songer.getCanonicalName() + ": " + message);
	    }


	    public void warn(String message) {
	        logger.warn(songer.getCanonicalName() + ": " + message);
	    }


	    public void fatal(String message) {
	        logger.fatal(songer.getCanonicalName() + ": " + message);
	    }
	

}

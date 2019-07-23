package com.lz.libarary.utils;


public class test {
	   public static void main(String[] args) {
//		   File config=new File("D:/works/lzmh/log4j2.xml"); 
//	        ConfigurationSource source = new ConfigurationSource(new FileInputStream(config),config); 
//	        Configurator.initialize(null, source);
	        // TODO Auto-generated method stub
	        Log log=new Log(test.class);
	        
	        log.info("this is my test");
	        log.debug("this is a debug");
	        log.error("this is an error ");
	        log.fatal("this is a fatal");
	        log.trace("this is a trace");
	    }
}

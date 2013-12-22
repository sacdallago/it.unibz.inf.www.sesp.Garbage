package controller;

/*
 * Log Class
 * 
 * Version 0.1
 * 
 * Created 20/11/2013
 * 
 * copyright: This class and the following code is under copyright of the FUB and meant for internal use only.
 */

public abstract class Log {
	private static String log = "\n----------------------LOG\n";

	/**
	 * Method to create an event in the log.
	 * 
	 * @param s The string to be added to the log.
	 */
	public static void push(String s){
		long ms = System.currentTimeMillis();
		long sec = (ms / 1000) % 60;
		long min = (ms / (1000 * 60)) % 60;
		long hour = (ms / (1000 * 60 * 60)) % 24;
		log += "Entry created at " +hour+":"+min+":"+sec + "\t " + s + "\n";
				
	}
	
	/**
	 * Method to get the log
	 * 
	 * @return A string representing the log.
	 */
	public static String log(){
		return log;
	}
}

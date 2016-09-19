package com.engine.filemanager.parser;

/**
 * Error handling for all syntax, casts, types, and index exceptions found
 * within a .lvl format
 * 
 * @author Luke Roche 9/19/2016
 */

public class LVL_Exception extends Exception{

	/**
	 * serial
	 */
	private static final long serialVersionUID = -7410416287157296568L;
	
	/**
	 * For syntax, throws message and quits
	 */
	public LVL_Exception(String message){
		super(".lvl Syntax Error: " + message);
	}

}

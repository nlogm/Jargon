package com.engine.filemanager.parser;

public class LVL_Exception extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7410416287157296568L;
	
	public LVL_Exception(String message){
		super(".lvl Syntax Error: " + message);
	}

}

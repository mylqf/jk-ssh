package com.lqf.exception;
/**
 * 异常处理类
 * @author Administrator
 *
 */
public class SysException extends Exception {

	private String message;
	public String getMessage() {
		return message;
	}
	
	public SysException(String message){
		this.message = message;
	}	
}

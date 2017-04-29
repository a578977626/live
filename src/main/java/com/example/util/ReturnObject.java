package com.example.util;

public class ReturnObject {

	/** 错误代码 */
	private String errorCode;
	
	/** 提示信息 */
	private String errorMessage;
	
	/** 返回结果，可能是对象，也可能是数组或者空字符串。 */
	private Object returnObject;

	/**
	 * 构造方法
	 * @param msg 提示信息
	 */
	public ReturnObject(String msg) {
		this.errorCode = "00";
		this.errorMessage = msg;
		this.returnObject = "";
	}
	
	/**
	 * 构造方法
	 * @param object 返回对象
	 */
	public ReturnObject(Object object) {
		this.errorCode = "00";
		this.errorMessage = "";
		this.returnObject = object;
	}
	
	/**
	 * 构造方法
	 * @param msg 提示信息
	 * @param object 返回对象
	 */
	public ReturnObject(String msg, Object object) {
		this.errorCode = "00";
		this.errorMessage = msg;
		this.returnObject = object;
	}
	
	/**
	 * 构造方法
	 * @param errorCode 错误代码
	 * @param msg 错误提示信息
	 */
//	public ReturnObject(int errorCode, String msg) {
//		this.errorCode = ErrorConfig.getErrorCode(errorCode);
//		this.errorMessage = ErrorConfig.getErrorMsg(errorCode, msg);
//	}

	/**
	 * 获取错误代码
	 * @return 错误代码
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置错误代码
	 * @param errorCode 错误代码
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 获取提示信息
	 * @return 提示信息
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 设置提示信息
	 * @param errorMessage 提示信息
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 获取返回结果
	 * @return 返回结果
	 */
	public Object getReturnObject() {
		return returnObject;
	}

	/**
	 * 设置返回结果
	 * @param returnObject 返回结果
	 */
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

}

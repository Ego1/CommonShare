package com.ego.apps.commonshare.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.ego.apps.commonshare.actions.vo.CSError;
import com.ego.apps.commonshare.actions.vo.CSMessage;
import com.ego.apps.commonshare.exceptions.CSBusinessException;
import com.ego.apps.commonshare.exceptions.CSSystemException;
import com.ego.apps.commonshare.util.MathUtils;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Logger logger = Logger.getLogger(getClass());

	public static String RESULT_SUCCESS = "SUCCESS";
	public static String RESULT_ERROR = "ERROR";
	public static String RESULT_INVALID_DATA = "INVALID_DATA";
	public static String RESULT_LOGIN = "LOGIN";

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	private CSError error;
	private CSMessage message;

	protected void addError(int errorCode, String errorMessage) {
		if (error == null) {
			error = new CSError(errorCode);
		}
		error.addMessage(errorMessage);
		logger.info("ErrorCode: " + errorCode + " | Message: " + errorMessage);
	}

	protected void addError(String errorMessage) {
		int errCode = MathUtils.getRandomNumber();
		if (error == null) {
			error = new CSError(errCode);
		}
		error.addMessage(errorMessage);
		logger.info("ErrorCode: " + errCode + " | Message: " + errorMessage);
	}

	protected void addMessage(String msg) {
		if (message == null) {
			message = new CSMessage();
		}
		message.addMessage(msg);
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		session = request.getSession();
	}

	protected String handleBusinessException(
			CSBusinessException csBusinessException) {
		logger.error("A business exception occured.", csBusinessException);
		csBusinessException.printStackTrace();
		addError(csBusinessException.getCode(), csBusinessException.getMessage());
		return RESULT_ERROR;
	}

	protected String handleSystemException(CSSystemException csSystemException) {
		logger.fatal("A business exception occured.", csSystemException);
		csSystemException.printStackTrace();
		addError(csSystemException.getCode(), "system.exception.occured");
		return RESULT_ERROR;
	}

	// Value stack properties.
	public CSError getError() {
		return error;
	}

	public CSMessage getMessage() {
		return message;
	}
}
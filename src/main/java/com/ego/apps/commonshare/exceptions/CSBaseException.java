package com.ego.apps.commonshare.exceptions;

import com.ego.apps.commonshare.util.MathUtils;


public abstract class CSBaseException extends Exception
	{
	private String message;
	private Exception exception;
	private int code;

	public CSBaseException(String message)
		{
		this.message = message;
		this.code = MathUtils.getRandomNumber();
		}

	public CSBaseException(Exception exception)
		{
		this.exception = exception;
		this.message = exception.getMessage();
		this.code = MathUtils.getRandomNumber();
		}

	public CSBaseException(String message, Exception exception)
		{
		this.exception = exception;
		this.message = message;
		this.code = MathUtils.getRandomNumber();
		}

	public String toString()
		{
		return "Error: " + code + " | " + message;
		}

	public String getMessage()
		{
		return message;
		}

	public void setMessage(String message)
		{
		this.message = message;
		}

	public Exception getException()
		{
		return exception;
		}

	public void setException(Exception exception)
		{
		this.exception = exception;
		}

	public int getCode()
		{
		return code;
		}

	public void setCode(int code)
		{
		this.code = code;
		}
	}

package com.ego.apps.commonshare.exceptions;

public class CSSystemException extends CSBaseException
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CSSystemException(String message)
		{
		super(message);
		}

	public CSSystemException(Exception exception)
		{
		super(exception);
		}
	}

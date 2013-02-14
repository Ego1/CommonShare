package com.ego.apps.commonshare.exceptions;

public class CSBusinessException extends CSBaseException
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CSBusinessException(String message)
		{
		super(message);
		}

	public CSBusinessException(Exception exception)
		{
		super(exception);
		}
	}

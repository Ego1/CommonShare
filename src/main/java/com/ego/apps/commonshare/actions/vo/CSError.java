package com.ego.apps.commonshare.actions.vo;

import java.util.ArrayList;
import java.util.List;

public class CSError
	{
	private int code;
	private final List<String> messages = new ArrayList<String>();

	public CSError(int errorCode)
		{
		this.code = errorCode;
		}

	public CSError(int errorCode, String errorMessage)
		{
		this.code = errorCode;
		addMessage(errorMessage);
		}

	public int getCode()
		{
		return code;
		}

	public void setCode(int code)
		{
		this.code = code;
		}

	public void addMessage(String message)
		{
		messages.add(message);
		}

	public List<String> getMessages()
		{
		return messages;
		}

	}

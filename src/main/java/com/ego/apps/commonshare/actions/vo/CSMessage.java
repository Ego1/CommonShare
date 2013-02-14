package com.ego.apps.commonshare.actions.vo;

import java.util.ArrayList;
import java.util.List;

public class CSMessage
	{
	private List<String> messages;

	public CSMessage()
		{

		}

	public void addMessage(String message)
		{
		if (messages == null)
			{
			messages = new ArrayList<String>();
			}
		messages.add(message);
		}

	// For value stack access.
	public List<String> getMessages()
		{
		return messages;
		}

	public void setMessages(List<String> messages)
		{
		this.messages = messages;
		}

	}

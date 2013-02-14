package com.ego.apps.commonshare.cache;

import com.ego.apps.commonshare.dao.entities.User;

public class SessionCache
	{
	private User user;
	private boolean loggedInUser;

	public User getUser()
		{
		return user;
		}

	public void setUser(User user)
		{
		this.user = user;
		}

	public boolean isLoggedInUser()
		{
		return loggedInUser;
		}

	public void setLoggedInUser(boolean loggedInUser)
		{
		this.loggedInUser = loggedInUser;
		}

	}

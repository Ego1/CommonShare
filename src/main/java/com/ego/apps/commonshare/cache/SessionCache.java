package com.ego.apps.commonshare.cache;

import com.ego.apps.commonshare.beans.url.AuthorizedUrl;
import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.User;

public class SessionCache
	{
	private User user;
	private boolean loggedInUser;
	private Group group;
	private AuthorizedUrl authorizedUrl;

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

	public Group getGroup()
		{
		if (group == null)
			{
			group = user.getGroup();
			}
		return group;
		}

	public void setGroup(Group group)
		{
		this.group = group;
		}

	public AuthorizedUrl getAuthorizedUrl()
		{
		return authorizedUrl;
		}

	public void setAuthorizedUrl(AuthorizedUrl authorizedUrl)
		{
		this.authorizedUrl = authorizedUrl;
		}

	}

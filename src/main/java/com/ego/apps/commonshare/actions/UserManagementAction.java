package com.ego.apps.commonshare.actions;

import java.util.List;

import org.apache.log4j.Logger;

import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.GroupDAO;
import com.ego.apps.commonshare.dao.entities.User;

public class UserManagementAction extends BaseAction
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());

	List<User> users;		// Required to show all users.
	
	public String getAllUsers()
		{
		SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
		String groupName = sessionCache.getUser().getGroup().getName();
		users = sessionCache.getGroup().getUsers();
		if(users == null)
			{
			GroupDAO groupDao = new GroupDAO();
			users = groupDao.getAllUsersInGroup(groupName);
			sessionCache.getGroup().setUsers(users);
			}
		return RESULT_SUCCESS;
		}

	public List<User> getUsers()
		{
		return users;
		}

	public void setUsers(List<User> users)
		{
		this.users = users;
		}
	
	
	}

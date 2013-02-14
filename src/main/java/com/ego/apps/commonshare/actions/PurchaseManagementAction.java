package com.ego.apps.commonshare.actions;

import java.util.List;

import org.apache.log4j.Logger;

import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.GroupDAO;
import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.User;

public class PurchaseManagementAction extends BaseAction
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());
	
	private List<User> users;
	
	public String showAddPurchases()
		{
		// The main task for this is to populate the users in a group.
		SessionCache cache = SessionCacheManager.getSessionCache(request);
		Group userGroup = cache.getUser().getGroup();
		GroupDAO groupDAO = new GroupDAO();
		users = groupDAO.getAllUsersInGroup(userGroup.getName());
		return RESULT_SUCCESS;
		}
	
	// Getters and Setters

	public List<User> getUsers()
		{
		return users;
		}

	public void setUsers(List<User> users)
		{
		this.users = users;
		}
	
	
	}

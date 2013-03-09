package com.ego.apps.commonshare.cache;

import java.util.List;

import com.ego.apps.commonshare.dao.GroupDAO;
import com.ego.apps.commonshare.dao.ItemDAO;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.User;

public class GroupCache
	{
	private String groupName;
	private List<Item> items;
	private List<User> users;

	public GroupCache(String groupName)
		{
		this.groupName = groupName;
		}

	public String getGroupName()
		{
		return groupName;
		}

	public void setGroupName(String groupName)
		{
		this.groupName = groupName;
		}

	public List<Item> getItems()
		{
		if (items == null)
			{
			ItemDAO itemDAO = new ItemDAO();
			items = itemDAO.getAllItems(groupName);
			}
		return items;
		}

	public void setItems(List<Item> items)
		{
		this.items = items;
		}

	public List<User> getUsers()
		{
		if(users == null)
			{
			GroupDAO groupDAO = new GroupDAO();
			users = groupDAO.getAllUsersInGroup(groupName);
			}
		return users;
		}

	public void setUsers(List<User> users)
		{
		this.users = users;
		}

	public void refreshGroupCache()
		{

		}

	}

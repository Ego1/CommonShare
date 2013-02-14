package com.ego.apps.commonshare.dao;

public interface CommonShareNativeQueries
	{
	public String GET_ITEMS = "SELECT i.* FROM item i JOIN user_group g ON i.usergroup = g.id AND g.name = :groupName";
	}

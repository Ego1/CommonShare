package com.ego.apps.commonshare.cache;

import java.util.HashMap;
import java.util.Map;

public class GroupCacheManager
	{

	private static Map<String, GroupCache> groupCacheMap;

	public static void setGroupCacheItem(String groupName, GroupCache groupCache)
		{
		if (groupCacheMap == null)
			{
			groupCacheMap = new HashMap<String, GroupCache>();
			}
		groupCacheMap.put(groupName, groupCache);
		}

	public static GroupCache getGroupItem(String groupName)
		{
		if (groupCacheMap == null)
			{
			groupCacheMap = new HashMap<String, GroupCache>();
			}
		GroupCache groupCache = groupCacheMap.get(groupName);
		if (groupCache == null)
			{
			groupCache = new GroupCache();
			groupCacheMap.put(groupName, groupCache);
			}
		return groupCache;
		}
	
	public static void emptyGroupCache(String groupName)
		{
		if (groupCacheMap == null)
			{
			groupCacheMap = new HashMap<String, GroupCache>();
			}
		groupCacheMap.put(groupName, null);
		}

	}

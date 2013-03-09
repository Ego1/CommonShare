package com.ego.apps.commonshare.beans.url;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Holds the URLs that a user can have. It is set in session cache.
 * 
 * @author sony
 * 
 */
public class AuthorizedUrl
	{
	/**
	 * Each URL is a part of group. This is a map of group vs the list of URLs in that group.
	 */
	private Map<String, List<MenuUrl>> urlMap;

	public Map<String, List<MenuUrl>> getUrlMap()
		{
		return urlMap;
		}

	public void setUrlMap(Map<String, List<MenuUrl>> urlMap)
		{
		this.urlMap = urlMap;
		}

	public void addUrl(String group, MenuUrl url)
		{
		if (urlMap == null)
			{
			urlMap = new TreeMap<String, List<MenuUrl>>();
			}
		List<MenuUrl> urlList = urlMap.get(group);
		if (urlList == null)
			{
			urlList = new ArrayList<MenuUrl>();
			urlMap.put(group, urlList);
			}
		urlList.add(url);
		}
	}


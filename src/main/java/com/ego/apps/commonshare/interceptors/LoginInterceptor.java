package com.ego.apps.commonshare.interceptors;

import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.entities.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor
	{
	private static final long serialVersionUID = 1L;

	public void destroy()
		{

		}

	public void init()
		{

		}

	public String intercept(ActionInvocation invocation) throws Exception
		{
		SessionCache sessionCache = SessionCacheManager.getSessionCache(invocation);

		if (sessionCache == null)
			{
			return "login";
			}
		else
			{
			User user = (User) sessionCache.getUser();
			if (user == null)
				{
				return "login";
				}
			}
		return invocation.invoke();
		}
	}

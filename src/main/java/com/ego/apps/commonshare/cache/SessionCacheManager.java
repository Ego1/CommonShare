package com.ego.apps.commonshare.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionInvocation;

public class SessionCacheManager
	{
	private static String SESSION_CACHE_ATTRIBUTE = "SESSION_CACHE_ATTRIBUTE";

	private SessionCacheManager()
		{

		}

	/**
	 * Obtains a session instance (if one doesn't already exist, creates one). Then creates SessionCache instance and
	 * inserts it into the session object as session attribute SESSION_CACHE_ATTRIBUTE.
	 * 
	 * @param request
	 *            The request object from which session object is obtained.
	 * @return The new creates SessionCache object.
	 */
	public static SessionCache createSessionCache(HttpServletRequest request)
		{
		SessionCache sessionCache = new SessionCache();
		HttpSession session = request.getSession(true);
		session.setAttribute(SESSION_CACHE_ATTRIBUTE, sessionCache);
		return sessionCache;
		}

	public static SessionCache getSessionCache(HttpServletRequest request)
		{
		SessionCache sessionCache = (SessionCache) request.getSession().getAttribute(SESSION_CACHE_ATTRIBUTE);
		return sessionCache;
		}

	public static SessionCache getSessionCache(ActionInvocation actionInvocation)
		{
		SessionCache sessionCache = (SessionCache) actionInvocation.getInvocationContext().getSession().get(SESSION_CACHE_ATTRIBUTE);
		return sessionCache;
		}
	}

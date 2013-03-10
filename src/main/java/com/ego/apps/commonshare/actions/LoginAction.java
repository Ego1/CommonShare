package com.ego.apps.commonshare.actions;

import org.apache.log4j.Logger;

import com.ego.apps.commonshare.actions.vo.UserLoginVO;
import com.ego.apps.commonshare.beans.url.AuthorizedUrl;
import com.ego.apps.commonshare.beans.url.MenuUrl;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.UserDAO;
import com.ego.apps.commonshare.dao.entities.User;
import com.ego.apps.commonshare.messaging.Messages;
import com.ego.apps.commonshare.util.StringUtils;

public class LoginAction extends BaseAction
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());
	private UserLoginVO login;

	public String doLogin()
		{
		if (login == null || StringUtils.isEmpty(login.getLoginName()) || StringUtils.isEmpty(login.getPassword()))
			{
			addError(Messages.getMsg(Messages.LOGIN_ERROR_MISSING_DATA));
			return RESULT_ERROR;
			}
		logger.debug("Trying to login");
		UserDAO userDao = new UserDAO();
		User userProfile = userDao.authenticate(login.getLoginName(), login.getPassword());
		if (userProfile == null)
			{
			addError(Messages.getMsg(Messages.LOGIN_ERROR_BAD_CREDENTIALS));
			return RESULT_ERROR;
			}

		// Fetch user's authorization links.
		AuthorizedUrl authorizedURLs = new AuthorizedUrl();
		switch (userProfile.getRole())
			{
			case ADMIN:
			case MODERATOR:
				authorizedURLs.addUrl("User Management", new MenuUrl("View all Users", request.getContextPath() + "p/showAllUsers.action"));
				authorizedURLs.addUrl("User Management", new MenuUrl("Add User", request.getContextPath() + "p/showAddUser.action"));
			case MEMBER:
				authorizedURLs.addUrl("Item Management", new MenuUrl("See all Items", request.getContextPath() + "p/showItems.action"));
				authorizedURLs.addUrl("Item Management", new MenuUrl("Add an Item", request.getContextPath() + "p/showAddItem.action"));
				
				authorizedURLs.addUrl("Share Management", new MenuUrl("Show Purchases", request.getContextPath() + "p/showPurchases.action"));
				authorizedURLs.addUrl("Share Management", new MenuUrl("Add Purchases", request.getContextPath() + "p/showAddPurchase.action"));
				authorizedURLs.addUrl("Share Management", new MenuUrl("Show Calculations", request.getContextPath() + "p/showCalculations.action"));
				break;
			}

		// Setup the user's session cache.
		SessionCache sessionCache = SessionCacheManager.createSessionCache(request);
		sessionCache.setUser(userProfile);
		sessionCache.setLoggedInUser(true);
		sessionCache.setAuthorizedUrl(authorizedURLs);
		return RESULT_SUCCESS;
		}

	// Getters and Setters
	public UserLoginVO getLogin()
		{
		return login;
		}

	public void setLogin(UserLoginVO login)
		{
		this.login = login;
		}

	}

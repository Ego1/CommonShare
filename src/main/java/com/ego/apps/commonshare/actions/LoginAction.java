package com.ego.apps.commonshare.actions;

import org.apache.log4j.Logger;

import com.ego.apps.commonshare.actions.vo.UserLoginVO;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.UserDAO;
import com.ego.apps.commonshare.dao.entities.User;
import com.ego.apps.commonshare.messaging.LoginMsgs;
import com.ego.apps.commonshare.util.StringUtils;

public class LoginAction extends BaseAction implements LoginMsgs{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());
	private UserLoginVO login;
	
	public String doLogin()
	{
		if(login == null || StringUtils.isEmpty(login.getLoginName()) || StringUtils.isEmpty(login.getPassword()))
		{
			addError(LOGIN_ERROR_MISSING_DATA);
			return RESULT_ERROR;
		}
		logger.debug("Trying to login");
		UserDAO userDao = new UserDAO();
		User userProfile = userDao.authenticate(login.getLoginName(), login.getPassword());
		if(userProfile == null)
		{
			addError(LOGIN_ERROR_BAD_CREDENTIALS);
			return RESULT_ERROR;
		}
		SessionCache sessionCache = SessionCacheManager.createSessionCache(request);
		sessionCache.setUser(userProfile);
		sessionCache.setLoggedInUser(true);
		return RESULT_SUCCESS;
		
	}

	// Getters and Setters
	public UserLoginVO getLogin() {
		return login;
	}

	public void setLogin(UserLoginVO login) {
		this.login = login;
	}

	
	
}

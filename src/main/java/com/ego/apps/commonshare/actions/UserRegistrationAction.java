package com.ego.apps.commonshare.actions;

import com.ego.apps.commonshare.actions.vo.UserRegistrationVO;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.GroupDAO;
import com.ego.apps.commonshare.dao.UserDAO;
import com.ego.apps.commonshare.dao.entities.User;
import com.ego.apps.commonshare.exceptions.CSBusinessException;
import com.ego.apps.commonshare.exceptions.CSSystemException;
import com.ego.apps.commonshare.messaging.GroupMsgs;
import com.ego.apps.commonshare.messaging.UserRegistrationMsgs;
import com.ego.apps.commonshare.util.StringUtils;

/**
 * All the user's registration operations like group registration, non-moderator user registration etc are done here.
 * 
 * @author sony
 * 
 */
public class UserRegistrationAction extends BaseAction implements UserRegistrationMsgs
	{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserRegistrationVO register;

	private enum Operation
		{
		REGISTER_GROUP, REGISTER_USER
		};

	public String registerGroup()
		{
		if (!isValid(Operation.REGISTER_GROUP))
			{
			return RESULT_ERROR;
			}

		GroupDAO groupDAO = new GroupDAO();
		User user = null;
		try
			{
			user = groupDAO.registerGroup(register);
			if (user == null)
				{
				addError(GroupMsgs.REGISTRATION_FAILED);
				logger.error(GroupMsgs.REGISTRATION_FAILED);
				}
			addMessage(getText(SUCCESS_PROFILE_CREATED, new String[]
				{ user.getEmailId() }));
			}
		catch (CSBusinessException e)
			{
			handleBusinessException(e);
			}
		catch (CSSystemException e)
			{
			handleSystemException(e);
			}
		finally
			{
			groupDAO.close();
			}

		return RESULT_SUCCESS;
		}

	public String registerUser()
		{
		if (register == null)
			{
			addError(getText(ERROR_INVALID_PROFILE));
			return RESULT_ERROR;
			}

		if (!isValid(Operation.REGISTER_USER))
			{
			return RESULT_ERROR;
			}
		
		// Get the user profile object of current user from session and use it's group.
		SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
		String groupName = sessionCache.getUser().getGroup().getName(); 
		
		UserDAO userDao = new UserDAO();
		try
			{
			userDao.createUser(register, groupName);
			}
		catch (CSBusinessException e)
			{
			handleBusinessException(e);
			}
		catch (CSSystemException e)
			{
			handleSystemException(e);
			}
		return RESULT_SUCCESS;
		}

	/**
	 * Validates all the mandatory fields.
	 * 
	 * @return
	 */
	public boolean isValid(Operation operation)
		{
		if (register == null)
			{
			addError(getText(ERROR_INVALID_PROFILE));
			return false;
			}

		StringBuffer missingFields = new StringBuffer();
		if (StringUtils.isEmpty(register.getName()))
			{
			missingFields.append(getText(LABEL_NAME));
			missingFields.append(", ");
			}

		if (StringUtils.isEmpty(register.getLoginName()))
			{
			missingFields.append(getText(LABEL_LOGIN_NAME));
			missingFields.append(", ");
			}

		if (StringUtils.isEmpty(register.getPassword()))
			{
			missingFields.append(getText(LABEL_PASSWORD));
			missingFields.append(", ");
			}

		switch (operation)
			{
			case REGISTER_GROUP:
				if (StringUtils.isEmpty(register.getGroupName()))
					{
					missingFields.append(getText(LABEL_GROUP_NAME));
					missingFields.append(", ");
					}
				break;
			}
		if (missingFields.length() > 0)
			{
			String errorString = missingFields.substring(0, missingFields.length() - 2);
			addError(getText(ERROR_MISSING_REQUIRED_FIELDS, errorString));
			}
		return true;
		}

	public UserRegistrationVO getRegister()
		{
		return register;
		}

	public void setRegister(UserRegistrationVO register)
		{
		this.register = register;
		}

	}

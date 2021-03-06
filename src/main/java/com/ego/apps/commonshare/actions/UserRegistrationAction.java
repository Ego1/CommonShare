package com.ego.apps.commonshare.actions;

import com.ego.apps.commonshare.actions.vo.UserRegistrationVO;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.GroupDAO;
import com.ego.apps.commonshare.dao.UserDAO;
import com.ego.apps.commonshare.dao.entities.User;
import com.ego.apps.commonshare.exceptions.CSBusinessException;
import com.ego.apps.commonshare.exceptions.CSSystemException;
import com.ego.apps.commonshare.messaging.Messages;
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
				addError(Messages.getMsg(Messages.REGISTRATION_ERROR_FAILED));
				}
			// User was created. Add him in the cache too.
			addMessage(getText(Messages.getMsg(Messages.REGISTRATION_SUCCESS_PROFILE_CREATED), user.getEmailId()));
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
			addError(Messages.getMsg(Messages.REGISTRATION_ERROR_MISSINS_PROFILE));
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
			User user = userDao.createUser(register, groupName);
			sessionCache.getGroup().getUsers().add(user);
			}
		catch (CSBusinessException e)
			{
			handleBusinessException(e);
			return RESULT_ERROR;
			}
		catch (CSSystemException e)
			{
			handleSystemException(e);
			return RESULT_ERROR;
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
			addError(Messages.getMsg(Messages.REGISTRATION_ERROR_MISSINS_PROFILE));
			return false;
			}

		StringBuffer missingFields = new StringBuffer();
		if (StringUtils.isEmpty(register.getName()))
			{
			missingFields.append(Messages.getMsg(Messages.LABEL_NAME));
			missingFields.append(", ");
			}

		if (StringUtils.isEmpty(register.getLoginName()))
			{
			missingFields.append(Messages.getMsg(Messages.LABEL_LOGIN_NAME));
			missingFields.append(", ");
			}

		if (StringUtils.isEmpty(register.getPassword()))
			{
			missingFields.append(Messages.getMsg(Messages.LABEL_PASSWORD));
			missingFields.append(", ");
			}

		switch (operation)
			{
			case REGISTER_GROUP:
				if (StringUtils.isEmpty(register.getGroupName()))
					{
					missingFields.append(Messages.getMsg(Messages.LABEL_GROUP_NAME));
					missingFields.append(", ");
					}
				break;
			}
		if (missingFields.length() > 0)
			{
			String errorString = missingFields.substring(0, missingFields.length() - 2);
			addError(Messages.getMsg(Messages.REGISTRATION_ERROR_MISSING_REQUIRED_FIELDS, errorString));
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

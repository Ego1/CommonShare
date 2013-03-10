package com.ego.apps.commonshare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ego.apps.commonshare.actions.vo.UserRegistrationVO;
import com.ego.apps.commonshare.actions.vo.helpers.UserHelper;
import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.User;
import com.ego.apps.commonshare.enumerations.Role;
import com.ego.apps.commonshare.exceptions.CSBusinessException;
import com.ego.apps.commonshare.exceptions.CSSystemException;
import com.ego.apps.commonshare.messaging.Messages;
import com.ego.apps.commonshare.util.SecurityUtils;
import com.ego.apps.commonshare.util.StringUtils;

public class UserDAO extends BaseDAO
	{

	public UserDAO()
		{
		super();
		}

	public UserDAO(EntityManager entityManager)
		{
		super(entityManager);
		}
	
	/**
	 * Creates a user based on the passed information. This is a non moderator user. A moderator shall be created along
	 * with the group. A moderator will be created using GroupDAO class.
	 * 
	 * @param register
	 *            The user's registration VO.
	 * @return The User object that is created - prefilled with user-ID if
	 *         needed.
	 * @throws CSSystemException
	 *             Thrown when either user password encryption fails or a user
	 *             role mentioned is missing.
	 * @throws CSBusinessException
	 *             Thrown when group is missing.
	 */
	public User createUser(UserRegistrationVO register, String groupName) throws CSBusinessException, CSSystemException
		{
		User user = UserHelper.createUserFromRegistrationProfile(register);
		if (user == null)
			{
			throw new CSBusinessException(Messages.getMsg(Messages.REGISTRATION_ERROR_MISSING_INFORATION));
			}
		user.setRole(Role.MEMBER);
		user = createUser(user, groupName);
		user.setPassword(null);
		return user;
		}

	/**
	 * Creates a user based on the passed information. This is a non moderator user. A moderator shall be created along
	 * with the group.
	 * A moderator will be created using GroupDAO class.
	 * 
	 * @param user
	 *            The user object that needs to be created in database. Make
	 *            sure that the role and group information for user have already
	 *            been populated.
	 * @return The User object that is created - prefilled with user-ID if
	 *         needed.
	 * @throws CSSystemException
	 *             Thrown when either user password encryption fails or a user
	 *             role mentioned is missing.
	 * @throws CSBusinessException
	 *             Thrown when group is missing.
	 */
	@SuppressWarnings("unchecked")
	public User createUser(User user, String groupName) throws CSSystemException, CSBusinessException
		{
		if (user == null)
			{
			throw new CSBusinessException(Messages.getMsg(Messages.REGISTRATION_ERROR_MISSINS_PROFILE));
			}
		try
			{
			user.setPassword(SecurityUtils.md5encrypt(user.getPassword()));
			}
		catch (CSBusinessException csBusinessException)
			{
			logger.error("Unable to encrypt user password while account creation");
			throw new CSSystemException("Unable to encrypt user password.");
			}

		boolean isLocalTransaction = false;
		if (!entityManager.getTransaction().isActive())
			{
			entityManager.getTransaction().begin();
			isLocalTransaction = true;
			}

		Query query = entityManager.createNamedQuery("GET_USER_BY_LOGIN");
		query.setParameter("loginname", user.getLogin());
		List<User> alreadyCreatedUser = (List<User>) query.getResultList();
		if (alreadyCreatedUser.size() != 0)
			{
			entityManager.getTransaction().rollback();
			throw new CSBusinessException(Messages.getMsg(Messages.REGISTRATION_ERROR_LOGIN_ALREADY_EXISTS));
			}
		
		// Obtain the group entity and assign it to the User entity.
		Query queryGroup = entityManager.createNamedQuery("GET_GROUP");
		queryGroup.setParameter("name", groupName);
		List<Group> userGroups = queryGroup.getResultList();
		if(userGroups.size() == 0)
			{
			// The group does not exist.
			throw new CSBusinessException(Messages.getMsg(Messages.REGISTRATION_ERROR_GROUP_ALREADY_EXISTS));
			}
		user.setGroup(userGroups.get(0));
		entityManager.persist(user);
		if (isLocalTransaction)
			{
			entityManager.getTransaction().commit();
			}
		// Setting the password as null as it need not be passed to layers
		// above.
		user.setPassword(null);
		return user;
		}

	/**
	 * Authenticates the user based on the ID-Password combination.
	 * 
	 * @param login
	 *            The login name of the user.
	 * @param password
	 *            The password of the user
	 * @return User object on success. null on failure.
	 */
	@SuppressWarnings("unchecked")
	public User authenticate(String login, String password)
		{
		// Check if login id or password are empty
		if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password))
			{
			return null;
			}

		// Get user information from the database
		Query query = entityManager.createNamedQuery("GET_USER_BY_LOGIN");
		query.setParameter("loginname", login);
		List<User> users = query.getResultList();
		if (users == null || users.size() == 0)
			{
			// No user object found with this login name.
			return null;
			}
		User user = users.get(0);

		// Compare the password with the one in database - after encryption.
		String hashedPassword = null;
		try
			{
			hashedPassword = SecurityUtils.md5encrypt(password);
			}
		catch (CSBusinessException csBusinessException)
			{
			logger.error("Unable to encrypt the user password during login comparison.");
			}
		if (hashedPassword.equalsIgnoreCase(user.getPassword()))
			{
			// Setting password as null as it need not be passed to layers
			// above.
			user.setPassword(null);
			
			// Invoking following to load user group so that it can also be put in session.
			user.getGroup().getName();
			return user;
			}
		return null;
		}
	}

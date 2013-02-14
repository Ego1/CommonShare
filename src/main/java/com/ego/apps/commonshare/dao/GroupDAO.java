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
import com.ego.apps.commonshare.messaging.GroupMsgs;
import com.ego.apps.commonshare.messaging.UserRegistrationMsgs;
import com.ego.apps.commonshare.util.SecurityUtils;
import com.ego.apps.commonshare.util.StringUtils;

public final class GroupDAO extends BaseDAO
	{
	public GroupDAO()
		{
		super();
		}
	
	public GroupDAO(EntityManager entityManager)
		{
		super(entityManager);
		}

	/**
	 * When a user registers himself, he creates a group too. So we take the registration vo as input and return user
	 * object as output. This method creates a group and then the user who is its
	 * moderator. The user object contains group object as well.
	 * 
	 * @param userRegistrationVO
	 * @return User object for the user that is created.
	 * @throws CSBusinessException
	 * @throws CSSystemException
	 */
	public User registerGroup(UserRegistrationVO userRegistrationVO) throws CSBusinessException, CSSystemException
		{
		if (userRegistrationVO == null)
			{
			throw new CSBusinessException(GroupMsgs.REGISTRATION_MISSING_INFORATION);
			}

		if (StringUtils.isEmpty(userRegistrationVO.getGroupName()))
			{
			throw new CSBusinessException(GroupMsgs.REGISTRATION_MISSING_GROUP_NAME);
			}

		// Create a group object
		Group group = new Group();
		group.setName(userRegistrationVO.getGroupName());
		logger.debug("Adding a user group: " + group.getName());

		// Now create the User object based on the information.
		User user = UserHelper.createUserFromRegistrationProfile(userRegistrationVO);
		if (user == null)
			{
			throw new CSBusinessException(GroupMsgs.REGISTRATION_MISSING_INFORATION);
			}
		user.setRole(Role.MODERATOR);

		try
			{
			try
				{
				user.setPassword(SecurityUtils.md5encrypt(user.getPassword()));
				}
			catch (CSBusinessException csBusinessException)
				{
				logger.error("Unable to encrypt user password while account creation");
				throw new CSSystemException("Unable to encrypt user password.");
				}

			// See if group exists already.
			Query query = entityManager.createNamedQuery("GET_GROUP");
			query.setParameter("name", userRegistrationVO.getGroupName());
			List<Group> alreadyCreatedGroup = (List<Group>) query.getResultList();
			if (alreadyCreatedGroup.size() != 0)
				{
				throw new CSBusinessException(GroupMsgs.GROUP_ALREADY_EXISTS);
				}

			Query userQuery = entityManager.createNamedQuery("GET_USER_BY_LOGIN");
			userQuery.setParameter("loginname", user.getLogin());
			List<User> alreadyCreatedUser = (List<User>) userQuery.getResultList();
			if (alreadyCreatedUser.size() != 0)
				{
				throw new CSBusinessException(UserRegistrationMsgs.ERROR_LOGIN_ALREADY_EXISTS);
				}

			group.getUsers().add(user);
			user.setGroup(group);
			entityManager.getTransaction().begin();
			entityManager.persist(group);
			// entityManager.refresh(user);
			entityManager.getTransaction().commit();

			// Setting user password to null because this is not reqired to be kept in session memory.
			user.setPassword(null);
			}
		catch (CSSystemException exception)
			{
			entityManager.getTransaction().rollback();
			throw exception;
			}

		return user;
		}

	public List<User> getAllUsersInGroup(String groupName)
		{
		Query query = entityManager.createNamedQuery("GET_ALL_USERS_IN_GROUP");
		query.setParameter("groupName", groupName);
		return (List<User>) query.getResultList();
		}
	
	public Group getGroup(String groupName)
		{
		Query query = entityManager.createNamedQuery("GET_GROUP");
		query.setParameter("name", groupName);
		List<Group> groups = query.getResultList();
		if(groups == null || groups.size() == 0)
			{
			return null;
			}
		return groups.get(0);
		}
	}

package com.ego.apps.commonshare.actions.vo.helpers;

import com.ego.apps.commonshare.actions.vo.UserRegistrationVO;
import com.ego.apps.commonshare.dao.entities.User;

public final class UserHelper {
	/**
	 * Creates a User object out of the registration vo. The only fields not
	 * populated here are user's role and his group. This i nformation needs to
	 * be populated by the caller of this method.
	 * 
	 * @param userRegistrationVO
	 *            The registration VO populated by user.
	 * @return The User object. Note - role and group name are not populated.
	 *         The user is not created in DB by this method.
	 */
	public static User createUserFromRegistrationProfile(
			UserRegistrationVO userRegistrationVO) {
		if(userRegistrationVO == null)
		{
			return null;
		}
		User user = new User();
		user.setLogin(userRegistrationVO.getLoginName());
		user.setName(userRegistrationVO.getLoginName());
		user.setPassword(userRegistrationVO.getPassword());
		user.setEmailId(userRegistrationVO.getEmail());
		user.setActive(true);
		user.setDeleted(false);

		return user;
	}
}

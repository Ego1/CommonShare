package com.ego.apps.commonshare.messaging;

public class Messages
	{
	/* ****************** System Message ********************************** */
	public static final String SYSTEM_EX = "An internal error has occured. We are sorry we couldn't complete the operation you asked for.";

	/* ****************** Login Message *********************************** */
	public static final String LOGIN_ERROR_MISSING_DATA = "The login ID and password are mandatory.";
	public static final String LOGIN_ERROR_BAD_CREDENTIALS = "The login ID and password do not match.";

	/* ****************** Group and User Resistration Messages ************* */
	public static final String REGISTRATION_ERROR_MISSING_GROUP_NAME = "The group name isn't mentioned.";
	public static final String REGISTRATION_ERROR_MISSING_INFORATION = "The group and user information is missing.";
	public static final String REGISTRATION_ERROR_FAILED = "Both the user and group registration failed.";
	public static final String REGISTRATION_ERROR_GROUP_ALREADY_EXISTS = "The group name already exists. Please select a different name.";
	
	/* ***************** User Registration failed ************************* */
	public static final String REGISTRATION_ERROR_MISSINS_PROFILE = "User profile was missing and hence not created not be created.";
	public static final String REGISTRATION_ERROR_MISSING_REQUIRED_FIELDS = "Please enter the following mandatory fields {0}.";
	public static final String REGISTRATION_ERROR_LOGIN_ALREADY_EXISTS="Your user login name {0} already exists. Please choose another name.";
	
	public static final String REGISTRATION_SUCCESS_PROFILE_CREATED = "Your profile was created successfully. Please activate your profile with the link sent to your email account {0}.";
	public static final String LABEL_LOGIN_NAME = "Login Name";
	public static final String LABEL_NAME = "Name";
	public static final String LABEL_PASSWORD = "Password";
	public static final String LABEL_GROUP_NAME = "Group Name";
	
	/* **************** Item management *********************************** */
	public static final String ITEM_MANAGEMENT_ADD_SUCCESS = "Item was added successfully.";
	
	/* **************** Common errors ************************************* */
	public static final String ERROR_INVALID_GROUP = "The user belongs to an invalid group.";
	
	
	public static String getMsg(String message)
		{
		return message;
		}

	public static String getMsg(String message, String... snippets)
		{
		if (snippets.length > 0)
			{
			for (int ctr = 0; ctr < snippets.length; ctr++)
				{
				message = message.replace("{" + ctr + "}", snippets[ctr]);
				}
			}
		return message;
		}
	}

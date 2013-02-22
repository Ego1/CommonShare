package com.ego.apps.commonshare.actions;

import com.ego.apps.commonshare.actions.vo.AjaxResult;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.entities.Calculation;
import com.ego.apps.commonshare.util.CalculationsUtils;

public class ShareManagementAction extends BaseAction
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;		// Obtained when user wants a calculation.
	private AjaxResult result;
	Calculation calculation;

	/* ************************************* Action Methods ************************************* */
	public String calculateShare()
		{
		// Find user's group.
		SessionCache cache = SessionCacheManager.getSessionCache(request);
		String groupName = cache.getUser().getGroup().getName();
		calculation = CalculationsUtils.performCalculation(groupName, description);
		// TODO: Need to show this calculation in user interface.
		return RESULT_SUCCESS;
		}

	/* ************************************* Getters and Setters ******************************** */
	public String getDescription()
		{
		return description;
		}

	public void setDescription(String description)
		{
		this.description = description;
		}

	public AjaxResult getResult()
		{
		return result;
		}

	public void setResult(AjaxResult result)
		{
		this.result = result;
		}
	}

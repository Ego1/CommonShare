package com.ego.apps.commonshare.actions;

import com.ego.apps.commonshare.actions.vo.AjaxResult;

public class ShareManagementAction extends BaseAction
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;		// Obtained when user wants a calculation.
	private AjaxResult result;

	/* ************************************* Action Methods ************************************* */
	public String calculateShare()
		{
		
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

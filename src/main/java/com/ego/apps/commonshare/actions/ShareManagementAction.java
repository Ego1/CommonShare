package com.ego.apps.commonshare.actions;

import java.util.List;

import com.ego.apps.commonshare.actions.vo.AjaxResult;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.PurchaseDAO;
import com.ego.apps.commonshare.dao.entities.Purchase;

public class ShareManagementAction extends BaseAction
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;		// Obtained when user wants a calculation.
	List<Purchase> purchases;
	private AjaxResult result;

	/* ************************************* Action Methods ************************************* */
	public String calculateShare()
		{
		// Find user's group.
		SessionCache cache = SessionCacheManager.getSessionCache(request);
		String groupName = cache.getUser().getGroup().getName();
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		purchases = purchaseDAO.getPurchasesSinceLastCalculation(groupName);
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

	public List<Purchase> getPurchases()
		{
		return purchases;
		}

	public void setPurchases(List<Purchase> purchases)
		{
		this.purchases = purchases;
		}

	}

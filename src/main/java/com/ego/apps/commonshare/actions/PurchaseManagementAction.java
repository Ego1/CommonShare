package com.ego.apps.commonshare.actions;

import java.util.List;

import org.apache.log4j.Logger;

import com.ego.apps.commonshare.actions.vo.AjaxResult;
import com.ego.apps.commonshare.actions.vo.PurchaseUIVO;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.GroupDAO;
import com.ego.apps.commonshare.dao.ItemDAO;
import com.ego.apps.commonshare.dao.PurchaseDAO;
import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.Purchase;
import com.ego.apps.commonshare.dao.entities.User;
import com.ego.apps.commonshare.exceptions.CSBusinessException;

public class PurchaseManagementAction extends BaseAction
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());

	private List<User> users;
	private List<Item> items;
	private PurchaseUIVO purchaseUIVO = new PurchaseUIVO();
	private AjaxResult ajaxResult;

	public String showAddPurchases()
		{
		// The main task for this is to populate the users in a group.
		SessionCache cache = SessionCacheManager.getSessionCache(request);
		Group userGroup = cache.getUser().getGroup();
		GroupDAO groupDAO = new GroupDAO();

		// Collect all the users for exclusion list and payment spread.
		users = groupDAO.getAllUsersInGroup(userGroup.getName());

		// Collect all the items for item selection list.
		ItemDAO itemDAO = new ItemDAO();
		items = itemDAO.getAllItems(userGroup.getName());

		return RESULT_SUCCESS;
		}

	public String addPurchase()
		{
		try
			{
			Purchase purchase = purchaseUIVO.getPurchase();
			SessionCache cache = SessionCacheManager.getSessionCache(request);
			Group userGroup = cache.getUser().getGroup();
			purchase.setUserGroup(userGroup);
			PurchaseDAO purchaseDAO = new PurchaseDAO();
			purchaseDAO.addPurchase(purchase);
			ajaxResult = new AjaxResult();
			ajaxResult.setResult(true);
			ajaxResult.setMessage("Purchase added successfully.");
			}
		catch (CSBusinessException csBusinessException)
			{
			ajaxResult = new AjaxResult();
			ajaxResult.setResult(false);
			ajaxResult.setMessage(csBusinessException.getMessage());
			handleBusinessException(csBusinessException);
			}
		return RESULT_SUCCESS;
		}

	/* ***************************** Getters and Setters ******************************* */
	public List<User> getUsers()
		{
		return users;
		}

	public void setUsers(List<User> users)
		{
		this.users = users;
		}

	public List<Item> getItems()
		{
		return items;
		}

	public void setItems(List<Item> items)
		{
		this.items = items;
		}

	public PurchaseUIVO getPurchaseUIVO()
		{
		return purchaseUIVO;
		}

	public void setPurchaseUIVO(PurchaseUIVO purchaseUIVO)
		{
		this.purchaseUIVO = purchaseUIVO;
		}

	}

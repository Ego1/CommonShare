package com.ego.apps.commonshare.actions;

import java.util.Collections;
import java.util.List;

import com.ego.apps.commonshare.actions.vo.AjaxResult;
import com.ego.apps.commonshare.actions.vo.ItemVO;
import com.ego.apps.commonshare.cache.GroupCache;
import com.ego.apps.commonshare.cache.GroupCacheManager;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.ItemDAO;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.comparators.ItemComparator;
import com.ego.apps.commonshare.exceptions.CSBusinessException;
import com.ego.apps.commonshare.messaging.Messages;

public class ItemManagementAction extends BaseAction
	{

	/**
	 * The default serial version id.
	 */
	private static final long serialVersionUID = 1L;

	private List<Item> items;
	private ItemVO itemVO;
	private AjaxResult ajaxResult;

	public String readyPage()
		{
		// Obtain the user's group name.
		SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
		String groupName = sessionCache.getGroup().getName();

		// Obtain the items for this user.
		GroupCache groupCache = GroupCacheManager.getGroupCache(groupName);
		items = groupCache.getItems();

		// Sort the items according to taxonomy levels.
		Collections.sort(items, new ItemComparator());

		return RESULT_SUCCESS;
		}

	public String addItem()
		{
		SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
		String groupName = sessionCache.getGroup().getName();
		ItemDAO itemDAO = new ItemDAO();
		Item item = null;
		ajaxResult = new AjaxResult();
		try
			{
			item = itemDAO.addItem(itemVO, groupName);
			// The item has been added successfully. So add it to group cache too.
			GroupCache groupCache = GroupCacheManager.getGroupCache(groupName);
			groupCache.getItems().add(item);
			ajaxResult.setResult(true);
			ajaxResult.setMessage(Messages.getMsg(Messages.ITEM_MANAGEMENT_ADD_SUCCESS));
			ajaxResult.setData("{\"id\" : \"" + item.getId() + "\", \"name\": \"" + item.getName() + "\", \"description\":\"" + item.getDescription() + "\"}");
			}
		catch (CSBusinessException e)
			{
			ajaxResult.setResult(false);
			ajaxResult.setMessage(e.getMessage());
			e.printStackTrace();
			}
		return RESULT_AJAX;
		}

	/* ****************************** GETTERS and SETTERS ************************ */
	public List<Item> getItems()
		{
		return items;
		}

	public void setItems(List<Item> items)
		{
		this.items = items;
		}

	public ItemVO getItemVO()
		{
		return itemVO;
		}

	public void setItemVO(ItemVO itemVO)
		{
		this.itemVO = itemVO;
		}

	public AjaxResult getAjaxResult()
		{
		return ajaxResult;
		}

	public void setAjaxResult(AjaxResult ajaxResult)
		{
		this.ajaxResult = ajaxResult;
		}

	}

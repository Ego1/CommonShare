package com.ego.apps.commonshare.actions;

import java.util.Collections;
import java.util.List;

import com.ego.apps.commonshare.actions.vo.AjaxResult;
import com.ego.apps.commonshare.actions.vo.ItemVO;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.ItemDAO;
import com.ego.apps.commonshare.dao.TaxonomyDAO;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.Taxonomy;
import com.ego.apps.commonshare.dao.entities.comparators.ItemComparator;
import com.ego.apps.commonshare.exceptions.CSBusinessException;

public class ItemManagementAction extends BaseAction
	{

	/**
	 * The default serial version id.
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Item> items;
	private List<Taxonomy> taxonomies;
	private ItemVO itemVO;
	private AjaxResult ajaxResult;

	public String readyPage()
		{
		// Obtain the user's group name.
		SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
		String groupName = sessionCache.getUser().getGroup().getName();
		
		// Obtain the items for this user.
		ItemDAO itemDAO = new ItemDAO();
		items = itemDAO.getAllItems(groupName);
		
		// Sort the items according to taxonomy levels.
		Collections.sort(items, new ItemComparator());
		
		// Obtain all the taxonomies allowed for this user.
		TaxonomyDAO taxonomyDAO = new TaxonomyDAO();
		taxonomies = taxonomyDAO.getTaxonomies(groupName);
		
		System.out.println("Items Size: " + items.size());
		System.out.println("Taxonomies Size: " + taxonomies.size());
		return RESULT_SUCCESS;
		}

	public String addItem()
		{
		SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
		String groupName = sessionCache.getUser().getGroup().getName();
		ItemDAO itemDAO = new ItemDAO();
		Item item = null;
		ajaxResult = new AjaxResult();
		try
			{
			item = itemDAO.addItem(itemVO, groupName);
			ajaxResult.setResult(true);
			ajaxResult.setMessage("itemmanagement.success.itemadded");
			ajaxResult.setData("{\"id\" : \"" + item.getId() + "\", \"name\": \"" + item.getName() + "\", \"description\":\"" + item.getDescription() + "\", \"taxonomy\" : \""+item.getTaxonomy().getId()+"\"}");
			}
		catch (CSBusinessException e)
			{
			ajaxResult.setResult(false);
			ajaxResult.setMessage(e.getMessage());
			e.printStackTrace();
			}
		return RESULT_SUCCESS;
		}

	public List<Item> getItems()
		{
		return items;
		}

	public void setItems(List<Item> items)
		{
		this.items = items;
		}

	public List<Taxonomy> getTaxonomies()
		{
		return taxonomies;
		}

	public void setTaxonomies(List<Taxonomy> taxonomies)
		{
		this.taxonomies = taxonomies;
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

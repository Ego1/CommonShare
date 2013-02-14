package com.ego.apps.commonshare.actions;

import java.util.List;

import com.ego.apps.commonshare.actions.vo.AjaxResult;
import com.ego.apps.commonshare.actions.vo.TaxonomyVO;
import com.ego.apps.commonshare.cache.GroupCache;
import com.ego.apps.commonshare.cache.GroupCacheManager;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.ItemDAO;
import com.ego.apps.commonshare.dao.TaxonomyDAO;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.Taxonomy;
import com.ego.apps.commonshare.dao.entities.User;
import com.ego.apps.commonshare.exceptions.CSBusinessException;

public class TaxonomyManagementAction extends BaseAction
	{
	private static final long serialVersionUID = 1L;

	private List<Item> items;
	private List<Taxonomy> taxonomies;
	private TaxonomyVO taxonomyVO;
	private AjaxResult ajaxResult;

	public String getAllTaxonomiesForGroup()
		{
		SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
		String groupName = sessionCache.getUser().getGroup().getName();
		GroupCache groupCache = GroupCacheManager.getGroupItem(groupName);
		taxonomies = groupCache.getTaxonomies(groupName);
		return RESULT_SUCCESS;
		}

	public String addTaxonomy()
		{
		// Code to insert the taxonomy goes here.
		TaxonomyDAO taxonomyDAO = new TaxonomyDAO();
		SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
		Taxonomy newTaxonomy = null;
		ajaxResult = new AjaxResult();
		try
			{
			newTaxonomy = taxonomyDAO.addTaxonomy(taxonomyVO, sessionCache.getUser().getGroup().getName());
			if (newTaxonomy == null)
				{
				ajaxResult.setResult(false);
				ajaxResult.setMessage("taxonomymanagement.error.unable-to-add-taxonomy");
				return RESULT_ERROR;
				}
			else
				{
				ajaxResult.setMessage("Taxonomy added successfully.");
				ajaxResult.setResult(true);
				}
			}
		catch (CSBusinessException csBusinessException)
			{
			ajaxResult.setResult(false);
			ajaxResult.setMessage(csBusinessException.getMessage());
			}
		String parentId = "";
		if (newTaxonomy.getParent() == null)
			{
			parentId = "";
			}
		else
			{
			parentId = "" + newTaxonomy.getParent().getId();
			}

		ajaxResult.setData("{\"id\":\"" + newTaxonomy.getId() + "\",\"name\":\"" + newTaxonomy.getName() + "\",\"description\" : \"" + newTaxonomy.getDescription() + "\", \"parent\" : \"" + parentId
				+ "\"}");
		
		String groupName = sessionCache.getUser().getGroup().getName();
		GroupCacheManager.emptyGroupCache(groupName);
		return RESULT_SUCCESS;
		}

	public String getAllItems()
		{
		User user = (User) request.getSession().getAttribute("USER_PROFILE");
		String groupName = user.getGroup().getName();
		ItemDAO itemDao = new ItemDAO();
		items = itemDao.getAllItems(groupName);
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

	public TaxonomyVO getTaxonomyVO()
		{
		return taxonomyVO;
		}

	public void setTaxonomyVO(TaxonomyVO taxonomyVO)
		{
		this.taxonomyVO = taxonomyVO;
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

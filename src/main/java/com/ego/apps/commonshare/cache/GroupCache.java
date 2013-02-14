package com.ego.apps.commonshare.cache;

import java.util.List;

import com.ego.apps.commonshare.dao.TaxonomyDAO;
import com.ego.apps.commonshare.dao.entities.Taxonomy;

public class GroupCache
	{
	List<Taxonomy> taxonomies;

	public List<Taxonomy> getTaxonomies(String groupName)
		{
		if(taxonomies == null)
			{
			TaxonomyDAO taxonomyDAO = new TaxonomyDAO();
			taxonomies = taxonomyDAO.getTaxonomies(groupName);
			}
		return taxonomies;
		}

	public void setTaxonomies(List<Taxonomy> taxonomies)
		{
		this.taxonomies = taxonomies;
		}
	
	public void refreshGroupCache()
		{
		
		}
	

	}

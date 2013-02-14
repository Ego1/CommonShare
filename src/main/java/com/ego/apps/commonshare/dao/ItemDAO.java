package com.ego.apps.commonshare.dao;

import java.awt.Robot;
import java.util.List;

import javax.persistence.Query;

import com.ego.apps.commonshare.actions.vo.ItemVO;
import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.Taxonomy;
import com.ego.apps.commonshare.exceptions.CSBusinessException;

public class ItemDAO extends BaseDAO
	{
	public List<Item> getAllItems(String group)
		{
		Query query = entityManager.createNamedQuery("GET_ALL_GROUP_ITEMS");
		query.setParameter("groupName", group);
		List<Item> items = (List<Item>) query.getResultList();
		return items;
		}

	public List<ItemVO> getAllItemsWithTaxonomy(String group)
		{
		Query query = entityManager.createNativeQuery(CommonShareNativeQueries.GET_ITEMS);
		query.setParameter("groupName", group);
		List items = query.getResultList();

		return items;
		}
	
	public Item addItem(ItemVO itemVO, String groupName) throws CSBusinessException
		{
		entityManager.getTransaction().begin();
		GroupDAO groupDAO = new GroupDAO(entityManager);
		Group group = groupDAO.getGroup(groupName);
		if(group == null)
			{
			// This scenario must not occur.
			entityManager.getTransaction().rollback();
			throw new CSBusinessException("itemmanagement.error.invalidgroup");
			}
		
		TaxonomyDAO taxonomyDAO = new TaxonomyDAO();
		Taxonomy taxonomy = taxonomyDAO.getTaxonomyById(itemVO.getTaxonomy());
		if(taxonomy == null)
			{
			entityManager.getTransaction().rollback();
			throw new CSBusinessException("itemmanagement.error.taxonomy-doesnt-exist");
			}
		
		Item item = new Item();
		item.setUserGroup(group);
		item.setTaxonomy(taxonomy);
		item.setName(itemVO.getName());
		item.setDescription(itemVO.getDescription());
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		return item;
		}
	}

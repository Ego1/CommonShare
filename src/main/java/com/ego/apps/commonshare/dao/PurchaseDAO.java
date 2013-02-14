package com.ego.apps.commonshare.dao;

import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.Purchase;

public class PurchaseDAO extends BaseDAO
	{
	public void addPurchase(Purchase purchase)
		{
		// Step 1: Get the group object
		GroupDAO groupDAO = new GroupDAO();
		Group group = groupDAO.getGroup(purchase.getUserGroup().getId());
		purchase.setUserGroup(group);
		// Step 2: Get the item object
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.getItem(purchase.getItem().getId());
		purchase.setItem(item);
		// Step 3: Persist
		entityManager.getTransaction().begin();
		entityManager.persist(purchase);
		entityManager.getTransaction().commit();
		}
	}

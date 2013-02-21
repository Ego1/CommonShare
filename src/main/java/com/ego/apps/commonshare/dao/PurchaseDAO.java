package com.ego.apps.commonshare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.Purchase;

public class PurchaseDAO extends BaseDAO
	{
	public PurchaseDAO()
		{
		super();
		}

	public PurchaseDAO(EntityManager entityManager)
		{
		super(entityManager);
		}

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

	@SuppressWarnings("unchecked")
	public List<Purchase> getPurchaseForGroup(String groupName)
		{
		List<Purchase> purchases = new ArrayList<Purchase>();
		Query query = entityManager.createNamedQuery("GET_ALL_PURCHASES_FOR_GROUP");
		query.setParameter("groupName", groupName);
		purchases = query.getResultList();
		return purchases;
		}

	@SuppressWarnings("unchecked")
	public List<Purchase> getPurchasesSinceLastCalculation(String groupName)
		{
		CalculationsDAO calculationsDAO = new CalculationsDAO(entityManager);
		int lastPurchaseId = calculationsDAO.getLastCalculationId(groupName);
		Query query = entityManager.createNamedQuery("GET_PURCHASES_FOR_GROUP_AFTER_PURCHASE_ID");
		query.setParameter("groupName", groupName);
		query.setParameter("purchaseId", lastPurchaseId);
		List<Purchase> purchases = query.getResultList();
		return purchases;
		}
	}

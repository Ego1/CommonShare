package com.ego.apps.commonshare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ego.apps.commonshare.dao.entities.Purchase;

public class CalculationsDAO extends BaseDAO
	{
	public CalculationsDAO()
		{
		super();
		}

	public CalculationsDAO(EntityManager entityManager)
		{
		super(entityManager);
		}

	/**
	 * Finds out the last calculation's purchase id for given group. This will help the calculation module to perform
	 * calculation after this id.
	 * 
	 * @param groupName
	 *            The group name of the group for which this id needs to be returned.
	 * @return The purchase id that was last calculation for a given group. Returns -1 if the purchase id could not be
	 *         found. i.e. No calculation was made for this group.
	 */
	public int getLastCalculationId(String groupName)
		{
		Query query = entityManager.createNamedQuery("GET_LAST_CALCULATION_ID");
		query.setParameter("groupName", groupName);
		Integer lastCalculationId = (Integer) query.getSingleResult();
		if (lastCalculationId == null)
			{
			return -1;
			}
		else
			{
			return lastCalculationId;
			}
		}
	
	public void calculatePurchaseSinceLastPurchase(String groupName)
		{
		PurchaseDAO purchaseDAO = new PurchaseDAO(entityManager);
		List<Purchase> purchasesToBeCalculated = purchaseDAO.getPurchasesSinceLastCalculation(groupName);
		// Perform the purchase calculation here
		}
	}

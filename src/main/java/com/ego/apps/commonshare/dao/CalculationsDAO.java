package com.ego.apps.commonshare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ego.apps.commonshare.actions.vo.CalculationVO;
import com.ego.apps.commonshare.dao.entities.Calculation;
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
	 * Adds a calculation into database.
	 * 
	 * @param calculation
	 *            The calculation object that needs to be added into database. This same object will be updated by the
	 *            calculation id.
	 */
	public void addCalculation(Calculation calculation)
		{
		entityManager.getTransaction().begin();
		entityManager.persist(calculation);
		entityManager.getTransaction().commit();
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

	@SuppressWarnings("unchecked")
	public List<Calculation> getAllCalculations(String groupName)
		{
		Query query = entityManager.createNamedQuery("GET_ALL_CALCULATIONS_FOR_GROUP");
		query.setParameter("groupName", groupName);
		List<Calculation> calculations = query.getResultList();
		return calculations;
		}

	/**
	 * Returns a calculation's information based on its id.
	 * 
	 * @param calculationId
	 *            The calculation id for which the calculation information needs to be returned.
	 * @param groupName
	 *            The name of group to which the user belongs. This is for authorization purposes. *
	 * @return A CalculationVO which contains both a Calculation object for this calculation and a list of purchases
	 *         that were calculated as a part of this calculation.
	 */
	@SuppressWarnings("unchecked")
	public CalculationVO getCalculationPurchases(int calculationId, String groupName)
		{
		CalculationVO calculationVO = new CalculationVO();
		List<Calculation> calculations = getAllCalculations(groupName);
		// The following variables keep track of the purchase ids between which we have our purchases.
		int lastToLastPurchaseCalculatedId = -1;	// Variable to hold the last - 1 calculated purchase id.
		int lastPurchaseCalculatedId = -1;		// Variable to hold the last calculated purchase id of current calculation object
		for(Calculation calculation: calculations)
			{
			if(calculation.getId() == calculationId)
				{
				// The calculation required is found.
				lastPurchaseCalculatedId = calculation.getLastPurchase().getId();
				calculationVO.setCalculation(calculation);
				continue;
				}
			lastToLastPurchaseCalculatedId = calculation.getLastPurchase().getId();
			}
		// Now we have last purchase id that our required calculation had and its previous calculation had. 
		// So our purchases lie in between of these purchase ids.
		if(lastPurchaseCalculatedId == -1)
			{
			// The required calculation was not found as the value of this variable hasn't changed.
			return null;
			}
		Query query = entityManager.createNamedQuery("GET_PURCHASES_BETWEEN_IDS");
		query.setParameter("groupName", groupName);
		query.setParameter("oldPurchaseId", lastToLastPurchaseCalculatedId);
		query.setParameter("newPurchaseId", lastPurchaseCalculatedId);
		List<Purchase> purchases = (List<Purchase>)query.getResultList();
		calculationVO.setPurchases(purchases);
		return calculationVO;
		}
	}

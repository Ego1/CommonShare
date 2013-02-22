package com.ego.apps.commonshare.util;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ego.apps.commonshare.dao.CalculationsDAO;
import com.ego.apps.commonshare.dao.GroupDAO;
import com.ego.apps.commonshare.dao.PurchaseDAO;
import com.ego.apps.commonshare.dao.entities.Calculation;
import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.Purchase;
import com.ego.apps.commonshare.dao.entities.User;

public class CalculationsUtils
	{
	public static String getSpreadsFromMap(Map<Integer, Float> paymentSpread)
		{
		StringBuilder builder = new StringBuilder();
		for (int userId : paymentSpread.keySet())
			{
			builder.append(userId);
			builder.append(":");
			builder.append(paymentSpread.get(userId));
			builder.append(",");
			}
		return builder.substring(0, builder.length() - 1);
		}

	/**
	 * Performs calculation for group after last purchase calculated. So the last purchase is not included in the
	 * calculation.
	 * 
	 * @param groupName
	 *            The name of the group.
	 * @param description
	 *            The description of calculation that needs to be put in database.
	 * @return The Calculation object that was put into the database.
	 */
	public static Calculation performCalculation(String groupName, String description)
		{
		//Collect all the users in a usermap.
		GroupDAO groupDAO = new GroupDAO();
		List<User> users = groupDAO.getAllUsersInGroup(groupName);
		Group userGroup = groupDAO.getGroup(groupName);
		Map<Integer, Float> paymentSpread = new HashMap<Integer, Float>();
		Map<Integer, Float> shareSpread = new HashMap<Integer, Float>();
		for (User user : users)
			{
			if (user.isDeleted())
				{
				// The user is deleted. We will not consider him in calculations.
				continue;
				}
			paymentSpread.put(user.getId(), 0.0f);
			shareSpread.put(user.getId(), 0.0f);
			}
		int numberOfMembersInCalculation = paymentSpread.keySet().size();

		// Get all the purchases that need to be calculated.
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		List<Purchase> purchases = purchaseDAO.getPurchasesSinceLastCalculation(groupName);

		// Perform calculation
		for (Purchase purchase : purchases)
			{
			String[] paidBy = purchase.getPaidBy().split(",");

			// Calculate payments made
			float totalPaymentMade = 0f;
			for (String paidByItem : paidBy)
				{
				if (StringUtils.isEmpty(paidByItem))
					{
					// There is no information available.
					continue;
					}
				String[] paymentDetail = paidByItem.split(":");
				float userPaidTotal = paymentSpread.get(Integer.parseInt(paymentDetail[0]));
				float userPaidAmount = Float.parseFloat(paymentDetail[1]);
				userPaidTotal = userPaidTotal + userPaidAmount;
				paymentSpread.put(Integer.parseInt(paymentDetail[0]), userPaidTotal);
				totalPaymentMade += userPaidAmount;
				}

			// Calculate Shares
			String[] excludedPersonIds = purchase.getExcludedPersons().split(",");
			Set<Integer> excludedMembers = new HashSet<Integer>();
			for (String excludedPersonId : excludedPersonIds)
				{
				if (StringUtils.isEmpty(excludedPersonId))
					{
					continue;
					}
				excludedMembers.add(Integer.parseInt(excludedPersonId.trim()));
				}
			int numberOfMembersToSpreadAmong = numberOfMembersInCalculation - excludedMembers.size();
			float equalShare = totalPaymentMade / numberOfMembersToSpreadAmong;

			// Add the equal share amount among all the members who are included in share
			for (User user : users)
				{
				if (excludedMembers.contains(user.getId()))
					{
					// The member should be excluded.
					continue;
					}
				float usersShare = shareSpread.get(user.getId());
				usersShare += equalShare;
				shareSpread.put(user.getId(), usersShare);
				}
			}
		Calculation calculation = new Calculation();
		calculation.setCalculationDate(new Date());
		calculation.setDescription(description);
		calculation.setLastPurchase(purchases.get(purchases.size() - 1));
		calculation.setUserGroup(userGroup);
		calculation.setAmountPaid(getSpreadsFromMap(paymentSpread));
		calculation.setAmountShare(getSpreadsFromMap(shareSpread));

		CalculationsDAO calculationsDAO = new CalculationsDAO();
		calculationsDAO.addCalculation(calculation);
		return calculation;
		}
	}

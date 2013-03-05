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
import com.ego.apps.commonshare.exceptions.CSBusinessException;

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
	 * @throws CSBusinessException
	 */
	public static Calculation performCalculation(String groupName, String description) throws CSBusinessException
		{
		//Collect all the users in a usermap.
		GroupDAO groupDAO = new GroupDAO();
		Group userGroup = groupDAO.getGroup(groupName);
		if (userGroup == null)
			{
			// The mentioned usergroup doesn't exist.
			throw new CSBusinessException("User group mentioned does not exist.");
			}

		// Declaring spread maps
		Map<Integer, Float> paymentSpread = new HashMap<Integer, Float>();
		Map<Integer, Float> shareSpread = new HashMap<Integer, Float>();

		// Set up the spread maps. If a user is not present in spread map, we do not include him in our calculations. This happens when he is disabled (soft deleted).
		List<User> users = groupDAO.getAllUsersInGroup(groupName);
		for (User user : users)
			{
			if (user.isDeleted())
				{
				// The user is deleted. We will not consider him in calculations.
				continue;
				}
			// TODO: Check the date of user creation and deletion when working on purchases.
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
			float totalPaymentMade = 0f;

			// Calculate payments made
			Map<Integer, Float> purchaseMap = getSpread(purchase.getPaidBy());
			for (int userId : purchaseMap.keySet())
				{
				float userAmount = purchaseMap.get(userId);
				totalPaymentMade += userAmount;
				// Update the user's payment spread.
				Float userPaidTillNow = paymentSpread.get(userId);
				if (userPaidTillNow == null)
					{
					// The payment spread does not host this user. It is a problem.
					throw new CSBusinessException("We have encountered a problem. We have a user who is not allowed in calculations, but is still available in purchases. His userId is: " + userId);
					}
				paymentSpread.put(userId, userPaidTillNow + userAmount);
				}

			// Calculate Shares based on the purchase calculated.
			Set<Integer> excludedMembers = getExclusions(purchase.getExcludedPersons());
			int numberOfMembersToSpreadAmong = numberOfMembersInCalculation - excludedMembers.size();
			float equalShare = totalPaymentMade / numberOfMembersToSpreadAmong;
			// Add the equal share amount among all the members who are included in share
			for (int userId : shareSpread.keySet())
				{
				if (excludedMembers.contains(userId))
					{
					// The member should be excluded.
					continue;
					}
				float usersShare = shareSpread.get(userId);
				usersShare += equalShare;
				shareSpread.put(userId, usersShare);
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

	/**
	 * Takes payment spread as string in format "userId:amount,userId:amount,...". The spread is string splitted and
	 * then assigned into a map of userId vs Amount. This map is returned.
	 * 
	 * @param paymentSpread
	 *            The payment spread in string format mentioned in method description.
	 * @return The userId vs amount map as mentioned in the method description.
	 */
	public static Map<Integer, Float> getSpread(String paymentSpread)
		{
		// spreadMap keeps userId vs userSpread information.
		Map<Integer, Float> spreadMap = new HashMap<Integer, Float>();
		String[] paidBy = paymentSpread.split(",");

		for (String paidByItem : paidBy)
			{
			if (StringUtils.isEmpty(paidByItem))
				{
				// There is no information available.
				continue;
				}
			String[] paymentDetail = paidByItem.split(":");
			int userId = Integer.parseInt(paymentDetail[0].trim());
			float userSpread = Float.parseFloat(paymentDetail[1].trim());
			spreadMap.put(userId, userSpread);
			}
		return spreadMap;
		}

	/**
	 * Takes excluded user Ids as a comma separated list in format "userId,userId,userId,..."
	 * 
	 * The method then splits the string and returns as a list of userIds.
	 * 
	 * @param exclusionString
	 *            The exclusion string as described above.
	 * @return The hash set of userIds.
	 */
	public static Set<Integer> getExclusions(String exclusionString)
		{
		Set<Integer> excludedUserIds = new HashSet<Integer>();
		String[] excludedUsers = exclusionString.split(",");
		for (String excludedUser : excludedUsers)
			{
			if (!StringUtils.isEmpty(excludedUser))
				{
				excludedUserIds.add(Integer.parseInt(excludedUser.trim()));
				}
			}
		return excludedUserIds;
		}
	}

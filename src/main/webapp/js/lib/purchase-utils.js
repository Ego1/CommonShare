function PurchaseUtils()
{
	/**
	 * Method fills up a given table with purchases. 
	 * 
	 * userMap - The HashMap object with user objects.
	 * purchases - The array with purchases information.
	 * tablebodyname - The name of table body where the purchases information will be displayed.
	 * 
	 * It returns the count of the purchases.
	 */
	this.setupPurchasesInTable = function(userMap, purchases, tablebodyname)
	{
		// Setup variables that shall be used here.
		var spreadUtils = new SpreadUtils();
		// Iterate over the purchases and push them into the table.
		/*
		 * <tr> <th>Purchase Date</th> <th>Item</th> <th>Paid By</th> <th>Exclusions</th>
		 * <th>Comments</th> <th>Actions</th> </tr>
		 */
		for ( var ctr = 0; ctr < purchases.length; ctr++) {
			var purchase = purchases[ctr];
			var newRow = "<tr>";
			// Purchase Date
			newRow = newRow + "<td>" + purchase.purchaseDate + "</td>";
			// Item Name
			newRow = newRow + "<td>" + purchase.itemName + "</td>";
			// Spread
			var paymentSpreadStr = "";
			var userSpreads = spreadUtils.getSpread(purchase.paymentSpread);
			for(var userSpreadCtr = 0; userSpreadCtr < userSpreads.length; userSpreadCtr++)
				{
				var spreadObj = userSpreads[userSpreadCtr];
				paymentSpreadStr = paymentSpreadStr + "<br/>" + userMap.get(parseInt(spreadObj.id)).name + ": " + spreadObj.amount;
				}
			paymentSpreadStr = paymentSpreadStr.substring(5);
			newRow = newRow + "<td>" + paymentSpreadStr + "</td>";
			// Exclusions
			var excludedPersonsString = "";
			var excludedPersonIds = spreadUtils.getExclusions(purchase.excludePersons);
			if(excludedPersonIds == "")
				{
				// No use is excluded
				}
			else
				{
				for(var exclusionCtr = 0; exclusionCtr < excludedPersonIds.length; exclusionCtr++)
					{
					excludedPersonsString = excludedPersonsString + "<br/>" + userMap.get(parseInt(excludedPersonIds[exclusionCtr])).name;
					}
				excludedPersonsString = excludedPersonsString.substring(5);
				}
			
			newRow = newRow + "<td>" + excludedPersonsString + "</td>";
			// Comments
			newRow = newRow + "<td>" + purchase.comment + "</td>";

			newRow = newRow + "</tr>";
			$("#"+tablebodyname).append(newRow);
		}
		return ctr;
	};
}
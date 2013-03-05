$(document).ready(initShowCalculations);
/* ********************************* Global Variables ********************************** */
var userMap;
/* ********************************* Methods ******************************************* */
function initShowCalculations()
{
	// Create required objects
	var spreadUtils = new SpreadUtils();
	userMap = new HashMap();
	
	// Put the users in hashmap based on their ids.
	for ( var ctr = 0; ctr < users.length; ctr++) 
	{
		var user = users[ctr];
		userMap.put(user.id, user);
	}
	
	// Iterate over the purchases and push them into the table.
	/*
	 * <tr> <th>Calculation Date</th> <th>Description</th> <th>Amount Paid</th> <th>Amount Shared</th>
	 * <th>Actions</th> </tr>
	 */
	for(var calculationCtr = 0; calculationCtr < calculations.length; calculationCtr++)
		{
		var rowString = "<tr>";
		rowString = rowString + "<td>" + calculations[calculationCtr].calculationDate + "</td>";
		rowString = rowString + "<td>" + calculations[calculationCtr].description + "</td>";
		// Create amount paid string
		var amtPaidStr = "";
		if(calculations[calculationCtr].amountPaid == "")
			{
			// The calculations is not mentioned.
			}
		else
			{
			var amtPaidObjects = spreadUtils.getSpread(calculations[calculationCtr].amountPaid);
			for(var amtPaidObjectCtr = 0; amtPaidObjectCtr < amtPaidObjects.length; amtPaidObjectCtr++)
				{
				amtPaidStr = amtPaidStr + "<br/>" + userMap.get(parseInt(amtPaidObjects[amtPaidObjectCtr].id)).name + " : " + amtPaidObjects[amtPaidObjectCtr].amount;
				}
			amtPaidStr = amtPaidStr.substring(5);
			}
		rowString = rowString + "<td>" + amtPaidStr + "</td>";
		// Create share amount string
		var amtShareStr = "";
		if(calculations[calculationCtr].amountShare == "")
			{
			// The calculations is not mentioned
			}
		else
			{
			var amtShareObjects = spreadUtils.getSpread(calculations[calculationCtr].amountShare);
			for(var amtShareObjectCtr = 0; amtShareObjectCtr < amtShareObjects.length; amtShareObjectCtr++)
				{
				amtShareStr = amtShareStr + "<br/>" + userMap.get(parseInt(amtShareObjects[amtShareObjectCtr].id)).name + " : " + amtShareObjects[amtShareObjectCtr].amount;
				}
			amtShareStr = amtShareStr.substring(5);
			}
		rowString = rowString + "<td>" + amtShareStr + "</td>";
		rowString = rowString + "<td><a href=\"" + viewCalculationURL + "?id=" + calculations[calculationCtr].id + "\">View Calculation</a></td>";
		rowString = rowString + "</tr>";
		
		$("#calculationTableBody").append(rowString);
		}
}
$(document).ready(initShowPurchases);
/* ********************************** Global Variables *********************************** */
var messages = new Messages();
/* ********************************** Methods declaration ********************************* */
function initShowPurchases() {
	// Setting up the widgets
	$("button#performCalculationsButton").hide();
	$("button#performCalculationsButton").click(calculateShare);
	
	
	// Initialize the hash maps.
	var userMap = new HashMap();
	// Put the users in hashmap based on their ids.
	for ( var ctr = 0; ctr < users.length; ctr++) {
		var user = users[ctr];
		userMap.put(user.id, user);
	}
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
		var allPaymentSpreadById = purchase.paymentSpread;
		var perPersonPaymentSpreads = allPaymentSpreadById.split(",");
		for(var spreadCtr = 0; spreadCtr < perPersonPaymentSpreads.length; spreadCtr++)
			{
			var perPersonPaymentSpread = perPersonPaymentSpreads[spreadCtr];
			if(perPersonPaymentSpread == "")
				{
				// No payment spread was specified.
				continue;
				}
			var spread = perPersonPaymentSpread.split(":");
			paymentSpreadStr = paymentSpreadStr + "<br/>" + userMap.get(parseInt(spread[0])).name + ": " + spread[1];
			}
		paymentSpreadStr = paymentSpreadStr.substring(5);
		newRow = newRow + "<td>" + paymentSpreadStr + "</td>";
		// Exclusions
		var excludedPersonsIdString = purchase.excludePersons;
		var excludedPersonsString = "";
		if(excludedPersonsIdString.trim() == "")
			{
			// There is no value mentioned.
			}
		else
			{
			var excludedPersons = excludedPersonsIdString.split(",");
			for(var exclusionCtr = 0; exclusionCtr < excludedPersons.length; exclusionCtr++)
				{
				excludedPersonsString = excludedPersonsString + "<br/>" + userMap.get(parseInt(excludedPersons[exclusionCtr])).name;
				}
			excludedPersonsString = excludedPersonsString.substring(5);
			}
		newRow = newRow + "<td>" + excludedPersonsString + "</td>";
		// Comments
		newRow = newRow + "<td>" + purchase.comment + "</td>";

		newRow = newRow + "</tr>";
		$("#purchasesTable").append(newRow);
	}
	if(ctr > 0)
		{
		$("button#performCalculationsButton").show();
		}
}

function calculateShare()
	{
	var description = prompt("Please enter a description for the calculation and press OK.\nPress cancel to cancel the calculation.");
	$("input#description").val(description);
	if (description!=null && description!="")
		{
		$.post(calculateShareURL, $("form#calculatePurchase").serialize(),calculationSuccess).error(calculationFailure);
		}	
	}

function calculationSuccess(data)
	{
	messages.clearErrorMessage();
	messages.clearMessages();
	if(data.result == "true" || data.result == true)
		{
		// Purchase calculation was successful.
		messages.addSuccessMessage(data.message);
		}
	else
		{
		// Purchase calculation failed.
		messages.addErrorMessage(data.message);
		}
	}

function calculationFailure(data)
	{
	messages.addErrorMessage("We have ancountered a problem in calculating your purchases. Please try again after refreshing the page.");
	}
$(document).ready(initShowACalculation);

function initShowACalculation()
{
	// Initialize the hash maps.
	var userMap = new HashMap();
	// Put the users in hashmap based on their ids.
	for ( var ctr = 0; ctr < users.length; ctr++) {
		var user = users[ctr];
		userMap.put(user.id, user);
	}
	
	// Setup data in the calculation information.
	$("div#calculationdate").html(calculation.calculationDate);
	$("div#calculationdescription").html(calculation.description);
	
	var spreadUtils = new SpreadUtils();
	
	var paymentSpread = spreadUtils.getSpread(calculation.amountPaid);
	var payementStr = "";
	for(var paymentCtr = 0; paymentCtr < paymentSpread.length; paymentCtr++)
		{
		payementStr = payementStr + "<br/>" + userMap.get(parseInt(paymentSpread[paymentCtr].id)).name + " : " + paymentSpread[paymentCtr].amount;
		}
	payementStr = payementStr.substring(5);
	$("div#amountpaid").html(payementStr);
	
	var shareSpread = spreadUtils.getSpread(calculation.amountShare);
	var shareStr = "";
	for(var shareCtr = 0; shareCtr < shareSpread.length; shareCtr++)
		{
		shareStr = shareStr + "<br/>" + userMap.get(parseInt(shareSpread[shareCtr].id)).name + " : " + shareSpread[shareCtr].amount;
		}
	shareStr = shareStr.substring(5);
	$("div#amountshared").html(shareStr);
	
	// Setup all the purchase information in the table.
	var purchaseUtils = new PurchaseUtils();
	purchaseUtils.setupPurchasesInTable(userMap, purchases, "purchaseTableBody");
}
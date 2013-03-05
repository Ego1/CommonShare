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
	
	// Setup all the purchase information in the table.
	var purchaseUtils = new PurchaseUtils();
	var ctr = purchaseUtils.setupPurchasesInTable(userMap, purchases, "purchasesTable");
	if(ctr > 0)
		{
		// If there are minimum 1 purchase, we show the calculation button.
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
	alert("Calculation was successful.");
	messages.clearErrorMessage();
	messages.clearMessages();
	if(data.result == "true" || data.result == true)
		{
		// Purchase calculation was successful.
		messages.addSuccessMessage(data.message);
		$("tbody#purchaseTableBody").html("");
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
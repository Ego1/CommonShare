$(document).ready(initAddPurchase);

function initAddPurchase() {
	// Add date picker control.
	$("input#tbpurchasedate").datepicker({
		changeMonth : true,
		changeYear : true
	});

	// Add other users to exclusion and payment list.
	var exclusionCheckboxHTML = "";
	var paymentTextboxHTML = "";
	for ( var counter = 0; counter < users.length; counter++) {
		if (users[counter].active == true && users[counter].deleted == false) {
			var name = users[counter].name;
			var id = users[counter].id;

			// Add exclusion checkbox
			exclusionCheckboxHTML += "<p><label for=\"excludeFromShare" + id + "\">"
					+ name + "</label> <input type=\"checkbox\" id=\"excludeFromShare"
					+ id + "\" name=\"excludeFromShare" + id
					+ "\" class=\"formcb\"/></p>";

			// Add payment textbox
			paymentTextboxHTML += "<p><label for=\"paymentSpread"
					+ id
					+ "\">"
					+ name
					+ "</label><input type=\"text\" class=\"textinput\" id=\"paymentSpread"
					+ id + "\" name=\"paymentSpread" + id + "\"/> </p>";
		}
	}
	$("div#usersExclusionDiv").append(
			"<div><span class=\"head\">Check users who are excluded</span><br/>"
					+ exclusionCheckboxHTML + "</div>");
	$("div#paymentDiv").append(
			"<div><span class=\"head\">Type amount paid by every user</span>"
					+ paymentTextboxHTML + "</div>");
	
	// Attach blur event to the spread text boxes.
	// Attach blur events to all the spread text boxes.
	var paymentSpread = $("[name^=paymentSpread]");
	for(var ctr = 0; ctr < paymentSpread.length; ctr++)
	{
		var id = paymentSpread[ctr].id;
		$("input#"+id).keydown(allowOnlyNumbers);
		$("input#"+id).keyup(calculateSpread);
	}
	
	// Add event listener for submit button.
	$("button#submit").click(validateAndSubmit);
}

/**
 * Invoked when "Submit" button is clicked.
 * 
 * Its task is to perform validations and the submit the form.
 * 
 */
function validateAndSubmit(event)
{
	var errors = new Array();
	// Test if all the checkboxes are checked.
	var checkBoxes = $("[name^=excludeFromShare]");
	var allChecked = true;
	for(var ctr = 0; ctr < checkBoxes.length; ctr++)
	{
		var id = checkBoxes[ctr].id;
		if($("input#"+id).prop('checked') == false)
		{
		allChecked = false;
		}
	}
	if(allChecked)
	{
		errors.push("You cannot exclude all the members from share. Some one has to be available.");
	}
	
	if(errors.length != 0)
	{
		event.preventDefault();
	}
}


/**
 * Invoked on key down event of the payment spread text box.
 * 
 *  It will filter out the non digit key strokes.
 */
function allowOnlyNumbers(event)
{
	if((event.which >=96 && event.which <=105) 			// Key pressed through num pad
			|| (event.which >=48 && event.which <=57) 	// Key pressed through normal keyboard numbers
			|| event.which == 9							// Tab key
			|| event.which == 13						// Enter key
			)
		{
		// User typed a digit
		}
	else
		{
		// User typed something else. We need to ignore it.
		event.preventDefault();
		}
}


/**
 * Invoked on the keyup event of payment spread text box.
 * 
 * On every blur it calculates the sum of the amount and pushes it into the amount div.
 * If an amount entered is invalid e.g. it contains characters other than digits, that amount is removed from text box.
 */
function calculateSpread(event)
{	
	// Calculate total.
	// Get all the spreads.
	var spreads = $("[name^=paymentSpread]");
	var total = 0;
	for(var ctr = 0; ctr < spreads.length; ctr++)
	{
		var spreadtb = spreads[ctr];
		if($.isNumeric(spreadtb.value))
		{
			var value = parseInt(spreadtb.value);
			total = total + value;
		}
		else
		{
			var id = spreadtb.id;
			$("input#"+id).val("");
		}
	}
	
	// Set the total in span.
	$("div#totalAmount").html(total);
}




















function submitForm() {
	// To manipulate share
	var notSharedHidden = document.getElementById("notSharedHidden");
	notSharedHidden.value = "";
	var notSharedCheckBoxes = document.getElementsByName("notShared");
	for ( var i = 0; i < notSharedCheckBoxes.length; i++) {
		var notSharedCB = notSharedCheckBoxes[i];
		if (notSharedCB.checked) {
			notSharedHidden.value += notSharedCB.value + ";";
		}
	}
	notSharedHidden.value = notSharedHidden.value.substr(0,
			notSharedHidden.value.length - 1);

	// To manipulate payments
	var paymentHidden = document.getElementById("paymentHidden");
	paymentHidden.value = "";
	var paymentTextBoxes = document.getElementsByName("payments");
	for ( var paymentCounter = 0; paymentCounter < paymentTextBoxes.length; paymentCounter++) {
		var paymentTextBox = paymentTextBoxes[paymentCounter];
		if (paymentTextBox.value != "") {
			paymentHidden.value += paymentTextBox.id + ":"
					+ paymentTextBox.value + ";";
		}
	}
	paymentHidden.value = paymentHidden.value.substr(0,
			paymentHidden.value.length - 1);
	document.getElementById("form").submit();
}

function calculateAmount() {
	var hiddenAmountField = document.getElementById("amount");
	var amountSpan = document.getElementById("amountSpan");
	var totalAmount = 0;

	var amountBoxes = document.getElementsByName("payments");
	for ( var ctr = 0; ctr < amountBoxes.length; ctr++) {
		var amount = amountBoxes[ctr].value;
		totalAmount += Math.pow(amount, 1);
	}
	amountSpan.innerHTML = totalAmount;
	hiddenAmountField.value = totalAmount;
}
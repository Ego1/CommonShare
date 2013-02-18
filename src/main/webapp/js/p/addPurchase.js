$(document).ready(initAddPurchase);

/* **************** Global Variables *********************** */
var autocomplete;
var messages;
/* ********************************************************* */

function initAddPurchase() {
	// Initialize messaging
	messages = new Messages();
	
	// Add date picker control.
	$("input#tbpurchasedate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd/mm/yy"
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
					+ "</label><input type=\"text\" class=\"smallinput\" id=\"paymentSpread"
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
	
	// Setup autocomplete for items
	$('input#tbitem').focus(openTaxonomyAutocomplete);
	autocomplete = new AutoComplete();
	autocomplete.setCallbackMethod(setItem);
	var selectionList = new Array();
	for(var ctr = 0; ctr < items.length; ctr++)
		{
		selectionList.push({text:items[ctr].name, id:items[ctr].id});
		}
	autocomplete.setSelectionList(selectionList);
}

/**
 * This is invoked when the focus comes to items text box. It will open the autocomplete dialog.
 */
function openTaxonomyAutocomplete()
{
	autocomplete.open();
}

/**
 * This method is invoked by the autocomplete module.
 */
function setItem(selectedItem)
{
	$('input#tbitem').val(selectedItem.text);
	$('input#tbitemid').val(selectedItem.id);
}

/**
 * Invoked when "Submit" button is clicked.
 * 
 * Its task is to perform validations and the submit the form.
 * 
 */
function validateAndSubmit(event)
{
	// All errors will be published into this array.
	var errors = new Array();
	
	// Check if item name, and purchase date are filled.
	if($("input#tbitem").val() == "")
		{
		errors.push("We need item name to save data. Without it, we cannot proceed.");
		}
	else if($("input#tbitemid").val() == "")
		{
		// We need to check for id only if the item was selected.
		errors.push("We are sorry. An internal error has occured while saving the item. Please refresh the page and start again.");
		}
	if($("input#tbpurchasedate").val() == "")
		{
		errors.push("We need purchase date to save data. Without it, we cannot proceed.");
		}
	
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
	
	
	// If there is any error, the form will not be submitted.
	if(errors.length != 0)
	{
		messages.clearErrorMessage();
		messages.addErrorMessages(errors);
	}
	
	// Validation is complete
	// Collate all the exclude from share items.
	var checkBoxes = $("[name^=excludeFromShare]");
	var excludeFromShare = "";
	for(var ctr = 0; ctr < checkBoxes.length; ctr++)
	{
		var id = checkBoxes[ctr].id;
		if($("input#"+id).prop('checked') == true)
		{
			excludeFromShare = excludeFromShare + id.replace("excludeFromShare","") + " ";
		}
	}
	$("input#excludeFromShare").val(excludeFromShare);
	
	// Collate payment spread
	var spreads = $("[name^=paymentSpread]");
	var paymentSpread = "";
	for(var ctr = 0; ctr < spreads.length; ctr++)
	{
		var id = spreads[ctr].id;
		if($("input#"+id).val() == "" || $("input#"+id).val() == undefined)
		{
		// This user hasn't made any payment.
		continue;
		}
		paymentSpread = paymentSpread + id.replace("paymentSpread","") + ":" + $("input#"+id).val() + ",";
	}
	$("input#paymentSpread").val(paymentSpread);
	
	// Since we are making an AJAX call, we shall prevent default propagation. And then fire AJAX.
	event.preventDefault();
	if(errors.length == 0)
		{
		$.post(addPurchaseURL, $('form#addPurchaseForm').serialize(),addPurchaseSuccess).error(addPurchaseFailure).complete(addPurchaseComplete);
		}
}

function addPurchaseSuccess(data)
{
	if(data.result == "true" || data.result == true)
		{
		// Adding purchase was successful.
		messages.addSuccessMessage(data.message);
		
		}
	else
		{
		// Adding purchase failed.
		messages.addErrorMessage("Adding a purchase fialed: " + data.message);
		}
}

function addPurchaseFailure(data)
{
	messages.addErrorMessage("Error :: Adding a purchase fialed: " + data.message);
}

function addPurchaseComplete(data)
{
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
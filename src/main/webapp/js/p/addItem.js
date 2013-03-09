$(document).ready(initAddItem);

var messages;

function initAddItem()
{
	messages = new Messages();
	$('button#createItemBtn').click(createItem);
}

function createItem(event)
{
	event.preventDefault();
	messages.clearErrorMessage();
	validate();
	if(messages.getErrorMessagesLength() <= 0)
		{
		$.post(addItemURL, $('form#addItemForm').serialize(),addItemSuccess).error(addItemFailure);
		}
}

function validate()
{
	// Check all the values are present
	if($('input#name').val() == "")
		{
		messages.addErrorMessage("Please enter name of a item.");
		}
	if($('input#description').val() == "")
		{
		messages.addErrorMessage("Please enter a small description of the item.");
		}
}

function addItemFailure()
{
	
}

function addItemSuccess(data)
{
	messages.clearErrorMessage();
	messages.clearMessages();
	messages.addSuccessMessage("The item " + data.name + "was added successfully.");
	$('form#addItemForm')[0].reset();
}	
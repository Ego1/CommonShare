$(document).ready(initUserManagement);

var messages;

function initUserManagement()
{
	messages = new Messages();
	$('button#createUserBtn').click(createUser);
}

function createUser(event)
{
	event.preventDefault();
	messages.clearErrorMessage();
	validate();
	if(messages.getErrorMessagesLength() <= 0)
		{
		$('form#addUserForm').submit();
		}
	
}

function validate()
{
	// Check all the values are present
	if($('input#name').val() == "")
		{
		messages.addErrorMessage("Please enter name of a user.");
		}
	if($('input#loginName').val() == "")
		{
		messages.addErrorMessage("Please enter login name of a user.");
		}
	if($('input#email').val() == "")
		{
		messages.addErrorMessage("Please enter an email ID.");
		}
	if($('input#password').val() == $('input#retypePassword').val())
		{
		}
	else
		{
		messages.addErrorMessage("They Password and Re-type Password do not match.");
		}
}
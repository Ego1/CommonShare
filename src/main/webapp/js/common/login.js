$(document).ready(init);

function init()
{
	// Register all events
	$('a#submit').click(submitLogin);
}

function submitLogin(event)
{
	event.preventDefault();
	var loginId = $('input#loginName').val().trim();
	var password = $('input#password').val().trim();
	if(loginId.length == 0 || password.length==0)
		{
		alert(ERROR_MISSING_ID_AND_PASSWORD);
		return;
		}
	$('form#loginForm').submit();
}
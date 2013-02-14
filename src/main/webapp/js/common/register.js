$(document).ready(init);

function init()
{
	// Register all events
	$('a#submitBtn').click(submitRegister);
	$('a#cancelBtn').click(cancelRegistration);
}

function submitRegister()
{
	alert("Invoked Submit Register");
	var password = $('input#password').val().trim();
	var retypePassword = $('input#retypePassword').val().trim();
	if(password.length==0 || retypePassword.length == 0)
		{
		alert(ERROR_MISSING_ID_AND_PASSWORD);
		return;
		}
	if(password != retypePassword)
		{
		alert("The two passwords do not match");
		return;
		}
	$('form#registerForm').submit();
}

function cancelRegistration()
{
	history.go(-1);
}
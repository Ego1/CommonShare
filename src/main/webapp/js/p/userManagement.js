$(document).ready(initUserManagement);

function initUserManagement()
{
	var tabPages = new Array();
	tabPages.push("");
	$('a#createUserBtn').click(createUser);
	
	// Create tabs using jquery ui plugin.
	 $(function() {
	        $( "#tabs" ).tabs({
	            event: "mouseover"
	        });
	    });
}

function createUser()
{
	$('form#addUserForm').submit();
}
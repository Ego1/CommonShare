function Messages()
{
	this.addErrorMessage = function(message)
	{
		var messageul = $("ul#errormessageul");
		if(messageul.length == 0)
			{
			$("div#messagediv").append("<div class=\"errorMessage\"><ul id=\"errormessageul\"></ul></div>");
			messageul = $("ul#errormessageul");
			}
		messageul.append("<li>" + message + "</li>");
	};
	
	this.addErrorMessages = function(messages)
	{
		for(var ctr = 0; ctr < messages.length; ctr++)
			{
			this.addErrorMessage(messages[ctr]);
			// addErrorMessage(messages[ctr]);
			}
	};
	
	this.clearErrorMessage = function()
	{
		$("ul#errormessageul").html = "";
	};
	
	this.clearMessages = function()
	{
		$("ul#messageul").html = "";
	};
	
	this.addSuccessMessage = function()
	{
		var messageul = $("ul#messageul");
		if(messageul.length == 0)
			{
			$("div#messagediv").append("<div class=\"msg\"><ul id=\"messageul\"></ul></div>");
			messageul = $("ul#messageul");
			}
		messageul.append("<li>" + message + "</li>");
	};
}
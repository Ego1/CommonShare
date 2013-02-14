function Messages()
{
	this.addErrorMessage = function(message)
	{
		var messageul = $("ul#errormessageul");
		if(messageul == null)
			{
			$("div#messagediv").append("<ul id=\"errormessageul\"></div>");
			messageul = $("ul#errormessageul");
			}
		messageul.append("<li>" + message + "</li>");
	};
	
	this.addErrorMessages = function(messages)
	{
		for(var ctr = 0; ctr < messages.length; ctr++)
			{
			addErrorMessage(messages[ctr]);
			}
	};
	
	this.clearErrorMessage = function()
	{
		$("ul#errormessageul").html = "";
	};
}
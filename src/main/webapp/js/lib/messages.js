function Messages()
{
	var errormsgs = new Array();
	var simplemsgs = new Array();
	this.addErrorMessage = function(message)
	{
		errormsgs.push(message);
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
			}
	};
	
	this.clearErrorMessage = function()
	{
		errormsgs = new Array();
		$("ul#errormessageul").html("");
	};
	
	this.getErrorMessagesLength = function()
	{
		return errormsgs.length;
	};
	
	/* ********************************* Simple Messages ************************* */	
	this.clearMessages = function()
	{
		simplemsgs = new Array();
		$("ul#messageul").html = "";
	};
	
	this.addSuccessMessage = function(message)
	{
		simplemsgs.push(message);
		var messageul = $("ul#messageul");
		if(messageul.length == 0)
			{
			$("div#messagediv").append("<div class=\"msg\"><ul id=\"messageul\"></ul></div>");
			messageul = $("ul#messageul");
			}
		messageul.append("<li>" + message + "</li>");
	};
}
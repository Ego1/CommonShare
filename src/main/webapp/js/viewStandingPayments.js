function makePayment()
{
	var fromUserWidget = document.getElementById("fromUser");
	var fromUserSelectedOption = fromUserWidget[fromUserWidget.selectedIndex];
	var fromUserId = fromUserSelectedOption.value;
	
	var toUserWidget = document.getElementById("toUser");
	var toUserSelectedOption = toUserWidget[toUserWidget.selectedIndex];
	var toUserId = toUserSelectedOption.value;
	
	if(fromUserId == toUserId)
		{
		alert("A person can not pay to himself.");
		return;
		}
	
	var amountBox = document.getElementById("amountBox");
	if(amountBox.value <= 0)
		{
		alert("You need to pay some money.");
		return;
		}
	
	var fromUserAmount = 0;
	var toUserAmount = 0;
	for(var ctr = 0; ctr < users.length; ctr++)
		{
		if(users[ctr] == fromUserId)
			{
			fromUserAmount = amount[ctr];
			}
		if(users[ctr] == toUserId)
			{
			toUserAmount = amount[ctr];
			}
		}
	
	if(fromUserAmount < amountBox.value )
		{
		alert("You are giving more than expected amount.");
		return;
		}
	if(toUserAmount > -amountBox.value)
		{
		alert("You are getting more than expected amount.");
		return;
		}
	document.getElementById("form").submit();
}
function SpreadUtils()	{
	/**
	 * This method takes a spread string as an input and returns an array for each input set.
	 * 
	 * Input is of format "userid:amount, userid:amount, userid:amount..."
	 * 
	 * The output is of an array for each userid:amount pair. 
	 * The array records are objects {"id":the user id, "amount": the amount}.
	 * 
	 **/
	this.getSpread = function(spreadString)
	{
		var users = new Array();
		var spreads = spreadString.split(",");
		for(var spreadCtr = 0; spreadCtr < spreads.length; spreadCtr++)
			{
			var perPersonSpread = spreads[spreadCtr];
			if(perPersonSpread == "")
				{
				// No payment spread was specified.
				continue;
				}
			var spreadItems = perPersonSpread.split(":");
			users.push({"id" : spreadItems[0], "amount" : spreadItems[1]});
			}
		return users;
	};
	
	this.getExclusions = function(exclusionString)
	{
		var exclusionArr = new Array();
		var exclusions = exclusionString.split(",");
		for(var exclusionCtr = 0; exclusionCtr < exclusions.length; exclusionCtr++)
			{
			exclusionArr.push(exclusions[exclusionCtr]);
			}
		return exclusionArr;
	};
	
	this.setupUserMap = function(users)
	{
	var userMap = new HashMap();
	for(var userCtr = 0; userCtr < users.length; userCtr++)
		{
		userMap.put(users[userCtr].id, users[userCtr]);
		}
	return userMap;
	}
}
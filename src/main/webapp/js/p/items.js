$(document).ready(initItemManagement);

function initItemManagement()
{
	 // Add items in table
	setupItemsInTable();
}

function setupItemsInTable()
{
	for(var counter = 0; counter < items.length; counter++)
		{
		var item = items[counter];
		$("table#allItemsTable").append("<tr><td>"+item.id+"</td><td>"+item.name+"</td><td>"+item.description+"</td></tr>");
		}
}
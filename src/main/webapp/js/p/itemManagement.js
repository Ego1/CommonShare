$(document).ready(initItemManagement);
var autocomplete = null;
var taxonomyMap = null;
function initItemManagement()
{
	// Setup tabs
	var tabPages = new Array();
	tabPages.push("");
	
	// Create tabs using jquery ui plugin.
	 $(function() {
	        $( "#tabs" ).tabs({
	            event: "mouseover"
	        });
	    });
	
	// Register events
	$('input#taxonomy').focus(openTaxonomyAutocomplete);
	$("#createItemBtn").click(addItem);
	 
	 // Add items in table
	setupTaxonomyMap();
	setupItemsInTable();
	
	// Setup autocomplete for taxonomies
	autocomplete = new AutoComplete();
	autocomplete.setCallbackMethod(autocompleteCompleted);
	var selectionList = new Array();
	for(var ctr = 0; ctr < taxonomies.length; ctr++)
		{
		selectionList.push({text:taxonomies[ctr].name, id:taxonomies[ctr].id});
		}
	autocomplete.setSelectionList(selectionList);
}

function autocompleteCompleted(selectedItem)
{
	$('input#taxonomy').val(selectedItem.text);
	$('input#taxonomyhidden').val(selectedItem.id);
}

function openTaxonomyAutocomplete()
{
	autocomplete.open();
}

function setupItemsInTable()
{
	for(var counter = 0; counter < items.length; counter++)
		{
		var item = items[counter];
		$("table#allItemsTable").append("<tr><td>"+item.id+"</td><td>"+item.name+"</td><td>"+item.description+"</td><td>"+taxonomyMap.get(item.taxonomy).name+"</td></tr>");
		}
}

function setupTaxonomyMap()
{
	taxonomyMap = new HashMap();
	for(var counter = 0; counter < taxonomies.length; counter++)
	{
	var taxonomy = taxonomies[counter];
	taxonomyMap.put(taxonomy.id, taxonomy);
	}
}

function addItem(event)
{
	event.preventDefault();
	$.post(addItemURL, $('#addItemForm').serialize(),addItemSuccess).error(addItemFailure).complete(addItemComplete);
}

function addItemSuccess(data)
{
	if(data.result == "true")
		{
		// Adding item was successful.
		$("#allItemsTable").append("<tr><td>"+data.data.id+"</td><td>"+data.data.name+"</td><td>"+data.data.description+"</td><td>"+taxonomyMap.get(data.data.taxonomy).name+"</td></tr>");
		}
	else
		{
		// Adding item failed.
		}
}

function addItemFailure()
{
}

function addItemComplete()
{
}

/*
function initItemManagement()
{
	// Set up event listeners
	$("#createItemBtn").click(addItem);
	
	// Initialization code
	setupTaxonomyMap();
	setupItemsInTable();
	var tabPages = new Array();
	tabPages.push("");
	
	// Create tabs using jquery ui plugin.
	 $(function() {
	        $( "#tabs" ).tabs({
	            event: "mouseover"
	        });
	    });
	 
	 
}

function setupTaxonomyMap()
{
	taxonomyMap = new HashMap();
	for(var counter = 0; counter < taxonomies.length; counter++)
	{
	var taxonomy = taxonomies[counter];
	taxonomyMap.put(taxonomy.id, taxonomy);
	}
}

function addItem(event)
{
	event.preventDefault();
	$.getJSON(addItemURL, $('#addItemForm').serialize(),addItemSuccess).error(addItemFailure).complete(addItemComplete);
}

function addItemSuccess(data)
{
	if(data.result == "true")
		{
		// Adding item was successful.
		$("#allItemsTable").append("<tr><td>"+data.data.id+"</td><td>"+data.data.name+"</td><td>"+data.data.description+"</td><td>"+taxonomyMap.get(data.data.taxonomy).name+"</td></tr>");
		}
	else
		{
		// Adding item failed.
		}
	alert(data.message);
}

function addItemFailure()
{
}

function addItemComplete()
{
}
*/
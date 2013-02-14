$(document).ready(init);

// Global variables for this file.
var taxonomyMap;
var addingTaxonomyForSelect = "";
var addingTaxonomyLevel = 0;

function init()
{
	// Register all events
	$('#rootleveltaxonomy').change(selectedRootLevelTaxonomy);
	$('#midleveltaxonomy').change(selectedMidLevelTaxonomy);
	$('#addrootleveltaxonomy').click(openAddTaxonomyForm);
	$('#addmidleveltaxonomy').click(openAddTaxonomyForm);
	$('#addleafleveltaxonomy').click(openAddTaxonomyForm);
	$('#addchildforrootleveltaxonomy').click(addChildForRoot);
	$('#addchildformidleveltaxonomy').click(addChildForMidLevel);
	
	$('#addTaxonomySubmit').click(addTaxonomy);
	$('#addTaxonomyCancel').click(cancelAddTaxonomy);
	
	
	// Setting initial state
	$('#addTaxonomyForm').hide();
	
	// Operations
	collectTaxonomies();
	
	readjustSelect("rootleveltaxonomy");
	readjustSelect("midleveltaxonomy");
	readjustSelect("leafleveltaxonomy");
}

function addChildForRoot(event)
{
	event.preventDefault();
	if(addingTaxonomyForSelect!="")
		{
		alert("A process is already in progress. Please wait for it to continue. Or the add window is already open.");
		return;
		}
	if($("#rootleveltaxonomy")[0].selectedIndex == -1)
		{
		alert("You need to select at least one item and then add child for it.");
		return;
		}
	openAddTaxonomyForm(event);
}

function addChildForMidLevel(event)
{
	event.preventDefault();
	if(addingTaxonomyForSelect!="")
		{
		alert("A process is already in progress. Please wait for it to continue. Or the add window is already open.");
		return;
		}
	if($("#midleveltaxonomy")[0].selectedIndex == -1)
		{
		alert("You need to select at least one item and then add child for it.");
		return;
		}
	openAddTaxonomyForm(event);
}

function cancelAddTaxonomy()
{
	$('#addTaxonomyForm').hide();
	addingTaxonomyForSelect = "";
}

function openAddTaxonomyForm(event)
{
	if(addingTaxonomyForSelect == "")
		{
		$('#message').html("");
		event.preventDefault();
		var selectedLink = event.currentTarget.id;
		if(selectedLink == "addrootleveltaxonomy" || selectedLink == "" || selectedLink == undefined)
			{
			addingTaxonomyForSelect = 0;
			}
		else if(selectedLink == "addmidleveltaxonomy" || selectedLink == "addchildforrootleveltaxonomy")
			{
			addingTaxonomyForSelect = 1;
			}
		else if(selectedLink == "addleafleveltaxonomy" || selectedLink == "addchildformidleveltaxonomy")
			{
			addingTaxonomyForSelect = 2;
			}
		if(addingTaxonomyForSelect == 0)
			{
			$('#parent').val(0);
			}
		else if(addingTaxonomyForSelect == 1)
			{
			var selectedIndex = $("#rootleveltaxonomy")[0].selectedIndex;
			$('#parent').val($("#rootleveltaxonomy")[0].options[selectedIndex].value);
			}
		else if(addingTaxonomyForSelect == 2)
			{
			var selectedIndex = $("#midleveltaxonomy")[0].selectedIndex;
			$('#parent').val($("#midleveltaxonomy")[0].options[selectedIndex].value);
			}
		$('#name').val("");
		$('#description').val("");
		$('#addTaxonomyForm').show();
		}
	else
		{
		alert("An operation is already in progress. Please wait for a minute and try again.");
		}
}

function addTaxonomy()
{
	$('#message').html("");
	$.getJSON(addTaxonomyURL, $('#addtaxonomyform').serialize(),addTaxonomySuccess).error(addTaxonomyFailure).complete(addTaxonomyComplete);
}

function addTaxonomySuccess(data)
{
	$("#addTaxonomyForm").hide();
	if(data.result == "true")
		{
		if(addingTaxonomyForSelect == 0)
			{
			$("#rootleveltaxonomy").append("<option value='" + data.data.id + "'>" + data.data.name + "</option>" );
			readjustSelect("rootleveltaxonomy");
			}
		else if(addingTaxonomyForSelect == 1)
			{
			$("#midleveltaxonomy").append("<option value='" + data.data.id + "'>" + data.data.name + "</option>" );
			readjustSelect("midleveltaxonomy");
			}
		else if(addingTaxonomyForSelect == 2)
			{
			$("#leafleveltaxonomy").append("<option value='" + data.data.id +	"'>" + data.data.name + "</option>" );
			readjustSelect("leafleveltaxonomy");
			}
		}
	else
		{
		// The addition failed.
		}
	alert(data.message);
}

function addTaxonomyFailure(jqXHR, textStatus, errorThrown)
{
	$('#message').html("" + textStatus + ": " + errorThrown);
}

function addTaxonomyComplete()
{
	addingTaxonomyForSelect = "";
}

function collectTaxonomies()
{
	taxonomyMap = new HashMap();
	
	// Collecting all the taxonomies in a taxonomy map.
	for(var taxonomyCount = 0; taxonomyCount < taxonomies.length; taxonomyCount++)
		{
		var taxonomyJsonObject = taxonomies[taxonomyCount];
		// Putting each taxonomy JSON object into the hashmap.
		taxonomyMap.put(taxonomyJsonObject.id, taxonomyJsonObject);
		}
	
	// Re-iterating over the taxonomy list to adjust children of parent. This is necessary.
	for(var taxonomyCount = 0; taxonomyCount < taxonomies.length; taxonomyCount++)
	{
		var taxonomyJsonObject = taxonomies[taxonomyCount]; 
		// Putting each taxonomy as a child in its parent object list.
		if(taxonomyJsonObject.parent != "")
		{
			var taxonomyParent = taxonomyMap.get(taxonomyJsonObject.parent);
			if(taxonomyParent == undefined)
				{
				// It is a root level taxonomy. So skip it.
				}
			else
				{
				taxonomyParent.children.push(taxonomyJsonObject.id);
				}
		}
		else
		{
			// Inserting the taxonomy into root level select box.
			$('select#rootleveltaxonomy').append("<option value='"+taxonomyJsonObject.id+	"'>"+taxonomyJsonObject.name+"</option>" );
		}
		
	}
	readjustSelect('rootleveltaxonomy');
}

/*
 * Retrieves the taxonomy from the map based on the id provided. 
 * Using this id, all the children are found and populated in the select box(decided automatically).
 */
function fillSelectBoxWithChildren(taxonomyId)
{
	var parentTaxonomy = taxonomyMap.get(taxonomyId);
	var allChildren = getChildrenTaxonomy(taxonomyId);
	var selectBox = null;
	if(parentTaxonomy.parent == "" || parentTaxonomy.parent == undefined || parentTaxonomy.parent == null)
		{
		// This is root level taxonomy. Hence the children will go in the mid level select box.
		$("#leafleveltaxonomydiv").hide();
		selectBox = 'midleveltaxonomy';
		}
	else if(parentTaxonomy.parent.parent == "" || parentTaxonomy.parent.parent == undefined || parentTaxonomy.parent.parent == null)
		{
		// This is root level taxonomy. Hence the children will go in the mid level select box.
		selectBox = 'leafleveltaxonomy';
		}
	if(selectBox != null)
		{
		// Clean the select box we will be populating.
		$('#'+selectBox+' option').remove();
		// We need to populate this with children.
		for(var counter = 0; counter < allChildren.length; counter++)
			{
			$('#'+selectBox).append("<option value='"+allChildren[counter].id+"'>"+allChildren[counter].name+"</option>");
			}
		}
	readjustSelect(selectBox);
}

function selectedRootLevelTaxonomy()
{
	var selectedTaxonomyId = $('select#rootleveltaxonomy option:selected').val();
	if(selectedTaxonomyId == undefined)
		{
		// Nothing was selected.
		return;
		}
	fillSelectBoxWithChildren(selectedTaxonomyId);
}

function selectedMidLevelTaxonomy()
{
	$('#leafleveltaxonomydiv').hide();
	var selectedTaxonomyId = $('select#midleveltaxonomy option:selected').val();
	if(selectedTaxonomyId == undefined)
	{
	// Nothing was selected.
	return;
	}
	fillSelectBoxWithChildren(selectedTaxonomyId);
}

function getChildrenTaxonomy(parentTaxonomyId)
{
	var returnList = new Array();
	var parentTaxonomy = taxonomyMap.get(parentTaxonomyId);
	if(parentTaxonomy == undefined)
		{
		// This is a big problem and must not occur.
		}
	else
		{
		// We need to set the mid level taxonomy in it's select box.
		for(var counter = 0; counter < parentTaxonomy.children.length; counter++)
			{
			var childTaxonomyId = parentTaxonomy.children[counter];
			if(childTaxonomyId != "" && childTaxonomyId != undefined)
				{
				var childTaxonomy = taxonomyMap.get(childTaxonomyId);
				if(childTaxonomy == undefined)
					{
					// Big problem. Must not happen.	
					}
				else
					{
					returnList.push(childTaxonomy);
					}
				}
			}
		}
	return returnList;
}

function readjustSelect(selectBox)
{
	if($('#'+selectBox)[0].options.length == 0)
		{
		$('#'+selectBox+'div').hide();
		}
	else
		{
		var optionLength = $('#'+selectBox)[0].options.length;
		if(optionLength == 1)
			{
			optionLength = 2;
			}
		$('#'+selectBox)[0].size = optionLength;
		var selectBoxHeight = $('#'+selectBox)[0].size * 30; 
		$('#'+selectBox)[0].style.height = selectBoxHeight;
		$('#'+selectBox)[0].selectedIndex = -1;
		$('#'+selectBox+'div').show();
		}
//	if(selectBox == "rootleveltaxonomy")
//		{
//		readjustSelect("midleveltaxonomy");
//		}
//	else if(selectBox == "midleveltaxonomy")
//		{
//		readjustSelect("leafleveltaxonomy");
//		}
}
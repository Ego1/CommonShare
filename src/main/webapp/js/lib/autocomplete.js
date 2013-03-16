function AutoComplete()
{
	var selectionList = null;
	/*
	 * Set the selection list or the superset of list from which a user can search for items.
	 * Each item in list has 2 parts - text and id. The text and id will be returned when the value is found/selected by user.
	 */
	this.setSelectionList = function(list)
		{
			selectionList = list;
		};
	
	/*
	 * Set a callback method here. It will return the item that has been selected.
	 * The item has a 'text' and an 'id' properties. These properties can be used by the 
	 * callback method to perform required activities.
	 */
	var callbackMethod = null;
	this.setCallbackMethod = function(callbackMeth) 
		{
			callbackMethod = callbackMeth;
		};
	
	this.open = function ()
		{
			createWidgets();
		};
	
	function filterSearch()
	{
	// Obtain the word typed by user till now.
	var word =  $('input#autocompleteSearchBox').val().trim();
	
	// Clear the search list.
	clearSearchList();
	$('button#autocompleteOkBtn').attr("disabled", "disabled");
	
	// Iterate over the selected list (all the items in the list) and search for the selected word.
	for(var ctr = 0; ctr < selectionList.length; ctr++)
		{
		// Pass the sentence to the KMP search algorithm.
		KMPSearchAlgorithm(selectionList[ctr], word, addSearchedSentence);
		}
	}
	
	/*
	 * Searches for the worg in given sentence. If the word is found, the mentioned function is invoked.
	 * Note: The search is case insensitive.
	 * Sentence is passed into the mentioned method as an argument.
	 *
	 * Implement Knuth-Morris-Pratt String matching algorithm.
	 * Implementing very basic no frills brute force version as in our case both sentence and word are small.
	 * If you have a higher requirement, please override this method.
	 */
	function KMPSearchAlgorithm(searchItem, word, wordFoundFunction)
	{
		var sentence = searchItem.text.toUpperCase();
		word = word.toUpperCase();
		var firstCharOfWord = word.charAt(0);
		for(var sentenceCtr = 0; sentenceCtr < sentence.length; sentenceCtr++)
		{
			if(sentence.charAt(sentenceCtr) ==  firstCharOfWord)
			{
				// The first character of word matches with sentence.
				var match = true;
				for(var wordCtr = 1; wordCtr < word.length; wordCtr++)
				{
					if(word.charAt(wordCtr) != sentence.charAt(sentenceCtr + wordCtr))
					{
						// The sentence and word mismatch.
						match = false;
						break;
					}
				}
				if(match)
				{
					wordFoundFunction(searchItem);
					break;
				}
			}
		}
	}
	
	/*
	 * Invoked when we wish to clear the searched list and enter new searched list in it.
	 */
	function clearSearchList()
	{
		$('select#autocompleteSearchList').empty();
	}
	
	function searchedSentenceSelected()
	{
		$('input#autocompleteSearchBox').val("");
		$('input#autocompleteSearchBox').val($('select#autocompleteSearchList option:selected').text());
		$('button#autocompleteOkBtn').removeAttr("disabled"); 
	}
	
	function searchedSentenceDeselected()
	{
		// Disable the OK button. It will be enabled only when an item is selected.
		if ($('select#autocompleteSearchList option:selected') == null )
		{
			$('button#autocompleteOkBtn').attr("disabled", "disabled");
		}		
	}
	
	/*
	 * Found a sentence to be put in the search list.
	 */
	function addSearchedSentence(sentence)
	{
		$('select#autocompleteSearchList').append(new Option(sentence.text,sentence.id));
		// $('select#autocompleteSearchList').end().append("<option value=\"" + sentence.id + "\">" + sentence.text + "</option>");
	}
	
	function searchComplete()
	{
		// The search is complete. Close the widget.
		$('div#autocompleteScreenMask').hide();
		
		// Invoke the callback.
		callbackMethod({
						text: $('select#autocompleteSearchList option:selected').text(), 
						id: $('select#autocompleteSearchList option:selected').val()
						});
		
		// We are done. So we reset the selection.
		$('select#autocompleteSearchList option:selected').removeAttr("selected");
	}
	
	function cancelAutocomplete()
	{
		$('div#autocompleteScreenMask').hide();
	}
	
	// --------------------------------- Functions for widget handling ---------------------------------------------
	// The widget
	var autocompleteDivWidgets = 
	"<div class=\"autocompleteMask\" id=\"autocompleteScreenMask\">" + 
	"<div class=\"autocompleteDiv\" id=\"autocompleteDiv\">" +
	"<div class=\"autocompleteSearchTermDiv\" id=\"autocompleteSearchTermDiv\">" +
	"<label for=\"autocompleteSearchBox\">Start typing your search term</label>" +
	"<input type=\"text\" id=\"autocompleteSearchBox\"/>" +
	"<label>Select from Right and Press OK</label>" +
	"<button id=\"autocompleteOkBtn\">OK</button>" +
	"<button id=\"autocompleteCancelBtn\">Cancel</button>" +
	"</div>" +
	"<div class=\"autocompleteSearchResultsDiv\" id=\"autocompleteSearchResultsDiv\">" +
	"<select id=\"autocompleteSearchList\"multiple>" +
	"</select>" +
	"</div>" +
	"</div>" +	
	"</div>"
	;
	function createWidgets()
	{
	// Append the widget to the document body.
	$('body').append(autocompleteDivWidgets);
	
	// Load the selected items in the select list.
	for( var ctr = 0; ctr < selectionList.length; ctr++)
		{
			addSearchedSentence(selectionList[ctr]);
		}
	
	// Register the listeners
	registerListeners();
	
	// Disable the OK button. It will be enabled only when an item is selected.
	$('button#autocompleteOkBtn').attr("disabled", "disabled");
	
	// Show the widget.
	$('div#autocompleteScreenMask').show();
	}
	
	// Function to invoke listeners.
	function registerListeners()
	{
		// Register the select box click event.
		$('select#autocompleteSearchList').change(searchedSentenceSelected);
		$('select#autocompleteSearchList').blur(searchedSentenceDeselected);
		// Register the autocomplete textbox keypress event
		$('input#autocompleteSearchBox').keyup(filterSearch);
		// Register click event for the OK button
		// $('button#autocompleteOkBtn').click(searchComplete);
		$('button#autocompleteOkBtn').click(searchComplete);
		$('button#autocompleteCancelBtn').click(cancelAutocomplete);
	}
}
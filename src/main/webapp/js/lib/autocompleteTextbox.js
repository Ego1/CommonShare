function AutoCompleteTextbox()
{
	// The autocomplete list that will provide autocomplete values
	var autocompleteList;
	
	// The widget
	var autocompleteDivWidgets = 
	"<div class=\"autocompleteDiv\" id=\"autocompleteDiv\">" +
	"<div class=\"autocompleteTextboxSelectionItem\"></div>" +
	"</div>";
	this.setAutocompleteList = new function(list)
		{
		this.autocompleteList = list;
		};
	
	this.setTargetTextbox = new function(textbox)
		{
		// Registering the listener.
		$('input#'+textbox).keyup(filter);
		$('input#'+textbox).focus(textboxFocussed);
		
		// Adding the filterdiv to the textbox.
		$('input#'+textbox).append(autocompleteDivWidgets);
		};
		
	function filter()
	{
		// Obtain the word typed by user till now.
		var word =  $('input#autocompleteSearchBox').val().trim();
	}
	
	function textboxFocussed()
	{
		// Textbox is focussed. We need to attach turn on the filter div.
		
	}
}
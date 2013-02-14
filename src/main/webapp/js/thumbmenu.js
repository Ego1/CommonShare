$(document).ready(init);

function init()
{
	// Register all events
	$('a.thumbmenuitem-default').mouseover(thumb_menuitem_over);
	$('a.thumbmenuitem-hover').mouseout(thumb_menuitem_out);
}

function menuover(event)
{
	event.source.removeClass("thumbmenuitem-default");
	event.source.addClass("thumbmenuitem-hover");
}

function menuout(event)
{
	event.source.addClass("thumbmenuitem-default");
	event.source.removeClass("thumbmenuitem-hover");
}
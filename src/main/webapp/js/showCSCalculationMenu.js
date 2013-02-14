function captureComment()
{
	var hrefObject = document.getElementById("performCalculationAnchor");
	var comment = prompt("Enter a comment for this calculation.");
	hrefObject.href += "?comment=" + comment;
}
function deletePayment(id)
{
	var form = document.getElementById("form");
	form.action = "deletePurchase.action?purchaseUIVO.id=" + id;
	form.submit();
}
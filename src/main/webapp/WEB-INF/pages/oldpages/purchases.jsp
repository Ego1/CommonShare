<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>

<cspage:defaultpage title="View Purchases">
	<script type="text/javascript" src="<uiutils:contextedurl urlFragment="js/purchases.js"/>"></script>
	<div class="mainBodyDiv">
		<a href="<uiutils:contextedurl urlFragment="/p/showAddPurchases.action"/>">Add Payment details.</a><br/>
		
		Following is the payment information since last calculation.<br/> 
		<table class="purchaseDisplayTable">
			<tr>
				<th>Actions</th>
				<th>Item</th>
				<th>Date</th>
				<th>Amount</th>
				<th>Comment</th>
				<th>Not Shared</th>
				<th>Payment</th>				
			</tr>
			
			<s:iterator value="purchases">
				<tr>
					<td><a href="#" onClick="deletePayment(<s:property value="id"/>);" class="greenButton">Delete</a></td>
					<td><s:property value="item"/></td>
					<td><s:property value="date"/></td>
					<td><s:property value="amount"/></td>
					<td><s:property value="comment"/></td>
					<td><s:property value="userNameNotShared"/></td>
					<td><s:property value="userNamePayment"/></td>
				</tr>
			</s:iterator>				
		</table>
		<form id="form" method="post">
		</form>
		
	</div>
</cspage:defaultpage>
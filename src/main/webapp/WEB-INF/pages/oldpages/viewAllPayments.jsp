<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>

<cspage:defaultpage title="View Purchases">
	<div class="mainBodyDiv">
		Following is the payment information: <br/> 
		<table class="purchaseDisplayTable">
			<tr>
				<th>Date</th>
				<th>From</th>
				<th>To</th>
				<th>Amount</th>				
			</tr>
			
			<s:iterator value="payments">
				<tr>
					<td><s:property value="date"/></td>
					<td><s:property value="users[userIdFrom].name"/></td>
					<td><s:property value="users[userIdTo].name"/></td>
					<td><s:property value="amount"/></td>
				</tr>
			</s:iterator>				
		</table>
		
	</div>
</cspage:defaultpage>
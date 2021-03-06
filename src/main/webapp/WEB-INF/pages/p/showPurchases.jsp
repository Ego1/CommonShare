<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<cspage:defaultpage title="Home">
	<script type="text/javascript">
		var calculateShareURL = "${pageContext.request.contextPath}p/calculateShare.action";
	</script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Add Purchase</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay></message:messageDisplay>
			<p>
			Here we show only those purchases that have not been calculated yet. <br/>
			To view purchases that have already been calculated, please visit different section.
			</p>
			<table id="purchasesTable" class="table">
				<thead>
					<tr>
						<th>Purchase Date</th>
						<th>Item</th>
						<th>Paid By</th>
						<th>Exclusions</th>
						<th>Comments</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody id="purchaseTableBody">
				</tbody> 
			</table>
			<button id="performCalculationsButton" class="submitbutton" style ="display:none">Perform Calculations</button>
			<form id="calculatePurchase">
				<input type="hidden" name="description" id="description">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var users = 
			[
				<s:iterator value="users">
					{
					"id": <s:property value="id"/>,
					"name": "<s:property value="name"/>",
					"active": <s:property value="active"/>,
					"deleted": <s:property value="deleted"/>
					},
				</s:iterator>
				 
			];
		
		var purchases = 
			[
			 	<s:iterator value="purchases">
			 		{
			 			"id" : <s:property value="purchaseId"/>,
			 			"itemName" : "<s:property value="itemname"/>",
			 			"purchaseDate" : "<s:property value="date"/>",
			 			"paymentSpread" : "<s:property value="paymentSpread"/>",
			 			"excludePersons" : "<s:property value="excludeFromShare"/>",
			 			"comment" : "<s:property value="comment"/>"
			 		},
			 	</s:iterator>
			];
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/string-hashmap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/spread-utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/purchase-utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/p/showPurchases.js"></script>
</cspage:defaultpage>
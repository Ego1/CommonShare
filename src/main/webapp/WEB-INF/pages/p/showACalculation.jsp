<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<cspage:defaultpage title="Home">
	<script type="text/javascript">
	</script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Calculation Details</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay></message:messageDisplay>
			<div class="purchaseFormDiv">
				<p>Calculation information</p>
				<div class="formdiv">
					<div class="formitem">
						<label for="tbitem">Calculated On</label>
						<div id="calculationdate"></div>
					</div>
					<div class="formitem">
						<label for="tbpurchasedate">Description</label>
						<div id="calculationdescription"></div>
					</div>
					<div class="formitem">
						<label for="totalAmount">Amount Paid</label>
						<div id="amountpaid"></div>
					</div>
					<div class="formitem">
						<label for="tbcomment">Amount Shared</label>
						<div id="amountshared"></div>
					</div>
				</div>
			</div>	
			<p>These are the purchases which were calculated as a part of this calculation</p>
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
		
		var calculation =
			 		{
			 			"id" : <s:property value="calculation.id"/>,
			 			"description" : "<s:property value="calculation.description"/>",
			 			"calculationDate" : "<s:property value="calculation.calculationDate"/>",
			 			"amountPaid" : "<s:property value="calculation.amountPaid"/>",
			 			"amountShare" : "<s:property value="calculation.amountShare"/>"
			 		};
		
		var purchases = 
			[
			 	<s:iterator value="calculation.purchases">
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
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}css/p/showACalculation.css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/string-hashmap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/spread-utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/purchase-utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/p/showACalculation.js"></script>
</cspage:defaultpage>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<cspage:defaultpage title="Home">
	<script type="text/javascript">
		var viewCalculationURL = "${pageContext.request.contextPath}p/showACalculation.action";
	</script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">All Calculations</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay></message:messageDisplay>
			<p>These are all the calculations that have been done for your group.</p>
			<table class="table">
				<thead>
					<tr>
						<th>Calculation Date</th>
						<th>Description</th>
						<th>Amount Paid</th>
						<th>Amount Share</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody id="calculationTableBody">
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
		
		var calculations = 
			[
			 	<s:iterator value="calculations">
			 		{
			 			"id" : <s:property value="id"/>,
			 			"description" : "<s:property value="description"/>",
			 			"calculationDate" : "<s:property value="calculationDate"/>",
			 			"amountPaid" : "<s:property value="amountPaid"/>",
			 			"amountShare" : "<s:property value="amountShare"/>",
			 		},
			 	</s:iterator>
			];
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/string-hashmap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/spread-utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/p/showCalculations.js"></script>
</cspage:defaultpage>
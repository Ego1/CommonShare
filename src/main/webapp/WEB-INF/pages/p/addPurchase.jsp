<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<cspage:defaultpage title="Home">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}css/p/addPurchase.css"/>
	<script type="text/javascript">
		var addPurchaseURL = "${pageContext.request.contextPath}p/addPurchase.action";
	</script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Add Purchase</div>
		<div class="moduleBodyDiv">
			<div class="purchaseFormDiv">
				<form action="addPurchase.action" id='addPurchaseForm' method="POST">
					<div class="formdiv">
						<message:messageDisplay/>
						<div class="formitem">
							<label for="tbitem">Item</label>
							<input type="text" id="tbitem" name="purchaseUIVO.itemname"/>
							<input type="hidden" id="tbitemid" name="purchaseUIVO.itemid"/>
						</div>
						<div class="formitem">
							<label for="tbpurchasedate">Purchase Date</label>
							<input type="text" id="tbpurchasedate" name="purchaseUIVO.date"/>
						</div>
						<div class="formitem">
								<label for="totalAmount">Amount</label>
								<div id="totalAmount">0</div>
						</div>
						<div class="formitem">
							<label for="tbcomment">Comment</label>
							<input type="text" class="textinput" id="tbcomment" name="purchaseUIVO.comment"/>
						</div>
						<div class="formitem">
							<label>Not Shared</label>
							<input type="hidden" id="excludeFromShare" name="purchaseUIVO.excludeFromShare" value=""/>
							<div id="usersExclusionDiv" class="singleRowSet"></div>
						</div>
						<div class="formitem">
							<label>Payments</label>
							<input type="hidden" id="paymentSpread" name="purchaseUIVO.paymentSpread" value=""/>
							<div id="paymentDiv" class="singleRowSet"></div>
						</div>
						<button id="submit" class="submitbutton">Add Purchase</button>
					</div>
				</form>
			</div>	
			<div class="addedPurchasesDiv" id="purchasesAddedSummary">
				<table id="purchasesTable">
					<thead>
						<tr>
							<th>Purchase Date</th>
							<th>Item</th>
							<th>Paid By</th>
							<th>Exclusions</th>
						</tr>
					</thead>
					<tbody>
					</tbody> 
				</table>
			</div>		
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
		
		var items = 
			[
				<s:iterator value="items">
					{
					"id": <s:property value="id"/>,
					"name": "<s:property value="name"/>"
					},
				</s:iterator>
			];
	</script>
	<link href="${pageContext.request.contextPath}css/lib/autocomplete.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/autocomplete.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/lib/string-hashmap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/p/addPurchase.js"></script>
</cspage:defaultpage>
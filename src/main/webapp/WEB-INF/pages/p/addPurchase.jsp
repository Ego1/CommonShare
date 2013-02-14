<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<cspage:defaultpage title="Home">
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Add Purchase</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay/>
			<form action="addPurchase.action" id='form' method="POST">
				<div class="formdiv">
					<div class="formitem">
						<label for="name">Name</label>
						<input type="text" id="name" name="itemVO.name"/>
					</div>		
					<div class="formitem">
						<label for="tbitem">Item</label>
						<input type="text" id="tbitem" name="purchaseUIVO.item"/>
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
						<div id="usersExclusionDiv" class="singleRowSet"></div>
					</div>
					<div class="formitem">
						<label>Payments</label>
						<div id="paymentDiv" class="singleRowSet"></div>
					</div>
					<button id="submit" class="submitbutton"id="submit">Add Purchase</button>
				</div>
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
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/datePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/p/addPurchase.js"></script>
</cspage:defaultpage>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="msg" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>

<cspage:defaultpage title="View Purchases">
	<script type="text/javascript" src="<uiutils:contextedurl urlFragment="js/viewStandingPayments.js"/>"></script>
	<script type="text/javascript">
	var users = new Array();
	var amount = new Array();
	<s:iterator value="standingPayments">
		users.push("<s:property value="userId"/>");
		amount.push(<s:property value="amount"/>);
	</s:iterator>		
	</script>
	
	<div class="mainBodyDiv">
		<msg:messageDisplay/>
		Following is the standing payment information.<br/> 
		<table class="purchaseDisplayTable">
			<tr>
				<th>User</th>
				<th>Amount</th>				
			</tr>			
			<s:iterator value="standingPayments">
				<tr>
					<td><s:property value="users[userId].name"/></td>
					<td><s:property value="amount"/></td>
				</tr>
			</s:iterator>		
		</table>
		<br/>A positive spread means person has to give money.
		<br/>A negative spread means person has to take money.
		
		<br/>
		Make standing payments: <br/>
		<form id="form" action="makeStandingPayment.action">
			<dl>
				<dt>From </dt>
				<dd>
					<select id="fromUser" name="payment.userIdFrom" class="input-text">
						<s:iterator value="users">
							<option value="<s:property value="key"/>"><s:property value="value.name"/></option>
						</s:iterator>
					</select>
				</dd>
			</dl>
			
			<dl>
				<dt>To </dt>
				<dd>
					<select id="toUser" name="payment.userIdTo" class="input-text">
						<s:iterator value="users">
							<option value="<s:property value="key"/>"><s:property value="value.name"/></option>
						</s:iterator>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>Amount</dt>
				<dd><input type="text" id="amountBox" name="payment.amount" class="input-text"></dd>
			</dl>
			<dl>
				<dt></dt>
				<dd>
					<a href="#" onClick="makePayment();" class="greenButton">Make Payment</a>
				</dd>
			</dl>
		</form>
	</div>
</cspage:defaultpage>
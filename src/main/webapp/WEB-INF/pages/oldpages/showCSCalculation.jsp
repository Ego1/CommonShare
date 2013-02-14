<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>
<cspage:defaultpage title="Home">
	<script type="text/javascript" src="<uiutils:contextedurl urlFragment="js/datePicker.js"/>"></script>
	<script type="text/javascript" src="<uiutils:contextedurl urlFragment="js/addPurchases.js"/>"></script>
	<div class="mainBodyDiv">
		<div>
			Following is the payment information:
			<dl>
				<dt>Total amount</dt>
				<dd><s:property value="calculationUIVO.amount"/></dd>
			</dl>
			<dl>
				<dt>Comment</dt>
				<dd><s:property value="calculationUIVO.comment"/></dd>
			</dl>
			<dl>
				<dt>Money matters</dt>
				<dd>
					<table class="purchaseDisplayTable">
						<tr>
							<th></th>
							<s:iterator value="userMap">
								<th><s:property value="value.name"/></th>
							</s:iterator>
						</tr>
						<tr>
							<td>Amount paid</td>
							<s:iterator value="calculationUIVO.amountPaid" >
								<td><s:property value="value"/></td>
							</s:iterator>
						</tr>
						<tr>
							<td>Amount spread</td>
							<s:iterator value="calculationUIVO.amountSpread" >
								<td><s:property value="value"/></td>
							</s:iterator>
						</tr>
						<tr>
							<td>Amount to be paid by each</td>
							<s:iterator value="calculationUIVO.amountToPay" >
								<td><s:property value="value"/></td>
							</s:iterator>
						</tr>
					</table>
					<br/>
					A negative spread means the user will get money.<br/>
					A positive spread means the user has to give money.
				</dd>
			</dl>
		</div>
		Following are the purchases that have been calculated in this parse.
		<table class="purchaseDisplayTable">
			<tr>
				<th>Id</th>
				<th>Item</th>
				<th>Date</th>
				<th>Amount</th>
				<th>Comment</th>
				<th>Not Shared</th>
				<th>Payment</th>				
			</tr>
			
			<s:iterator value="purchases">
				<tr>
					<td><s:property value="id"/></td>
					<td><s:property value="item"/></td>
					<td><s:property value="date"/></td>
					<td><s:property value="amount"/></td>
					<td><s:property value="comment"/></td>
					<td><s:property value="userNameNotShared"/></td>
					<td><s:property value="userNamePayment"/></td>
				</tr>
			</s:iterator>				
		</table>
	</div>
</cspage:defaultpage>
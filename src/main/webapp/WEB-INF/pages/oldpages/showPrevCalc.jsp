<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>
<%@taglib prefix="cs" tagdir="/WEB-INF/tags/message"%>
<cspage:defaultpage title="Home">
	<script type="text/javascript" src="<uiutils:contextedurl urlFragment="js/viewPrevCalc.js"/>"></script>
	<div class="mainBodyDiv">
		<cs:messageDisplay/>
		<br/>
		Following are the previously made calculations.
		<table class="purchaseDisplayTable">
			<tr>
				<th>Id</th>
				<th>Date</th>
				<th>Comment</th>
				<th>Amount</th>				
			</tr>
			
			<s:iterator value="calculations">
				<tr>
					<td><s:property value="id"/></td>
					<td><s:property value="date"/></td>
					<td><s:property value="comment"/></td>
					<td><s:property value="amount"/></td>
					<td><a href="<uiutils:contextedurl urlFragment="p/viewCalculation.action?id="/>" onclick="prepareLink(this, '<s:property value="id"/>')">view</a></td>
				</tr>
			</s:iterator>				
		</table>
	</div>
</cspage:defaultpage>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:defaultpage title="Home Page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/menu.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/p/commonShareManagement.css">
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Common Share Management</div>
		<message:messageDisplay/>
		<div class="menublockdiv">
			<div class="thumbmenu">
				<div class="header">Options</div>
				<a href="${pageContext.request.contextPath}p/showItemManagement.action" class="thumbmenuitem">Manage Items</a>
				<a href="${pageContext.request.contextPath}p/showTaxonomyManagement.action" class="thumbmenuitem">Manage Taxonomy</a>
			</div>
		</div>
		<div class="menublockdiv">
			<div class="thumbmenu">
				<div class="header">Purchase Management</div>
				<a href="${pageContext.request.contextPath}p/showPurchases.action" class="thumbmenuitem">Purchases</a>
				<a href="${pageContext.request.contextPath}p/showAddPurchase.action" class="thumbmenuitem">Add Purchases</a>
				<a href="${pageContext.request.contextPath}p/showCalculations.action" class="thumbmenuitem">Calculate Present Shares</a>
				<a href="${pageContext.request.contextPath}p/showPurchases.action" class="thumbmenuitem">Pay Status</a>
			</div>
		</div>
	</div>
</cspage:defaultpage>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<cspage:defaultpage title="User Management - Add User">
	<link rel="stylesheet" type="text/css" href="css/p/menu.css">
	<script src="js/p/addUser.js" type="text/javascript"></script>
	<div class="appBodyDiv">
		This is our home page.
		<message:messageDisplay/>
		<div class="thumbmenu">
			<div class="thumbmenuitem-default" id="userMgmgBtn">User Management</div>
			<div class="thumbmenuitem-default" id="purchaseMgmgBtn">Purchase Management Management</div>
		</div>
		<a href="${pageContext.request.contextPath}p/showAddPurchases.action">Purchases</a><br/>
		<a href="${pageContext.request.contextPath}p/showAddUser.action">Add User</a>
	</div>
</cspage:defaultpage>


</cspage:defaultpage>
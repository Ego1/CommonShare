<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:defaultpage title="Home Page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/menu.css">
	<script src="${pageContext.request.contextPath}js/p/home.js" type="text/javascript"></script>
	<div class="appBodyDiv">
		This is our home page.
		<message:messageDisplay/>
		<div class="thumbmenu">
			<a href="${pageContext.request.contextPath}p/commonShareManagement.action" class="thumbmenuitem">Common Share</a>
			<a href="${pageContext.request.contextPath}p/userManagement.action" class="thumbmenuitem">Add User</a>
		</div>
	</div>
</cspage:defaultpage>
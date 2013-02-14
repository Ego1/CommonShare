<!DOCTYPE html>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>
<%@ attribute name="title" required="true" %>

<html>
	<head>
		<title>${title}</title>
		<script src="${pageContext.request.contextPath}js/jquery-1.8.1.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}js/jquery-ui-1.9.2.js" type="text/javascript"></script>
		<!-- 
		<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js" type="text/javascript"></script>
		 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/default.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/main.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/tabs.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/forms.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/black-form.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/sizes.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/jqueryui.css">
	</head>
	<body>
		<div class="appBodyDiv">
			<cspage:header/>
			<div class="bodyDiv">
					<jsp:doBody/>
			</div>
			<cspage:footer/>
		</div>		
	</body>
</html>
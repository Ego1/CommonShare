<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cs" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:fullpage title="Login">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/p/login.css"/>
	<script src="${pageContext.request.contextPath}js/common/login.js" type="text/javascript"></script>
	<script type="text/javascript">
		var ERROR_MISSING_ID_AND_PASSWORD = "Please enter both user id and password.";
	</script>
	<div class="loginDiv">
		<form action="${pageContext.request.contextPath}login.action" id='loginForm' method="POST">
			<div class="formdiv">
				<cs:messageDisplay/>
				<div class="formitem">
					<label for="loginName">Login Name</label>
					<input type="text" id="loginName" name="login.loginName"/>
				</div>
				<div class="formitem">
					<label for="password">Password</label>
					<input type="password" id="password" name="login.password"/>
				</div>
				<button id="submit" class="submitbutton">Add Purchase</button>				
				<span>
					<a href="/showGroupRegistrationPage.action">Register yourself</a>
				</span>					
			</div>
		</form>
	</div>
</cspage:fullpage>
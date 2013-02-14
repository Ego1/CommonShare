<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cs" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:defaultpage title="Login">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/common/login.css">
	<script src="${pageContext.request.contextPath}js/common/login.js" type="text/javascript"></script>
	<script type="text/javascript">
		var ERROR_MISSING_ID_AND_PASSWORD = "Please enter both user id and password.";
	</script>
	<div id="loginDiv" class="loginDiv">
		<cs:messageDisplay/>
		<form action="${pageContext.request.contextPath}login.action" id='loginForm' method="POST">
			<div class="form size250px">
				<ul class="elementul">
					<li>
						<label for="loginName">Login Name</label>
						<input type="text" id="loginName" class="textinput" name="login.loginName"/>
					</li>
					<li>
						<label for="password">Password</label>
						<input type="password" id="password" class="textinput" name="login.password"/>
					</li>
				</ul>
				<div class="spacer130">
					<a href="#" id="submit" class="submitButton">Submit</a>
				</div>
				<span>
					<a href="/showGroupRegistrationPage.action">Register yourself</a>
				</span>					
			</div>
			
		</form>
		
	</div>
</cspage:defaultpage>
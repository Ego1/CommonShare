<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cs" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<cspage:defaultpage title="Login">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/p/login.css"/>
	<script src="js/common/register.js" type="text/javascript"></script>
	<div id="registerDiv" class="loginDiv">
		<div class="instructions">
			Fill up this form to register a new group. Your activation link will be sent to your email id.
			Once you login, you will be made the group moderator and will have access to group administration functions.
		</div>
		
		<div class="registerformdiv">
			<form action="registerGroup" id='registerForm' method="POST">
				<div class="formdiv">
					<message:messageDisplay/>
					<div class="formitem">
						<label for="name">Name</label>
						<input type="text" id="name" name="register.name"/>
					</div>
					<div class="formitem">
						<label for="loginName">Login Name</label>
						<input type="text" id="loginName" name="register.loginName"/>
					</div>
					<div class="formitem">
						<label for="email">E-mail ID</label>
						<input type="text" id="email" name="register.email"/>
					</div>
					<div class="formitem">
						<label for="password">Password</label>
						<input type="password" id="password" name="register.password"/>
					</div>
					<div class="formitem">
						<label for="retypePassword">Re-type Password</label>
						<input type="password" id="retypePassword"/>
					</div>
					<div class="formitem">
						<label for="groupName">Group Name</label>
						<input type="text" id="groupName" name="register.groupName"/>
					</div>
					<button id="submitBtn" class="submitbutton">Submit</button>
					<button id="cancelBtn" class="submitbutton">Cancel</button>
				</div>
			</form>
		</div>
	</div>
</cspage:defaultpage>
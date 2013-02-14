<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cs" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<cspage:defaultpage title="Login">
	<link rel="stylesheet" type="text/css" href="css/forms.css">
	<link rel="stylesheet" type="text/css" href="css/common/login.css">
	<script src="js/common/register.js" type="text/javascript"></script>
	<div id="registerDiv" class="loginDiv">
		<div class="instructions">
			Fill up this form to register a new group. Your activation link will be sent to your email id.
			Once you login, you will be made the group moderator and will have access to group administration functions.
		</div>
		<div class="loginDiv">
			<form action="registerGroup" id='registerForm' method="POST">
				<cs:messageDisplay/>
				<div class="form">
					<ul class="elementul">
						<li>
							<label for="name">Name</label>
							<input type="text" id="name" class="textinput" name="register.name"/>
						</li>
						<li>
							<label for="loginName">Login Name</label>
							<input type="text" id="loginName" class="textinput" name="register.loginName"/>
						</li>
						<li>
							<label for="email">E-mail ID</label>
							<input type="text" id="email" class="textinput" name="register.email"/>
						</li>
						<li>
							<label for="password">Password</label>
							<input type="password" id="password" class="textinput" name="register.password"/>
						</li>
						<li>
							<label for="retypePassword">Re-type Password</label>
							<input type="password" id="retypePassword" class="textinput"/>
						</li>
						<li>
							<label for="groupName">Group Name</label>
							<input type="text" id="groupName"class="textinput" name="register.groupName"/>
						</li>
					</ul>
					<div class="spacer130">
						<a href="#" id="submitBtn" class="submitButton">Submit</a>
						<a href="#" id="cancelBtn" class="submitButton">Cancel</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</cspage:defaultpage>
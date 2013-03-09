<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<cspage:defaultpage title="User Management">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}css/p/userManagement.css"/>
	<script src="${pageContext.request.contextPath}js/p/addUser.js" type="text/javascript"></script>
	<div class="moduleHeaderDiv" id="moduleHeader">Add User</div>
	<div class="moduleBodyDiv">
		<message:messageDisplay/>
        <div class="showAllUsersDiv">
			<div class="instructions">
				Fill up this form to register a your friend into your group. Your activation link will be sent to your email id.
				Once your friend logs in, he/she would be able to add his share into the application.
			</div>
			<form action="addUser" id='addUserForm' method="POST">
				<div class="formdiv">
					<div class="formitem">
						<label for="name">Name</label>
						<input type="text" id="name" class="textinput" name="register.name"/>
					</div>
					<div class="formitem">
						<label for="loginname">Login Name</label>
						<input type="text" id="loginName" class="textinput" name="register.loginName"/>
					</div>
					<div class="formitem">
						<label for="email">E-mail ID</label>
						<input type="text" id="email" class="textinput" name="register.email"/>
					</div>
					<div class="formitem">
						<label for="password">Password</label>
						<input type="password" id="password" class="textinput" name="register.password"/>
					</div>
					<div class="formitem">
						<label for="retypePassword">Re-type Password</label>
						<input type="password" id="retypePassword" class="textinput"/>
					</div>
					<button id="createUserBtn" class="submitbutton">Create User</button>
				</div>
			</form>
		</div>
	</div>
</cspage:defaultpage>






	
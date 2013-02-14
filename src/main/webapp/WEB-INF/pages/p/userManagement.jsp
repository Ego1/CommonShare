<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<cspage:defaultpage title="User Management">
	<script src="${pageContext.request.contextPath}js/p/userManagement.js" type="text/javascript"></script>
	<script type="text/javascript">
		var selectedTab = ''<s:property value="selectedTab"/>;
	</script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">User Management</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay/>
			<div id="tabs">
				<div class="tabsNavBar">
				    <ul>
				        <li><a href="#tabs-1">All Users</a></li>
				        <li><a href="#tabs-2">Add User</a></li>
				    </ul>
				</div>
			    <div id="tabs-1" class="tabpage">
			        <div id="allUsersTabPage" class="tabPage">
						<a href="${pageContext.request.contextPath}p/showAddPurchases.action">Purchases</a><br/>
						<a href="${pageContext.request.contextPath}p/showAddUser.action">Add User</a>
						<table class="table">
							<tr>
								<th>Role</th>
								<th>Name</th>
								<th>Active</th>
								<th>Deleted</th>
								<th>Actions</th>
							</tr>
							<s:iterator value="users">
								<tr>
									<td><s:property value="role"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="active"/></td>
									<td><s:property value="deleted"/></td>
								</tr>
							</s:iterator>
						</table>
					</div>
			    </div>
			    <div id="tabs-2" class="tabpage">
			        <div id="addUserTabPage" class="tabPage">
						<div class="instructions">
							Fill up this form to register a your friend into your group. Your activation link will be sent to your email id.
							Once your friend logs in, he/she would be able to add his share into the application.
						</div>
						<form action="addUser" id='addUserForm' method="POST">
							<div class="form size300px">
								<ul class="elementul">
									<li>
										<label for="name">Name</label>
										<input type="text" id="name" class="textinput" name="register.name"/>
									</li>
									<li>
										<label for="loginname">Login Name</label>
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
										<a href="#" class="createUserBtn" id="createUserBtn">Create User</a>
									</li>
								</ul>
							</div>
						</form>
					</div>
			    </div>
			</div>
		</div>
	</div>
</cspage:defaultpage>






	
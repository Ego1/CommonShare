<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<cspage:defaultpage title="User Management">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}css/p/userManagement.css"/>
	<div class="userManagementDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">View all Users</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay/>
		    <div class="showAllUsersDiv">
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
	</div>
</cspage:defaultpage>
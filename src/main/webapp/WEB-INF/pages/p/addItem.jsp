<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:defaultpage title="Home Page">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}css/p/itemManagement.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}js/p/addItem.js"></script>
	<script type="text/javascript">
		var addItemURL = "${pageContext.request.contextPath}p/addItem.action";
	</script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Add Item</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay/>
	       	<div class="instructions">
				Add an item using this form. Please, also allocate a taxonomy to the item you create.<br/>
				You will be allowed to edit the item at a later point in time.
			</div>
			<div class="addItemDiv">
				<form action="addItem" id='addItemForm' method="POST">
					<div class="formdiv">
						<div class="formitem">
							<label for="name">Name</label>
							<input type="text" id="name" class="textinput" name="itemVO.name"/>
						</div>
						<div class="formitem">
							<label for="loginname">Description</label>
							<input type="text" id="description" class="textinput" name="itemVO.description"/>
						</div>
						<button class="submitbutton" id="createItemBtn">Create Item</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</cspage:defaultpage>
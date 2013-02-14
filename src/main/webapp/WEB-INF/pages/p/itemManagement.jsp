<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:defaultpage title="Home Page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/menu.css">
	<link href="${pageContext.request.contextPath}css/lib/autocomplete.css" rel="stylesheet" type="text/css">
	<script src="${pageContext.request.contextPath}js/lib/autocomplete.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}js/lib/string-hashmap.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}js/p/itemManagement.js" type="text/javascript"></script>
	<script type="text/javascript">
		var addItemURL = "${pageContext.request.contextPath}p/addItem.action";
	</script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Item Management</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay/>
			<div id="tabs">
				<div class="tabsNavBar">
				    <ul>
				        <li><a href="#tabs-1">All Items</a></li>
				        <li><a href="#tabs-2">Add Items</a></li>
				    </ul>
				</div>
				<div id="tabs-1" class="tabpage">
			        <div id="allItemsDiv" class="tabPage">
			        	<div class="table">
			        		<table id="allItemsTable">
			        			<tr>
			        				<th>Id</th>
			        				<th>Name</th>
			        				<th>Description</th>
			        				<th>Taxonomy</th>
			        				<th>Remove</th>
			        			</tr>
			        		</table>			        		
			        	</div>
					</div>
			    </div>
			    <div id="tabs-2" class="tabpage">
			        <div id="addItem" class="tabPage">
						<div class="instructions">
							Add an item using this form. Please, also allocate a taxonomy to the item you create.<br/>
							You will be allowed to edit the item at a later point in time.
						</div>
						<form action="addItem" id='addItemForm' method="POST">
							<div class="form size300px">
								<ul class="elementul">
									<li>
										<label for="name">Name</label>
										<input type="text" id="name" class="textinput" name="itemVO.name"/>
									</li>
									<li>
										<label for="loginname">Description</label>
										<input type="text" id="description" class="textinput" name="itemVO.description"/>
									</li>
									<li>
										<label for="email">Taxonomy</label>
										<input type="text" id="taxonomy" class="textinput"/>
										<input type="hidden" id="taxonomyhidden" class="textinput" name="itemVO.taxonomy"/>
									</li>
									<li>
										<a href="#" class="createItemBtn" id="createItemBtn">Create Item</a>
									</li>
								</ul>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var items = [
	            	 <s:iterator value="items">
	            	 	{
	            	 		"id" : <s:property value="id"/>,
	            	 		"name" : "<s:property value="name"/>",
	            	 		"description" : "<s:property value="description"/>",
	            	 		"taxonomy" : "<s:property value="taxonomy.id"/>"
	            	 	},
	            	 </s:iterator>
		             ];
		var taxonomies = [
		                  
		                  <s:iterator value="taxonomies">
		                  {
		                	  "id" : "<s:property value="id"/>",
		                	  "name" : "<s:property value="name"/>",
		                	  "description" : "<s:property value="description"/>",
		                	  "parent" : "<s:property value="parent.id"/>",
		                	  "children" : []
		                  },
		                  </s:iterator>
		                  ];
	</script>
</cspage:defaultpage>
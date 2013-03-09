<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:defaultpage title="Home Page">
	<script src="${pageContext.request.contextPath}js/p/items.js" type="text/javascript"></script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Item Management</div>
		<div class="moduleBodyDiv">
			<message:messageDisplay/>
	    	<div class="table">
        		<table id="allItemsTable">
        			<tr>
        				<th>Id</th>
        				<th>Name</th>
        				<th>Description</th>
        				<th>Remove</th>
        			</tr>
        		</table>			        		
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
	</script>
</cspage:defaultpage>
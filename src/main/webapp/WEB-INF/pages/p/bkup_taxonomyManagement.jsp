<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:defaultpage title="Home Page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/menu.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/p/taxonomy.css">
	<script src="${pageContext.request.contextPath}js/p/taxonomyManagement.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}js/lib/string-hashmap.js" type="text/javascript"></script>
	<script type="text/javascript">
	var addTaxonomyURL = "${pageContext.request.contextPath}p/addTaxonomy.action";
	var removeTaxonomyURL = "${pageContext.request.contextPath}p/removeTaxonomy.action";
	var editTaxonomyURL = "${pageContext.request.contextPath}p/editTaxonomy.action";
	</script>
	<div class="appBodyDiv">
		<div class="moduleHeaderDiv" id="moduleHeader">Taxonomy Management</div>
	</div>
	<div class="taxonomyManagement">
		<div id="rootleveltaxonomydiv" class="taxonomyPanel">
			<div class="taxonomyaddpanel">
				<a href="" id="addrootleveltaxonomy">Add</a>
				<a href="" id="addchildforrootleveltaxonomy">Add Child</a>
			</div>
			<select id="rootleveltaxonomy" class="taxonomySelect"></select>
		</div>
		<div id="midleveltaxonomydiv" class="taxonomyPanel">
			<div class="taxonomyaddpanel">
				<a href="" id="addmidleveltaxonomy">Add</a>
				<a href="" id="addchildformidleveltaxonomy">Add Child</a>
			</div>
			<select id="midleveltaxonomy"></select>
		</div>
		<div id="leafleveltaxonomydiv" class="taxonomyPanel">
			<div class="taxonomyaddpanel">
				<a href="" id="addleafleveltaxonomy">Add</a>
			</div>
			<select id="leafleveltaxonomy"></select>
		</div>
	</div>
	<div id="addTaxonomyForm" class="addTaxonomyForm">
		<form action="${pageContext.request.contextPath}addTaxonomy.action" id='addtaxonomyform' method="POST">
			<input type="hidden" name="taxonomyVO.parent" id="parent"/>
			<div class="form size250px">
				<div id="message"></div>
				<ul class="elementul">
					<li>
						<label for="name">Name</label>
						<input type="text" id="name" class="textinput" name="taxonomyVO.name"/>
					</li>
					<li>
						<label for="description">Description</label>
						<input type="text" id="description" class="textinput" name="taxonomyVO.description"/>
					</li>
				</ul>
				<div class="spacer130">
					<a href="#" id="addTaxonomySubmit" class="submitButton">Add Taxonomy</a>
				</div>
				<span>
					<a href="#" id="addTaxonomyCancel">Cancel</a>
				</span>					
			</div>	
		</form>
	</div>
	<script type="text/javascript">
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
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="menuDiv">
	<s:iterator value="%{#session.SESSION_CACHE_ATTRIBUTE.authorizedUrl.urlMap}">
		<h3><s:property value="key"/></h3>
		<ul>
			<s:iterator value="value">
				<li><a href="<s:property value="url"/>"><s:property value="text"/></a></li>
			</s:iterator>
		</ul>
	</s:iterator>
</div>
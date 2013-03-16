<%@tag import="com.ego.apps.commonshare.cache.SessionCacheManager"%>
<%@tag import="com.ego.apps.commonshare.cache.SessionCache"%>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>
<div class="headerDiv">
	Common share calculator version v 1.0
	<%
	// I took this dirty path down the road because 
	// I didn't wish to create another tag just to check
	// if a user is logged in or not.
	// Moreover it is too simple to be complicated by tags and all.
	SessionCache sessionCache = SessionCacheManager.getSessionCache(request);
	if(sessionCache != null && sessionCache.getUser() != null)
		{
		// We made sure that the user is already logged in.
	%>
		<div style="float:right">
			<a href="${pageContext.request.contextPath}logout.action">Logout</a>
		</div>
	<%
		}
	%>
	
</div>
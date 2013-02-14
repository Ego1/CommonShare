<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>
<cspage:defaultpage title="Home">
	<div class="mainBodyDiv">
		Standing Payments <br/>
		<ol>
			<li><a href="<uiutils:contextedurl urlFragment="/p/viewStandingPayments.action"/>">View Standing Payments</a></li>
			<li><a href="<uiutils:contextedurl urlFragment="/p/viewPreviousPayments.action"/>">View Previous Payments</a></li>
		</ol>
	</div>
</cspage:defaultpage>
 


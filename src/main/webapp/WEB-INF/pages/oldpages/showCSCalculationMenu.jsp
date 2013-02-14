<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="uiutils" uri="/WEB-INF/tags/utils.tld"%>
<%@taglib prefix="cs" tagdir="/WEB-INF/tags/message"%>
<cspage:defaultpage title="Home">
	<script type="text/javascript" src="<uiutils:contextedurl urlFragment="js/showCSCalculationMenu.js"/>"></script>
	<div class="mainBodyDiv">
		<cs:messageDisplay/>
		Welcome <s:property value="login.name"/><br/>
		What acton do you wish to perform? <br/>
		<ol>
			<li><a href="<uiutils:contextedurl urlFragment="p/viewPreviousCalculations.action"/>">View Previous Calculations</a></li>
			<li><a id="performCalculationAnchor" href="<uiutils:contextedurl urlFragment="p/calculateCS.action"/>" onclick="captureComment();">Perform Calculation</a></li>
		</ol>
	</div>
</cspage:defaultpage>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="messagediv" id="messagediv">
	<s:if test="error!=null">
		<div class="errorBox">
			<div class="errorCode">
				Error Code: <s:property value="error.code"/><br/>
			</div>
			<div class="errorMessage">
				<ul id="errormessageul">
					<s:iterator value="error.messages" var="msg">
						<li><s:property/></li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</s:if>
	<s:if test="message!=null">
		<div class="msgBox">
			<div class="msg">
				<ul id="messageul">
					<s:iterator value="message.messages">
						<li><s:property/></li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</s:if>
</div>
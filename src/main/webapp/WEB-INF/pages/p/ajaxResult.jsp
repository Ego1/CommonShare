<%--@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"--%>
<%@ page contentType="application/json"%>
<%@taglib prefix="s" uri="/struts-tags"%>
{
	"result" : "<s:property value="ajaxResult.result"/>",
	"message" : "<s:property value="ajaxResult.message"/>",
	"data" : <s:property value="ajaxResult.data" escapeHtml="false"/>
}
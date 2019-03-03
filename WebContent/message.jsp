<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNavigator.jsp"%>

<title>消息提示</title>
	
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">消息提示</div>
	  <div class="panel-body">
		<p algin:center>${message}</p>
	  </div>
	</div>

<%@include file="../include/adminFooter.jsp"%>
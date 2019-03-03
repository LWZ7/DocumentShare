<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNavigator.jsp"%>

<title>消息列表</title>


<div class="workingArea">
	<h1 class="label label-info" >消息列表</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv" style="text-align:center">
		<table class="table table-striped table-bordered table-hover  table-condensed" style="text-align:center">
			<thead>
				<tr class="success">
					<th style="display:none;">ID</th>
					<th>消息</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${Friends}" var="c">
					<tr>
						<td style="display:none;">${c.id}</td>
						<td>${c.requestName}请求添加你为好友</td>
						<td><a updateLink="true" href="admin_friend_update?id=${c.id}">同意</a></td>				 
						<td><a refuseLink="true" href="admin_friend_refuse?id=${c.id}">拒绝</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

    <div class="pageDiv">
		<%@include file="../include/Page.jsp" %>
	</div>
	
	
<%@include file="../include/adminFooter.jsp"%>
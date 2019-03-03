<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNavigator.jsp"%>

<title>好友管理</title>


<div class="workingArea">
	<h1 class="label label-info" >好友管理</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv" style="text-align:center">
		<table class="table table-striped table-bordered table-hover  table-condensed" style="text-align:center">
			<thead>
				<tr class="success">
					<th style="display:none;">ID</th>
					<th>我的好友</th>
					<!--  <th>在线聊天</th> -->
					<th>删除好友</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${friends}" var="c">
					<tr>
						<td style="display:none;">${c.id}</td>	
						<td><a href="admin_friend_getFid?id=${c.id}">${c.requestName}</a></td>
						<!--  <td><a href="chat.jsp">在线聊天</a> -->
						<td><a deleteLink="true" href="admin_friend_delete?id=${c.id}">删除</a></td>				 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

    <div class="pageDiv">
		<%@include file="../include/Page.jsp" %>
	</div>
</div>

<%@include file="../include/adminFooter.jsp"%>
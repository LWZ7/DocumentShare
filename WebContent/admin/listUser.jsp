<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNavigator.jsp"%>

<title>用户管理</title>


<div class="workingArea">
	<h1 class="label label-info" >用户管理</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv" style="text-align:center">
		<table class="table table-striped table-bordered table-hover  table-condensed" style="text-align:center">
			<thead>
				<tr class="success">
					<th style="display:none;">ID</th>
					<th>用户名字</th>
					<th>添加</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="c">
					<tr>
						<td style="display:none;">${c.id}</td>					 
						<td>${c.name}</td>
						<td><a addLink="true" href="admin_friend_add?id=${c.id}">添加</a></td>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNavigator.jsp"%>


<title>好友共享文档</title>


<div class="workingArea">
	<h1 class="label label-info" >好友共享文档</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv" style="text-align:center">
		<table class="table table-striped table-bordered table-hover  table-condensed" style="text-align:center">
			<thead>
				<tr class="success">
					<th style="display:none;">ID</th>
					<th>文件名</th>
 					<th>文件类型</th> 
					<th>所有者</th>
					<th>上传时间</th>
					<th>下载</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${Documents}" var="c">
					<tr>
						<td style="display:none;">${c.id}</td>
						<td>${c.fileName}</td>
						<td>${c.type}</td> 
						<td>${c.userName}</td> 
						<td>${c.date}</td>		
						<td>			         
							 <c:url value="admin_file_download" var="downurl">
					             <c:param name="filename" value="${c.realName}"></c:param>
					         </c:url>
					         <a href="${downurl}">下载</a>
				        </td> 					 
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNavigator.jsp"%>

<script>
$(function(){ 
    <c:if test="${!empty msg}">
    alert("该资源已删除");  
    alert(msg);
    </c:if>
    
    $("#addForm").submit(function(){
		if(!checkEmpty("name","上传文件")){
			return false;
		}	
		return true;
	});
})
</script>


<title>文档管理</title>


<div class="workingArea">
	<h1 class="label label-info" >文档管理</h1>
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
					<th>权限设置</th>
					<th>重命名</th>
					<th>删除</th>
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
				              	
				        <td> 
				        	<form method="post" action="admin_privilege_add"> 
				        		<input type="hidden" name="id" value=${c.id}>
				        		 <select name="permission" class="selectpicker show-tick form-control" multiple>
				              		<c:forEach items="${friends}" var="a">
					               		<option value=${a.fid}>${a.requestName}</option>
					          		</c:forEach>
				          		 </select>
				          		 <button type="submit" >提 交</button>
				          	</form>
				         </td> 
			          	
						<td><a href="admin_file_edit?id=${c.id}">重命名</a></td>
						<td><a deleteLink="true" href="admin_file_delete?id=${c.id}">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

    <div class="pageDiv">
		<%@include file="../include/Page.jsp" %>
	</div>
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">上传文件</div>
	  <div class="panel-body">
	  		<form method="post" id="addForm" action="admin_file_add" enctype="multipart/form-data">
	  			<table class="addTable">
	    			<tr>
	    				<td>上传文件</td>
	    				<td><input  id="name" name="name" type="file" class="form-control" ></td>
	    			</tr>
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<button type="submit" class="btn btn-success" >提 交</button>
	    				</td>
	    			</tr>
	    		</table>
		    </form>
	  </div>
	</div>
	
</div>

<%@include file="../include/adminFooter.jsp"%>
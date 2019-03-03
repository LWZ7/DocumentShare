<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNavigator.jsp"%>



<title>编辑分类</title>


<script>
$(function(){
	
	$("#editForm").submit(function(){
		if(!checkEmpty("name","分类名称"))
			return false;

		return true;
	});
});

</script>

<div class="workingArea">

	<ol class="breadcrumb">
	  <li><a href="admin_category_list">重命名</a></li>
	  <li class="active">重命名</li>
	</ol>

	<div class="panel panel-warning editDiv">
	  <div class="panel-heading">重命名</div>
	  <div class="panel-body">
	    	<form method="post" id="editForm" action="admin_file_update">
	    		<table class="editTable">
	    			<tr>
	    				<td>文件名称</td>
	    				<td><input  id="name" name="name" value="${doc.fileName}" type="text" class="form-control"></td>
	    			</tr>    			
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<input type="hidden" name="id" value="${doc.id}">
	    					<button type="submit" class="btn btn-success">提 交</button>
	    				</td>
	    			</tr>
	    		</table>
	    	</form>
	  </div>
	</div>	
</div>
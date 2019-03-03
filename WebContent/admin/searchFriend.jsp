<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNavigator.jsp"%>
<title>查找用户</title>
	
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">查找用户</div>
	  <div class="panel-body">
	  		<form method="post" id="addForm" action="admin_friend_get">
	  			<table class="addTable">
	    			<tr>
	    				<td>查找用户</td>
	    				<td><input  id="name" name="name" type="text" class="form-control" placeholder="请输入用户名字"></td>
	    			</tr>
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<button type="submit" class="btn btn-success" >查找</button>
	    				</td>
	    			</tr>
	    		</table>
		    </form>
	  </div>
	</div>

<%@include file="../include/adminFooter.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@include file="../include/adminHeader.jsp"%>

<script>
$(function(){
    
    <c:if test="${!empty msg}">
    $("span.errorMessage").html("${msg}");
    $("div.registerErrorMessageDiv").show();      
    </c:if>
	
    $(".registerForm").submit(function(){
        if(0==$("#name").val().length){
        	alert("请输入用户名");           
            return false;
        }    
        if(0==$("#email").val().length){
            alert("请输入邮箱");          
            return false;
        } 
        if(0==$("#password").val().length){
            alert("请输入密码");          
            return false;
        }      
        if(0==$("#repeatpassword").val().length){
        	alert("请输入重复密码");          
            return false;
        }      
        if($("#password").val() !=$("#repeatpassword").val()){
            alert("重复密码不一致");        
            return false;
        }      
 
        return true;
    });
})
</script>
<div id="registerDiv" class="panel panel-warning addDiv">
      <div class="panel-heading">注册</div>
	  <div class="panel-body">
 
<form method="post" action="admin_user_add" class="registerForm">
 
<div class="registerDiv">
    
    <div class="registerErrorMessageDiv" style="display:none;">
        <div class="alert alert-danger" >
          <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
            <span class="errorMessage"></span>
        </div>
    </div>
 
    <table class="registerTable" align="center">
        <tr>
            <td class="registerTableLeftTD">登陆名</td>
            <td  class="registerTableRightTD"><input id="name" name="name" placeholder="会员名设置成功便无法修改" > </td>
        </tr>
 		
 		<tr>
            <td class="registerTableLeftTD">登陆邮箱</td>
            <td  class="registerTableRightTD"><input id="email" name="email" placeholder="设置用户邮箱" > </td>
        </tr>
 		
        <tr>
            <td class="registerTableLeftTD">登陆密码</td>
            <td class="registerTableRightTD"><input id="password" name="password" type="password"  placeholder="设置你的登陆密码" > </td>
        </tr>
        <tr>
            <td class="registerTableLeftTD">密码确认</td>
            <td class="registerTableRightTD"><input id="repeatpassword" type="password"   placeholder="请再次输入你的密码" > </td>
        </tr>
                 
        <tr>
            <td colspan="2" class="registerButtonTD">
            	<button class="btn btn-block redButton" type="submit">注 册</button>
            </td>
        </tr>            
    </table>
</div>
</form>
 </div>
</div>   
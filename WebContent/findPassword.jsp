<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/adminHeader.jsp"%>

<title>找回密码</title>
<script>
$(function(){ 

    $(".emailform").submit(function(){
        if(0==$("#name").val().length){
        	alert("请输入用户名");           
            return false;
        }    
        if(0==$("#email").val().length){
            alert("请输入邮箱");          
            return false;
        } 
        return true;
    });
})
</script>
 
<div id="loginDiv" class="panel panel-warning addDiv">
      <div class="panel-heading">找回密码</div>
	  <div class="panel-body">
     
    <form class="emailform" method="post" action="admin_user_sendPasswordTo" >
                 
            <div class="login_acount_text">  </div>
            <div class="loginInput " >
                <input type="text" id="name" name="name" class="form-control userEmail" placeholder="请输入用户名" >       
            </div>
             
            <div class="loginInput " >
                <input type="text" id="email" name="email" class="form-control userEmail" placeholder="请输入注册时的邮箱">
            </div>
             
            <div>
                <a class="notImplementLink" ></a>
                <a class="pull-right"></a>
            </div>
            <div style="margin-top:20px">
                <button class="btn btn-block redButton" type="submit">发送</button>
            </div>
        </div>   
    </form>
 </div>
</div>   

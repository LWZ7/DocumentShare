<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>聊天室</title>
    <style type="text/css">

        .talk_con{

            width:600px;

            height:500px;

            border:1px solid #666;

            margin:50px auto 0;

            background:#f9f9f9;

        }

        .talk_show{

            width:580px;

            height:420px;

            border:1px solid #666;

            background:#fff;

            margin:10px auto 0;

            overflow:auto;

        }

        .talk_input{

            width:580px;

            margin:10px auto 0;

        }

        .whotalk{

            width:80px;

            height:30px;

            float:left;

            outline:none;

        }

        .talk_word{

            width:420px;

            height:26px;

            padding:0px;

            float:left;

            margin-left:10px;

            outline:none;

            text-indent:10px;

        }
        
.talk_sub{

            width:56px;

            height:30px;

            float:left;

            margin-left:10px;

        }

        .atalk{

           margin:10px;
 
        }

        .atalk span{

            display:inline-block;

            background:#0181cc;

            border-radius:10px;

            color:#fff;

            padding:5px 10px;

        }

        .btalk{

            margin:10px;

            text-align:right;

        }

        .btalk span{

            display:inline-block;

            background:#ef8201;

            border-radius:10px;

            color:#fff;

            padding:5px 10px;

        }

    </style>
    
    <script type="text/javascript">
    
    window.onload = function(){

        var Words = document.getElementById("words");

        var Who = document.getElementById("who");

        var TalkWords = document.getElementById("talkwords");

        var TalkSub = document.getElementById("talksub");



        TalkSub.onclick = function(){

            //定义空字符串

            var str = "";

            if(TalkWords.value == ""){

                 // 消息为空时弹窗

                alert("消息不能为空");

                return;

            }

            if(Who.value == 0){

                //如果Who.value为0n那么是 A说

                str = '<div class="atalk"><span>A说 :' + TalkWords.value +'</span></div>';

            }

            else{

                str = '<div class="btalk"><span>B说 :' + TalkWords.value +'</span></div>' ;

            }

            // 将之前的内容与要发的内容拼接好 提交

            Words.innerHTML = Words.innerHTML + str;

        }
    }
    
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8010/DocumentShare/websocket");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
    
	function send(){
		var message = document.getElementById('text').value;
		websocket.send(message);
		var str = "";
		str = '<div class="btalk"><span>B说 :' + message +'</span></div>' ;
		document.getElementById('mess').innerHTML = message + '<br/>';
	}
</script>
    
</head>



<body>
    Welcome<br/><input id="text" type="text"/>
    <button onclick="send()">发送消息</button>
    <hr/>
    <button onclick="closeWebSocket()">关闭WebSocket连接</button>
    <hr/>
    <!-- <div class="talk_con">

        <div class="talk_show" id="words">

            <div class="atalk"><span id="a">吃饭了吗？</span></div>
            <div class="btalk"><span id="b">还没呢，你呢？</span></div>

        </div>

        <div class="talk_input">

            <select class="whotalk" id="who">

                <option value="0">a</option>

                <option value="1">b</option>

            </select>

            <input type="text" class="talk_word" id="talkwords">

           <!--  <button class="talk_sub" id="talksub" onclick="send()">发送</button> --> 
         <!--     <input type="button" value="发送" class="talk_sub" id="talksub">
        </div>

    </div> -->

    
    <div id="message"></div>
    <!-- <div id="mess" style="color:green"></div>  -->
</body>

</html>
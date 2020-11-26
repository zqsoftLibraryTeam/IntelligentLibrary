<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图书馆后台管理</title>
    <link href="..\..\css/main.css" rel="stylesheet">

    <style>

body{
    background-color: #e2e2e2;}

    </style>
</head>
<body>
 <div class="manage_header">
     <div class="manager_header_left">
          <span>图书馆</span>
     </div>
     <div class="manager_header_center">
           <span>后台管理</span>
     </div>
     <div class="manager_header_right">
         <div class="manager_header_right_time_top">
             <span id="date">Loding</span>
             <i id="day"></i>
         </div>
         <div class="manager_header_right_time_bottom">
             <span id="time">00:00</span>
         </div>
     </div>
 </div> <!--manage_header 结束-->
<div class="manager_nav_top">
    <div class="manager_nav_top_left">
        <span>系统导航</span>
    </div>
    <div class="manager_nav_top_right">
        <span>当前管理员：</span>
        <i>XX</i>
        <a href="#">退出系统</a>
    </div>
</div><!--manager_nav_top 结束-->
<div class="manager_content">
    <div class="manager_content_nav">
        <dl >
            <dt style="color: #ffffff;background-color:#1a8dd9;" onclick="showUserlist">用户管理</dt>
            <dd style="color: #f0ff00">添加用户</dd>
            <dd>修改用户</dd>

        </dl>
        <dl>
            <dt>图书管理</dt>

             <dd>添加图书</dd>
             <dd>修改图书</dd>

        </dl>
        <dl>
            <dt>系统工具</dt>
            <dd>管理员信息</dd>
            <dd>修改密码</dd>

        </dl>
    </div>
    <div class="manager_content_icon"></div>
    <div class="manager_content_right">
        <div id="0" class="manager_content_right_content">
          <!--  <span class="manager_span_title ">用户添加界面：</span>-->
                <iframe id='index' src="listUser.jsp" style="width: 100%;height: 100%; overflow: hidden">
                </iframe>
        </div>

        <div id="1" class="hid manager_content_right_content" >
            <!-- <span class="manager_span_title">图书管理：</span>-->
             <iframe src="listBook.jsp" style="width: 100%;height: 100%; overflow: hidden">
             </iframe>
        </div>

        <div id="2"class="hid manager_content_right_content">
            <span class="manager_span_title">系统工具：</span>
        </div>


    </div>

</div>
<script >



    /*  获取时间*/
    function  nowDate(){
    var day=document.getElementById('day');
    var time=document.getElementById("time");
    var date=document.getElementById("date");
    var timer = null;
    timer = setInterval(fun_timer, 1000)
    function fun_timer() {
        var iNow = new Date();
        var iHours = iNow.getHours();
        var iMinutes = iNow.getMinutes();
        var iSeconds = iNow.getSeconds();

         /*显示2位数*/
        function toTow(x) {
            if (x / 10 < 1) {
                return '0' + x;
            }
            else return ''+x;
        }
        time.innerHTML=toTow(iHours) + ':'+toTow(iMinutes)+':'+toTow(iSeconds);

        var iYear=iNow.getFullYear();
        var iMonth=iNow.getMonth()+1;
        var iDate = iNow.getDate();
       date.innerHTML=iYear+'年'+iMonth+'月'+iDate+'日';
        var iDay=iNow.getDay();
        if(iDay==0){
            day.innerHTML='星期日';
        }
        else {
            day.innerHTML='星期'+iDay;
        }
    }}

    /* 切换导航条*/
    function changeNav(){
    var dd = document.getElementsByTagName('dd');
    var dt=document.getElementsByTagName('dt');
    var Ifr=document.getElementsByTagName('index');
   
    

        for(var i=0;i<dt.length;i++){
            dt[i].index=i;
            dt[i].onclick= function () {
                for(var i=0;i<dt.length;i++){
                    dt[i].style.color='#343434';
                    dt[i].style.backgroundColor=' #fff';

                    document.getElementById(i).style.display='none';
                }
                this.style.color='#ffffff';
                this.style.backgroundColor=' #1a8dd9';
                document.getElementById(this.index).style.display='block';


            }
        }



    for(var i=0;i<dd.length;i++){
        dd[i].index=i;



        dd[i].onclick=function(){
            for(var i=0;i<dd.length;i++){
                dd[i].style.color='#ffffff';
            }
            this.style.color='#f0ff00';
            for(var i=0;i<dt.length;i++){
                dt[i].style.color='#343434';
                dt[i].style.backgroundColor=' #fff';
                document.getElementById(i).style.display='none';

            }
            if(this.index<2&&this.index>=0){
                dt[0].style.color='#ffffff';
                dt[0].style.backgroundColor=' #1a8dd9';
                document.getElementById("0").style.display='block';
                if(this.index==0){
                    alert('添加用户');
                }
                if(this.index==1){
                    alert('修改用户');
                }
            }
            if(this.index<4&&this.index>=2){
                dt[1].style.color='#ffffff';
                dt[1].style.backgroundColor=' #1a8dd9';
                document.getElementById("1").style.display='block';
                if(this.index==2){
                    alert("添加图书");
                }
                if(this.index==3){
                    alert("修改图书");
                }
            }
            if(this.index==5||this.index==4){
                dt[2].style.color='#ffffff';
                dt[2].style.backgroundColor=' #1a8dd9';
                document.getElementById("2").style.display='block';
                if(this.index==4){
                    alert("管理员信息");
                }
                if(this.index==5){
                    alert("修改密码");
                }
            }



        }
    }}
    nowDate();
    changeNav();

</script>
</body>
</html>


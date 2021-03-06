<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="stylesheet" type="text/css" href="<%=basePath%>themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>themes/icon.css">          
<script type="text/javascript" src="<%=basePath%>jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery/jquery.easyui.min.js"></script>
<script type="text/javascript">

</script>
<head></head>
<body>
   <div class="deskbox" id="box1">
       <div id="p1" class="easyui-panel" title="通知公告" icon="icon-save" collapsible="false" style="width:380px;heigth:180px;">
		<div class="deskitem">
		<c:forEach items="${notices}" var="n">
		  <li>${n.content}</li>
		</c:forEach>
		</div>
		<div class="deskmore"><a href="<%=path %>/control/tolist"><img src="<%=path %>/img/more.jpg"/></a></div>
      </div>
   </div>
   <!--  
   <div class="deskbox" id="box2">
       <div id="p2" class="easyui-panel" title="待办事项" icon="icon-save" collapsible="false" style="width:380px;heigth:180px;">
        <div class="deskitem">
		<li>周一上午8:30开会</li>
        <li>周二上午8:30开会</li>
        <li>周三上午8:30开会</li>
        <li>周四上午8:30开会</li>
        <li>周五上午8:30开会</li>
		<li>周五上午8:30公司开会</li>
		</div>
		<div class="deskmore"><a href="#"><img src="img/more.jpg"/></a></div>
      </div>
   </div>
   -->
   <div class="deskbox" id="box3">
       <div id="p3" class="easyui-panel" title="用户信息" icon="icon-save" collapsible="false" style="width:380px;heigth:180px;">
        <div class="deskitem clearfix">
		<img class="userimg" src="<%=path %>/img/user_header.jpg"/>
		<div class="userinfo">
			<p>用户名：${user.username}</p>
			<p>部&nbsp;&nbsp;&nbsp;门：${user.dep.depname}</p>
		</div>
		</div>
		<div class="deskmore"></div>
      </div>
   </div>
   <div class="deskbox" id="box4">
       <div id="p4" class="easyui-panel" title="日历信息" icon="icon-save" collapsible="false" style="width:380px;heigth:180px;">
        <div class="deskitem" id="calenderbox">
		<script type="text/javascript">
document.getElementById("calenderbox").innerHTML="<div id='calenderdiv' style>日历加载中...</div>";
var press_tag;
function changecal(action,year,month)
{
      var strcal;
      switch(action)
      {
      case "nextmonth":
            if(month==11)
            {
                  month = 1;
                  year = year*1 + 1;
            }else{
                  month = month*1 + 2;
            }
            strcal = "<span onmouseover=\"this.className='arrow_over'\" onmouseout=\"this.className='arrow_out'\" class='arrow_out'  onclick='calender(" + year + "," + month +")' title='下一个月' style='cursor:hand;'>> </span>";
            break;
      case "premonth":
            if(month==0)
            {
                  month = 12;
                  year = year*1 - 1;
            }
            strcal = "<span onmouseover=\"this.className='arrow_over'\" onmouseout=\"this.className='arrow_out'\" class='arrow_out' onclick='calender(" + year + "," + month +")' title='上一个月' style='cursor:hand;'> <</span>";
            break;
      case "nextyear":
            year = year*1 + 1;
            month = month*1 + 1;
            strcal = "<span onmouseover=\"this.className='arrow_over'\" onmouseout=\"this.className='arrow_out'\" class='arrow_out' onclick='calender(" + year + "," + month +")' title='下一年' style='cursor:hand;'>>></span>";
            break;
      case "preyear":
            year = year*1 - 1;
            month = month*1 + 1;
            strcal = "<span onmouseover=\"this.className='arrow_over'\" onmouseout=\"this.className='arrow_out'\" class='arrow_out' onclick='calender(" + year + "," + month +")' title='上一年' style='cursor:hand;'><<</span>";
            break;
      default:;
      }
      strcal = " " + strcal + " ";
      return(strcal);
}
function calender(cyear,cmonth)
{
      var d,d_date,d_day,d_month;
      //定义每月天数数组
      var monthdates = ["31","28","31","30","31","30","31","31","30","31","30","31"]
      d = new Date();
      d_year = d.getYear();      //获取年份
      //判断闰月，把monthdates的二月改成29
      if (((d_year % 4 == 0) && (d_year % 100 != 0)) || (d_year % 400 == 0)) monthdates[1] = "29";
      if ((cyear != "" ) || (cmonth != ""))
      {
            //如果用户选择了月份和年份，则当前的时间改为用户设定
            d.setYear(cyear);
            d.setMonth(cmonth-1);
            d.setDate(1);
      }
      d_month = d.getMonth();      //获取当前是第几个月
      d_year = d.getYear();      //获取年份
      d_date = d.getDate();      //获取日期
      //修正19XX年只显示两位的错误
      if(d_year<2000){d_year = d_year + 1900}
//===========输出日历===========
      var str;
      str = "<table cellspacing='0' cellpadding='0' id='calender'>";
      str += "<tr><td id='cal_title' colspan='7' >"
      str += changecal("preyear",d_year,d_month)
      str += changecal("premonth",d_year,d_month)
      str += d_year + " 年 " + (d_month*1+1)+"月"
      str += changecal("nextmonth",d_year,d_month)
      str += changecal("nextyear",d_year,d_month)
      str += "</td></tr>";
      str += "<tr id='week'><td class='red'>日</td><td>一</td><td>二</td><td>三</td><td>四</td><td>五</td><td class='red'>六</td></tr>";
      str += "<tr>";
      var firstday,lastday,totalcounts,firstspace,lastspace,monthdays;
      //需要显示的月份共有几天，可以用已定义的数组来获取
      monthdays = monthdates[d.getMonth()];
      //设定日期为月份中的第一天
      d.setDate(1);
      //需要显示的月份的第一天是星期几
      firstday = d.getDay();
      //1号前面需要补足的的空单元格的数
      firstspace = firstday;
      //设定日期为月份的最后一天
      d.setDate(monthdays);
      //需要显示的月份的最后一天是星期几
      lastday = d.getDay();
      //最后一天后面需要空单元格数
      lastspace = 6 - lastday;
      //前空单元格+总天数+后空单元格，用来控制循环
      totalcounts = firstspace*1 + monthdays*1 + lastspace*1;
      //count：大循环的变量;f_space:输出前空单元格的循环变量;l_space:用于输出后空单元格的循环变量
      var count,flag,f_space,l_space;
      //flag：前空单元格输完后令flag=1不再继续做这个小循环
      flag = 0;
      for(count=1;count<=totalcounts;count++)
      {
            //一开始flag=0，首先输出前空单元格，输完以后flag=1，以后将不再执行这个循环
            if(flag==0)
            {
                  if(firstspace!=0)
                  {
                        for(f_space=1;f_space<=firstspace;f_space++)
                        {
                              str += "<td> </td>";
                              if(f_space!= firstspace) count++;
                        }
                        flag = 1;
                        continue;
                  }
            }
            if((count-firstspace)<=monthdays)
            {
                  //输出月份中的所有天数
                  curday = d_year+","+(d_month*1+1)+","+(count - firstspace)+"|"
                  linkday = d_year+","+(d_month*1+1)+","+(count - firstspace)
                  var today = new Date();
                  if( (d_year == today.getYear()) && (d_month == today.getMonth()) && ((count-firstspace) == today.getDate()) )
                  {
                        //将本地系统中的当前天数高亮
                        str += "<td><span class='current'>" + (count - firstspace) + "</span></td>";
                  }else{
                        //不用高亮的部分,没有日志
                        str += "<td>" + (count - firstspace) + "</td>";
                  }
                  if(count%7==0)
                  {
                        if(count<totalcounts)
                        {
                              str += "</tr><tr>";
                        }else{
                              str += "</tr>";
                        }
                  }
            }else{
                  //如果已经输出了月份中的最后一天，就开始输出后空单元格补足
                  for(l_space=1;l_space<=lastspace;l_space++)
                  {
                        str += "<td> </td>";
                        if(l_space!= lastspace) count++;
                  }
                  continue;
            }
      }
      document.getElementById("calenderdiv").innerHTML = "<div id='calenderdiv'>" + str + "</div>";
}
//调用函数
calender("","")
</script>
		</div>
		<div class="deskmore"><em id="year">2014</em></div>
      </div>
   </div>
</body>
</html>

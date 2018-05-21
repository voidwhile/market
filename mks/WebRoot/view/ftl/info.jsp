<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>会议详情</title>
        <link href="${pageContext.request.contextPath}/library/css/detail.css" rel="stylesheet" />
	</head>
	<body>
		<div class="article">
            <div class="article-con">
            	<p class="article-tit">${meetingName}</p>
               <p class="article-jj"><span class="article-jj-date"><fmt:formatDate value="${beginTime }" pattern="yyyy-MM-dd HH:mm"/></span><span class="article-jj-come">主讲：${speakerUser}</span></p>
                <c:forEach var="a" items="${image }">
                	<img class="img-res" src="${imgurl }${a.path}"/>
                </c:forEach>
            </div>
        	
		</div>
	</body>
</html>
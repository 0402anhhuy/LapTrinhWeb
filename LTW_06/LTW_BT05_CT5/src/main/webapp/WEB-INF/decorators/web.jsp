<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<header>
    <div>
        <%@ include file="/commons/web/header.jsp"%>
    </div>
</header>
<div>
    <sitemesh:write property="body"/>
</div>
<footer>
    <div>
        <%@ include file="/commons/web/footer.jsp"%>
    </div>
</footer>
</body></html>
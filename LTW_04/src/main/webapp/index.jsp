<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lading page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap (tùy ý) -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">>
</head>
<body>
<h2>Hello World!</h2>
<div>
    <c:url value="/login" var="addUrl"/>
    <a href="${addUrl}" class="btn btn-success">Đăng nhập</a>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quên mật khẩu</title>
    <style>
        body { font-family: Arial; background-color: #f5f5f5; text-align: center; padding-top: 100px; }
        form { display: inline-block; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        input, button { margin-top: 10px; padding: 10px; width: 100%; }
        .alert { margin-bottom: 10px; color: white; padding: 10px; border-radius: 5px; }
        .alert-success { background-color: #4CAF50; }
        .alert-danger { background-color: #f44336; }
    </style>
</head>
<body>
    <form method="POST" action="forgotpassword">
        <c:if test="${not empty alert}">
            <div class="alert ${alert == 'Email không tồn tại' ? 'alert-danger' : 'alert-success'}">
                    ${alert}
            </div>
        </c:if>
        <h2>Quên mật khẩu</h2>
        <input type="email" name="email" placeholder="Nhập email của bạn" required />
        <button type="submit">Đặt lại mật khẩu</button>
    </form>
</body>
</html>

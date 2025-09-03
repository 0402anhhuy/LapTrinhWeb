<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 300px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        input[type="password"], button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .alert {
            margin-top: 10px;
            padding: 10px;
            border-radius: 4px;
            font-size: 14px;
        }

        .alert-success {
            background-color: #d4edda;
            color: #155724;
        }

        .alert-danger {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>
<form method="POST" action="resetpassword">
    <!-- Hiển thị thông báo -->
    <c:if test="${not empty alert}">
        <div class="alert ${alert == "Mật khẩu không trùng với nhau" ? 'alert-danger' : 'alert-success'}">
                ${alert}
        </div>
    </c:if>
    <h2>Đặt lại mật khẩu</h2>
    <!-- Truyền lại email ẩn -->
    <input type="hidden" name="email" value="${email}" />
    <!-- Nhập mật khẩu mới -->
    <input type="password" name="newPassword" placeholder="Mật khẩu mới" required />

    <!-- Nhập lại mật khẩu -->
    <input type="password" name="confirmPassword" placeholder="Nhập lại mật khẩu" required />

    <button type="submit">Xác nhận</button>


</form>

</body>
</html>

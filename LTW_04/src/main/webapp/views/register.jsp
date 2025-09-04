<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
        }

        .register-container {
            max-width: 420px;
            margin: 60px auto;
            padding: 25px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .alert {
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
            font-size: 14px;
        }

        .alert-danger {
            background-color: #f8d7da;
            color: #842029;
            border: 1px solid #f5c2c7;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 6px;
        }

        .input-group {
            display: flex;
            align-items: center;
            border: 1px solid #ccc;
            border-radius: 5px;
            overflow: hidden;
        }

        .input-group i {
            background: #eee;
            padding: 10px;
            color: #555;
        }

        .input-group input {
            border: none;
            flex: 1;
            padding: 10px;
            outline: none;
        }

        .btn-submit {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background: #28a745;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s;
        }

        .btn-submit:hover {
            background: #218838;
        }

    </style>
</head>
<body>

<div class="register-container">
    <form action="register" method="POST">
        <c:if test="${not empty alert}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>
        <h2>Tạo tài khoản mới</h2>
        <div class="form-group">
            <label>Tài khoản</label>
            <div class="input-group">
                <i class="fa fa-user"></i>
                <input type="text" name="username" placeholder="Nhập tài khoản" required>
            </div>
        </div>

        <div class="form-group">
            <label>Email</label>
            <div class="input-group">
                <i class="fa fa-envelope"></i>
                <input type="email" name="email" placeholder="Nhập email" required>
            </div>
        </div>

        <div class="form-group">
            <label>Mật khẩu</label>
            <div class="input-group">
                <i class="fa fa-lock"></i>
                <input type="password" name="password" placeholder="Nhập mật khẩu" required>
            </div>
        </div>

        <div class="form-group">
            <label>Họ và tên</label>
            <div class="input-group">
                <i class="fa fa-id-card"></i>
                <input type="text" name="fullname" placeholder="Nhập họ tên đầy đủ" required>
            </div>
        </div>

        <div class="form-group">
            <label>Số điện thoại</label>
            <div class="input-group">
                <i class="fa fa-phone"></i>
                <input type="text" name="phone" placeholder="Nhập số điện thoại" required>
            </div>
        </div>

        <button type="submit" class="btn-submit">Đăng ký</button>
    </form>
</div>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/30/2025
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
        }

        .register-container {
            width: 350px;
            padding: 2em;
            background: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
        }

        h2 {
            margin-bottom: 1em;
            color: #333;
        }

        .form-group {
            margin-bottom: 1em;
            text-align: left;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="tel"] {
            width: 100%;
            padding: 0.75em;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1em;
        }

        input::placeholder {
            color: #999;
        }

        button {
            width: 100%;
            padding: 0.75em;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: #fff;
            font-size: 1em;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .login-link {
            margin-top: 1em;
            font-size: 0.9em;
            color: #333;
        }

        .login-link a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>Tạo tài khoản mới</h2>
    <form action="register" method="POST">
        <div class="form-group">
            <label>
                <input type="text" name="username" placeholder="Tên đăng nhập" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <input type="text" name="fullname" placeholder="Họ tên" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <input type="email" name="email" placeholder="Nhập Email" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <input type="tel" name="phone" placeholder="Số điện thoại" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <input type="password" name="password" placeholder="Mật khẩu" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <input type="password" name="confirmPassword" placeholder="Nhập lại mật khẩu" required>
            </label>
        </div>
        <button type="submit">Tạo tài khoản</button>
    </form>

    <div class="login-link">
        Nếu bạn đã có tài khoản? <a href="login">Đăng nhập</a>
    </div>
</div>
</body>
</html>

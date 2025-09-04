<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
            background-color: #f5f5f5;
        }

        .logout-btn {
            padding: 10px 20px;
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .logout-btn:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>

<h1>Chào mừng đến trang chủ!</h1>

<form action="home" method="POST">
    <button type="submit" class="logout-btn">Đăng xuất</button>
</form>

</body>
</html>

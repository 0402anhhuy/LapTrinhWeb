<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <style>
        body {
            background: #f5f7fa;
            font-family: "Segoe UI", Arial, sans-serif;
        }
        .card {
            border-radius: 1rem;
            box-shadow: 0 4px 12px rgba(0,0,0,.1);
        }
        .avatar-preview {
            width: 140px;
            height: 140px;
            border-radius: 50%;
            object-fit: cover;
            border: 2px solid #e0e0e0;
            background: #fafafa;
            display: none;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8">
            <div class="card p-4">
                <h2 class="h4 mb-4 text-primary text-center">Thêm danh mục mới</h2>

                <c:url value="/add" var="addUrl"/>
                <form action="${addUrl}" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>

                    <!-- Tên danh mục -->
                    <div class="mb-3">
                        <label for="name" class="form-label fw-semibold">Tên danh mục</label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="Nhập tên danh mục" required>
                        <div class="invalid-feedback">Vui lòng nhập tên danh mục.</div>
                    </div>

                    <!-- Số điện thoại -->
                    <div class="mb-3">
                        <label for="phone" class="form-label fw-semibold">Số điện thoại</label>
                        <input type="tel" class="form-control" id="phone" name="phone"
                               placeholder="VD: 0912345678 hoặc +84912345678"
                               pattern="^(0|\\+84)([0-9]{9,10})$" required>
                        <div class="invalid-feedback">Số điện thoại không hợp lệ.</div>
                    </div>

                    <!-- Upload ảnh -->
                    <div class="mb-3">
                        <label for="icon" class="form-label fw-semibold">Ảnh sản phẩm</label>
                        <input type="file" class="form-control" id="icon" name="icon" accept="image/*" onchange="previewImage(event)" required>
                        <img id="preview" class="avatar-preview" alt="Preview">
                        <div class="invalid-feedback">Vui lòng chọn một ảnh.</div>
                    </div>

                    <!-- Actions -->
                    <div class="d-flex justify-content-between mt-4">
                        <button type="reset" class="btn btn-outline-secondary">Nhập lại</button>
                        <button type="submit" class="btn btn-primary">Thêm mới</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Preview ảnh
    function previewImage(e) {
        const file = e.target.files[0];
        if (!file) return;
        const img = document.getElementById("preview");
        img.style.display = "block";
        img.src = URL.createObjectURL(file);
        img.onload = () => URL.revokeObjectURL(img.src);
    }

    // Bootstrap validation
    (() => {
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', e => {
                if (!form.checkValidity()) {
                    e.preventDefault();
                    e.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Profile - Cập nhật</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .profile-card { max-width: 700px; margin: auto; }
        .avatar-preview {
            width: 140px; height: 140px;
            border-radius: 50%;
            object-fit: cover;
            border: 2px solid #ddd;
            background: #f8f9fa;
        }
    </style>
</head>
<body class="bg-light">

<div class="container py-4">
    <div class="card shadow-sm profile-card">
        <div class="card-body">
            <h2 class="h4 mb-4 text-center">Cập nhật hồ sơ</h2>

            <!-- Thông báo -->
            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <!-- Form update -->
            <form action="${pageContext.request.contextPath}/profile/update"
                  method="post" enctype="multipart/form-data"
                  class="needs-validation" novalidate>

                <!-- Avatar -->
                <div class="mb-4 text-center">
                    <img id="avatarPreview"
                         src="<c:out value='${empty user.avatarUrl ? pageContext.request.contextPath.concat("/images/default-avatar.png") : user.avatarUrl}'/>"
                         class="avatar-preview mb-2"
                         alt="Avatar">
                    <input type="file" class="form-control mt-2" id="avatar" name="avatar" accept="image/*">
                </div>

                <!-- Fullname -->
                <div class="mb-3">
                    <label for="fullname" class="form-label">Họ và tên</label>
                    <input type="text" class="form-control" id="fullname" name="fullname"
                           value="<c:out value='${user.fullName}'/>" required>
                    <div class="invalid-feedback">Vui lòng nhập họ tên.</div>
                </div>

                <!-- Phone -->
                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="tel" class="form-control" id="phone" name="phone"
                           value="<c:out value='${user.phone}'/>"
                           pattern="^(0|\\+84)([0-9]{9,10})$" required>
                    <div class="invalid-feedback">SĐT không hợp lệ.</div>
                </div>

                <!-- Nút -->
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">Quay lại</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Preview avatar
    document.getElementById('avatar')?.addEventListener('change', function (e) {
        const file = e.target.files[0];
        if (!file) return;
        const reader = new FileReader();
        reader.onload = ev => document.getElementById('avatarPreview').src = ev.target.result;
        reader.readAsDataURL(file);
    });

    // Validation
    (() => {
        const form = document.querySelector('.needs-validation');
        form.addEventListener('submit', e => {
            if (!form.checkValidity()) {
                e.preventDefault();
                e.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    })();
</script>
</body>
</html>

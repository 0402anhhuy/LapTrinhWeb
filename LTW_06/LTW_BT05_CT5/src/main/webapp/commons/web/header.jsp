
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<header class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
    <div class="container">
        <!-- Logo -->
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" style="height:40px;">
            JewelryShop
        </a>

        <!-- Nút menu khi thu nhỏ -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Menu -->
        <div class="collapse navbar-collapse" id="mainNavbar">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/products">Product</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/about">Introduce</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/contact">About</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cart">🛒Cart</a></li>
            </ul>
        </div>
    </div>
</header>

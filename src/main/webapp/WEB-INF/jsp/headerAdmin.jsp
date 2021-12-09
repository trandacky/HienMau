<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vinh Danh Hiến Máu</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>

<style>
.bg-image {
	background-image: url("/image/background3.jpg");
	background-color: #cccccc;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: cover;
}
 .centertab {
	 margin: 0 auto;
	 width: 50%;
 }
.topright {
	position: absolute;
	top: 10px;
	right: 16px;
	font-size: 18px;
}
</style>
<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarTogglerDemo01"
				aria-controls="navbarTogglerDemo01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link ${activetrangchu}"
						href="/home">Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link ${activetrangcanhan}"
						href="/admin/trangcanhan">Trang cá nhân</a></li>
					<li class="nav-item"><a class="nav-link ${activetaikhoan}"
						href="/admin/taikhoan">Quản lý tài khoản</a></li>
					<li class="nav-item"><a class="nav-link ${activecoso}"
						href="/admin/coso">Cơ Sở</a></li>
					<li class="nav-item"><a class="nav-link ${activelichsuvinhdanh}"
						href="/admin/lichsuvinhdanh">Lịch sử vinh danh</a></li>
					<li class="nav-item"><a class="nav-link ${activevinhdanhmoi}"
											href="/admin/vinhdanhmoi">Vinh Danh Mới</a></li>
					<li class="nav-item"><a class="nav-link ${activedoimatkhau}"
											href="/admin/quen-mat-khau" onclick="return confirm('Thao tác trên sẽ không hoàn lại được! Bạn có chắc thực hiện thao tác?');">Đổi mật khẩu</a></li>
				</ul>

				<div class="topright">
					<label>Xin chào: ${tennguoidung}</label> <a href="/logout"><button
							type="button" class="btn btn-primary">Đăng xuất</button></a>
				</div>
			</div>
		</nav>
	</div>
</body>
<c:if test="${not empty message}">
	<div class="alert alert-${alert}">
			${message}
	</div>
</c:if>
</html>
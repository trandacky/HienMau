<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet"
		  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		  integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
		  crossorigin="anonymous">
</head>
<body>
<a href="/admin/taikhoan">
	<input type="button" class="btn btn-warning" value="< Quay lại Thêm tài khoản"></a>
<form action="/admin/taikhoan/capnhat/${nguoiDung.tenDangNhap}" class="card card-body" method="Post">
	<div class="row">
		<div class="col-md-6">
			<label>Tên đăng nhập</label>
			<input class="form-control" placeholder=" Tên đăng nhập" disabled  value="${nguoiDung.tenDangNhap}">
			<input type="hidden" name="tenDangNhap" value="${nguoiDung.tenDangNhap}">
		</div>
		<div class="col-md-6">
			<label>Email</label>
			<input class="form-control" placeholder="  Email" disabled  value="${nguoiDung.email}">
			<input type="hidden" name="email" value="${nguoiDung.email}">
		</div>
		<div class="col-md-6">
			<label>Địa chỉ</label>
			<input class="form-control" placeholder=" Địa chỉ" name="diaChi" value="${nguoiDung.diaChi}">
		</div>
		<div class="col-md-6">
			<label>Họ và tên</label>
			<input class="form-control" placeholder=" Họ và tên" name="hoTen" value="${nguoiDung.hoTen}">
		</div>
		<div class="col-md-6">
			<label>Ngày sinh</label>
			<input class="form-control" type="date" placeholder=" Ngày sinh" name="ngaySinh"
				   value="${nguoiDung.ngaySinh.toString().substring(0,10)}">
		</div>
		<div class="col-md-6">
			<label>Số điện thoại</label>
			<input class="form-control" placeholder=" Số điện thoại" name="soDienThoai"
				   value="${nguoiDung.soDienThoai}">
		</div>
		<div class="col-md-6">
			<label>Mật khẩu</label>
			<input class="form-control" type="password" placeholder=" Mật khẩu" name="matKhau">
		</div>
		<div class="col-md-6">
			<label>Giới tính</label>
			<select class="form-control" name ="gioiTinh">
				<option value="true" <c:if test="${nguoiDung.gioiTinh}">selected</c:if>>Nam</option>
				<option value="false" <c:if test="${!nguoiDung.gioiTinh}">selected</c:if>>Nữ</option>
			</select>
		</div>
		<div class="col-md-6">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit" type="submit">Sửa</button>
		</div>
	</div>
</form>
</body>
</html>
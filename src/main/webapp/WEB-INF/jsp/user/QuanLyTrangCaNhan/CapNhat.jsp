<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<c:import url="/WEB-INF/jsp/HeaderUser.jsp" />
	
		<div class="content form-control">
			<div class="container-fluid">
			<form method="post" action="/nguoidung/trangcanhan/capnhat/submit">
				<div class="row">
					
					<div class="col-md-5">
						<div class="form-group">
							<label>Tên đăng nhập: </label> <input class="form-control"
								name="tendangnhaplamcanh" value="${taikhoan.tenDangNhap}"
								 disabled>
								 
								 <input class="form-control"
								name="tendangnhap" value="${taikhoan.tenDangNhap}"
								 type="hidden">
								 
								 <input class="form-control"
								name="matkhau" value="${taikhoan.matKhau}" type="hidden">
								
								<input class="form-control"
								name="quyen" value="${taikhoan.quyen}" type="hidden">
								
						</div>
					</div>
				</div>
				<div class="row">
					
					<div class="col-md-5">
						<div class="form-group">
							<label>Email: </label>
							<input class="form-control"
								name="email" value="${taikhoan.email}"
								placeholder="${taikhoan.email}">
						</div>
					</div>
				</div>
				<div class="row">
					
					<div class="col-md-5">
						<div class="form-group">
							<label>Giới tính: </label>
							<select value="${taikhoan.gioiTinh}" class="form-control"
							name="gioitinh">
							<option <c:if test="${taikhoan.gioiTinh==true}">selected</c:if> value=true>Nam</option>
							<option <c:if test="${taikhoan.gioiTinh==false}">selected</c:if> value=false>Nữ</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					
					<div class="col-md-5">
						<div class="form-group">
							<label>Họ tên: </label>
							<input class="form-control"
								name="hoten" value="${taikhoan.hoTen}"
								placeholder="${taikhoan.hoTen}">
						</div>
					</div>
				</div>
				<div class="row">
					
					<div class="col-md-5">
						<div class="form-group">
							<label>Ngày tháng năm sinh: </label>
							<input class="form-control" value="${taikhoan.ngayThangNamSinh}" type="date" name="ngaythangnamsinh">
						</div>
					</div>
				</div>
				<div class="row">
					
					<div class="col-md-5">
						<div class="form-group">
							<label>Quê quán: </label>
							<input class="form-control"
								name="quequan" value="${taikhoan.queQuan}"
								placeholder="${taikhoan.queQuan}">
						</div>
					</div>
				</div>
				<div class="row">
					
					<div class="col-md-5">
						<div class="form-group">
							<label>Số điện thoại liên hệ: </label>
							<input class="form-control"
								name="sodienthoailienhe" value="${taikhoan.soDienThoaiLienHe}"
								placeholder="${taikhoan.soDienThoaiLienHe}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-5">
						<div class="clearfix" style="float: left;">
							<button class="btn btn-primary pull-left" onclick="demo()"
								type="submit">Hoàn tất</button>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
	
	
</body>
<script>
	function demo() {
		alert("Đã cập nhật!");
	}
</script>
</html>
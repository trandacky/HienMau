<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="/quanly/taikhoan/them" method="post">
	<div class="content form-control bg-image">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">

					<div class="form-group">
						<label>Tên đăng nhập: </label> <input class="form-control" name="tendangnhap">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Password: </label> <input class="form-control" name="matkhau"
							type="password">
					</div>
				</div>
				<div class="col-md-6">

					<div class="form-group">
						<label>Họ tên: </label> <input class="form-control" name="hoten">
					</div>
				</div>
				<div class="col-md-4">

					<div class="form-group">
						<label>Số điện thoại: </label> <input class="form-control" name="sodienthoai">
					</div>
				</div>

				<div class="col-md-2">
					<div class="form-group">
						<label>Quyền: </label> <select class="form-control" name="quyen"
							id="selectquyen">
							<option value=0>DISABLE</option>
							<option value="ROLE_ADMIN" selected>ADMIN</option>
							<option value="ROLE_USER">USER</option>

						</select>
					</div>
				</div>
			</div>
			
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">

					<div class="form-group">
						<label>Email: </label> <input class="form-control" name="email">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Quê quán: </label> <input class="form-control" name="quequan">
					</div>
				</div>
				<div class="col-md-2">

					<div class="form-group">
						<label>Ngày tháng năm sinh: </label> <input class="form-control"  type="date" name="ngaythangnamsinh">
					</div>
				</div>

				<div class="col-md-2">
					<div class="form-group">
						<label>Giới tính: </label> <select class="form-control"
							name="gioitinh">
							<option value=true>NAM</option>
							<option value=false>Nữ</option>

						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit"
							onclick="#">Thêm</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
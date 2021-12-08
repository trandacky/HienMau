<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="/quanly/taikhoan/capnhat" method="post">
<a href="quanly/taikhoan"><button class="btn btn-primary">Quay về trang thêm</button> </a>
	<div class="content form-control bg-image">
	
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">

					<div class="form-group">
						<label>Tên đăng nhập: </label> <label>${tendangnhap}</label>
						<input type="hidden" value="${tendangnhap}" name="tendangnhap">
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
						<label>Họ tên: </label> <input class="form-control" value="${hoten}" name="hoten">
					</div>
				</div>
				<div class="col-md-4">

					<div class="form-group">
						<label>Số điện thoại: </label> <input class="form-control" value="${sodienthoai}" name="sodienthoai">
					</div>
				</div>

				<div class="col-md-2">
					<div class="form-group">
						<label>Quyền: </label> <select class="form-control"  name="quyen">
							<option <c:if test="${quyen==0}">selected</c:if> value=0>DISABLE</option>
							<option <c:if test="${quyen==1}">selected</c:if> value="1">ADMIN</option>
							<option <c:if test="${quyen==2}">selected</c:if> value="2">USER</option>
						</select>
					</div>
				</div>
			</div>
			
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">

					<div class="form-group">
						<label>Email: </label> <input class="form-control" value="${email}" name="email">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Quê quán: </label> <input class="form-control" value="${quequan}" name="quequan">
					</div>
				</div>
				<div class="col-md-2">

					<div class="form-group">
						<label>Ngày tháng năm sinh: </label> <input class="form-control" value="${ngaythangnamsinh}" type="date" name="ngaythangnamsinh">
					</div>
				</div>

				<div class="col-md-2">
					<div class="form-group">
						<label>Giới tính: </label> <select value="${gioitinh}" class="form-control"
							name="gioitinh">
							<option <c:if test="${gioitinh==true}">selected</c:if> value=true>NAM</option>
							<option <c:if test="${gioitinh==false}">selected</c:if> value=false>Nữ</option>

						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit"
							onclick="#">Cập nhật</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
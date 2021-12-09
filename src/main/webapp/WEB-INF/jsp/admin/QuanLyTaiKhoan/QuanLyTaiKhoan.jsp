<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký kí túc xá</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
<c:import url="/WEB-INF/jsp/headerAdmin.jsp"/>
<%--<c:import url="${FormUpdateOrAdd}"/>--%>
<form action="/admin/coso" class="card card-body" method="Post">
    <div class="row">
        <div class="col-md-6">
            <label>Tên đăng nhập</label>
            <input class="form-control" placeholder=" Tên đăng nhập" name="tenDangNhap">
        </div>
        <div class="col-md-6">
            <label>Email</label>
            <input class="form-control" placeholder=" Email" name="email">
        </div>
        <div class="col-md-6">
            <label>Mật Khẩu</label>
            <input class="form-control" type="password" placeholder=" Mật Khẩu" name="matKhau">
        </div>
        <div class="col-md-6">
            <label>Địa chỉ</label>
            <input class="form-control" placeholder=" Địa chỉ" name="diaChi">
        </div>
        <div class="col-md-6">
            <label>Họ và tên</label>
            <input class="form-control" placeholder=" Họ và tên" name="hoVaTen">
        </div>
        <div class="col-md-6">
            <label>Ngày sinh</label>
            <input class="form-control" placeholder=" Ngày sinh" name="ngaySinh">
        </div>
        <div class="col-md-6">
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit" type="submit">Thêm</button>
        </div>
    </div>
</form>
<div>
    <form class="form-inline my-2 my-lg-0 form-control" action="/quanly/taikhoan/seach" method="get">
        <input class="form-control mr-sm-2"
               placeholder="Tìm kiếm" name="seach" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" type="submit"
                onclick="#">Tìm kiếm
        </button>

    </form>
</div>

<div class="text-center">
    <table class="table table-striped">
        <tr>
            <th>Tên đăng nhập</th>
            <th>Họ tên</th>
            <th>Ngày tháng năm sinh</th>
            <th>Số điện thoại</th>
            <th>Email</th>
            <th>Giới tính</th>
            <th>Quyền</th>

        </tr>
        <c:forEach items="${nguoiDungs}" var="nguoiDung">
            <tr>
                <td><a href="/quanly/taikhoan/capnhat/${nguoiDung.tenDangNhap}">${nguoiDung.tenDangNhap}</a></td>
                <td><a href="/quanly/taikhoan/capnhat/${nguoiDung.tenDangNhap}">${nguoiDung.hoTen}</a></td>
                <td>${nguoiDung.ngaySinh}</td>
                <td>${nguoiDung.soDienThoai}</td>
                <td>${nguoiDung.email}</td>
                <td><c:if test="${nguoiDung.gioiTinh==false}">Nữ</c:if><c:if
                        test="${nguoiDung.gioiTinh==true}">Nam</c:if></td>
                <td>${nguoiDung.getQuyenNguoiDungs()}</td>
            </tr>
        </c:forEach>

    </table>
</div>
<%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
<div class="text-center">
    <H5>
        <c:forEach begin="1" end="${totalPage}" var="p">
            <a href="<c:url value="/admin/taikhoan" >
                <c:param name="page" value="${p}"/>${p}</c:url>">
                    ${p}</a>
        </c:forEach>
    </H5>
</div>
</body>
</html>
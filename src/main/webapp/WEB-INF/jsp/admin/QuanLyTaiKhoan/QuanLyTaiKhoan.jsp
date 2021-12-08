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
<c:if test="${not empty themfail}">
    <div class="alert alert-${alert}">
            ${themfail}
    </div>
</c:if>
<%--<c:import url="${FormUpdateOrAdd}"/>--%>
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
        <c:forEach items="${nguoiDung}" var="nguoiDungs">
            <tr>
                <td><a href="/quanly/taikhoan/capnhat/${nguoiDung.tenDangNhap}">${nguoiDung.tenDangNhap}</a></td>
                <td><a href="/quanly/taikhoan/capnhat/${nguoiDung.tenDangNhap}">${nguoiDung.hoTen}</a></td>
                <td>${nguoiDung.ngayThangNamSinh}</td>
                <td>${nguoiDung.soDienThoaiLienHe}</td>
                <td>${nguoiDung.email}</td>
                <td><c:if test="${nguoiDung.gioiTinh==false}">Nữ</c:if><c:if
                        test="${nguoiDung.gioiTinh==true}">Nam</c:if></td>
                <td><input type="button"
                           style='background-color:
                           <c:if test="${nguoiDung.quyen==0}">red</c:if>
                           <c:if test="${nguoiDung.quyen==1}">blue</c:if>
                           <c:if test="${nguoiDung.quyen==2}">green</c:if>'
                           class="btn btn-primary" disabled
                           value="<c:if test="${nguoiDung.quyen==0}">DISABLE</c:if><c:if test="${nguoiDung.quyen==1}">ADMIN</c:if>
						<c:if test="${nguoiDung.quyen==2}">USER</c:if>"></input></td>
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
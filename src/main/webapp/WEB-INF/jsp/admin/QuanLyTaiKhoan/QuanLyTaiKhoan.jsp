<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
<c:import url="/WEB-INF/jsp/headerAdmin.jsp"/>
<c:if test="${not empty them}">
    <c:import url="/WEB-INF/jsp/admin/QuanLyTaiKhoan/Them.jsp"/>
</c:if>
<c:if test="${not empty sua}">
    <c:import url="/WEB-INF/jsp/admin/QuanLyTaiKhoan/CapNhat.jsp"/>
</c:if>


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
            <%--            <th>Quyền</th>--%>
            <th>Tình Trạng</th>
            <th>Thao tác</th>

        </tr>
        <c:forEach items="${nguoiDungs}" var="nguoiDung">
            <tr>
                <td><a href="/admin/taikhoan/capnhat/${nguoiDung.tenDangNhap}">${nguoiDung.tenDangNhap}</a></td>
                <td><a href="/admin/taikhoan/capnhat/${nguoiDung.tenDangNhap}">${nguoiDung.hoTen}</a></td>
                <td>${nguoiDung.ngaySinh}</td>
                <td>${nguoiDung.soDienThoai}</td>
                <td>${nguoiDung.email}</td>
                <td><c:if test="${nguoiDung.gioiTinh==false}">Nữ</c:if><c:if
                        test="${nguoiDung.gioiTinh==true}">Nam</c:if></td>
                    <%--                <td>${nguoiDung.getQuyenNguoiDungs()}</td>--%>
                <td><a href="/admin/taikhoan/active/${nguoiDung.tenDangNhap}">
                    <input type="button" class="btn btn-primary"
                           style='background-color:
                           <c:if test="${nguoiDung.getActive()==false}">red</c:if>
                           <c:if test="${nguoiDung.getActive()==true}">green</c:if>'
                           value="<c:if test="${nguoiDung.getActive()==false}">Đã ngừng hoạt động</c:if>
                            <c:if test="${nguoiDung.getActive()==true}">Đang hoạt động</c:if>"/>
                </a></td>
                <td><a href="/admin/taikhoan/capnhat/${nguoiDung.tenDangNhap}">
                    <input type="button" class="btn btn-warning" value="Sửa"></a>
                </td>
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
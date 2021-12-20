<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
<c:import url="/WEB-INF/jsp/headerAdmin.jsp"/>
<h2>Danh sách hiến máu cho cơ sở ${coso.tenCoSo}</h2>
<div class="row">
    <div class="col-md-12">
        <form class="form-inline my-2 my-lg-0 form-control" enctype="MULTIPART/FORM-DATA"  action="/admin/coso/danhsach?id=${coso.id}" method="Post">
            <label class="form-label" for="customFile">Upload Danh Sách Hiến Máu Mới</label>
            <input type="file" class="form-control col-md-7" name="file" id="customFile"/>
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Upload</button>
        </form>
    </div>
</div>
<br>
<br>
<div>
    <form class="form-inline my-2 my-lg-0 form-control" action="/admin/coso/search" method="get">
        <input class="form-control mr-sm-2"
               placeholder="Tìm kiếm" name="seach" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" type="submit">Tìm kiếm</button>
    </form>
</div>

<div class="text-center">
    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>Người hiến</th>
            <th>Số điện thoại</th>
            <th>CMND/CCCD</th>
            <th>Email</th>

            <th>Ngày sinh</th>
            <th>Địa chỉ</th>
            <th>Nhóm máu</th>
            <th>Giới tính</th>
            <th>Mức tôn vinh</th>
            <th>Số lần hiến</th>
            <th>Lần hiến gần nhât</th>

        </tr>
        <c:forEach items="${nguoiHienMaus}" var="nguoiHienMau">
            <tr>
                <td>${nguoiHienMau.id}</td>
                <td>${nguoiHienMau.hoTen}</td>
                <td>${nguoiHienMau.soDienThoai}</td>
                <td>${nguoiHienMau.cmndOrCccd}</td>
                <td>${nguoiHienMau.email}</td>
                <td>${nguoiHienMau.ngaySinh}</td>

                <td>${nguoiHienMau.diaChi}</td>
                <td>${nguoiHienMau.nhomMau}</td>
                <td>
                    <c:if test="${nguoiHienMau.gioiTinh==false}">Nữ</c:if>
                    <c:if test="${nguoiHienMau.gioiTinh==true}">Nam</c:if>
                </td>
                <td>${nguoiHienMau.mucTonVinh}</td>
                <td>${nguoiHienMau.soLanHienMau}</td>
                <td>${nguoiHienMau.lanHienGanNhat}</td>

            </tr>
        </c:forEach>

    </table>
</div>
<%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
<div class="text-center">
    <H5>
        <c:forEach begin="1" end="${totalPage}" var="p">
            <a href="<c:url value="/admin/coso/danhsach?id=${coso.id}" >
                <c:param name="page" value="${p}"/>${p}</c:url>">
                    ${p}</a>
        </c:forEach>
    </H5>
</div>
</body>
</html>
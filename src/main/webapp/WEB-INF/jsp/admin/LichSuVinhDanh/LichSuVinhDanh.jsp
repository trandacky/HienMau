<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
<c:import url="/WEB-INF/jsp/headerAdmin.jsp"/>
<div class="row">
    <div class="col-md-12">
        <form class="form-inline my-2 my-lg-0 form-control"  action="/admin/coso/id/upload" method="Post">
            <label class="form-label" for="customFile">Upload Danh Sách Hiến Máu Mới</label>
            <input type="file" class="form-control col-md-7" id="customFile"/>
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
            <th>Ngày sinh</th>
            <th>Địa chỉ</th>
            <th>Mức tôn vinh</th>
            <th>Số lần hiến</th>
            <th>Lần hiến gần nhât</th>
        </tr>
        <c:forEach items="${coSos}" var="coSo">
            <tr>
                <td><a href="/admin/coso/danhsach?id=${coSo.id}">${coSo.id}</a></td>
                <td><a href="/admin/coso/danhsach?id=${coSo.id}">${coSo.tenCoSo}</a></td>
                <td><a href="/admin/coso/doiquyen?id=${coSo.id}">
                    <input type="button" class="btn btn-primary"
                           style='background-color:
                           <c:if test="${coSo.tinhTrangHoatDong==false}">red</c:if>
                           <c:if test="${coSo.tinhTrangHoatDong==true}">green</c:if>'
                           value="<c:if test="${coSo.tinhTrangHoatDong==false}">Đã ngừng hoạt động</c:if>
                            <c:if test="${coSo.tinhTrangHoatDong==true}">Đang hoạt động</c:if>
						<c:if test="${nguoiDung.quyen==2}">USER</c:if>"/>
                </a></td>
            </tr>
        </c:forEach>

    </table>
</div>
<%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
<div class="text-center">
    <H5>
        <c:forEach begin="1" end="${totalPage}" var="p">
            <a href="<c:url value="/admin/coso" >
                <c:param name="page" value="${p}"/>${p}</c:url>">
                    ${p}</a>
        </c:forEach>
    </H5>
</div>
</body>
</html>
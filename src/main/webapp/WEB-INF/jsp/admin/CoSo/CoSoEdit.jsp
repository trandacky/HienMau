<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
<c:import url="/WEB-INF/jsp/headerAdmin.jsp"/>
<a href="/admin/coso">
    <input type="button" class="btn btn-warning" value="< Quay lại Thêm Cơ sở"></a>
<form action="/admin/coso/${coso.id}" method="Post">
    <div class="col-md-12 centertab">
        <div class="card">
            <div class="card-body">
                <input class="form-control col-md-12"
                       placeholder="Tên Cơ sở" name="tenCoSo" value="${coso.tenCoSo}">
                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit" type="submit">Sửa</button>
            </div>
        </div>
    </div>
</form>
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
            <th>Tên Cơ Sở</th>
            <th>Tình trạng hoạt động</th>
        </tr>
        <c:forEach items="${coSos}" var="coSo">
            <tr>
                <td><a href="/admin/coso/danhsach?id=${coSo.id}">${coSo.id}</a></td>
                <td><a href="/admin/coso/danhsach?id=${coSo.id}">${coSo.tenCoSo}</a></td>
                <td><a href="/admin/coso/doiquyen?id=${coSo.id}">
                    <input type="button"  class="btn btn-primary"
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
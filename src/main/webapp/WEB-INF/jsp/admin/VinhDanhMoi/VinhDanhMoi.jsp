<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
<c:import url="/WEB-INF/jsp/headerAdmin.jsp"/>
<h2>Vinh Danh Mới</h2>
<div class="text-center">
    <h2>Danh Sách đề xuất tôn vinh</h2>
    <div class="clearfix" style="float: right;">
        <td><a href="/admin/vinhdanhmoi/sync">
            <input type="button"  class="btn btn-primary"
                   style='background-color:
                   <c:if test="${tuDongDeXuat==false}">red</c:if>
                   <c:if test="${tuDongDeXuat==true}">green</c:if>'
                   value="<c:if test="${tuDongDeXuat==false}">Bật tự động đề xuất</c:if>
                            <c:if test="${tuDongDeXuat==true}">Tắt tự động đề xuất</c:if>"/>
        </a></td>



    </div>
    <table class="table table-striped">
        <tr>
            <th>Họ tên</th>
            <th>Tên Cơ Sở</th>
            <th>Số lần hiến</th>
            <th>Gợi ý vinh danh</th>
            <th>Thao tác</th>
        </tr>
        <c:forEach items="${deXuatTonVinhs}" var="deXuatTonVinh">
            <tr>
                <td>${deXuatTonVinh.getNguoiHienMau().getHoTen()}</td>
                <td>${deXuatTonVinh.getNguoiHienMau().getCoSo().getTenCoSo()}</td>
                <td>${deXuatTonVinh.getNguoiHienMau().getSoLanHienMau()}</td>
                <td>${deXuatTonVinh.mucTonVinh}</td>
<%--                <td><a href="/admin/coso/doiquyen?id=${coSo.id}">--%>
<%--                    <input type="button"  class="btn btn-primary"--%>
<%--                           style='background-color:--%>
<%--                           <c:if test="${coSo.tinhTrangHoatDong==false}">red</c:if>--%>
<%--                           <c:if test="${coSo.tinhTrangHoatDong==true}">green</c:if>'--%>
<%--                           value="<c:if test="${coSo.tinhTrangHoatDong==false}">Đã ngừng hoạt động</c:if>--%>
<%--                            <c:if test="${coSo.tinhTrangHoatDong==true}">Đang hoạt động</c:if>--%>
<%--						<c:if test="${nguoiDung.quyen==2}">USER</c:if>"/>--%>
<%--                </a></td>--%>
<%--                <td>{{deXuatTonVinh.}}</td>--%>
                <td><a href="#">
                    <button class="btn btn-warning">Sửa</button>

                </a></td>
            </tr>
        </c:forEach>

    </table>
</div>
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
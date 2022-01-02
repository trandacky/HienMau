<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
<c:import url="/WEB-INF/jsp/headerAdmin.jsp"/>
<h2>Lịch sử Vinh Danh</h2>
<div class="card text center">
    <div class="card-body">

        <form action="/admin/lichsuvinhdanh" method="get" class="row">
            Năm:
            <div class="col-md-3">
                <select class="form-control" name="year">
                    <%
                        for (int i = 2020; i < 2030; i += 1) {
                    %>
                    <option value="<%=i%>"><%=i%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>
            <button class="btn btn-primary">Tìm</button>
        </form>
    </div>
</div>
<a href="/admin/lichsuvinhdanh/xuatfile/${year}">
    <button class="btn btn-primary"
            onclick="return confirm('bạn có chắc muốn xuất file excel cho năm ${year}?');"
            style="float: right">Xuất file
    </button>
</a>
<div>
    <form class="form-inline my-2 my-lg-0 form-control" action="/admin/coso/search" method="get">
        <input class="form-control mr-sm-2"
               placeholder="Tìm kiếm" name="seach" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" type="submit">Tìm kiếm</button>
    </form>
</div>

<div class="text-center">
    <h3>Năm: ${year}</h3>
    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>Người hiến</th>
            <th>Cơ sở</th>
            <th>CMND/CCCD</th>
            <th>Ngày sinh</th>
            <th>Mức tôn vinh</th>
            <th>Số lần hiến</th>
            <th>Ngày vinh danh</th>
            <th>Lần hiến gần nhât</th>
        </tr>
        <c:forEach items="${tonVinhs}" var="tonVinh">
            <tr>
                <td><a href="/admin/coso/danhsach?id=${tonVinh.id}">${tonVinh.id}</a></td>
                <td><a href="/admin/coso/danhsach?id=${tonVinh.id}">${tonVinh.getNguoiHienMau().getHoTen()}</a></td>
                <td>${tonVinh.getNguoiHienMau().getCoSo().getTenCoSo()}</td>
                <td>${tonVinh.getNguoiHienMau().getCmndOrCccd()}</td>
                <td>${tonVinh.getNguoiHienMau().getNgaySinh()}</td>

                <td>${tonVinh.mucTonVinh}</td>
                <td>${tonVinh.getNguoiHienMau().getSoLanHienMau()}</td>
                <td>${tonVinh.ngayTonVinh}</td>
                <td>${tonVinh.getNguoiHienMau().getLanHienGanNhat()}</td>
                    <%--                <td><a href="/admin/coso/doiquyen?id=${coSo.id}">--%>
                    <%--                    <input type="button" class="btn btn-primary"--%>
                    <%--                           style='background-color:--%>
                    <%--                           <c:if test="${coSo.tinhTrangHoatDong==false}">red</c:if>--%>
                    <%--                           <c:if test="${coSo.tinhTrangHoatDong==true}">green</c:if>'--%>
                    <%--                           value="<c:if test="${coSo.tinhTrangHoatDong==false}">Đã ngừng hoạt động</c:if>--%>
                    <%--                            <c:if test="${coSo.tinhTrangHoatDong==true}">Đang hoạt động</c:if>--%>
                    <%--						<c:if test="${nguoiDung.quyen==2}">USER</c:if>"/>--%>
                    <%--                </a></td>--%>
            </tr>
        </c:forEach>

    </table>
</div>
<%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
<div class="text-center">
    <H5>
        <c:forEach begin="1" end="${totalPage}" var="p">
            <a href="<c:url value="/admin/lichsuvinhdanh" >
                <c:param name="page" value="${p}"/>${p}</c:url>&year=${year}">
                    ${p}</a>
        </c:forEach>
    </H5>
</div>
</body>
</html>
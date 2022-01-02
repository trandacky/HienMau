<%@ page import="java.util.List" %>
<%@ page import="com.k41.entity.TonVinh" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<style>
    .img-size {
        height: auto;
        width: auto;
        max-width: 100px;
        max-height: 100px;
    }

    .img-size2 {
        height: auto;
        width: auto;
        max-width: 50px;
        max-height: 50px;
    }
</style>
<body>
<c:if test="${not empty tennguoidung}">
    <c:import url="/WEB-INF/jsp/headerAdmin.jsp"/>
</c:if>
<c:if test="${empty tennguoidung}">
    <c:import url="/WEB-INF/jsp/HeaderUser.jsp"/>
</c:if>

<div class="text-center"><h3>Trang Chủ</h3></div>
<div>
    <form action="/home" method="get">
        Năm:
        <div class="row">
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
        </div>
    </form>
</div>
<div class="text-center">
    <h3>Năm: ${year}</h3>
    <table class="table table-striped text-center">
        <%
            List<TonVinh> tonVinhs = (List<TonVinh>) request.getAttribute("tonVinhs");
            int size = 5;
            for (int i = 0; i <= tonVinhs.size(); i = i + size) {
                if (i + size < tonVinhs.size()) {
                    request.setAttribute("tonVinhs2", tonVinhs.subList(i, i + size));
                } else {
                    request.setAttribute("tonVinhs2", tonVinhs.subList(i, tonVinhs.size()));
                }
        %>
        <tr class="text-center">
            <c:forEach items="${tonVinhs2}" var="tonVinh">
                <c:if test="${tonVinh.mucTonVinh <= 5}">
                    <td><img src="/image/heartxanh.png" class="img-size">
                        <h5> ${tonVinh.getNguoiHienMau().getHoTen()} </h5>
                        Cấp độ vinh danh: ${tonVinh.mucTonVinh}
                        <br>
                        Ngày sinh: ${tonVinh.getNguoiHienMau().getNgaySinh().toString().substring(0, 10)}
                        <br>
                        Ngày Vinh Danh: ${tonVinh.ngayTonVinh.toString().substring(0, 10)}
                    </td>

                </c:if>
                <c:if test="${(tonVinh.mucTonVinh>5)&&(tonVinh.mucTonVinh<=10)}">
                    <td><img src="/image/heartdo.png" class="img-size">
                        <h5> ${tonVinh.getNguoiHienMau().getHoTen()} </h5>
                        Cấp độ vinh danh: ${tonVinh.mucTonVinh}
                        <br>
                        Ngày sinh: ${tonVinh.getNguoiHienMau().getNgaySinh().toString().substring(0, 10)}
                        <br>
                        Ngày Vinh Danh: ${tonVinh.ngayTonVinh.toString().substring(0, 10)}
                    </td>
                </c:if>
                <c:if test="${tonVinh.mucTonVinh>10}">
                    <td><img src="/image/hearthong.png" class="img-size">
                        <h5> ${tonVinh.getNguoiHienMau().getHoTen()} </h5>
                        Cấp độ vinh danh: ${tonVinh.mucTonVinh}
                        <br>
                        Ngày sinh: ${tonVinh.getNguoiHienMau().getNgaySinh().toString().substring(0, 10)}
                        <br>
                        Ngày Vinh Danh: ${tonVinh.ngayTonVinh.toString().substring(0, 10)}
                    </td>
                </c:if>

            </c:forEach>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
<div class="text-center">
    <H5>
        <c:forEach begin="1" end="${totalPage}" var="p">
            <a href="<c:url value="/home" >
                <c:param name="page" value="${p}"/>${p}</c:url>&year=${year}">
                    ${p}</a>
        </c:forEach>
    </H5>
</div>
</html>
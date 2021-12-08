<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<c:import url="/WEB-INF/jsp/${headerForm}" />
	<c:if test="${not empty noidungalert}">
		<div class="alert alert-${alert}">${noidungalert}</div>
	</c:if>
	<div class="text-center"><h3>Khu</h3></div>
	<div class="text-center form-control">
		<table class="table table-striped">
			<tr>
				<c:forEach items="${ListKhu}" var="khuv">

					<td
						class="btn btn-outline-warning <c:if test="${khuv.getIdKhu()==khu.getIdKhu()}">active</c:if>"><a
						href="/home/khu/idkhu=${khuv.idKhu}&tang=<% if(request.getSession().getAttribute("giuong")=="") ;else out.print(1); %>"><img
							src="/image/ktx.jpg" class="img-size">
							<h3>${khuv.tenKhu}</h3>Số tầng: ${khuv.soTang}<br>Giới tính:
							${khuv.gioiTinh==true ? 'Nam':'Nữ'}</a></td>
				</c:forEach>
			</tr>
		</table>
	</div>
	<c:import url="/WEB-INF/jsp/user/${formtang}" />
	<c:import url="/WEB-INF/jsp/user/${formphong}" />
	<c:import url="/WEB-INF/jsp/user/${formgiuong}" />
</body>
</html>
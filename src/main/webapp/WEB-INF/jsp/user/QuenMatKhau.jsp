<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên Mật Khẩu</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <style>
        .centertab {
            margin: 0 auto;
            width: 250px;
        }

        .bg-image {
            background-color: #cccccc;
        }
    </style>
</head>
<body>
<c:if test="${not empty message}">
    <div class="text-center">
        <div class="alert alert-${alert}">
                ${message}
        </div>
        <h2><a href="/"> Trang Chủ</a></h2>
    </div>
</c:if>
<c:if test="${alert!='danger'}">
    <div class="text-center">
        <div class="container-fluid">
            <form method="post" action="/quen-mat-khau/${uuid}">
                <div class="col-md-12 centertab">
                    <label>Mật Khẩu</label>
                    <input class="form-control"
                           name="matKhauMoi" placeholder="Mật khẩu mới" min="3">
                </div>
                <div class="col-md-12 centertab">
                    <label>Mật Khẩu</label>
                    <input class="form-control"
                           name="nhapLaiMatKhauMoi" placeholder="Nhập lại mật khẩu mới" min="3">
                </div>
                <div class="row"></div>
                <button
                        class="btn btn-primary pull-left" type="submit">Đổi Mật Khẩu
                </button>
            </form>
        </div>
    </div>
</c:if>
</body>
</html>
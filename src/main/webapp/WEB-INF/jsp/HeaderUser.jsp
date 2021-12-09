<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
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
    .bg-image {
        background-image: url("/image/background3.jpg");
        background-color: #cccccc;
        background-repeat: no-repeat;
        background-position: center center;
        background-size: cover;
    }

    .topright {
        position: absolute;
        top: 10px;
        right: 16px;
        font-size: 18px;
    }

</style>
<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarTogglerDemo01"
                aria-controls="navbarTogglerDemo01" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <ul class="nav nav-tabs">
                <li class="nav-item"><a class="nav-link ${activetrangchu}" href="/home">Trang chủ</a>
                </li>
                <li class="nav-item"><a class="nav-link ${activetrangcanhan}" href="/nguoidung/trangcanhan">Trang cá
                    nhân</a>
                </li>
                <li class="nav-item"><a class="nav-link ${activelichsu}" href="/nguoidung/lichsu">Lịch sử đăng ký</a>
                </li>

            </ul>

            <div class="topright">
                ${urlAdmin}
                <label>Xin chào: ${tennguoidung}</label>

                <a href="/logout">
                    <button type="button" class="btn btn-primary">Đăng xuất</button>
                </a>
            </div>
        </div>
    </nav>
</div>
</body>
<c:if test="${not empty message}">
    <div class="alert alert-${alert}">
            ${message}
    </div>
</c:if>
</html>
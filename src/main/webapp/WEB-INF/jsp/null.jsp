<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
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
<body class="">
<div class="container">
    <div class="text-center">
        <div class="">
            <span> <img src="/image/logoqnu.jpg"></span><br>
            <span>
				 <H3>Hệ thống đăng ký kí túc xá</H3>
				 </span>
        </div>
        <div class="card-body">
            <form action="/loginktx" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <input type="text" name="username" class="form-control centertab"
                               placeholder="Tên đăng nhập">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-12">
                        <input type="password" name="password"
                               class="form-control centertab" placeholder="Mật khẩu">
                    </div>
                </div>
                <input name="remember-me" type="hidden" value="on">
                <br>
                <div class="form-group">
                    <button type="submit" class="btn btn-outline-info btn-lg centertab">Đăng nhập</button>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
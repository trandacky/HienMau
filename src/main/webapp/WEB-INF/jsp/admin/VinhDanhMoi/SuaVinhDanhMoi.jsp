<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
<div class="text-center">
    <form method="post" action="/admin/vinhdanhmoi/edit/${deXuatTonVinh.id}">
        <h5> Họ tên: ${deXuatTonVinh.getNguoiHienMau().getHoTen()}
            <br>
            Cơ sở: ${deXuatTonVinh.getNguoiHienMau().getCoSo().getTenCoSo()}
            <br>
            Số lần hiến: ${deXuatTonVinh.getNguoiHienMau().getSoLanHienMau()}
            <br>
            Đã tôn tinh: ${deXuatTonVinh.getNguoiHienMau().getMucTonVinh()}
        </h5>
        <div class="row card-body">
            <h5> Mức tôn vinh: </h5>
            <input class="form-control col-md-3" placeholder="Gợi ý tôn vinh" type="number" name="mucTonVinh"
                   value="${deXuatTonVinh.mucTonVinh}">
            <button class="btn btn-primary col-md-1">Sửa</button>
        </div>
    </form>

</div>
</body>
</html>
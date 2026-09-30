<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cập nhật Giảng viên</title>
</head>
<body>
<h2>Cập nhật Giảng viên</h2>

<form action="${pageContext.request.contextPath}/giang-vien/update" method="post">
    <input type="hidden" name="id" value="${gv.id}">

    Mã GV: <input type="text" name="mssv" value="${gv.mssv}"><br><br>
    Họ tên: <input type="text" name="ten" value="${gv.ten}"><br><br>
    Tuổi: <input type="number" name="tuoi" value="${gv.tuoi}"><br><br>
    Quê quán: <input type="text" name="queQuan" value="${gv.queQuan}"><br><br>

    Giới tính:
    <input type="radio" name="gioiTinh" value="1" ${gv.gioiTinh ? "checked" : ""}> Nam
    <input type="radio" name="gioiTinh" value="0" ${!gv.gioiTinh ? "checked" : ""}> Nữ
    <br><br>

    <button type="submit">Update</button>
</form>
</body>
</html>
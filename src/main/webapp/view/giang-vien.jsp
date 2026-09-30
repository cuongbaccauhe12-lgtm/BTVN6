<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản lý Giảng viên</title>
</head>
<body>

<!-- ===== FORM SEARCH ===== -->
<form action="${pageContext.request.contextPath}/giang-vien/tim-kiem" method="post">
    <fieldset style="width:320px">
        <legend>Tìm kiếm</legend>
        Tên: <input type="text" name="ten"><br><br>
        Tuổi Min: <input type="number" name="tuoiMin"><br><br>
        Tuổi Max: <input type="number" name="tuoiMax"><br><br>
        <button type="submit">Search</button>
    </fieldset>
</form>
<br>

<!-- ===== FORM ADD / DETAIL ===== -->
<form action="${pageContext.request.contextPath}/giang-vien/add" method="post">
    Mã GV: <input type="text" name="mssv" value="${gvDetail.mssv}">
    &nbsp;&nbsp;
    Họ tên: <input type="text" name="ten" value="${gvDetail.ten}"><br><br>

    Tuổi: <input type="number" name="tuoi" value="${gvDetail.tuoi}">
    &nbsp;&nbsp;
    Quê quán: <input type="text" name="queQuan" value="${gvDetail.queQuan}"><br><br>

    Giới tính:
    <input type="radio" name="gioiTinh" value="1" ${gvDetail.gioiTinh == true ? "checked" : ""}> Nam
    <input type="radio" name="gioiTinh" value="0" ${gvDetail.gioiTinh == false ? "checked" : ""}> Nữ
    <br><br>
    <button type="submit">Add</button>
</form>

<p style="color:red">${error}</p>
<br>

<!-- ===== TABLE ===== -->
<table border="1" cellpadding="6">
    <tr>
        <th>Mã GV</th>
        <th>Họ tên</th>
        <th>Tuổi</th>
        <th>Quê quán</th>
        <th>Giới tính</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${list}" var="gv">
        <tr>
            <td>${gv.mssv}</td>
            <td>${gv.ten}</td>
            <td>${gv.tuoi}</td>
            <td>${gv.queQuan}</td>
            <td>${gv.gioiTinh ? "Nam" : "Nữ"}</td>
            <td>
                <a href="${pageContext.request.contextPath}/giang-vien/detail?id=${gv.id}">
                    <button type="button">Detail</button>
                </a>
                <a href="${pageContext.request.contextPath}/giang-vien/view-update?id=${gv.id}">
                    <button type="button">Edit</button>
                </a>
                <a href="${pageContext.request.contextPath}/giang-vien/remove?id=${gv.id}"
                   onclick="return confirm('Bạn có chắc muốn xóa?')">
                    <button type="button">Remove</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/5/2023
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8"/>
    <title>Title</title>
</head>
<body>
<h1>Thêm mới danh mục</h1>
<form action="<%=request.getContextPath()%>/CatalogServlet" method="post">
<%--  <div>--%>
<%--    <label for="id">ID</label>--%>
<%--    <input type="text" id="id" name="id"/>--%>
<%--  </div>--%>
  <div>
    <label for="name">Name</label>
    <input type="text" id="name" name="name"/>
  </div>
  <button type="submit" name="action" value="ADD">Add</button>
</form>
</body>
</html>


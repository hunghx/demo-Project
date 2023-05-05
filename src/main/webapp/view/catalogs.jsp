<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/5/2023
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
</head>
<body>
  <h1>Danh sách danh mục</h1>
  <a href="<%=request.getContextPath()%>/CatalogServlet?action=CREATE">New Catalog</a>
  <form method="get" action="<%=request.getContextPath()%>/CatalogServlet">
    <input type="text" name="search" placeholder="..." value="${search}"/>
    <button type="submit" name="action" value="SEARCH">Search</button>
  </form>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>NAME</th>
    <th colspan="2">Action</th>

  </tr>
  </thead>
  <tbody>
  <c:forEach items="${list_catalog}" var="cat">
    <tr>
      <td>${cat.id}</td>
      <td>${cat.name}</td>
      <td><a href="<%=request.getContextPath()%>/CatalogServlet?action=EDIT&id=${cat.id}">Sửa</a></td>
      <td><a href="<%=request.getContextPath()%>/CatalogServlet?action=DELETE&id=${cat.id}">Xoá</a></td>
    </tr>
  </c:forEach>

  </tbody>
</table>
</body>
</html>

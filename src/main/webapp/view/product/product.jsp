<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06/05/2023
  Time: 10:44 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center">
    <a href="<%=request.getContextPath()%>/ProductServlet?action=CREATE">New product</a>
    <form method="get" action="<%=request.getContextPath()%>/ProductServlet">
        <input type="text" name="search" placeholder="..." value="${search}"/>
        <button type="submit" name="action" value="SEARCH">Search</button>
    </form>
    <form method="post" action="<%=request.getContextPath()%>/ProductServlet">
         <div>
            <label >catalog: </label>
            <select name="catalog"  >
                <c:forEach items="${list_cata}" var="cata">
                    <option value="${cata.id}" >${cata.name}</option>
                </c:forEach>
            </select>
        </div>
            <button type="submit" name="action" value="SEARCHBYCATALOG">search by catalog</button>
    </form>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PRICE</th>
            <th>QUANTITY</th>
            <th>CATALOG</th>
            <th colspan="2">Action</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list_product}" var="pr">
            <tr>
                <td>${pr.id}</td>
                <td>${pr.name}</td>
                <td>${pr.price}</td>
                <td>${pr.quantity}</td>
                <td>${pr.catalog.name}</td>
                <td><a href="<%=request.getContextPath()%>/ProductServlet?action=EDIT&id=${pr.id}">Sửa</a></td>
                <td><a href="<%=request.getContextPath()%>/ProductServlet?action=DELETE&id=${pr.id}">Xoá</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>

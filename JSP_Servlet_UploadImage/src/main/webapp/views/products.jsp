<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 17/11/2022
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Product</title>
</head>
<body>
  <table border="1">
    <thead>
      <tr>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Status</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${listPro}" var="pro">
        <tr>
          <td>${pro.productId}</td>
          <td>${pro.productName}</td>
          <td>${pro.productStatus?'Active':"Inactive"}</td>
          <td>
            <a href="<%=request.getContextPath()%>/ProductServlet?action=GetById&&productId=${pro.productId}">Detail</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
<a href="views/newProduct.jsp">Create New Product</a>
</body>
</html>

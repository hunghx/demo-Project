<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 17/11/2022
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Detail</title>
</head>
<body>
  <div>
    <img src="<%=request.getContextPath()%>/images/${pro.productImage}"/>
  </div>
  <div>
    <c:forEach items="${pro.listImage}" var="imageLink">
      <img src="<%=request.getContextPath()%>/images/${imageLink}"/>
    </c:forEach>
  </div>
  <h3>${pro.productName}</h3>
</body>
</html>

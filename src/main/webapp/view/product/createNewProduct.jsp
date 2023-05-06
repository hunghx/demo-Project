<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06/05/2023
  Time: 11:27 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Thêm mới danh mục</h1>
<form action="<%=request.getContextPath()%>/ProductServlet" method="post">
  <%--  <div>--%>
  <%--    <label for="id">ID</label>--%>
  <%--    <input type="text" id="id" name="id"/>--%>
  <%--  </div>--%>
  <div>
    <label for="name">Name</label>
    <input type="text" id="name" name="name"/>
  </div>
    <div>
      <label for="price">price</label>
      <input type="number" id="price" name="price"/>
    </div>
    <div>
      <label for="quantity">quantity</label>
      <input type="number" id="quantity" name="quantity"/>
    </div>
    <div>
      <label >catalog: </label>
      <select name="catalog"  >
        <c:forEach items="${list_cata}" var="cata">
          <option value="${cata.id}">${cata.name}</option>
        </c:forEach>
      </select>
    </div>

  <button type="submit" name="action" value="ADD">Add</button>
</form>
</body>
</html>

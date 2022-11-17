<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 17/11/2022
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Product</title>
</head>
<body>
  <form action="<%=request.getContextPath()%>/ProductServlet" method="post" enctype="multipart/form-data">
    <table>
      <tr>
        <td>Product Name</td>
        <td><input type="text" name="productName"/></td>
      </tr>
      <tr>
        <td>Product Image</td>
        <td><input type="file" name="image"/></td>
      </tr>
      <tr>
        <td>Sub Images</td>
        <td><input type="file" name="subImages" multiple/></td>
      </tr>
      <tr>
        <td>Status</td>
        <td><input type="text" name="status"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="Create" name="action"/></td>
      </tr>
    </table>
  </form>
</body>
</html>

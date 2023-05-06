<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<a href="CatalogServlet?action=GETALL"> catalog </a>
<br>
<a href="ProductServlet?action=GETALL"> product </a>
<%--<%response.sendRedirect("CatalogServlet?action=GETALL");%>--%>
<%--<%response.sendRedirect("ProductServlet?action=GETALL");%>--%>
</body>
</html>
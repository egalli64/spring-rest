<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Model-View by JSP</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/css/simple.css">
</head>
<body>
    <h1>Spring Model-View by JSP</h1>
    <p>Message from the controller: ${bean.message}</p>
    <%@include file="/fragment/footer.html"%>
</body>
</html>
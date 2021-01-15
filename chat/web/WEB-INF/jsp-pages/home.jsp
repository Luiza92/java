<%@ page import="am.gitc.chat.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/logout">Logout</a>
<%=((User) request.getSession().getAttribute("user")).getFirstName()%>
<%=((User) request.getSession().getAttribute("user")).getLastName()%>
<%=((User) request.getSession().getAttribute("user")).getEmail()%>
</body>
</html>

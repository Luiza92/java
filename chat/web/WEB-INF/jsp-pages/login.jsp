<%@ page import="am.gitc.chat.util.DataValidator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    private String getParamValue(HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        if (DataValidator.isNullOrEmpty(value)) {
            value = "";
        }
        return value.trim();
    }
%>
<h1 style="color: blue">
    <% if (request.getSession().getAttribute("loginSuccessfully") != null) {%>
    <span style="color: red">
            <%=request.getSession().getAttribute("loginSuccessfully")%>
            <%request.getSession().removeAttribute("loginSuccessfully");%>
        </span>
    <% }%>
</h1>
<form method="post" action="/login">
    <label>Email</label>
    <br>
    <input type="text" name="email" value="<%=getParamValue(request, "email")%>"/>
    <br>
    <% if (request.getAttribute("emailError") != null) {%>
    <span style="color: red">
            <%=request.getAttribute("emailError")%>
        </span>
    <% }%>
    <br>
    <label>Password</label>
    <br>
    <input type="password" name="password"/>
    <br>
    <% if (request.getAttribute("passwordError") != null) {%>
    <span style="color: red">
            <%=request.getAttribute("passwordError")%>
        </span>
    <% }%>

    <% if (request.getAttribute("emailPasswordError") != null) {%>
    <span style="color: red">
            <%=request.getAttribute("emailPasswordError")%>
        </span>
    <% }%>
    <br>
    <input type="submit" value="Login">
    <a href="/register">Register</a>
</form>
</body>
</html>

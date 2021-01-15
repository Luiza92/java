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
<form method="post" action="/register">
    <label>First Name</label>
    <br>
    <input type="text" name="firstName" value="<%=getParamValue(request, "firstName")%>"/>
    <br>
    <% if (request.getAttribute("firstNameError") != null) {%>
    <span style="color: red">
            <%=request.getAttribute("firstNameError")%>
        </span>
    <% }%>
    <br>
    <label>Last Name</label>
    <br>
    <input type="text" name="lastName" value="<%=getParamValue(request, "lastName")%>"/>
    <br>
    <% if (request.getAttribute("lastNameError") != null) {%>
    <span style="color: red">
            <%=request.getAttribute("lastNameError")%>
        </span>
    <% }%>
    <br>
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
    <br>
    <label>Confirm Password</label>
    <br>
    <input type="password" name="confirmPassword"/>
    <br>
    <% if (request.getAttribute("confirmPasswordError") != null) {%>
    <span style="color: red">
            <%=request.getAttribute("confirmPasswordError")%>
        </span>
    <% }%>
    <br>
    <input type="submit" value="Register">
    <a href="/login">Login</a>
</form>
</body>
</html>

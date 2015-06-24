<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form:form method="post" action="/login" modelAttribute="clientContext">
        <form:hidden path="clientId"/>
        <form:hidden path="secret"/>
        <form:hidden path="scope"/>
        <form:hidden path="authorizedGrantTypes"/>
        <table>
            <tr><td>User</td><td><form:input path="username"/> </td></tr>
            <tr><td>Password</td><td><form:password path="password"/></td></tr>
        </table>
        <input type="submit" value="Login">
    </form:form>
</body>
</html>

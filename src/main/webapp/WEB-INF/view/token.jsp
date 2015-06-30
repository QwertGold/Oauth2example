<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

The response to /oauth/token will normally be JSON. Here we have put the JSON into a text area so we can have an HTML page with links

<br>
<textarea cols="100" rows="10">${json}</textarea>
<br>
<a href="">Revoke token</a>
<br>
<a href="">Refresh Token</a>
</body>
</html>

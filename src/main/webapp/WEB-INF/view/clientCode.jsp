<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  Grant code '${code}' was extracted on the backend. This code can be exchanged to an access_token by a request to
  /oauth/token. /oauth/token is protected by basic authentication so the client must include the authentication
  header with the basic auth value.
</body>
</html>

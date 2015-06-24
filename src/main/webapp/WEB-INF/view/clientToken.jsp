<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<script>
  if(window.location.hash) {
    alert("access_token: " +window.location.hash)
  } else {
    alert("There was no #fragment.")
  }
</script>
<p>
  In the Oauth2 implicit flow, the access_token is passed to the client in a #fragment so JavaScript must be used to extract it.
</p>
</body>
</html>

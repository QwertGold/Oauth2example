<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p>
  Grant code '${code}' was extracted on the backend. This code can be exchanged to an access_token by a request to
  /oauth/token. /oauth/token is protected by basic authentication so the client must include the authentication
  header with the basic auth value. The username in the basic auth must be the 'client_id', the password is the client
    'secret' or empty string there is no client secret.
</p>
<p>When you click "Exchange Code" the client logic continues in the ClientController</p>

  <form action="exchangeCode" method="get">
      <label for="clientId">client_id</label>
      <input id="clientId" type="text" name="client_id" value="my-trusted-client" readonly>
      <br>
      <label for="code">code</label>
      <input id="code" type="text" name="code" value="${code}" readonly>
      <br>
      <label for="grantType">grant_type</label>
      <input id="grantType" type="text" name="grant_type" value="authorization_code" readonly>
      <br>
      <input type="submit" name="submit" value="Exchange Code">
  </form>

</body>
</html>

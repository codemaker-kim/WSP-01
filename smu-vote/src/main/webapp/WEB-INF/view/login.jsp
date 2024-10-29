<%--
  Created by IntelliJ IDEA.
  User: tnals
  Date: 24. 10. 25.
  Time: 오후 4:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>로그인</h1>
<form name="login" method="post">
    아이디 <input type="text" name="username">
    패스워드 <input type="password" name="password">
    <input type="submit" value="로그인">
</form>
<button type="button" onclick="location.href='/signup'">회원가입</button>
</body>
</html>

<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 등록</title>
</head>
<body>
<h1>방명록 등록</h1>
<form action='add.do' method='post'>
이메일: <input type='text' name='email'><br>
암호: <input type='password' name='password'><br>
내용: <input type='text' name='content'><br>
<input type='submit' value='추가'>
<input type='reset' value='취소' onclick='location.href="list.do"'>
</form>
</body>
</html>
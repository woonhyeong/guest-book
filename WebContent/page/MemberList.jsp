<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
<h1>회원 목록</h1>
<p><a href='add.do'>방명록 추가</a></p>

<c:forEach var="guestBook" items="${guestBooks}">
${guestBook.no},
${guestBook.email},
${guestBook.modifiedDate}
<a href='update.do?no=${guestBook.no}'>[수정]</a><br>
<p>"${guestBook.content}"</p><br>
</c:forEach>
</body>
</html>
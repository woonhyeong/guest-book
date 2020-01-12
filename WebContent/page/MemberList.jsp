<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
	<h1>회원 목록</h1>
	<p>
		<a href='add.do'>방명록 추가</a>
	</p>

	<table border="1">
		<tr>
			<th><c:choose>
					<c:when test="${orderCond == 'MNO_ASC'}">
						<a href="list.do?orderCond=MNO_DESC">번호(오름)</a>
					</c:when>
					<c:when test="${orderCond == 'MNO_DESC'}">
						<a href="list.do?orderCond=MNO_ASC">번호(내림)</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=MNO_ASC">번호(내림)</a>
					</c:otherwise>
				</c:choose></th>
			<th>이메일</th>
			<th><c:choose>
					<c:when test="${orderCond == 'CREDATE_ASC'}">
						<a href="list.do?orderCond=CREDATE_DESC">수정날짜(오름)</a>
					</c:when>
					<c:when test="${orderCond == 'CREDATE_DESC'}">
						<a href="list.do?orderCond=CREDATE_ASC">수정날짜(내림)</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=CREDATE_ASC">수정날짜(내림)</a>
					</c:otherwise>
				</c:choose></th>
			<th>내용</th>
			<th></th>
		</tr>

		<c:forEach var="guestBook" items="${guestBooks}">
			<tr>
				<td>${guestBook.no}</td>
				<td>${guestBook.email}</td>
				<td>${guestBook.modifiedDate}</td>
				<td><p>"${guestBook.content}"</p></td>
				<td><a href='update.do?no=${guestBook.no}'>[수정]</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
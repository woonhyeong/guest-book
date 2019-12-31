<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script>
$(document).ready(function(){
	$("#submit").click(function(){
		if($("#Password").val().length==0){ alert("비밀번호를 입력하세요."); return false;}
		if($("#Content").val().length==0){ alert("비밀번호를 입력하세요."); return false;}
	})
})
</script>
<title>방명록 정보</title>
</head>
<body>
<h1>방명록 정보</h1>
<form action='update.do' method='post'>
번호: <input type='text' name='no' value='${guestBook.no}' readonly><br>
이메일: <input type='text' name='email' value='${guestBook.email}' readonly><br>
마지막 수정일: ${guestBook.modifiedDate}<br>
비밀번호: <input type='password' id="Password" name='password'><br>
내용: <input type='text' name='content' id="Content" value='${guestBook.content}'><br>
<input type='submit' id="submit" value='저장'>
<input type='button' value='삭제' 
  onclick='location.href="delete.do?no=${guestBook.no}";'>
<input type='button' value='취소' onclick='location.href="list.do"'>
</form>
</body>
</html>
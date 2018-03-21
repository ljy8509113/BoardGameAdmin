<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<title>공지사항 작성</title>
</head>
<body>
	<h1>공지사항 작성</h1>
	
	<form action="<c:url value="/admin/noticeAdd.do" />" method="post" enctype="multipart/form-data">
		<div>
			<label>작성자 : ${ admin.nickname }</label>	
			<input type="hidden" value="${ admin.id }" name="writer">
		</div>
		<div>
			<label>제목: <input type="text" name="title"></label>
		</div>
		<div>
			<label>내용</label>
			<textarea name="description"></textarea>
		</div>
		<div>
			<label>showTime<input type="datetime-local" name="showTime"></label>
		</div>
		<div>
			<label>endTime<input type="datetime-local" name="endTime"></label>
		</div>
		<div>
			<label>타입 </label>
				<input type="radio" name="type" value="B" checked> 게시판형
				<input type="radio" name="type" value="G"> 게임공지용
		</div>
		<div>
			<label>참고 주소 <input type="url" name="link"></label>
		</div>
		<div>
			<label>게임번호</label>
				<input type="radio" id="notice1" name="gameNo" value=0 checked> 전체공지
				<input type="radio" id="notice2" name="gameNo" value=1> 1게임공지
		</div>
		
		<input type="submit" value="작성">
		<input type="reset" value="전체 삭제"><br>
		
		<a href="<c:url value='/admin/noticeList.do?gameNo=1'/>">취소</a>
		
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	
	</form>
	
</body>
</html>
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
			<span>작성자: </span>
			<span>${ admin.nickname } (${ admin.id })</span>		
		</div>
		<div>
			<label>제목: <input type="text" name="title"></label>
		</div>
	
	</form>
	
</body>
</html>
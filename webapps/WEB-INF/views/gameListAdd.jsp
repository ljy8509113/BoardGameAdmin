<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게임 정보 작성</title>
</head>
<body>
	<h1>게임 정보 작성</h1>
	<form action="<c:url value='/admin/gameListAdd.do'/>" method="post" enctype="multipart/form-data">
		
		<div>
			<span>작성자: </span>
			<span>${ admin.nickname } (${ admin.id })</span>
		</div>
		<div>
			<label>제목<input type="text" name="title"></label>
		</div>
		<div>
			<label>설명</label>
			<textarea name="description"></textarea>
		</div>
		<div>
			<label>상태<input type="text" name="state"></label>
		</div>
		<div>
			<label>버전<input type="text" name="version"></label>
		</div>
		<div>
			<label>프리팹경로<input type="text" name="fileName"></label>
		</div>
		<div>
			<label>이미지<input type="file" name="coverImage"></label>
		</div>
		
		<input type="submit" value="글 작성">
		<input type="reset" value="글 전체 삭제"><br>
		
		<a href="<c:url value='/admin/gameList.do.do' />">게임 목록으로 이동</a>
		
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		
		
	</form>
</body>
</html>
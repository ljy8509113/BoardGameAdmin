<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게임 작성글 수정</title>
</head>
<body>
	<h1>게임 글 수정</h1>
	
	<form method="post" enctype="multipart/form-data" action="<c:url value='/admin/gameListModify.do' />">
		<div>번호: ${ game.gameNo }</div>
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
		
		<input type="submit" value="글 수정">
		<input type="reset" value="입력한 내용 삭제"><br>
		
		<a href="<c:url value='/admin/gameList.do'/>">게임 목록으로 이동</a>
		<input type="hidden" name="gameNo" value="${ game.gameNo }">
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
</body>
</html>
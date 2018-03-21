<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>공지사항 수정</title>
</head>
<body>
	<h1>공지사항 수정</h1>
	
	<form method="post" enctype="multipart/form-data" action="<c:url value='/admin/noticeModify.do' />">
	<table>
		<tr>
			<td>작성자: </td>
			<td>
				${ admin.nickname }
				<input type="hidden" value="${ admin.id }" name="writer">
			</td>
		</tr>
		<tr>
			<td>제목: </td>
			<td><input type="text" name="title" value="${ notice.title }"></td>
		</tr>
		<tr>
			<td>내용: </td>
			<td><textarea name="description">${ notice.description }</textarea></td>
		</tr>
		<tr>	
			<td>showTime</td>
			<td><input type="datetime-local" name="showTime" value="${ notice.showTime }"></td>
		</tr>
		<tr>	
			<td>endTime</td>
			<td><input type="datetime-local" name="endTime"></td>
		</tr>
		<tr>
			<td>타입: </td>
			<td><input type="radio" name="type" value="B" checked> 게시판형</td>
			<td><input type="radio" name="type" value="G"> 게임공지용</td>
		</tr>
		<tr>	
			<td>참고주소</td>
			<td> <input type="url" name="link" value="${ notice.link }"></td>
		</tr>
		<tr>
			<td>게임번호: </td>
			<td><input type="radio" id="notice1" name="gameNo" value=0 checked> 전체공지</td>
			<td><input type="radio" id="notice2" name="gameNo" value=1> 1게임공지</td>
		</tr>
	</table>

		<input type="submit" value="글 수정">
		<input type="reset" value="입력한 내용 삭제"><br>
		<a href="<c:url value='/admin/noticeList.do?gameNo=1'/>">취소</a>
		
		<input type="hidden" name="no" value="${ notice.no }">
		
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
</body>
</html>
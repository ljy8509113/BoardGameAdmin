<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<title>Confirm</title>
</head>
<body>
	<div>
	<h1>정말로 ${ no }번 글을 삭제하시겠습니까?</h1>
	</div>
	
	<form action="<c:url value='/admin/noticeRemove.do'/>" method="post">
		<input type="hidden" name="no" value="${ no }">
		<input type="submit" value="삭제">
		
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
	<a href="<c:url value='/admin/noticeList.do?gameNo=1'/>">취소</a>
</body>
</html>
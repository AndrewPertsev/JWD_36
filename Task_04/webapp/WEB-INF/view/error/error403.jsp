<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Forbidden</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>

<div style="text-align: center;">403 error occurs. Forbidden.
    <c:out value="${message_fail}"/>
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>

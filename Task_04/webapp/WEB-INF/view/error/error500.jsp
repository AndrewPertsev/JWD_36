<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<fmt:setBundle basename="local"/>

<html>
<head>
    <title>Server Error</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>


<div style="text-align: center; color: #de1212">500 error occurs. Internal Server Error<br>
    <c:out value="${message_fail}"/>
</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"/>


</body>
</html>

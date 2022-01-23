<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<%--TODO !!!!--%>



<html lang="en">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css\bootstrap-datepicker3.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<head>
    <title>main after reg</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>


<%String messageFail = request.getParameter("message_fail");
    if (messageFail != null) {%>
<h4 style="color:#de1212"><fmt:message key="message.fail"/><%}%></h4>
<%String messageSuccess = request.getParameter("message_success");
    if (messageSuccess != null) {%>
<h4 style="color:#1bb7b1"><fmt:message key="message.success"/><%}%></h4>


<h4>Mr. <c:out value="${userName}"/> <c:out value="${userSurName}"/></h4>
<p>you have id <c:out value="${userId}"/> and you are<c:choose>
    <c:when test="${userRoleId == 1}"> our client </c:when>
    <c:when test="${userRoleId == 2}"> manager</c:when>
    <c:when test="${userRoleId == 0}"> ! unauthorized guest</c:when>
</c:choose></p>

<h5>INF about success ::: <c:out value="${message_success}"/></h5>

<td><i
        <c:choose>
            <c:when test="${reservation.reservationStatus.toString() == 'REERVED'}">
                style="color:#797474"
            </c:when>
            <c:when test="${reservation.reservationStatus.toString() == 'ISUED'}">
                style="color:green"
            </c:when>
        </c:choose>
></i>${reservation.reservationStatus}</td>


<td style="text-align: center">
    <c:if test="${reservation.reservationStatus.toString() != 'ISSUED'}">
        <a href="?command=cancel-reservation&reservation-id=${reservation.reservationId}">
            <i class="bi bi-trash-fill" style="font-size: 20px;color: #ff6f00"></i></a>
    </c:if>
</td>


<%--<c:set var="message" scope="page" value="${2*255}"/>--%>
<%--<c:out value="${message}"/>--%>
<%--<c:if test="${message<500}" var="testIF">--%>
<%--    <p>my message <c:out value="${message}"/></p>--%>
<%--</c:if>--%>
<%--<c:out value="${testIF}"/>--%>
<h1 style="color:#fa8847"> HELLO</h1>

<div class="container">
    <div class="form-row col-md-3 text-center">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="PUSH_OFFER_TO_USER">
            <input type="text" name="apartmentId" value="" class="form-control setMargin"
                   placeholder="Enter ">
            <input type="submit" class=" btn-primary btn-block  badge-pill " value="Push "/>
        </form>
    </div>
</div>





<%--check if admin--%>
<c:set var="role" scope="session" value="roleId"/>
<%--<c:out value="${message}"/>--%>
<c:if test="${not empty role and role eq 'roleId'}" var="testRole">
    <p>here is role : <c:out value="it ccccc is admin"/></p>
</c:if>
<c:out value="${testRole}"/>

<div class="container">
    <a href="Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE" button class="btn btn-primary  badge-pill">TO MANAGE
        REQ</a>
    <a href="Controller?command=GO_TO_APARTMENT_MANAGEMENT_PAGE" button class="btn btn-primary badge-pill">TO MANAGE
        APAR</a>
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>

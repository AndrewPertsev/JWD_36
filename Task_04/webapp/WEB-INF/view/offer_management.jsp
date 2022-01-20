<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
<link href="css/style.css" rel="stylesheet">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="header.common.offers"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>

<div class="container">
    <h2><fmt:message key="header.common.offers"/></h2>
    <p>This table shows list of all offers</p>
    <div class="scrollable" class="table-responsive-sm" style="overflow-y: scroll;">
        <table class="table-condensed table table-hover table-bordered fixtable">

            <thead>
            <tr align="center">
                <th> id</th>
                <th> id request</th>
                <th>Room</th>
                <th>Quantity</th>
                <th>Check-in-Date</th>
                <th>Check-out-Date</th>
                <th>Menu</th>
                <th>Transfer</th>
                <th>Sent</th>
                <th>Paid</th>
                <th>Closed</th>
                <th>Total price</th>
                <th>Update price</th>
            </thead>
            <tbody>

            <c:forEach var="z" items="${ATTRIBUTE_OFFERS}">
                <tr align="center">
                    <td><c:out value="${z.offerId}"/></td>
                    <td><c:out value="${z.requestId}"/></td>
                    <td><c:out value="${z.apartmentId}"/></td>
                    <td><c:out value="${z.quantity}"/></td>
                    <td><c:out value="${z.start}"/></td>
                    <td><c:out value="${z.end}"/></td>
                    <td><c:out value="${z.menu}"/></td>
                    <td><c:out value="${z.transfer}"/></td>
                    <td><c:out value="${z.sent}"/></td>
                    <td><c:out value="${z.paid}"/></td>
                    <td><c:out value="${z.closed}"/></td>
                    <td><c:out value="${z.priceOffer}"/></td>
                        <%--                    <td><c:out value="${z.managerId}"/></td>--%>
                    <td>
                        <a href="Controller?command=UPDATE_OFFER_PRICE&offerId=${z.offerId}"
                           class="btn btn-outline-info  btn-sm badge-pill">SEND EMAIL
                                ${z.offerId} </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<c:set var="role" scope="session" value="admin"/>
<%--<c:out value="${message}"/>--%>
<c:if test="${not empty role and role eq 'admin'}" var="testRole">
    <p>here is role : <c:out value="uuuuu"/></p>
</c:if>
<c:out value="${testRole}"/>


<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>


<%--<div class="container">+--%>
<%--    <form action="Controller" method="get">--%>
<%--        <input type="hidden" name="command" value="SEND EMAIL">--%>
<%--        <input type="text" name="apartmentId" value="" id="apartmentId" class="form-control setMargin"--%>
<%--               placeholder="change">--%>
<%--        <input type="submit" class=" btn-primary btn-block " value="change"/>--%>
<%--    </form>--%>
<%--</div>--%>
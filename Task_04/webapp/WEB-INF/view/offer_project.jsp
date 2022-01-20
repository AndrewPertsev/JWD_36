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
    <title><fmt:message key="header.common.offers"/>Offer Project</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>

<%--<%--%>
<%--    Date current = (Date)request.getAttribute("dateAuto");--%>
<%--    if (current!= null){--%>
<%--%>--%>
<%--<h3><%out.println("smt  found    ");--%>
<%--    out.println("Today is : "+current);--%>
<%--    } %></h3>--%>

<p>Guest <c:out
        value="${sessionScope.userName} ${sessionScope.userSurName} ${(sessionScope.userVIP == true) ? ' has VIP ' :' none '}"/>
    status.</p>

<div class="container">
    <h2>Available offers for request number ${requestScope.idRequest}</h2>
    <p>This table shows list of available offers <a href="Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE" button
                                                    class="btn btn-outline-success  badge-pill">Back to requests</a></p>
    <div
    " class="table-responsive-sm" >
    <table class="table-condensed table table-hover table-bordered table-striped fixtable">
        <thead>
        <tr align="center">
            <th>Local id</th>
            <th>Quantity Persons</th>
            <th>Check-in-Date</th>
            <th>Check-out-Date</th>
            <th>Apartment</th>
            <th>Menu</th>
            <th>Transfer</th>
            <th>Total price</th>
            <%--                <th>VIP</th>--%>
            <th>Push offer to user</th>

        </thead>
        <tbody>
        <c:set var="count" value="0" scope="page"/>
        <c:forEach var="z" items="${ATTRIBUTE_OFFERS}">
            <c:set var="count" value="${count+1}" scope="page"/>

            <tr align="center">
                <td><c:out value="${count}"/></td>
                <td><c:out value="${z.quantity}"/></td>
                <td><c:out value="${z.start}"/></td>
                <td><c:out value="${z.end}"/></td>
                <td><c:out value="${z.apartmentId}"/></td>
                <td><c:out value="${z.menu}"/></td>
                <td><c:out value="${z.transfer}"/></td>
                <td><c:out value="${z.priceOffer}"/></td>
                    <%--                   <td><c:out value="${z.vip}"/></td>&ndash;%&gt;--%>
                <td>
                    <a href="Controller?command=PUSH_OFFER_TO_USER&apartmentId=${z.apartmentId}&requestId=${requestScope.requestId}"
                       class="btn btn-outline-primary  btn-sm  badge-pill">Push offer ${count} to user</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>


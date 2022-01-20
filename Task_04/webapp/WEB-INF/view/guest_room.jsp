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
    <title>GUEST ROOM</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>

<p>Dear <c:out value="${userName}"/> <c:out value="${userSurName}"/>, you have id <c:out value="${userId}"/>
    ${(sessionScope.userVIP == true) ? ' and  VIP status.' :  '.'  }
    <%--    <c:if test="${userVIP == true}"> and VIP status--%>
    <%--    </c:if>--%>
</p>

<div class="container">
    <h2>Mr. ${sessionScope.userName}, here your offers: </h2>
    <p>This table shows list of yours offers</p>
    <div class="table-responsive-sm" class="scrollable" style="overflow-y: scroll;">
        <table class="table-condensed table table-hover table-bordered  fixtable" id="TABLE" class="display"
               style="width:100%">
            <thead>
            <tr align="center">
                <th>id</th>
                <th>Offer id</th>
                <th>Quantity Persons</th>
                <th>Check-in</th>
                <th>Check-out</th>
                <th>Apartment №</th>
                <th>Menu</th>
                <th>Transfer</th>
                <th>Total price</th>
                <%--                <th>confirmed</th>--%>
            </thead>
            <tbody>
            <c:set var="count" value="0" scope="page"/>
            <c:forEach var="z" items="${ATTRIBUTE_USER_OFFERS}">

                <c:set var="count" value="${count+1}" scope="page"/>
                <tr align="center">
                    <td><c:out value="${count}"/></td>
                    <td><c:out value="${z.offerId}"/></td>
                    <td><c:out value="${z.quantity}"/></td>
                    <td><c:out value="${z.start}"/></td>
                    <td><c:out value="${z.end}"/></td>
                    <td><c:out value="${z.apartmentId}"/></td>
                    <td><c:choose>
                        <c:when test="${z.menu== 1}">Breakfast only</c:when>
                        <c:when test="${z.menu== 2}"> Half-board</c:when>
                        <c:when test="${z.menu== 3}">All Inclusive</c:when>
                        <c:when test="${z.menu== 4}">Ultra All Inclusive</c:when>
                        <c:otherwise>"EMPTY"!</c:otherwise>
                    </c:choose>
                    </td>
                    <td><c:choose>
                        <c:when test="${z.transfer== 1}">Shuttle</c:when>
                        <c:when test="${z.transfer== 2}">Taxi</c:when>
                        <c:when test="${z.transfer== 3}">Vip car</c:when>
                        <c:when test="${z.transfer== 4}">Helicopter</c:when>
                        <c:otherwise>"EMPTY"!</c:otherwise>
                    </c:choose>
                    </td>
                    <td><c:out value="${z.priceOffer}"/></td>

                        <%--                    <td>--%>
                        <%--                        <form action="Controller" method="post">--%>
                        <%--                            <input type="hidden" name="command" value="DELETE_OFFER">--%>
                        <%--                                &lt;%&ndash;                          ////  <c:set var="apartmentId"  value ="${apartmentId[count]}"/>&ndash;%&gt;--%>
                        <%--                            <input type="submit" name="apartmentId" value="${z.apartmentId}" id="apartmentId"--%>
                        <%--                                   class="btn btn-outline-info  btn-sm  badge-pill" value="delete"/>--%>
                        <%--                        </form>--%>
                        <%--                    </td>--%>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.jqueryui.min.css">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.11.3/js/dataTables.jqueryui.min.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>


<%--------------------SCRIPTS------------------------%>

<%--PAGINATION Script--%>
<script>$(document).ready(function () {
    $('#TABLE').DataTable({
        "pagingType": "full_numbers",
        // "lengthMenu": [[8, 12, 18, 24, -1], [8, 12, 18, 24, "All"]],
        "lengthMenu": [[-1, 5, 10, 20], ["ALL", 5, 10, 20]],
        responsive: true,
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Search",
        }
    });
});</script>
<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>
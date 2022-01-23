<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="header.common.timesheet"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>


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

<%--PAGINATION Script--%>
<script>$(document).ready(function () {
    $('#TABLE').DataTable({
        "pagingType": "full_numbers",
        "lengthMenu": [[-1, 5, 10, 20], ["ALL", 5, 10, 20]],
        responsive: true,
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Search",
        }
    });
});</script>

<%--------------------------TABLE-----------------------------%>

<div class="container">
    <h2><fmt:message key="header.common.timesheet"/></h2>
    <p><fmt:message key="timesheet.header_second"/></p>
    <div class="scrollable" class="table-responsive-sm" style="overflow-y: scroll;">
        <table class="table-condensed table table-hover table-bordered table-striped fixtable" id="TABLE"
               class="display"
               style="width:100%">
            <thead>
            <tr align="center">
                <th><fmt:message key="registration.edit.id"/></th>
                <th><fmt:message key="timesheet.apartment_id"/> </th>
                <th><fmt:message key="timesheet.reserved_date"/> </th>
                <th><a href="Controller?command=SORT_TIMESHEET_BY_APARTMENT_ID"
                >Sort</a>
                    Select
                </th>

            </thead>
            <tbody>
            <c:forEach var="z" items="${ATTRIBUTE_TIMESHEET_LIST}">
                <tr align="center">
                    <td><c:out value="${z.timesheetId}"/></td>
                    <td><c:out value="${z.apartmentId}"/></td>
                    <td><c:out value="${z.reservedDate}"/></td>
                    <td>
                        <a href="Controller?command=OFFE&requestId=${z.timesheetId}"
                        >$$$$$ for ${z.timesheetId}</a>
<%--                        <button type="button" class="btn btn-outline-info  btn-sm badge-pill UPDATE_BTN"--%>
<%--                                data-toggle="modal" data-target="#UPDATE_Element">--%>
<%--                            UPDATE ${r.requestId} </button>--%>
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


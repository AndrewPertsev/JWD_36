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
<br>

<%--------------------UPDATE POP UP------------------------%>

<div class="modal fade" id="UPDATE_Element" tabindex="-1" role="dialog" aria-labelledby="1"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="UPDATE_TIMESHEET">
                        <div class="modal-header">
                            <h5 class="modal-title" id="1">Update request data</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="form-group-sm">
                            <label>Number</label>
                            <input type="text" class="form-control" required autofocus
                                   id="timesheetId"
                                   name="timesheetId">
                        </div>

                        <div class="form-group-sm">
                            <label for="apartmentId">Category</label>
                            <input type="text" class="form-control" required autofocus
                                   id="apartmentId"
                                   name="apartmentId">
                        </div>

                        <div class="form-group-sm">
                            <label for="reservedDate">Quantity</label>
                            <input type="text" class="form-control" required autofocus
                                   id="reservedDate"
                                   name="reservedDate">
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-secondary  badge-pill" data-dismiss="modal">
                                Close
                            </button>
                            <button type="submit" class="btn btn-outline-info  badge-pill" name="UpdateData">UPDATE
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
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
        "lengthMenu": [[-1, 5, 10, 20], ["ALL", 5, 10, 20]],
        responsive: true,
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Search",
        }
    });
});</script>

<%--UPDATE Script--%>
<script>
    $(document).ready(function () {
        $('.UPDATE_BTN').on('click', function () {
            $('#UPDATE_Element').modal('show');
            $tr = $(this).closest('tr');
            var data = $tr.children("td").map(function () {
                return $(this).text();
            }).get();
            console.log(data);
            $('#timesheetId').val(data[0]);
            $('#apartmentId').val(data[1]);
            $('#reservedDate').val(data[2]);
        });
    });
</script>

<%--DELETE Script--%>
<script>
    $(document).ready(function () {
        $('.DELETE_BTN').on('click', function () {
            $('#DELETE_Element').modal('show');
            $tr = $(this).closest('tr');
            var data = $tr.children("td").map(function () {
                return $(this).text();
            }).get();
            console.log(data);
            $('#requestIdDelete').val(data[0]);
        });
    });
</script>


<%--------------------DELETE POP UP------------------------%>

<div class="modal fade" id="DELETE_Element" tabindex="-1" role="dialog" aria-labelledby="2"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form action="Controller" method="delete">
                    <input type="hidden" name="command" value="DELETE_TIMESHEET">
                    <div class="modal-header">
                        <h5 class="modal-title" id="2">Are you sure to delete request?</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="form-group-sm">
                        <label> Number </label>
                        <input type="text" class="form-control" required autofocus
                               id="timesheetIdDelete"
                               name="timesheetId">
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary  badge-pill" data-dismiss="modal">NO,
                            CLOSE
                        </button>
                        <button type="submit" class="btn btn-outline-danger  badge-pill" name="DeleteData">YES, DELETE
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--------------------------TABLE-----------------------------%>

<div class="container">
    <h2><fmt:message key="header.common.timesheet"/></h2>
    <p>This table shows timesheet</p>
    <div class="scrollable" class="table-responsive-sm" style="overflow-y: scroll;">
        <table class="table-condensed table table-hover table-bordered table-striped fixtable" id="TABLE"
               class="display"
               style="width:100%">
            <thead>
            <tr align="center">
                <th> id</th>
                <th>Apartment</th>
                <th>Reserved Date</th>
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


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet">

<fmt:setBundle basename="local"/>

<html>
<head>
    <title>REQUESTS</title>
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
                        <input type="hidden" name="command" value="UPDATE_REQUEST">
                        <div class="modal-header">
                            <h5 class="modal-title" id="1">Update request data</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="form-group-sm">
                            <label>Number</label>
                            <input type="text" class="form-control" required autofocus
                                   id="requestId"
                                   name="requestId">
                        </div>

                        <div class="form-group-sm">
                            <label for="category">Category</label>
                            <input type="text" class="form-control" required autofocus
                                   id="category"
                                   name="category">
                        </div>

                        <div class="form-group-sm">
                            <label for="quantity">Quantity</label>
                            <input type="text" class="form-control" required autofocus
                                   id="quantity"
                                   name="quantity">
                        </div>

                        <div class="form-group">
                            <label for="start">Check in</label>
                            <input type="text" class="form-control" required autofocus
                                   id="start"
                                   name="start">
                        </div>

                        <div class="form-group">
                            <label for="end">Check out</label>
                            <input type="text" class="form-control" required autofocus
                                   id="end"
                                   name="end">
                        </div>

                        <div class="form-group">
                            <label for="menu">Menu</label>
                            <select class="form-control setMargin" id="menu" name="menu" class="col-sm-4" ype="text"
                                    required autofocus>
                                <option value="1">Breakfast only</option>
                                <option value="2">Half-board</option>
                                <option value="3">All Inclusive</option>
                                <option value="4">Ultra All Inclusive</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="transfer">Transfer</label>
                            <select class="form-control setMargin" name="transfer" id="transfer" class="col-sm-4"
                                    required autofocus
                                    type="text">
                                <option value="1">Transfer by shuttle</option>
                                <option value="2">Transfer by taxi</option>
                                <option value="3">Transfer by vip-car</option>
                                <option value="4">Transfer by helicopter</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="guestId">User Id</label>
                            <input type="text" class="form-control" required autofocus
                                   id="guestId"
                                   name="guestId">
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
            $('#requestId').val(data[0]);
            $('#category').val(data[1]);
            $('#quantity').val(data[2]);
            $('#start').val(data[3]);
            $('#end').val(data[4]);
            $('#menu').val(data[5]);
            $('#transfer').val(data[6]);
            $('#guestId').val(data[7]);
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
                    <input type="hidden" name="command" value="DELETE_REQUEST">
                    <div class="modal-header">
                        <h5 class="modal-title" id="2">Are you sure to delete request?</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="form-group-sm">
                        <label> Number </label>
                        <input type="text" class="form-control" required autofocus
                               id="requestIdDelete"
                               name="requestId">
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
    <h2><fmt:message key="header.common.requests"/></h2>
    <p>This table shows list of requests</p>
    <div class="scrollable" class="table-responsive-sm" style="overflow-y: scroll;">
        <table class="table-condensed table table-hover table-bordered table-striped fixtable" id="TABLE"
               class="display"
               style="width:100%">
            <thead>
            <tr align="center">
                <th> id</th>
                <th>cat.</th>
                <th>Persons</th>
                <th>Check_in</th>
                <th>Check_out</th>
                <th>Menu</th>
                <th>Transfer</th>
                <th>User</th>
                <th>Request_date</th>
                <th><a href="Controller?command=SORT_REQUESTS_BY_RESPONDED_STATUS"
                >Show unsent</a>
                    <%--                    <a href="Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE"--%>
                    <%--                       class="btn btn-outline-info  btn-sm badge-pill" >Show all requests</a>--%>
                    Status
                </th>
                <th>Guest</th>
                <th>Assemble offer for request №</th>
                <%--<th>VIP</th>--%>

            </thead>
            <tbody>
<%--            <c:forEach var="z" items="${requests}">--%>
            <c:forEach var="z" items="${ATTRIBUTE_REQUESTS}">
                <tr align="center">
                    <td><c:out value="${z.requestId}"/></td>
                    <td><c:out value="${z.category}"/></td>
                    <td><c:out value="${z.quantity}"/></td>
                    <td><c:out value="${z.start}"/></td>
                    <td><c:out value="${z.end}"/></td>
                    <td><c:choose>
                        <c:when test="${z.menu== 1}">Breakfast only</c:when>
                        <c:when test="${z.menu==2}"> Half-board</c:when>
                        <c:when test="${z.menu== 3}">All Inclusive</c:when>
                        <c:when test="${z.menu== 4}">Ultra All Inclusive</c:when>
                        <c:otherwise>"EMPTY"!</c:otherwise>
                    </c:choose>
                    </td>
                        <%--                    <td><c:out value="${z.menuId}"/></td> --%>
                        <%--                    <td><c:out value="${z.transferId== 1 ? 'Shuttle' :'Vip-Car'}"/></td>--%>
                    <td><c:choose>
                        <c:when test="${z.transfer== 1}">Shuttle</c:when>
                        <c:when test="${z.transfer==2}">Taxi</c:when>
                        <c:when test="${z.transfer== 3}">Vip car</c:when>
                        <c:when test="${z.transfer== 4}">Helicopter</c:when>
                        <c:otherwise>"EMPTY"!</c:otherwise>
                    </c:choose>
                    </td>

                    <td><c:out value="${z.guestId}"/></td>
                    <td><c:out value="${z.dateRequest}"/></td>
                    <td><c:out value="${z.responded== true ? 'Response sent ' :'TO PROCESS'}"/></td>
                        <%--                        <td><c:out value="${z.Vip}"/></td> badge badge-pill badge-info--%>
                    <td>
                        <a href="Controller?command=SHOW_GUEST_DATA&guestId=${z.guestId}"
                        > Guest ${z.guestId} data </a>

                                                    <button type="button" class="btn btn-outline-danger  btn-sm badge-pill DELETE_BTN"
                                                            data-toggle="modal" data-target="#DELETE_Element">DELETE ${r.id} </button>

                    </td>

                    <td>
                        <a href="Controller?command=OFFER&requestId=${z.requestId}"
                            <%--                           class="btn btn-outline-info  btn-sm badge-pill"--%>
                        >offer for ${z.requestId}</a>
                            <%--                    <td>--%>
                        <button type="button" class="btn btn-outline-info  btn-sm badge-pill UPDATE_BTN"
                                data-toggle="modal" data-target="#UPDATE_Element">
                            UPDATE ${r.requestId} </button>

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


<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>


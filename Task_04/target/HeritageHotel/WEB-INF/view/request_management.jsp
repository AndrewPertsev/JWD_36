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
    <title><fmt:message key="header.common.requests"/></title>
</head>
<style>body {
    background-image: url("../../img/sight5.png");
    background-repeat: no-repeat;
}</style>
<%--<style>.transparent25{filter: alpha(Opacity=25);--%>
<%-- style="opacity: 0.25";   opacity: 0.25;}--%>
<%--</style>--%>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>

<%
    String messageFail = request.getParameter("message_fail");
    if (messageFail != null) {
%>
<h4 style="color:#de1212"><fmt:message key="message.fail"/><%}%></h4>
<%
    String messageSuccess = request.getParameter("message_success");
    if (messageSuccess != null) {
%>
<h4 style="color:#1bb7b1"><fmt:message key="message.success"/><%}%></h4>

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
                            <h5 class="modal-title" id="1"><fmt:message key="request.management.Update_request_data"/>
                            </h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="registration.edit.id"/>
                            </label>
                            <input type="text" class="form-control" required autofocus
                                   id="requestId"
                                   name="requestId">
                        </div>

                        <div class="form-group-sm">
                            <label for="category"><fmt:message key="request.category_apartment.Apartment_category"/>
                            </label>
                            <input type="text" class="form-control" required autofocus
                                   id="category"
                                   name="category">
                        </div>

                        <div class="form-group-sm">
                            <label for="quantity"><fmt:message key="request.management.Quantity"/>
                            </label>
                            <input type="text" class="form-control" required autofocus
                                   id="quantity"
                                   name="quantity">
                        </div>

                        <div class="form-group">
                            <label for="start"><fmt:message key="request.Check-in"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="start"
                                   name="start">
                        </div>

                        <div class="form-group">
                            <label for="end"><fmt:message key="request.Check-out"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="end"
                                   name="end">
                        </div>

                        <%--                        <div class="form-group">--%>
                        <%--                            <label for="menu">Menu</label>--%>
                        <%--                        <input type="text" class="form-control" required autofocus--%>
                        <%--                               id="menu" name="menu"> </div>--%>
                        <div class="form-group">
                            <label for="menu"><fmt:message key="request.Menu"/></label>
                            <select class="form-control setMargin" id="menu" name="menu" class="col-sm-4" type="text"
                                    required autofocus>
                                <option value="1"><fmt:message key="request.Menu.Breakfast_only"/></option>
                                <option value="2"><fmt:message key="request.Menu.Half-board"/></option>
                                <option value="3"><fmt:message key="request.Menu.All_Inclusive"/></option>
                                <option value="4"><fmt:message key="request.Menu.Ultra_All_Inclusive"/></option>
                            </select>
                        </div>


                        <div class="form-group">
                            <label for="transfer"><fmt:message key="request.Transfer"/>
                            </label>
                            <select class="form-control setMargin" name="transfer" id="transfer" class="col-sm-4"
                                    required autofocus
                                    type="text">
                                <option value="1"><fmt:message key="request.Transfer.Shuttle"/></option>
                                <option value="2"><fmt:message key="request.Transfer.Taxi"/></option>
                                <option value="3"><fmt:message key="request.Transfer.Vip_car"/></option>
                                <option value="4"><fmt:message key="request.Transfer.Helicopter"/></option>
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
                                <fmt:message key="button.commands.close"/>
                            </button>
                            <button type="submit" class="btn btn-outline-info  badge-pill" name="UpdateData">
                                <fmt:message key="button.commands.update"/>
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
                        <h5 class="modal-title" id="2"><fmt:message key="button.commands.Are_you_sure_to_delete"/>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="form-group-sm">
                        <label><fmt:message key="registration.edit.id"/>
                        </label>
                        <input type="text" class="form-control" required autofocus
                               id="requestIdDelete"
                               name="requestId">
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary  badge-pill" data-dismiss="modal">
                            <fmt:message key="button.commands.close"/>
                        </button>
                        <button type="submit" class="btn btn-outline-danger  badge-pill" name="DeleteData"><fmt:message
                                key="button.commands.delete"/>
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
    <p><fmt:message key="request.management.header_second"/></p>
    <div class="table">
        <%--    <div class="scrollable" class="table-responsive-sm" table-hoverstyle="overflow-y: scroll;">--%>
        <table class="table-condensed table  table-hover table-bordered  fixtable" id="TABLE"
               class="display"
               style="width:100%">
            <thead>
            <tr align="center">
                <th><fmt:message key="registration.edit.id"/></th>
                <th><fmt:message key="request.category_apartment"/></th>
                <th><fmt:message key="request.management.Quantity"/></th>
                <th><fmt:message key="request.Check-in"/></th>
                <th><fmt:message key="request.Check-out"/></th>
                <th><fmt:message key="request.Menu"/></th>
                <th><fmt:message key="request.Transfer"/></th>
                <th><fmt:message key="request.management.Guest"/></th>
                <th><fmt:message key="request.management.Request_date"/></th>
                <th><fmt:message key="request.management.filter"/><a
                        href="Controller?command=FIND_REQUESTS_UNRESPONDED_STATUS_ONLY"
                        class="btn btn-outline-success  btn-sm badge-pill btn-block"><fmt:message
                        key="request.management.Unsent"/>
                </a>
                    <a href="Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE"
                       class="btn btn-outline-success  btn-sm badge-pill  btn-block"><fmt:message
                            key="request.management.all"/>
                    </a>
                </th>
                <th><fmt:message key="request.management.guest_info"/></th>
                <th><fmt:message key="button.commands.offer"/></th>
                <%--<th>VIP</th>--%>
            </thead>

            <tbody>
            <c:forEach var="z" items="${ATTRIBUTE_REQUESTS}">
                <tr align="center">
                    <td><c:out value="${z.requestId}"/></td>
                    <td><c:out value="${z.category}"/></td>
                    <td><c:out value="${z.quantity}"/></td>
                    <td><c:out value="${z.start}"/></td>
                    <td><c:out value="${z.end}"/></td>
                    <td><c:out value="${z.menu}"/></td>
                    <td><c:out value="${z.transfer}"/></td>
                        <%--                    <td><c:choose>--%>
                        <%--                        <c:when test="${z.menu== 1}"><fmt:message key="request.Menu.Breakfast_only"/></c:when>--%>
                        <%--                        <c:when test="${z.menu==2}"> <fmt:message key="request.Menu.Half-board"/></c:when>--%>
                        <%--                        <c:when test="${z.menu== 3}"><fmt:message key="request.Menu.All_Inclusive"/> </c:when>--%>
                        <%--                        <c:when test="${z.menu== 4}"><fmt:message key="request.Menu.Ultra_All_Inclusive"/></c:when>--%>
                        <%--                    </c:choose>--%>
                        <%--                    </td>--%>
                        <%--                    <td><c:choose>--%>
                        <%--                        <c:when test="${z.transfer== 1}"><fmt:message key="request.Transfer.Shuttle"/> </c:when>--%>
                        <%--                        <c:when test="${z.transfer==2}"><fmt:message key="request.Transfer.Taxi"/> </c:when>--%>
                        <%--                        <c:when test="${z.transfer== 3}"><fmt:message key="request.Transfer.Vip_car"/> </c:when>--%>
                        <%--                        <c:when test="${z.transfer== 4}"><fmt:message key="request.Transfer.Helicopter"/></c:when>--%>
                        <%--                    </c:choose></td>--%>
                    <td><c:out value="${z.guestId}"/></td>
                    <td><c:out value="${z.dateRequest}"/></td>
                    <c:if test="${z.responded==true}"><td style="color:#2254a4"><fmt:message key="request.management.response_sent"/></td></c:if>
                    <c:if test="${z.responded==false}"><td style="color:#e9080d"><fmt:message key="request.management.to_process"/></td></c:if>
                        <%--<td><c:out value="${z.responded== true ?( 'Response sent' ) :'TO PROCESS'}"/></td>--%>
                        <%--                        <td><c:out value="${z.Vip}"/></td> badge badge-pill badge-info--%>
                    <td>
                        <a href="Controller?command=SHOW_GUEST_DATA&guestId=${z.guestId}"
                           class="btn btn-outline-dark btn-sm badge-pill btn-block"
                        > <fmt:message key="button.commands.info"/>
                        </a>
                        <c:if test="${z.responded==false}">
                            <button type="button" class="btn btn-outline-info  btn-sm badge-pill UPDATE_BTN btn-block"
                                    data-toggle="modal" data-target="#UPDATE_Element"><fmt:message
                                    key="button.commands.update"/>
                            </button>
                        </c:if>
                    </td>

                    <td>
                        <a href="Controller?command=OFFER&requestId=${z.requestId}"
                           class="btn btn-outline-success  btn-sm badge-pill btn-block"
                        > <fmt:message key="button.commands.offer"/>
                        </a>
                        <c:if test="${z.responded==false}">
                            <button type="button" class="btn btn-outline-danger  btn-sm badge-pill DELETE_BTN btn-block"
                                    data-toggle="modal" data-target="#DELETE_Element"><fmt:message
                                    key="button.commands.delete"/> ${r.id} </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<p>
<h3 style="color:#90de12"><c:out value="${messageFail}"/></h3></p>
<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>




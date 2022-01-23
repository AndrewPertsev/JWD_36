<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="header.common.offers"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>


<%String messageFail = request.getParameter("message_fail");
    if (messageFail != null) {%>
<h4 style="color:#de1212"><fmt:message key="message.fail"/><%}%></h4>
<%String messageSuccess = request.getParameter("message_success");
    if (messageSuccess != null) {%>
<h4 style="color:#1bb7b1"><fmt:message key="message.success"/><%}%></h4>


<%--------------------UPDATE POP UP------------------------%>

<div class="modal fade" id="UPDATE_Element" tabindex="-1" role="dialog" aria-labelledby="1"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="UPDATE_OFFER_STATUS">
                        <div class="modal-header">
                            <h5 class="modal-title" id="1"><fmt:message key="offer.Update_offer_data"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="registration.edit.id"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="offerId"
                                   name="offerId">
                        </div>

                        <div class="form-group">
                            <label for="sent"><fmt:message key="offer.edit.sent"/></label>
                            <select class="form-control setMargin" id="sent" name="sent" class="col-sm-4" type="text"
                                    required autofocus>
                                <option value="false"><fmt:message key="guest.No_status"/></option>
                                <option value="true">Sent</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="paid"><fmt:message key="offer.edit.paid"/></label>
                            <select class="form-control setMargin" id="paid" name="paid" class="col-sm-4" type="text"
                                    required autofocus>
                                <option value="false"><fmt:message key="guest.No_status"/></option>
                                <option value="true">Paid</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="closed"><fmt:message key="offer.edit.closed"/></label>
                            <select class="form-control setMargin" id="closed" name="closed" class="col-sm-4"
                                    type="text"
                                    required autofocus>
                                <option value="false"><fmt:message key="guest.No_status"/></option>
                                <option value="true">Closed</option>
                            </select>
                        </div>

                        <div class="form-group-sm">
                            <label for="priceOffer"><fmt:message key="offer.edit.price_offer"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="priceOffer"
                                   name="priceOffer"
                            >
                        </div>

                        <%--                                                      <div class="form-check">--%>
                        <%--                                                     <input type="checkbox" class="form-check-input" id="exampleCheck5" name="picture" placeholder="Enter email">--%>
                        <%--                                                    <label class="form-check-label" for="exampleCheck5">Check me out</label></div>--%>
                        <%--                   style="width:80px;"--%>
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
        // "lengthMenu": [[8, 12, 18, 24, -1], [8, 12, 18, 24, "All"]],
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
            $('#offerId').val(data[0]);

            $('#sent').val(data[1]);
            $('#paid').val(data[2]);
            $('#closed').val(data[3]);
            $('#priceOffer').val(data[4]);
        });
    });
</script>


<%-------------------------TABLE----------------------------%>

<div class="container">
    <h2><fmt:message key="header.common.offers"/></h2>
    <p><fmt:message key="offer.header_second"/></p>
    <div class="scrollable" class="table-responsive-sm" style="overflow-y: scroll;">
        <table class="table-condensed table table-hover table-bordered fixtable" id="TABLE" class="display"
               style="width:100%">

            <thead>
            <tr align="center">
                <th> id</th>
                <th><fmt:message key="offer.edit.sent"/></th>
                <th><fmt:message key="offer.edit.paid"/></th>
                <th><fmt:message key="offer.edit.closed"/></th>
                <th><fmt:message key="offer.project.Total_price"/></th>
                <th><fmt:message key="offer.project.request_id"/></th>
                <th><fmt:message key="offer.project.Apartment_id"/></th>
                <th><fmt:message key="request.management.Quantity"/></th>
                <th><fmt:message key="request.Check-in"/></th>
                <th><fmt:message key="request.Check-out"/></th>
                <th><fmt:message key="request.Menu"/></th>
                <th><fmt:message key="request.Transfer"/></th>
                <th>Update</th>
            </thead>
            <tbody>

            <c:forEach var="z" items="${ATTRIBUTE_OFFERS}">
                <tr align="center">
                    <td><c:out value="${z.offerId}"/></td>
                    <td><c:out value="${z.sent}"/></td>
                    <td><c:out value="${z.paid}"/></td>
                    <td><c:out value="${z.closed}"/></td>
                    <td><c:out value="${z.priceOffer}"/></td>
                    <td><c:out value="${z.requestId}"/></td>
                    <td><c:out value="${z.apartmentId}"/></td>
                    <td><c:out value="${z.quantity}"/></td>
                    <td><c:out value="${z.start}"/></td>
                    <td><c:out value="${z.end}"/></td>
                    <td><c:out value="${z.menu}"/></td>
                    <td><c:out value="${z.transfer}"/></td>
                    <td>
                        <a href="Controller?command=UPDATE_OFFER&offerId=${z.offerId}"
                           class="btn btn-outline-info  btn-sm badge-pill"><fmt:message key="offer.project.send_email"/>
                                ${z.offerId} </a>
                        <button type="button" class="btn btn-outline-info  btn-sm badge-pill UPDATE_BTN"
                                data-toggle="modal" data-target="#UPDATE_Element"><fmt:message
                                key="button.commands.update"/>
                                ${r.offerId} </button>
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


<%--<div class="container">+--%>
<%--    <form action="Controller" method="get">--%>
<%--        <input type="hidden" name="command" value="SEND EMAIL">--%>
<%--        <input type="text" name="apartmentId" value="" id="apartmentId" class="form-control setMargin"--%>
<%--               placeholder="change">--%>
<%--        <input type="submit" class=" btn-primary btn-block " value="change"/>--%>
<%--    </form>--%>
<%--</div>--%>
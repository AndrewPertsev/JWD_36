<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="header.common.guests"/></title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/style.css" rel="stylesheet" type="text/css">

</head><style>body{background-image: url("../../img/sight5.png") ; background-repeat: no-repeat ;}</style>

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
                        <input type="hidden" name="command" value="UPDATE_GUEST">
                        <div class="modal-header">
                            <h5 class="modal-title" id="1"><fmt:message key="guest.Update_guest_data"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="registration.edit.id"/>
                            </label>
                            <input type="text" class="form-control" required autofocus
                                   id="guestId"
                                   name="guestId">
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="registration.edit.roleId"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="roleId"
                                   name="roleId">
                        </div>
                        <div class="form-group-sm">
                            <label><fmt:message key="registration.name"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="name"
                                   name="name">
                        </div>
                        <div class="form-group-sm">
                            <label><fmt:message key="registration.surname"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="surname"
                                   name="surname">
                        </div>
                        <div class="form-group-sm">
                            <label><fmt:message key="registration.email"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="email"
                                   name="email">
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="registration.phone"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="phone"
                                   name="phone">
                        </div>

                        <div class="form-group">
                            <label for="isVip"><fmt:message key="registration.edit.vip"/></label>
                            <select class="form-control setMargin" id="isVip" name="isVip" class="col-sm-4" type="text"
                                    required autofocus>
                                <option value="false"><fmt:message key="guest.No_status"/></option>
                                <option value="true">VIP client</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="isNonGrata"><fmt:message key="registration.edit.nongrata"/></label>
                            <select class="form-control setMargin" id="isNonGrata" name="isNonGrata" class="col-sm-4"
                                    type="text"
                                    required autofocus>
                                <option value="false"><fmt:message key="guest.No_status"/></option>
                                <option value="true">NON GRATA!!!</option>
                            </select>
                        </div>

                        <%--                        <div class="form-check">--%>
                        <%--                            <input type="checkbox" class="form-check-input active" id="isVip" name="isVip"--%>
                        <%--                                   placeholder="check VIP" value="true">--%>
                        <%--                            <label class="form-check-label active" for="isVip"><fmt:message key="registration.edit.vip"/>--%>
                        <%--                            </label></div>--%>

                        <%--                        <div class="form-check">--%>
                        <%--                            <input type="checkbox" class="form-check-input active" id="isNonGrata" name="isNonGrata"--%>
                        <%--                                   placeholder="check status" value="true">--%>
                        <%--                            <label class="form-check-label active" for="isNonGrata"><fmt:message key="registration.edit.nongrata"/>--%>
                        <%--                            </label></div>--%>

                        <div class="form-group">
                            <label><fmt:message key="registration.edit.comment"/>
                            </label>
                            <textarea class="form-control" id="comment" name="comment"
                            ></textarea>
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
            $('#guestId').val(data[0]);
            $('#roleId').val(data[1]);
            $('#name').val(data[2]);
            $('#surname').val(data[3]);
            $('#email').val(data[4]);
            $('#phone').val(data[5]);
            $('#isVip').val(data[6]);
            $('#isNonGrata').val(data[7]);
            $('#comment').val(data[8]);

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
            $('#idDelete').val(data[0]);
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
                    <input type="hidden" name="command" value="DELETE_GUEST">
                    <div class="modal-header">
                        <h5 class="modal-title" id="2"><fmt:message key="button.commands.Are_you_sure_to_delete"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="form-group-sm">
                        <label><fmt:message key="registration.edit.id"/></label>
                        <input type="text" class="form-control" required autofocus
                               id="idDelete"
                               name="guestId">
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary  badge-pill" data-dismiss="modal">
                            <fmt:message key="button.commands.close"/>

                        </button>
                        <button type="submit" class="btn btn-outline-danger  badge-pill" name="DELETEDATA"><fmt:message
                                key="button.commands.delete"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<%--------------------TABLE---------------------%>

<div class="container">
    <h2><fmt:message key="header.common.guests"/></h2>
    <p><fmt:message key="guests.header.second"/>
        <a type="button" href="Controller?command=GO_TO_GUEST_MANAGEMENT_PAGE" button
           class="btn btn-outline-dark  btn-sm badge-pill "><fmt:message
                key="button.commands.guests.Show_all_guests"/></a>
    </p>
    <div class="table-responsive table-sm" class="scrollable" style="overflow-y: scroll;">
        <table class="table-condensed table table-hover table-bordered  fixtable" id="TABLE" class="display">
            <thead>
            <tr align="center">
                <th><fmt:message key="registration.edit.id"/></th>
                <th><fmt:message key="registration.edit.roleId"/></th>
                <th><fmt:message key="registration.name"/></th>
                <th><fmt:message key="registration.surname"/></th>
                <th><fmt:message key="registration.email"/></th>
                <th><fmt:message key="registration.phone"/></th>
                <th><fmt:message key="registration.edit.vip"/></th>
                <th><fmt:message key="registration.edit.nongrata"/></th>
                <th><fmt:message key="registration.edit.comment"/></th>
                <th><fmt:message key="button.commands.update"/></th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="r" items="${ATTRIBUTE_GUEST_LIST}">
                <tr align="center">
                    <td><c:out value="${r.guestId}"/></td>
                    <c:if test="${r.roleId==2}"><td style="color:#e9080d"><c:out value="${r.roleId}"/></td></c:if>
                    <c:if test="${r.roleId==1}"><td style="color:#081222"><c:out value="${r.roleId}"/></td></c:if>
                    <td><c:out value="${r.name}"/></td>
                    <td><c:out value="${r.surname}"/></td>
                    <td><c:out value="${r.email}"/></td>
                    <td><c:out value="${r.phone}"/></td>
                    <c:if test="${r.vip==true}"><td style="color:#e9080d"><c:out value="${r.vip}"/></td></c:if>
                    <c:if test="${r.vip==false}"><td style="color:#081222"><c:out value="${r.vip}"/></td></c:if>
                    <c:if test="${r.nonGrata==true}"><td style="color:#e9080d"><c:out value="${r.nonGrata}"/></td></c:if>
                    <c:if test="${r.nonGrata==false}"><td style="color:#081222"><c:out value="${r.nonGrata}"/></td></c:if>
                    <td><c:out value="${r.comment}"/></td>

                    <td>
                        <button type="button" class="btn btn-outline-info  btn-sm badge-pill UPDATE_BTN"
                                data-toggle="modal" data-target="#UPDATE_Element"><fmt:message
                                key="button.commands.update"/>
                        </button>
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




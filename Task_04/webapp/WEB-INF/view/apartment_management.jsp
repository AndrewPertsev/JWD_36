<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="header.common.apartments"/></title>
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
<h4 style=color:#1bb7b1"><fmt:message key="message.success"/><%}%></h4>

<%--------------------UPDATE POP UP------------------------%>

<div class="modal fade" id="UPDATE_Element" tabindex="-1" role="dialog" aria-labelledby="1"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="UPDATE_APARTMENT">
                        <div class="modal-header">
                            <h5 class="modal-title" id="1"><fmt:message key="apartment.Update_apartment_data"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="registration.edit.id"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="apartmentId"
                                   name="apartmentId"
                            <%--                                   placeholder="${r.id}"--%>
                            >
                        </div>

                        <div class="form-group-sm">
                            <label for="category"><fmt:message key="apartment.Category"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="category"
                                   name="category"
                            <%--                                   placeholder="category"--%>
                            >
                        </div>

                        <div class="form-group-sm">
                            <label for="capacity"><fmt:message key="apartment.Capacity"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="capacity"
                                   name="capacity"
                            <%--                                   placeholder="capacity"--%>
                            >
                        </div>

                        <div class="form-group">
                            <label for="pathToPicture"><fmt:message key="apartment.Picture"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="pathToPicture"
                                   name="pathToPicture"
                            <%--                                   placeholder="pathToPicture"--%>
                            >
                        </div>

                        <div class="form-group">
                            <label for="description"><fmt:message key="apartment.Description"/></label>
                            <textarea class="form-control" id="description" name="description" required autofocus
                            <%--                                   placeholder="Enter description">--%>
                            ></textarea>
                            <%--                            <small id="description1"class="form-text text-muted">text else.</small>--%>
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
            $('#apartmentId').val(data[0]);
            $('#category').val(data[1]);
            $('#capacity').val(data[2]);
            $('#description').val(data[3]);
            $('#pathToPicture').val(data[4]);
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
            $('#apartmentIdDelete').val(data[0]);
        });
    });
</script>

<%--ADD Script--%>
<script>
    $(document).ready(function () {
        $('.ADD_BTN').on('click', function () {
            $('#ADD_Element').modal('show');
            $tr = $(this).closest('tr');
            var data = $tr.children("td").map(function () {
                return $(this).text();
            }).get();
            console.log(data);
            $('#apartmentIdAdd').val(data[0]);
            $('#categoryAdd').val(data[1]);
            $('#capacityAdd').val(data[2]);
            $('#descriptionAdd').val(data[3]);
            $('#pathToPictureAdd').val(data[4]);
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
                    <input type="hidden" name="command" value="DELETE_APARTMENT">
                    <div class="modal-header">
                        <h5 class="modal-title" id="2">#<fmt:message key="button.commands.Are_you_sure_to_delete"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="form-group-sm">
                        <label><fmt:message key="registration.edit.id"/></label>
                        <input type="text" class="form-control" required autofocus
                               id="apartmentIdDelete"
                               name="apartmentId">
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
<%--------------------ADD POP UP------------------------%>

<div class="modal fade" id="ADD_Element" tabindex="-1" role="dialog" aria-labelledby="3"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="ADD_APARTMENT">
                        <div class="modal-header">
                            <h5 class="modal-title" id="3"><fmt:message key="button.commands.add_new_apartment"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="registration.edit.id"/></label>
                            <input type="text" class="form-control" required autofocus
                                   id="apartmentIdAdd"
                                   name="apartmentId">
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="apartment.Category"/>
                            </label>
                            <input type="text" class="form-control" required autofocus
                                   id="categoryAdd"
                                   name="category">
                        </div>

                        <div class="form-group-sm">
                            <label><fmt:message key="apartment.Capacity"/>
                            </label>
                            <input type="text" class="form-control" required autofocus
                                   id="capacityAdd"
                                   name="capacity">
                        </div>

                        <div class="form-group">
                            <label><fmt:message key="apartment.Picture"/>
                            </label>
                            <input type="text" class="form-control" required autofocus
                                   id="pathToPictureAdd"
                                   name="pathToPicture">
                        </div>

                        <div class="form-group">
                            <label><fmt:message key="apartment.Description"/>
                            </label>
                            <textarea class="form-control" id="descriptionAdd" name="description" required
                                      autofocus></textarea>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-secondary  badge-pill" data-dismiss="modal">
                                <fmt:message key="button.commands.close"/>
                            </button>
                            <button type="submit" class="btn btn-outline-info  badge-pill" name="ADDDATA"><fmt:message
                                    key="button.commands.add_new_apartment"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%--------------------TABLE---------------------%>

<div class="container">
    <h2><fmt:message key="header.common.apartments"/></h2>
    <p><fmt:message key="apartment.header_second"/>
        <button type="button" class="btn btn-outline-success  btn-sm badge-pill ADD_BTN"
                data-toggle="modal" data-target="#ADD_Element"><fmt:message key="button.commands.add_new_apartment"/>
        </button>
    </p>
    <div class="table-responsive-sm" class="scrollable" style="overflow-y: scroll;">
        <table class="table-condensed table table-hover table-bordered  fixtable" id="TABLE" class="display"
               style="width:100%">
            <thead>
            <tr align="center">
                <th><fmt:message key="registration.edit.id"/></th>
                <th><fmt:message key="apartment.Category"/></th>
                <th><fmt:message key="apartment.Capacity"/></th>
                <th><fmt:message key="apartment.Description"/></th>
                <th><fmt:message key="apartment.Picture"/></th>
                <th><fmt:message key="button.commands.update"/></th>
                <th><fmt:message key="button.commands.delete"/>e</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="r" items="${ATTRIBUTE_ROOM_LIST}">
                <tr align="center">
                    <td><c:out value="${r.apartmentId}"/></td>
                    <td><c:out value="${r.category}"/></td>
                    <td><c:out value="${r.capacity}"/></td>
                    <td><c:out value="${r.description}"/></td>
                    <td><c:out value="${r.pathToPicture}"/></td>
                    <td>
                        <button type="button" class="btn btn-outline-info  btn-sm badge-pill UPDATE_BTN"
                                data-toggle="modal" data-target="#UPDATE_Element"><fmt:message
                                key="button.commands.update"/>
                                ${r.apartmentId} </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-outline-danger  btn-sm badge-pill DELETE_BTN"
                                data-toggle="modal" data-target="#DELETE_Element"><fmt:message
                                key="button.commands.delete"/> ${r.apartmentId} </button>
                    </td>
                </tr>
            </c:forEach>

            <%--            <nav aria-label="Page navigation example">--%>
            <%--                <ul class="pagination">--%>
            <%--                    <li class="page-item">--%>
            <%--                        <a class="page-link" href="#" aria-label="Previous">--%>
            <%--                            <span aria-hidden="true">&laquo;</span>--%>
            <%--                            <span class="sr-only">Previous</span>--%>
            <%--                        </a>--%>
            <%--                    </li>--%>
            <%--                    <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
            <%--                    <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
            <%--                    <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
            <%--                    <li class="page-item">--%>
            <%--                        <a class="page-link" href="#" aria-label="Next">--%>
            <%--                            <span aria-hidden="true">&raquo;</span>--%>
            <%--                            <span class="sr-only">Next</span>--%>
            <%--                        </a>--%>
            <%--                    </li>--%>
            <%--                </ul>--%>
            <%--            </nav>--%>

            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>


<%--//////////////////////////////////////////////////////////////////////////////////////////

<%--<button type="button" class="btn btn-outline-success  btn-sm badge-pill ADD_BTN"--%>
<%--        data-toggle="modal" data-target="#ADD_APARTMENT">Add new apartment--%>
<%--</button>--%>
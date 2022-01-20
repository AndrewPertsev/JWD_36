<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="local"/>

<html>
<head>

    <title>REQUEST</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css\bootstrap-datepicker3.min.css">

</head>
<body>
<%--<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>--%>
<br>
<div class row>
    <div class="col-md-4" style="background-color: white"></div>

    <div class="container">
        <div class="col-md-4" style="background-color: #122b40">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="REQUEST">
                <br>
                <div class="container">
                    <div class="form-group" class="form-outline">
                        <div class="col-md-4" id="sandbox-container">

                            <div class="input-daterange input-group" id="datepicker">
                                <input type="text" id="check-in" class="col-md-5" class="form-control setMargin"
                                       placeholder="Check-in" name="start" required autofocus/>
                                <label class="form-label" class="control-label" for="check-in"></label>
                                <input type="text" id="check-out" class="col-md-5" class="form-control setMargin"
                                       placeholder="Check-out" name="end" required autofocus/>
                                <label class="form-label" class="control-label" for="check-out"></label>
                            </div>
                        </div>
                    </div>
                </div>

                <br>
                <select class="form-control setMargin" name="quantity" id="quantity" class="col-sm-4">
                    <option value=1>Quantity persons</option>
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                    <option value=4>4</option>
                    <option value=5>5</option>
                    <option value=6>6</option>
                </select>
                <br>
                <select class="form-control setMargin" name="category" id="category" class="col-sm-4">
                    <option value="1">Apartment category</option>
                    <option value="1">Suite</option>
                    <option value="2">Business apartment</option>
                    <option value="3">Luxury apartment</option>
                </select>
                <br>
                <select class="form-control setMargin" name="menu" id="menu" class="col-sm-4">
                    <option value="1">Menu</option>
                    <option value="1">Breakfast only</option>
                    <option value="2">Half-board</option>
                    <option value="3">All Inclusive</option>
                    <option value="4">Ultra All Inclusive</option>
                </select>
                <br>
                <select class="form-control setMargin" name="transfer" id="transfer" class="col-sm-4">
                    <option value="1">Transfer</option>
                    <option value="1">Transfer by shuttle</option>
                    <option value="2">Transfer by taxi</option>
                    <option value="3">Transfer by vip-car</option>
                    <option value="4">Transfer by helicopter</option>
                </select>
                <br>
                <%--                <div class="check-box">--%>
                <%--                    <input type="checkbox"> checking--%>
                <%--                </div>--%>
                <%--                <br>--%>
                <input type="submit" class=" btn-primary btn-block " value="BOOK NOW"/>
            </form>
            <br>
        </div>

    </div>
    <div class="col-md-4" style="background-color: white"></div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js\bootstrap-datepicker.min.js"></script>
<script src="js\bootstrap-datepicker.ru.min.js"></script>
<script>$('#sandbox-container .input-daterange').datepicker({
    format: "yyyy-mm-dd",
    startDate: "2021-11-01",
    autoclose: true,
    todayHighlight: true,
    weekStart: 1,
    daysOfWeekHighlighted: "6,0",
});</script>

<br><br><br>
<div class="container">
    <a href="Controller?command=GO_TO_MAIN_PAGE" button class="btn btn-primary  badge-pill">TO HOME</a>
</div>

<br><br><br>
<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>



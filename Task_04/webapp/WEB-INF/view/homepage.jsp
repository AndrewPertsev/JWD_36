<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css\bootstrap-datepicker3.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" >

<fmt:setBundle basename="local"/>

<head>
    <title>HOME</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>

<div class="container">
    <a href="Controller?command=GO_TO_MAIN_PAGE" button class="btn btn-outline-primary  badge-pill">TO MAINDB</a>
</div>
<div class="container">
    <a href="Controller?command=GO_TO_GUEST_MANAGEMENT_PAGE" button class="btn btn-outline-primary  badge-pill">TO guest</a>
</div>
<%--carousel --%>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>


        <c:if test="${message_regist_succ !=null}" >--%>
        <p><c:out value="${message_regist_succ}"/></p>
    </c:if>




    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="la.jpg" alt="Los Angeles">
        </div>

        <div class="item">
            <img src="chicago.jpg" alt="Chicago">
        </div>

        <div class="item">
            <img src="ny.jpg" alt="New York">
        </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>
<!doctype html>

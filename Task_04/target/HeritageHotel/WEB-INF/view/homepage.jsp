<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<head>
    <title>HOME</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link href="css/style.css" rel="stylesheet">


</head><style>body{background-image: url("../../img/sight5.png") ; background-repeat: no-repeat ;}</style>

<%--<style>--%>
<%--    body{--%>
<%--        /*background: url(img/backgroundcommon2.png) ;*/--%>
<%--        /*-webkit-background-size: cover;*/--%>
<%--        -moz-background-size: 100%;--%>
<%--        -o-background-size:  100%;--%>
<%--        background-size:  100%;--%>
<%--        /*height: 100%;*/--%>

<%--        /* Center and scale the image nicely */--%>
<%--        /*background-position: center;*/--%>
<%--        /*background-repeat: no-repeat;*/--%>
<%--        /*background-size: cover;*/--%>
<%--    }--%>
<%--</style>--%>
<body style=" background-color: #c0c4c4;">
<%--<body  style=" background-image: backgroundcommon2.png;">--%>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>


<div class="container">
    <div class="row">
<%--        <div class="col-md-1">1</div>--%>
<%--        <div class="col-md-10">--%>

            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" ></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"  ></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>

                </ol>
                <div class="carousel-inner">

                    <div class="carousel-item ">
                        <img src="../../img/apart3.png" alt="First slide" class="d-block w-100">
                    </div>
                    <div class="carousel-item ">
                        <img src="../../img/apart2.png" alt="Second slide" class="d-block w-100">
                    </div>
                    <div class="carousel-item active">
                        <img  src="../../img/apart10.png" alt="Third slide" class="d-block w-100">
                    </div>
                    <div class="carousel-item">
                        <img  src="../../img/apart2.png" alt="Fourth slide" class="d-block w-100">
                    </div>

                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

<%--        </div>--%>
<%--        <div class="col-md-1">3</div>--%>


    </div>

</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<c:if test="${message_regist_succ !=null}">--%>
    <p><c:out value="${message_regist_succ}"/></p>
</c:if>


<br>
<br>
<br>
<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>
<!doctype html>

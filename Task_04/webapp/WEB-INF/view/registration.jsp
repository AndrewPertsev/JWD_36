<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" >

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="header.common.sign_up"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>

<div class row>
    <div class="col-md-4" class="mx-auto"style="background-color: white"></div>
<h3><fmt:message key="header.common.sign_up"/></h3>
    <div class="container">
        <div class="col-md-4" style="background-color: #122b40">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="REGISTRATION">
                <br>
                <input type="text" name="name" value="" id="firstname" class="form-control setMargin" placeholder="First Name"required autofocus>
                <br>
                <input type="text" name="surname" value="" id="surname" class="form-control setMargin" placeholder="Last Name" required autofocus>
                <br>
                <input type="text" name="login" value="" id="login" class="form-control setMargin" placeholder="Login" required autofocus>
                <br>
                <input type="text" name="password" value="" id="password" class="form-control setMargin" placeholder="Password" required autofocus>
                <br>
                <input type="password" name="password2" value="" id="password2" class="form-control setMargin" placeholder="Repeat Password" required autofocus>
                <br>
                <input type="email" name="email" value="" id="email" class="form-control setMargin" placeholder="Email"
<%--                       required autofocus--%>
                >
                <br>
                <input type="text" name="phone" value="" id="phone" class="form-control setMargin" placeholder="Phone" required autofocus>
                <br>
                <input type="text" name="passport" value="" id="passport" class="form-control setMargin" placeholder="Passport Number" required autofocus>
                <br>
                <input type="text" name="country" value="" id="country" class="form-control setMargin" placeholder="Country" required autofocus>
                <br>
<%--                <div class="check-box">--%>
<%--                    <input type="checkbox"> checking--%>
<%--                </div>--%>
<%--                <br>--%>
                <input type="submit" class=" btn-primary btn-block " value="Siqn-in"/>
            </form>
            <br>
        </div>
    </div>
    <div class="col-md-4" style="background-color: white"></div>
</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>

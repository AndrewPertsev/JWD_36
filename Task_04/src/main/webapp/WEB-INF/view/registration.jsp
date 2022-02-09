<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="header.common.sign_up"/></title>
</head><style>body{background-image: url("../../img/sight4.png") ; background-repeat: no-repeat ;}</style>

<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>


<%String messageFail = request.getParameter("message_fail");
    if (messageFail != null) {%>
<h4 style="color:#de1212"><fmt:message key="message.fail"/><%}%></h4>
<%String messageSuccess = request.getParameter("message_success");
    if (messageSuccess != null) {%>
<h4 style="color:#1bb7b1"><fmt:message key="message.success"/><%}%></h4>

<div class="container h-100">
<div class ="row h-100 justify-content-center align-items-center">
    <div class="col-md-2"></div>
    <div class="col-md-8" >

<%--    <div class="col-md-4" class="mx-auto" style="background-color: white"></div>--%>
    <h3><fmt:message key="header.common.sign_up"/></h3>
        <div class="border rounded col-md-6"   style="background-color: #545481">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="REGISTRATION">
                <br>
                <input type="text" name="name" value="" id="firstname" class="form-control badge-pill setMargin"  required autofocus
                       placeholder=<fmt:message key="registration.name"/>  >
                <br>
                <input type="text" name="surname" value="" id="surname" class="form-control badge-pill setMargin" placeholder=
                <fmt:message key="registration.surname"/> required autofocus>
                <br>
                <input type="text" name="login" value="" id="login" class="form-control  badge-pill setMargin" placeholder=
                <fmt:message key="header.common.login"/> required autofocus>
                <br>
                <input type="password" name="password" value="" id="password" class="form-control  badge-pill setMargin" placeholder=
                <fmt:message key="registration.password"/> required autofocus>
                <br>
                <input type="password" name="password2" value="" id="password2" class="form-control badge-pill setMargin"
                       placeholder=
                       <fmt:message key="registration.password_repeat"/> required autofocus>
                <br>
                <input type="email" name="email" value="" id="email" class="form-control badge-pill setMargin" placeholder=
                <fmt:message key="registration.email"/> required autofocus>
                <br>
                <input type="text" name="phone" value="" id="phone" class="form-control badge-pill setMargin" placeholder=
                <fmt:message key="registration.phone"/> required autofocus>
                <br>
                <input type="text" name="passport" value="" id="passport" class="form-control badge-pill setMargin" placeholder=
                <fmt:message key="registration.passport"/> required autofocus>
                <br>
                <select class="form-control badge-pill setMargin" name="country" id="country" class="col-sm-4" required autofocus>
                    <option value="Others"><fmt:message key="registration.country"/>
                    </option>
                    <option value="USA"><fmt:message key="registration.country.USA"/>
                    </option>
                    <option value="Germany"><fmt:message key="registration.country.Germany"/>
                    </option>
                    <option value="France"><fmt:message key="registration.country.France"/>
                    </option>
                    <option value="Great Britain"><fmt:message key="registration.country.UK"/>
                    </option>
                    <option value="Italy"><fmt:message key="registration.country.Italy"/>
                    </option>
                    <option value="RPC"><fmt:message key="registration.country.PRC"/>
                    </option>
                    <option value="Japan"><fmt:message key="registration.country.Japan"/>
                    </option>
                    <option value="Others"><fmt:message key="registration.country.Others"/>
                    </option>
                </select>
                <br>
                <%--                <div class="check-box">--%>
                <%--                    <input type="checkbox"> checking--%>
                <%--                </div>--%>
                <%--                <br>--%>
                <input type="submit" class="btn-outline-info btn badge-pill  btn-block  " value=<fmt:message key="header.common.sign_up"/>/>
            </form>
            <br>
        </div>


    <jsp:include page="/WEB-INF/view/common/footer.jsp"/>
        </div>
    <div class="col-md-2" ></div>
</div>
</div>

</body>
</html>

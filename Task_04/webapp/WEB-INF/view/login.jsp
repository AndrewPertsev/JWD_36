<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" >

<html>
<head>
    <title><fmt:message key="header.common.login"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>
<br/>

<div class row>
    <h3><fmt:message key="header.common.login"/></h3>
    <div class="col-md-6"class="mx-auto" style="background-color: white"></div>
    <div class="container">
        <div class="col-md-4" style="background-color: #122b40">
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="LOG_IN">
                <br>
                <input type="text" name="login" value="" id="login" class="form-control setMargin" placeholder="Login" required autofocus>
                <br>
                <input type="password" name="password" value="" id="password" class="form-control setMargin" placeholder="Password" required autofocus>
                <br>
                <%--                <div class="check-box">--%>
                <%--                    <input type="checkbox"> checking--%>
                <%--                </div>--%>
                <%--                <br>--%>
                <input type="submit" class=" btn-primary btn-block " value="LOG IN"/>
            </form>
            <br>
        </div>
    </div>
    <div class="col-md-4" style="background-color: white"></div>
</div>


<br/><br/>
<%String islogin = (String) request.getParameter("message_fail");
    if (islogin != null) {%>
<h2 style="color:#de1212"><%out.println(islogin);
}%></h2>

<p><h3 style="color:#90de12"><c:out value="${islogin}"  /></h3></p>


<br>
<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>




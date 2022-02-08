<%@ page import="by.epam.heritage.ap.model.Apartment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<html>
<head>
    <title>U</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css\bootstrap-datepicker3.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" >

</head>
<body>
<div class="form-outline">
    <input type="text" id="form12" class="form-control" placeholder="Quantity persons"/>
    <label class="form-label" for="form12">Example label</label>
</div>

h2>Hello, mister <c:out value="${userName}" />  <c:out value="${userSurName}" /></h2>
<p>you have id  <c:out value="${userId}" /></p>
<p>and you are guest role id - <c:out value="${userRoleId}" /></p>
<p></p>

<%--<c:set var="message" scope="page" value="${2*255}"/>--%>
<%--<c:out value="${message}"/>--%>
<%--<c:if test="${message<500}" var="testIF">--%>
<%--    <p>my message <c:out value="${message}"/></p>--%>
<%--</c:if>--%>
<%--<c:out value="${testIF}"/>--%>
<h1 style="color:#fa8847"> HELLO</h1>

<div class="container">
    <div class="form-row col-md-3 text-center">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="PUSH_OFFER_TO_USER">
            <input type="text" name="apartmentId" value=""  class="form-control setMargin"
                   placeholder="Enter ">
            <input type="submit" class=" btn-primary btn-block " value="Push "/>
        </form>
    </div>
</div>

<select class="form-control form-control-sm" class="col-sm-4">
    <option value="no_transfer">Transfer name="quantity"</option>
    <option value="car">Transfer by taxi</option>
    <option value="vip_car">Transfer by vip-car</option>
    <option value="helicopter">Transfer by helicopter</option>
</select>

<%
    String errorMessage = (String) request.getParameter("errorMessage"); //REDIRECT PARAM
    if (errorMessage != null) {
%>
<h3><%
        out.println(errorMessage);
    } ///make red colour
%></h3>
<%--<%String errorMessage = (String) request.getAttribute("errorMessage"); //FORWARD  ATTR
<%--    if (errorMessage != null) {%>--%>
<%--<h3><%out.println(errorMessage); } ///make red colour%></h3>--%>



<%--check if admin--%>
<c:set var="role" scope="session" value="admin"/>
<%--<c:out value="${message}"/>--%>
<c:if test="${not empty role and role eq 'admin'}" var="testRole">
    <p>here is role :  <c:out value="it cccccccccccccccccc is admin"/></p>
</c:if>
<c:out value="${testRole}"/>
<%--Attribute or parameter when redirect--%>



<style>
    {
        background-color: #f4511e
    ; /* Orange */
        color: #ffffff
    ;
    }</style>

<div class="container">
    <a href="Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE" button class="btn btn-primary" >TO MANAGE REQ</a>
    <a href="Controller?command=GO_TO_APARTMENT_MANAGEMENT_PAGE" button class="btn btn-primary" >TO MANAGE APAR</a>
</div>

</body>
</html>


<%@ page import="Dao.CruiseDao" %>
<%@ page import="java.util.*" %>
<%@ page import="Entities.Cruise" %>
<%@ page import="Entities.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    CruiseDao cr = new CruiseDao();
    List<Cruise> cruises = cr.getAllCruises();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>CruiseShips</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/generalstyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400;500;600;700&family=Mulish:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Neucha&display=swap"
          rel="stylesheet">

</head>
<body style="min-height: 100vh">
<fmt:setLocale value="uk"/>
<fmt:setBundle basename="language"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand"><i class="fa fa-ship" aria-hidden="true"></i><fmt:message key="lable.header"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon">

            </span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="date.jsp"><fmt:message key="lable.addcruise"/></a>
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="admin_cruises.jsp"><fmt:message key="lable.editcruise"/></a>
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="requests.jsp"><fmt:message key="lable.requests"/></a>
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item-sing">
                    <a class="btn btn-outline-warning" aria-current="page" href="/final_project2/logout"><fmt:message key="lable.logout"/></a>
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0 bg-danger">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href=""><%=session.getAttribute("Admin")%></a>
                </li>
            </ul>

        </div>
    </div>
</nav>
<div class="container bg-dark my-3" style="border: 2px solid #ffffff; border-radius: 20px;">
    <div class="card-header my-lg-3" style=" background-color: #151618;text-align: center; color: #fff; font-family: 'Mulish', sans-serif; font-size: 2vmin;"> <fmt:message key="lable.allcruises"/></div>
    <div class="row">
        <%
            if (!cruises.isEmpty()){
                for (Cruise c:cruises){
        %>

            <div class="col-md-4 my-3" style="padding-bottom: 2rem;">
                <div class="card" style="width: 19rem; height: 26rem; margin: auto" >
                    <img class="card-img-top" src="cruises_images/<%= c.getImage()%>" style="width: auto; height: 200px" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><fmt:message key="lable.cruisename"/>: <%= c.getCruise_name()%></h5>
                        <h6 class="card-title"><fmt:message key="lable.route"/>: <%= c.getRoute()%> </h6>
                        <h6 class="card-title"><fmt:message key="lable.passagercapacity"/>: <%= c.getPassenger_capacity()%> </h6>
                        <h6 class="card-title"><fmt:message key="lable.placesleft"/>: <%= c.getPlaces()%> </h6>
                        <h6 class="card-title"><fmt:message key="lable.price"/>: <%= c.getPrice()%>$ </h6>
                        <h6 class="card-title"><fmt:message key="lable.dates"/>: (<%=c.getStart_cruise_date()%>)-(<%=c.getEnd_cruise_date()%>) </h6>
                        <a href="DeleteCruiseServlet?id=<%= c.getId()%>" class="btn btn-primary" style="background-color: #448b85; border-color: #448b85;"> <fmt:message key="lable.deletecruise"/></a>
                        <a href="UpdateCruiseServlet?id=<%= c.getId()%>" class="btn btn-primary" style="background-color: #448b85; border-color: #448b85;"><fmt:message key="lable.updatecruise"/></a>
                    </div>
                </div>
            </div>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>

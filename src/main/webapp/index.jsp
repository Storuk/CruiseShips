<%@ page import="Dao.CruiseDao" %>
<%@ page import="java.util.*" %>
<%@ page import="Entities.Cruise" %>
<%@ page import="Entities.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<%
    CruiseDao cr = new CruiseDao();
    List<Cruise> cruises = cr.getAllCruises();

    if(session.getAttribute("filtered_cruises") != null){
        cruises = (List<Cruise>) session.getAttribute("filtered_cruises");
        session.removeAttribute("filtered_cruises");
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>

<!DOCTYPE html>
<html language = "${param.lang}">
<head>
    <title>Cruise Ships</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/generalstyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


</head>
<body style="min-height: 100vh">
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="language"/>
<input type = "hidden" id = "status" value="<%= request.getAttribute("status")%>">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand"><i class="fa fa-ship" aria-hidden="true"></i><fmt:message key="lable.header"/></a>
        <ul class="navbar-nav  mb-lg-0">
            <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="?language=uk"><fmt:setLocale value="uk"/><img style="width: 40px;" src="images/UA.png"></a></li>
        </ul>
        <ul class="navbar-nav  mb-lg-0">
            <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="?language=en"><fmt:setLocale value="en"/><img style="width: 40px;" src="images/EN.png"></a></li>
        </ul>
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
                    <a class="nav-link active" aria-current="page" href="index.jsp"><fmt:message key="lable.mainpages"/></a>
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="cart.jsp"><fmt:message key="lable.cart"/><span class = "badge badge-danger">${cart_list.size()}</span></a>
                </li>
            </ul>
            <% //In case, if Admin session is not set, redirect to Login page
                if(session.getAttribute("User")== null) {
            %>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/final_project2/login"><fmt:message key="lable.singin"/></a>
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item-sing">
                    <a class="btn btn-outline-info" aria-current="page" href="/final_project2/register"><fmt:message key="lable.singup"/></a>
                </li>
            </ul>
            <%
            } else {
            %>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="orders.jsp"><fmt:message key="lable.orders"/></a>
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="user_profile.jsp"><fmt:message key="lable.profile"/></a>
                </li>
            </ul>
            <ul class="navbar-nav  mb-lg-0">
                <li class="nav-item-sing">
                    <a class="btn btn-outline-warning" aria-current="page" href="/final_project2/logout"><fmt:message key="lable.logout"/></a>
                </li>
            </ul>
            <%
                }
            %>
            <ul class="navbar-nav  mb-lg-0 bg-danger">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="">
                        <%if(session.getAttribute("User")== null) {%>
                            <fmt:message key="lable.guest"/>
                        <%} else {%>
                            <%=session.getAttribute("User")%>
                        <%}%>
                    </a>
                </li>
            </ul>

        </div>
    </div>
</nav>
<div class="container bg-dark my-3" style="border: 2px solid #ffffff; border-radius: 20px;">
    <div class="card-header my-lg-3" style=" text-align: center; color: #fff; font-family: 'Mulish', sans-serif; font-size: 2vmin; background-color: #212529;"><fmt:message key="lable.allcruises"/></div>
    <form action="<%= request.getContextPath() %>/CruisesFilterServlet" method="post" class="mx-1 mx-md-4">
        <div class="d-flex flex-row align-items-center mb-4">
            <div class="form-outline flex-fill mb-0">
                <label class="form-label"  for="form3Example1c" style="color: white"><fmt:message key="lable.cruise_date"/></label>
                <input type="date" name = "date" id="form3Example1c" class="form-control" />
            </div>
        </div>
        <div class="d-flex flex-row align-items-center mb-4">
            <div class="form-outline flex-fill mb-0">
                <label class="form-label"  for="form3Example2c" style="color: white"><fmt:message key="lable.price_from"/></label>
                <input type="number" min="<%=CruiseDao.minPrice()%>" max="<%=CruiseDao.maxPrice()%>" name = "min_price" placeholder="<fmt:message key="lable.price_from"/>" id="form3Example2c" class="form-control" />
            </div>
        </div>
        <div class="d-flex flex-row align-items-center mb-4">
            <div class="form-outline flex-fill mb-0">
                <label class="form-label"  for="form3Example3c" style="color: white"><fmt:message key="lable.price_to"/></label>
                <input type="number" min="<%=CruiseDao.minPrice()%>" max="<%=CruiseDao.maxPrice()%>" name = "max_price" placeholder="<fmt:message key="lable.price_to"/>" id="form3Example3c" class="form-control" />
            </div>
        </div>
        <div class="d-flex flex-row align-items-center mb-4">
            <div class="form-outline flex-fill mb-0">
                <label class="form-label"  for="form3Example5c" style="color: white"><fmt:message key="lable.duration"/></label>
                <input type="number" name = "duration" placeholder="<fmt:message key="lable.duration"/>" id="form3Example5c" class="form-control" />
            </div>
        </div>
        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
            <button type="submit" value="Submit" class="btn btn-primary btn-lg"><fmt:message key="lable.usefilter"/></button>
        </div>
    </form>
    <div class="row">
        <%
            if (!cruises.isEmpty()){
                for (Cruise c:cruises){
        %>

        <div class="col-md-4 my-3" style="padding-bottom: 2rem;">
            <div class="card" style="width: 19rem; height: 26.2rem; margin: auto" >
                <img class="card-img-top" src="cruises_images/<%= c.getImage()%>" style="width: auto; height: 163px" alt="Card image cap">
                <div class="card-body">
                    <h4 class="card-title"><fmt:message key="lable.cruisename"/>: <%= c.getCruise_name()%></h4>
                    <h6 class="card-title"><fmt:message key="lable.route"/>: <%= c.getRoute()%> </h6>
                    <h6 class="card-title"><fmt:message key="lable.passagercapacity"/>: <%= c.getPassenger_capacity()%> </h6>
                    <h6 class="card-title"><fmt:message key="lable.placesleft"/>: <%= c.getPlaces()%> </h6>
                    <h6 class="card-title"><fmt:message key="lable.price"/>: <%= c.getPrice()%>$ </h6>
                    <h6 class="card-title"><fmt:message key="lable.dates"/>: (<%=c.getStart_cruise_date()%>)-(<%=c.getEnd_cruise_date()%>) </h6>
                    <a href="AddToCartServlet?id=<%= c.getId()%>&places=<%= c.getPlaces()%>" class="btn btn-primary" style="background-color: #448b85; border-color: #448b85;"><img style = "width: 36px; height: 32px;" src="https://cdn-user-icons.flaticon.com/5674/5674975/1661980912645.svg?token=exp=1661981813~hmac=52db715fe4bd66bf671a89b585744278"></a>
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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script type="text/javascript">
    var status = document.getElementById("status").value;
    if(status == "cruise_already_exist"){
        swal("Error", "Cruise already in a cart", "error");
    }
</script>
</html>
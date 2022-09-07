<%@ page import="Entities.User" %>
<%@ page import="Entities.Cart" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/generalstyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400;500;600;700&family=Mulish:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Neucha&display=swap"
          rel="stylesheet">

</head>
<body>
<%  session.setAttribute("responsePage", "user_profile.jsp");
    if(session.getAttribute("language") != null){%>
<fmt:setLocale value="${sessionScope.language}"/>
<%}else{%>
<fmt:setLocale value="uk"/>
<%}%>
<fmt:setBundle basename="language"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand"><i class="fa fa-ship" aria-hidden="true"></i><fmt:message key="lable.header"/></a>
        <ul class="navbar-nav  mb-lg-0">
            <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="Language?language=uk"><fmt:setLocale value="uk"/><img style="width: 40px;" src="images/UA.png"></a></li>
        </ul>
        <ul class="navbar-nav  mb-lg-0">
            <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="Language?language=en"><fmt:setLocale value="en"/><img style="width: 40px;" src="images/EN.png"></a></li>
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
                    <a class="nav-link active" aria-current="page" href="cart.jsp"><fmt:message key="lable.cart"/><span class = "badge badge-light">${ cart_list.size() }</span></a>
                </li>
            </ul>
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
<section class="vh-100" style="background-color: #f4f5f7;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-lg-8 mb-4 mb-lg-0">
                <div class="card mb-3" style="border-radius: .5rem;">
                    <div class="row g-0">
                        <div class="col-md-4 gradient-custom text-center text-white"
                             style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                 alt="Avatar" class="img-fluid my-5" style="width: 80px;" />
                            <h4><%=user.getUsername()%></h4>
                            <h5> <fmt:message key="lable.balance"/>: <%=user.getScore()%> $</h5>
                            <!--<button style = "margin-top: 1rem; margin-bottom: 1rem;" href="AddBalance" class="btn btn-success">Add balance</button>-->
                            <a href="AddBalance" class="btn btn-primary" style="margin-bottom: 1rem; background-color: #448b85; border-color: #448b85;"> <fmt:message key="lable.addbalance"/></a>
                        </div>
                        <div class="col-md-8">
                            <div class="card-body p-4">
                                <h6> <fmt:message key="lable.information"/></h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6> <fmt:message key="lable.profilefirstname"/></h6>
                                        <p class="text-muted"><%=user.getFirstName()%></p>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6> <fmt:message key="lable.profilesecondname"/></h6>
                                        <p class="text-muted"><%=user.getLastName()%></p>
                                    </div>
                                </div>
                                <h6> <fmt:message key="lable.contacts"/></h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-10 mb-3">
                                        <h6> <fmt:message key="lable.profileemail"/></h6>
                                        <p class="text-muted"><%=user.getEmail()%></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>

</html>
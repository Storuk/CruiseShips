<%@ page import="Entities.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User) session.getAttribute("user");%>
<html>
<head>
    <title>CruisePayment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/stylelogin.css">
    <link rel="stylesheet"
          href="fonts/material-icon/css/material-design-iconic-font.min.css">
</head>
<body>
<%if(session.getAttribute("language") != null){%>
<fmt:setLocale value="${sessionScope.language}"/>
<%}else{%>
<fmt:setLocale value="uk"/>
<%}%>
<fmt:setBundle basename="language"/>
<input type = "hidden" id = "status" value="<%= request.getAttribute("status")%>">
<section class="vh-100">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-12">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-12 col-lg-8 col-xl-7 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"><fmt:message key="lable.paycruisepage"/></p>
                                <div class="container" style="background-color: #212529; width: 100% !important; color: white">
                                <form action="OrderServlet?cruiseID=<%=request.getAttribute("cruiseID")%>&cruiseQuantity=<%=request.getAttribute("cruiseQuantity")%>&places=<%=request.getAttribute("places")%>&cruiseName=<%=request.getAttribute("cruiseName")%>" method="post" class="mx-1 mx-md-4" enctype="multipart/form-data">
                                    <div class="d-flex flex-row align-items-center justify-content-center mb-4">
                                        <div class="form-outline flex-fill mb-0">
                                            <label for="form3Example2c"><fmt:message key="lable.usernamepay"/>:</label>
                                            <input type="text" name="description" required = "required" value = "<%=user.getUsername()%>" placeholder="Enter your username" id="form3Example2c" class="form-control" readonly="readonly" />
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center justify-content-center mb-4">
                                        <div class="form-outline flex-fill mb-0">
                                            <label for="formFileDisabled"><fmt:message key="lable.uploud_documents"/>:</label>
                                            <input class="form-control" type="file" required = "required" name="file" size="60" id="formFileDisabled" />
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center justify-content-center mb-4">
                                        <div class="form-outline flex-fill mb-0">
                                            <label class="form-label"  for="form3Example1c"><fmt:message key="lable.payment_amount"/></label>
                                            <input type="number" name = "sum" required = "required" value = "<%=session.getAttribute("Cruise_price")%>" placeholder="Enter the sum" id="form3Example1c" class="form-control" readonly="readonly" />
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" value="Submit" style="margin-bottom: 1rem" class="btn btn-warning btn-lg"><fmt:message key="lable.paycruise"/></button>
                                    </div>
                                </form>
                                </div>
                                <p class="text-center text-muted mt-5 mb-0"><a href="index.jsp"
                                                                               class="fw-bold text-body"><u><fmt:message key="lable.goback"/> </u></a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script type="text/javascript">

    var status = document.getElementById("status").value;
    if(status == "not_enough_balance"){
        swal("Error", "Not enough Balance: <%=user.getScore()%>", "error");
    }
    else if(status == "Invalid_photo_type"){
        swal("Invalid photo type", "JPEG PNG SVG is allowed", "error");
    }
    else if(status == "No_enough_places"){
        swal("Error", "No enough tickets", "Error");
    }
</script>
</body>
</html>
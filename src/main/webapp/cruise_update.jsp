<%@ page import="Entities.Cruise" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Cruise cruise = (Cruise) request.getAttribute("updateCruise");
if(request.getAttribute("updateCruise") == null)
response.sendRedirect("admin_cruises.jsp");
%>
<!DOCTYPE html>
<html language>
<head>
    <title>Java Date Picker</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/stylelogin.css">
</head>
<body style="min-height: 100vh">
<%if(session.getAttribute("language") != null){%>
<fmt:setLocale value="${sessionScope.language}"/>
<%}else{%>
<fmt:setLocale value="uk"/>
<%}%>
<fmt:setBundle basename="language"/>
<section class="vh-100">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-12">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"><fmt:message key="lable.updateCruise"/></p>

                                <form action="<%= request.getContextPath() %>/UpdateCruiseServlet?cruiseId=<%= request.getAttribute("cruiseId")%>" method="post" class="mx-1 mx-md-4">
                                    <div class="col-lg-6 col-xl-12">
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example1c"><fmt:message key="lable.updateCruiseName"/>:</label>
                                                <input type="text" name = "cruise_name" value="<%=cruise.getCruise_name()%>" required = "required" placeholder="Login" id="form3Example1c" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example1c"><fmt:message key="lable.updateCruiseDuration"/>:</label>
                                                <input type="text" name = "duration" value="<%=cruise.getDuration()%>" required = "required" placeholder="Password" id="form3Example5c" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example3c"><fmt:message key="lable.updateCruiseStartDate"/>:</label>
                                                <input type="date" name = "start_cruise_date" value="<%=cruise.getStart_cruise_date()%>" required = "required" placeholder="Login" id="form3Example3c" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-xl-12">
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example6c"><fmt:message key="lable.updateCruiseShip"/>:</label>
                                                <input type="number" name = "ship_id" oninput="this.value = this.value.replace(/[^1-5.]/g, '').replace(/(\..*)\./g, '$1');" value="<%=cruise.getShip_id()%>" required = "required" placeholder="Password" id="form3Example6c" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example2c"><fmt:message key="lable.updateCruisePrice"/>:</label>
                                                <input type="text" name = "price" value="<%=cruise.getPrice()%>" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required = "required" placeholder="Login" id="form3Example2c" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example4c"><fmt:message key="lable.updateCruiseEndDate"/>:</label>
                                                <input type="date" name = "end_cruise_date" value="<%=cruise.getEnd_cruise_date()%>" required = "required" placeholder="Password" id="form3Example4c" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="lable.updateButton"/></button>
                                    </div>
                                    <p class="text-center text-muted mt-5 mb-0"><a href="admin_cruises.jsp"
                                                                                                          class="fw-bold text-body"><u><fmt:message key="lable.updateBack"/></u></a></p>

                                </form>
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
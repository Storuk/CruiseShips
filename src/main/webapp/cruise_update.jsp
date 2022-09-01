<%@ page import="Entities.Cruise" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Cruise cruise = (Cruise) request.getAttribute("updateCruise");
if(request.getAttribute("updateCruise") == null)
response.sendRedirect("admin_cruises.jsp");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Java Date Picker</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/stylelogin.css">
</head>
<body style="min-height: 100vh">
<input type = "hidden" id = "status" value="<%= request.getAttribute("status")%>">
<section class="vh-100">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-12">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Update Cruise</p>

                                <form action="<%= request.getContextPath() %>/UpdateCruiseServlet?cruiseId=<%= request.getAttribute("cruiseId")%>" method="post" class="mx-1 mx-md-4">
                                    <div class="col-lg-6 col-xl-12">
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example1c">Cruise name</label>
                                                <input type="text" name = "cruise_name" value="<%=cruise.getCruise_name()%>" required = "required" placeholder="Login" id="form3Example1c" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example1c">Duration:</label>
                                                <input type="text" name = "duration" value="<%=cruise.getDuration()%>" required = "required" placeholder="Password" id="form3Example5c" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example3c">Start cruise date:</label>
                                                <input type="date" name = "start_cruise_date" value="<%=cruise.getStart_cruise_date()%>" required = "required" placeholder="Login" id="form3Example3c" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-xl-12">
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example6c">Ship name:</label>
                                                <input type="text" name = "ship_name"  value="<%=cruise.getShip_id()%>" required = "required" placeholder="Password" id="form3Example6c" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example2c">Price:</label>
                                                <input type="text" name = "price" value="<%=cruise.getPrice()%>" required = "required" placeholder="Login" id="form3Example2c" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"  for="form3Example4c">End cruise date:</label>
                                                <input type="date" name = "end_cruise_date" value="<%=cruise.getEnd_cruise_date()%>" required = "required" placeholder="Password" id="form3Example4c" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-primary btn-lg">Update</button>
                                    </div>
                                    <p class="text-center text-muted mt-5 mb-0"><a href="admin_cruises.jsp"
                                                                                                          class="fw-bold text-body"><u>Go back</u></a></p>

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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script type="text/javascript">
    var status = document.getElementById("status").value;
    if(status === "Invalid_photo_type"){
        swal("Invalid photo type", "JPEG PNG SVG is allowed", "error");
    }
    else if(status == "Uploaded"){
        swal("Congrats", "Cruise added successfully", "success");
    }
    else if(status == "Invalid_dates"){
        swal("Something went wrong", "Dates <%=request.getAttribute("dates")%> already exist", "error");
    }
    else if(status == "Cruise_name_exist"){
        swal("Error", "Cruise name already exist", "error");
    }
</script>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Java Date Picker</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/generalstyle.css">
    <link rel="stylesheet" href="css/addship.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400;500;600;700&family=Mulish:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Neucha&display=swap" rel="stylesheet">
</head>
<body style="min-height: 100vh">
<input type = "hidden" id = "status" value="<%= request.getAttribute("status")%>">

<%  session.setAttribute("responsePage", "add_cruise.jsp");
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
                    <a class="nav-link active" aria-current="page" href="add_cruise.jsp"><fmt:message key="lable.addcruise"/></a>
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
<div style="display: flex; align-items: center; flex-direction: column; margin-top: 4rem;">
    <form style = "position: relative; background-color: #ffffff; border-radius: 20px; box-shadow: 0 0 10px #000000; max-width: 1200px; font-weight:600; font-family: 'Mulish', sans-serif;" action="AddCruiseServlet" method="post" enctype="multipart/form-data">
        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"><fmt:message key="lable.addCruise"/></p>
        <div class="flex-row">
            <div class = "flex-column">
                <input placeholder="<fmt:message key="lable.addCruiseName"/>" type="text" name="cruise_name" id="cruise_name">

                <div class="ship-name">
                    <label for = "ship_name" id="cs" ><fmt:message key="lable.addCruiseShip"/></label>
                    <select name = "ship_name" id = "ship_name" required="required" value="3">
                        <option value="1">Symphony of the Seas</option>
                        <option value="2">Harmony of the Seas</option>
                        <option value="3">Oasis of the Seas</option>
                        <option value="4">AIDAnova of the Seas</option>
                        <option value="5">Allure of the seas</option>
                    </select>
                </div>

                <input placeholder="<fmt:message key="lable.addCruisePrice"/>" min="1" step="any" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" type="number" name="price" required="required" id="price">

            </div>

            <div class = "flex-column">

                <input placeholder="<fmt:message key="lable.addCruiseDuration"/>" min="1" type="number" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" name="duration" required="required" id="duration">

                <div class="date-picker">
                    <div>
                        <label for="start_cruise_date"><fmt:message key="lable.addCruiseStartDate"/></label>
                        <input placeholder="Start Cruise Date" type="date" name="start_cruise_date" required="required" id="start_cruise_date">
                    </div>

                    <div>
                        <label for="end_cruise_date"><fmt:message key="lable.addCruiseEndDate"/></label>
                        <input placeholder="End Cruise Date" type="date" name="end_cruise_date" required="required" id="end_cruise_date">
                    </div>

                </div>

                <div class="file-chooser">
                    <label for="formFileDisabled"><fmt:message key="lable.addCruisePhoto"/>:</label>
                    <input class="form-control" type="file" required = "required" name="file" size="60" id="formFileDisabled" />
                </div>

            </div>
        </div>
        <div style="margin-top: 2rem;">
            <button type="submit" style="margin-bottom: 1rem;" value="Submit" id="button"><fmt:message key="lable.addCruiseButton"/></button>
        </div>
    </form>
</div>
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
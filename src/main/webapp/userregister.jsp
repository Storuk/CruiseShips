<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styleregister.css">
    <link rel="stylesheet"
          href="fonts/material-icon/css/material-design-iconic-font.min.css">
</head>
<body>
<fmt:setLocale value="uk"/>
<fmt:setBundle basename="language"/>
<input type = "hidden" id = "status" value="<%= request.getAttribute("status")%>">
<section class="vh-100">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-12">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"><fmt:message key="lable.singupuser"/></p>

                                <form action="<%= request.getContextPath() %>/register" method="post" class="mx-1 mx-md-4">

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" name = "username" required = "required" placeholder="<fmt:message key="lable.username"/>" id="form3Example1c" class="form-control" />
                                            <label class="form-label"  for="form3Example1c"></label>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" name = "firstName" required = "required" placeholder="<fmt:message key="lable.firstname"/>" id="form3Example2c" class="form-control" />
                                            <label class="form-label"  for="form3Example1c"></label>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" name = "lastName" required = "required" placeholder="<fmt:message key="lable.secondname"/>" id="form3Example3c" class="form-control" />
                                            <label class="form-label"  for="form3Example1c"></label>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="email" name = "email" required = "required" placeholder="<fmt:message key="lable.email"/>" id="form3Example4c" class="form-control" />
                                            <label class="form-label"  for="form3Example3c"></label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" name = "password" required = "required" placeholder="<fmt:message key="lable.passwordsingup"/>" id="form3Example5c" class="form-control" />
                                            <label class="form-label"  for="form3Example4c"></label>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" name = "re_password" required = "required" placeholder="<fmt:message key="lable.repeatpassword"/>" id="form3Example4cd" class="form-control" />
                                            <label class="form-label" for="form3Example4cd"></label>
                                        </div>
                                    </div>
                                    <div class="form-check d-flex justify-content-center mb-4">
                                        <input class="form-check-input me-2" required = "required" type="checkbox" value="" id="form2Example3c" />
                                        <label class="form-check-label" for="form2Example3c">
                                            <fmt:message key="lable.agreeall"/><a href="#!"> <fmt:message key="lable.term"/></a>
                                        </label>
                                    </div>

                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" value="Submit" class="btn btn-primary btn-lg"><fmt:message key="lable.register"/></button>
                                    </div>
                                    <p class="text-center text-muted mt-5 mb-0" style="margin-top: 0 !important;"><fmt:message key="lable.haveaccount"/> <a href="/final_project2/login"
                                                                                                            class="fw-bold text-body"><u><fmt:message key="lable.loginhere"/></u></a></p>
                                </form>

                            </div>
                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                     class="img-fluid" alt="Sample image">
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
    if (status == "success") {
        swal("Congrats", "Account created successfully", "success");
    }else if(status == "invalid_pass"){
        swal("Something went wrong", "Password are different", "error");
    }
    else if(status == "invalid_login"){
        swal("Something went wrong", "Username already exist", "error");
    }
    else if(status == "invalid_password_type"){
        swal("Something went wrong", "Password length should be 8", "error");
    }
</script>
</body>
</html>

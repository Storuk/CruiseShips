<%@ page import="java.text.DecimalFormat" %>
<%@ page import="Entities.User" %>
<%@ page import="Entities.UserOrders" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.UserOrdersDao" %>
<%@ page import="Enums.CruiseStatusEnum" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);

    User admin = (User) request.getSession().getAttribute("admin");
    List<UserOrders> orders = null;

    if (admin != null) {
        UserOrdersDao orderDao  = new UserOrdersDao();
        orders = orderDao.userOrdersForAdmin();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .table tbody td{
            vertical-align: middle;
        }
        .btn-incre, .btn-decre{
            box-shadow: none;
            font-size: 25px;
        }

    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400;500;600;700&family=Mulish:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Neucha&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/generalstyle.css">
</head>
<body>
<%  session.setAttribute("responsePage", "requests.jsp");
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
<div class="container my-3 p-3" style="background-color: #ffffff; border-radius: 20px; box-shadow: 0px 0px 10px;">
    <div class="card-header my-3" style=" background-color: #212529; text-align: center; color: #fff; font-family: 'Mulish', sans-serif; font-size: 2vmin;">All Orders</div>
    <table class="table table-dark my-3">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="lable.orderdate"/></th>
            <th scope="col"><fmt:message key="lable.ordername"/></th>
            <th scope="col"><fmt:message key="lable.userid"/></th>
            <th scope="col"><fmt:message key="lable.quantity"/></th>
            <th scope="col"><fmt:message key="lable.orderprice"/></th>
            <th scope="col"><fmt:message key="lable.orderstatus"/></th>
            <th scope="col"><fmt:message key="lable.acceptrequest"/></th>
            <th scope="col"><fmt:message key="lable.deleteorder"/></th>
            <th scope="col"><fmt:message key="lable.documents"/></th>
        </tr>
        </thead>
        <tbody>


        <%
            if(orders != null){
                for(UserOrders o:orders){%>
        <tr>
            <td><%=o.getDate() %></td>
            <td><%=o.getCruise_name() %></td>
            <td><%=o.getU_id() %></td>
            <td><%=o.getQuantity() %></td>
            <td><%=dcf.format(o.getPaymentAmount()) %>$</td>
            <td><%=o.getStatusId() %></td>
            <% if(o.getStatusId() == CruiseStatusEnum.IN_PROGRESS){ %>
            <td><a class="btn btn-sm btn-success" href="AcceptOrder?id=<%=o.getOrderId()%>"><fmt:message key="lable.acceptrequest"/></a></td>
            <td><a class="btn btn-sm btn-danger" href="CancelOrder?money=<%=o.getPaymentAmount()%>&userId=<%=o.getU_id() %>&id=<%=o.getOrderId()%>&quantity=<%=o.getQuantity()%>&cruiseId=<%=o.getId()%>"><fmt:message key="lable.deleteorder"/></a></td>
            <%} else if(o.getStatusId() == CruiseStatusEnum.DELETED_BY_ADMIN){ %>
            <td><fmt:message key="lable.notallowed"/></td>
            <td><a class="btn btn-sm btn-danger" href="CancelOrder?money=<%=o.getPaymentAmount()%>&userId=<%=o.getU_id() %>&id=<%=o.getOrderId()%>&quantity=<%=o.getQuantity()%>&cruiseId=<%=o.getId()%>"><fmt:message key="lable.deleteorder"/></a></td>
            <%}
            else{%>
            <td><fmt:message key="lable.notallowed"/></td>
            <td><fmt:message key="lable.notallowed"/></td>
            <%}
            %>
            <td><img class="card-img-top" src="documents_images/<%= o.getImages()%>" style="width: 220px; height: 200px" alt="Card image cap"></td>
        </tr>
        <%}
        }
        %>
        </tbody>
    </table>
</div>
</body>
</html>

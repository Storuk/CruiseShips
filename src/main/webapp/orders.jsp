<%@ page import="java.text.DecimalFormat" %>
<%@ page import="Entities.User" %>
<%@ page import="Entities.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entities.UserOrders" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.UserOrdersDao" %>
<%@ page import="Enums.CruiseStatusEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  DecimalFormat dcf = new DecimalFormat("#.##");
  request.setAttribute("dcf", dcf);
  User user = (User) request.getSession().getAttribute("user");
  List<UserOrders> orders = null;
  if (user != null) {
    UserOrdersDao orderDao  = new UserOrdersDao();
    orders = orderDao.userOrders(user.getId());
  }
  else{
    response.sendRedirect("login.jsp");
  }
  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
  }
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="css/generalstyle.css">
</head>
<body>
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
<div class="container my-3 p-3" style="background-color: #ffffff; border-radius: 20px; box-shadow: 0px 0px 10px;">
  <div class="card-header my-3" style=" background-color: #212529; text-align: center; color: #fff; font-family: 'Mulish', sans-serif; font-size: 2vmin;"><fmt:message key="lable.allorders"/></div>
  <table class="table table-dark my-3">
    <thead>
    <tr>
      <th scope="col"> <fmt:message key="lable.orderid"/></th>
      <th scope="col"> <fmt:message key="lable.orderdate"/></th>
      <th scope="col"> <fmt:message key="lable.ordername"/></th>
      <th scope="col"> <fmt:message key="lable.quantity"/></th>
      <th scope="col"> <fmt:message key="lable.orderprice"/></th>
      <th scope="col"> <fmt:message key="lable.orderstatus"/></th>
      <th scope="col"> <fmt:message key="lable.deleteorder"/></th>
    </tr>
    </thead>
    <tbody>


    <%
      if(orders != null){
        for(UserOrders o:orders){%>
    <tr>
      <td><%=o.getOrderId()%></td>
      <td><%=o.getDate() %></td>
      <td><%=o.getCruise_name() %></td>
      <td><%=o.getQuantity() %></td>
      <td><%=dcf.format(o.getPaymentAmount()) %>$</td>
      <td><%=o.getStatusId() %></td>
      <% if(o.getStatusId() == CruiseStatusEnum.IN_PROGRESS || o.getStatusId() == CruiseStatusEnum.DELETED_BY_ADMIN){ %>
      <td><a class="btn btn-sm btn-danger" href="DeleteOrder?money=<%=o.getPaymentAmount()%>&userId=<%=user.getId()%>&id=<%=o.getOrderId()%>&quantity=<%=o.getQuantity()%>&cruiseId=<%=o.getId()%>"><fmt:message key="lable.deleteorder"/></a></td>
      <%} else{ %>
      <td><fmt:message key="lable.notallowed"/></td>
      <%} %>
    </tr>
    <%}
    }%>
    </tbody>
  </table>
</div>
</body>
</html>
<%@ page import="Entities.Cart" %>
<%@page import="java.util.*"%>
<%@ page import="Dao.CartDao" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  DecimalFormat dcf = new DecimalFormat("#.##");
  request.setAttribute("dcf", dcf);

  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  List<Cart> cartProduct = null;
  if (cart_list != null) {
    CartDao pDao = new CartDao();
    double total = pDao.getTotalCartPrice(cart_list);
    cartProduct = pDao.getCartProducts(cart_list);
    request.setAttribute("total", total);
    request.setAttribute("cart_list", cart_list);
  }
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cruise Ships</title>
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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  <link rel="stylesheet" href="css/generalstyle.css">
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
          <a class="nav-link active" aria-current="page" href="index.jsp"><fmt:message key="lable.mainpages"/></a>
        </li>
      </ul>
      <ul class="navbar-nav  mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="cart.jsp"><fmt:message key="lable.cart"/><span class = "badge badge-light">${ cart_list.size() }</span></a>
        </li>
      </ul>
      <% //In case, if Admin session is not set, redirect to Login page
        if(session.getAttribute("User") == null) {
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
<div class="container my-3 p-3" style="background-color: #ffffff; border-radius: 20px; box-shadow: 0px 0px 10px;">
  <div class="d-flex py-3"><h3><fmt:message key="lable.totalprice"/>: $ ${(total>0)?dcf.format(total):0}</h3></div>
  <table class="table table-dark my-3">
    <thead>
    <tr>
      <th scope="col"><fmt:message key="lable.name"/></th>
      <th scope="col"><fmt:message key="lable.date"/></th>
      <th scope="col"><fmt:message key="lable.cartprice"/></th>
      <th scope="col"><fmt:message key="lable.buynow"/></th>
      <th scope="col"><fmt:message key="lable.remove"/></th>
    </tr>
    </thead>
    <tbody>
    <%

      if (cart_list != null) {
        for (Cart c : cartProduct) {
    %>
    <tr>
      <td><%=c.getCruise_name()%></td>
      <td><%=c.getStart_cruise_date()%></td>
      <td><%= dcf.format(c.getPrice())%>$</td>

      <td> <form action="OrderServlet" method="get" class="form-inline">
          <input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
          <div class="form-group d-flex justify-content-between w-50">
            <a class="btn bnt-sm btn-incre text-success" href="QuantityIncDecServlet?action=inc&id=<%=c.getId()%>&places=<%=c.getPlaces()%>"><i class="fas fa-plus-square"></i></a>
            <input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly>
            <a class="btn btn-sm btn-decre text-success" href="QuantityIncDecServlet?action=dec&id=<%=c.getId()%>&places=<%=c.getPlaces()%>"><i class="fas fa-minus-square"></i></a>
            <button type="submit" name="places" value="<%=c.getPlaces()%>" class="btn btn-primary btn-sm"><fmt:message key="lable.buynow"/></button>
            <div class="ghost" style=" display: none;">
              <input type="text" name="Cruise_price" class="form-control"  value="<%= dcf.format(c.getPrice())%>" readonly>
              <input type="text" name="cruiseName" class="form-control"  value="<%=c.getCruise_name()%>" readonly>
            </div>
          </div>
        </form>
      </td>
      <td><a class="btn btn-sm btn-danger" href="RemoveFromCartServlet?id=<%=c.getId()%>"><fmt:message key="lable.remove"/></a></td>
    </tr>
    <%
        }
      }%>
    </tbody>
  </table>
</div>

</body>
</html>
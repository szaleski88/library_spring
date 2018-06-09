<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zaleski
  Date: 09.06.2018
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Borrow Book</title>
    <%@ include file="head.jsp" %>
</head>
<body>

<div class="container-fluid bg-white">
    <form action="/book/borrow" method="get">
        <div class="form-group">
            <label for="borrower">Users: </label>
            <select class="form-control" id="borrower" name="borrower">

                <c:forEach items="${availableBorrowers}" var="borrower">
                    <option value="${borrower.id}">${borrower}</option>
                </c:forEach>

            </select>
        </div>
        <button type="submit" class="btn btn-primary-outline">Add New User</button>
        <div class="form-group">
            <label for="availablebooks">Available Books: </label>
            <select class="form-control" id="availablebooks" name="book">
                <C:choose>
                    <C:when test="${chosenBook ne null}">
                        <option value="${chosenBook.idBook}">${chosenBook.title}</option>
                    </C:when>
                </C:choose>

                <c:forEach items="${books}" var="book">
                    <option value="${book.idBook}">${book.title}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Borrow</button>
        <button type="reset" class="btn btn-danger">Cancel</button>
    </form>
</div>

</body>
</html>

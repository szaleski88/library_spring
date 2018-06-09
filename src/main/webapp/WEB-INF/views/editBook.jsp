<%--
  Created by IntelliJ IDEA.
  User: zaleski
  Date: 03.06.2018
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Add new Book</title>

    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" type="text/css">--%>
    <%@ include file="head.jsp"%>
</head>
<body>
<div><br></div>

<div class="container-fluid bg-white">
    <form action="/book/edit-book" method="get">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="title">Title: </label>
                    <input type="text" class="form-control" id="title" name="title" value="${book.title}">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="ISBN">ISBN: </label>
                    <input type="text" class="form-control" id="ISBN" name="isbn" value="${book.isbn}">
                </div>
            </div>
        </div>

        <div class="container-fluid">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="authorFirstName">Name: </label>
                    <input type="text" class="form-control" id="authorFirstName" name="firstName" value="${book.author.firstName}">
                </div>
                <div class="form-group">
                    <label for="authorLastName">Surname: </label>
                    <input type="text" class="form-control" id="authorLastName" name="lastName" value="${book.author.lastName}">
                </div>
            </div>
        </div>
        </div>

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="exampleFormControlSelect1">Example select</label>
                    <select class="form-control" id="exampleFormControlSelect1" name="category">
                        <option>${book.category}</option>
                        <c:forEach items="${bookTypes}" var="booktype">
                            <option>${booktype}</option>
                        </c:forEach>

                    </select>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="description">Short Description</label>
                    <textarea class="form-control" id="description" name="summary" rows="2">${book.summary}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="pages">pages no.: </label>
                    <input type="text" class="form-control" id="pages" name="pages" value="${book.pages}">
                </div>
            </div>
        </div>
        <input type="hidden" name="release" value="${book.release}"/>
        <input type="hidden" name="id" value="${book.id}"/>
        <button type="submit" class="btn btn-primary">Accept</button>
        <button type="reset" class="btn btn-danger">Cancel</button>
    </form>
</div>

</body>
</html>

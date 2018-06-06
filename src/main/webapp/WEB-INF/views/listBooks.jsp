<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library Content</title>
    <%@ include file="head.jsp" %>
</head>
<body>
<h1></h1>
<div class="container-fluid">
    <table class="table table-bordered table-hover table-dark table-responsive" id="tableOfBooks">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">ISBN</th>
            <th scope="col">Author</th>
            <th scope="col">Description</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${listOfBooks ne null}">
                <c:forEach items="${listOfBooks}" var="book">
                    <tr data-toggle="modal"
                        data-id="${book.id}"
                        data-title="${book.title}"
                        data-summary="${book.summary}"
                        data-author="${book.author.toString()}"
                        data-pages="${book.pages}"
                        data-isbn="${book.isbn.toString()}"
                        data-target="#bookModal">
                        <th scope="row">"${book.id}"</th>
                        <td>"${book.title}"</td>
                        <td>"${book.isbn}"</td>
                        <td>"${book.author.toString()}"</td>
                        <td>"${book.summary}"</td>
                        <td><input type="radio" name="radio" class="form-check-input" value="${book.id}"></td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
        </tbody>
    </table>

    <%--<nav class="navbar navbar-light bg-light">--%>
    <%--<form class="form-inline">--%>
    <%--<button class="btn btn-outline-success" type="button">Add book</button>--%>
    <%--<button class="btn btn-outline-secondary" type="button">Edit</button>--%>
    <%--<button class="btn btn-outline-danger" type="button">DELETE</button>--%>
    <%--</form>--%>
    <%--</nav>--%>

</div>

<div id="bookModal" class="modal fade" role="dialog" aria-labelledby="bookModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div class="row">
                    <div class="col">
                        <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>--%>
                        <img src="https://www.freeiconspng.com/uploads/book-icon--icon-search-engine-6.png" height="150" width="150">
                    </div>
                    <div class="col">
                        <h2 id="title"></h2>
                        <h4 id="isbn"></h4>
                    </div>
                </div>
            </div>
            <div id="author" class="modal-body"></div>
            <div id="summary" class="modal-body"></div>
            <div id="pages" class="modal-body"></div>
            <div class="modal-footer">
                <button class="btn btn-primary" aria-hidden="true">Borrow</button>
                <button class="btn btn-secondary" aria-hidden="true">Edit</button>
                <button class="btn btn-danger" aria-hidden="true">DELETE</button>
                <button class="btn btn-dark" data-dismiss="modal" aria-hidden="true">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    // $('#bookModal').modal({
    //     keyboarnd: true,
    //     backdrop: "static",
    //     show:false,
    //
    // }).on('show.bs.modal', function(){
    //     var getIdFromRow = $(this).data('bookid');
    //     //make your ajax call populate items or what even you need
    //     $(this).find('#orderDetails').html($('<b> Order Id selected: ' + getIdFromRow  + '</b>'))
    // });

    $("#tableOfBooks").find('tr[data-target]').on('click', function () {
        var modal = $('#bookModal');

        // modal.find('#bookid').html($('<b>' +   $(this).data('bookid') + '</b>' ));
        modal.find('#title').html($(this).data('title'));
        modal.find('#author').html($(this).data('author'));
        modal.find('#isbn').html("Isbn: " + $(this).data('isbn'));
        modal.find('#pages').html('Pages:' + $(this).data('pages'));
        modal.find('#summary').html($('<p>Description: ' + $(this).data('summary') + '</p>'));
    });
</script>

</body>
</html>

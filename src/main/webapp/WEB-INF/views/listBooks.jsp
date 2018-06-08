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
            <c:when test="${listOfBookDtos ne null}">
                <c:forEach items="${listOfBookDtos}" var="book">
                    <tr data-toggle="modal"
                        data-id="${book.idBook}"
                        data-title="${book.title}"
                        data-summary="${book.summary}"
                        data-author="${book.author}"
                        data-pages="${book.pages}"
                        data-borrowed="${book.isBorrowed}"
                        data-isbn="${book.isbn.toString()}"
                        data-target="#bookModal">

                        <th scope="row">"${book.idBook}"</th>
                        <td>"${book.title}"</td>
                        <td>"${book.isbn}"</td>
                        <td>"${book.author}"</td>
                        <td>"${book.summary}"</td>
                        <c:choose>
                            <c:when test="${book.isBorrowed eq true}">
                                <td><img src="http://icons.iconarchive.com/icons/kyo-tux/phuzion/256/Sign-Stop-icon.png"
                                         width="60px" height="60px"></td>
                            </c:when>
                        </c:choose>

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
                        <img src="https://www.freeiconspng.com/uploads/book-icon--icon-search-engine-6.png" height="150"
                             width="150">
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
            <div id="isborrowed" value="" type="hidden"></div>
            <div class="modal-footer">
                <form action="/menage" method="get">
                    <input id="idbook" type="hidden" name="idbook" value=""/>
                    <span>
                    <button id="buttonborrow" class="btn btn-primary" type="submit" name="borrow" name="type"
                            value="borrow" aria-hidden="true">Borrow</button>
</span>
                    <span>
                    <button id="buttonedit" class="btn btn-secondary" type="submit" name="type" value="edit"
                            aria-hidden="true">Edit</button>
                    </span>
                    <span>

                        <button id="buttondelete" class="btn btn-danger" aria-hidden="true" name="type" value="delete">DELETE</button>
                 </span>
                    <span>
                            <button class="btn btn-dark" data-dismiss="modal" aria-hidden="true">Close</button>
                </span>
                </form>

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
        document.getElementById('idbook').value = $(this).data('id');
        var borrowed = $(this).data('borrowed');
        var borrowButton = document.getElementById('buttonborrow');
        if (borrowed) {
            borrowButton.innerText = "Return";
            borrowButton.className = "btn btn-warning";
            borrowButton.value = "return";
        } else {
            borrowButton.innerText = "Borrow";
            borrowButton.className = "btn btn-primary";
            borrowButton.value = "borrow";
        }

        modal.find('#summary').html($('<p>Description: ' + $(this).data('summary') + '</p>'));
    });
</script>

</body>
</html>

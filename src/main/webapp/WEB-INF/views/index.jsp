<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    <title>Форум job4j</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h4>Форум job4j</h4>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Тема</th>
                <th scope="col">Описание</th>
                <th scope="col">Дата создания</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${posts}" var="post">
                <tr>
                    <td><c:out value="${post.id}"/></td>
                    <td><c:out value="${post.name}"/></td>
                    <td><c:out value="${post.desc}"/></td>
                    <fmt:formatDate value="${post.created.time}" pattern="dd.MM.yyyy" var="created"/>
                    <td><c:out value="${created}"/></td>
                    <td>
                        <button type="button" class="btn btn-outline-primary"
                                onclick="location.href = '/post/update?id=' + ${post.id}"
                                style="margin: 10px">Edit</button>
                        <button type="button" class="btn btn-outline-primary deletePostButton" data-post-id="${post.id}" style="margin: 10px">Delete</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div>
        <button type="button" class="btn btn-outline-primary"
                onclick="location.href = '<%=request.getContextPath()%>/post/add'" style="margin: 10px">Add
        </button>
        <button type="button" class="btn btn-outline-primary"
                onclick="location.href = '<%=request.getContextPath()%>/logout'" style="margin: 10px">Logout
        </button>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script>
    $('.deletePostButton').on('click', function () {
        var postId = $(this).attr('data-post-id');
        $.ajax({
            type: 'DELETE',
            url: '/post/' + postId,
            success: function (result) {
                console.log(result);
                window.location='/';
            },
            error: function (e) {
                console.log(e);
            }
        });
    });
</script>
</body>
</html>

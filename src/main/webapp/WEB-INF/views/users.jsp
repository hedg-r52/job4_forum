<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        ._margin10 {
            margin: 10px;
        }

        .selected {
            background-color: gray;
            color: #FFF;
        }
    </style>
    <title>User list</title>
</head>

<body>
<div class="container">
    <div class="user-list">
        <div class="row">
            <table id="table" class="table">
                <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Enabled</th>
                    <th scope="col">Roles</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr data-row-id="${user.id}">
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.enabled}"/></td>
                        <td>
                            <c:forEach items="${user.roles}" var="role">
                                <c:out value="${role.authority}"/>
                                <c:out value=" "/>
                            </c:forEach>
                        </td>
                        <td>

                            <button type="button" class="btn btn-outline-primary _margin10" onclick="location.href = '/users/update?id=' + ${user.id}">Edit</button>
                            <button type="submit" class="btn btn-outline-primary _margin10 deleteUser" formaction="/user/delete" data-user-id="${user.id}">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
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
</body>
<script>
    $('.deleteUser').on('click', function () {
        const userId = $(this).attr('data-user-id');
        $.ajax({
            type: 'DELETE',
            url: '/users/' + userId,
            success: function (result) {
                window.location='/users';
            },
            error: function (e) {
                console.log(e);
            }
        });
    });
</script>
</html>
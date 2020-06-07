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
    <div class="user-form">
        <%--@elvariable id="user" type="ru.job4j.forum.model.User"--%>
        <form:form class="form-inline" method="POST" modelAttribute="user">
            <div class="input-group mb-3" style="margin-top: 20px">
                <form:input type="hidden" id="id" path="id"/>
                <div>
                    <label for="username" class="mr-sm-2">Username : </label>
                    <form:input type="text" class="form-control _margin10" id="username" path="username"
                                placeholder="Username"/>
                </div>
                <div>
                    <label for="password" class="mr-sm-2">Password : </label>
                    <form:input type="password" class="form-control _margin10" id="password" path="password"
                                placeholder="Password"/>
                </div>
                <div>
                    <div class="custom-control custom-switch">
                        <form:checkbox class="custom-control-input _margin10" id="enabled" path="enabled"/>
                        <label class="custom-control-label" for="enabled">enabled</label>
                    </div>
                </div>
                <label for="roles" class="mr-sm-2">Roles : </label>
                <div id="roles">
                    USER: <form:checkbox class="form-control _margin10" path="roles" id="roleuser" value="ROLE_USER"/>
                    ADMIN: <form:checkbox class="form-control _margin10" path="roles" id="roleadmin" value="ROLE_ADMIN"/>
                </div>
                <button type="submit" class="btn btn-outline-primary _margin10" formaction="/user/update">Update
                </button>
                <button type="submit" class="btn btn-outline-primary _margin10" formaction="/user/delete">Delete
                </button>
            </div>
        </form:form>
    </div>
    <div class="user-list">
        <div class="row">
            <table id="table" class="table">
                <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Enabled</th>
                    <th scope="col">Roles</th>
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
<script>
    $("#table tr").click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
        var value = $(this).find('td:first').html();
        $tds = $(this).find("td");
        document.getElementById("username").value = $tds[0].firstChild.textContent;
        document.getElementById("enabled").checked = ($tds[1].firstChild.textContent === 'true');
        document.getElementById("id").value = $(this).attr("data-row-id");
        $user_roles = $tds[2].firstChild.textContent;
        $('#roleuser').prop("checked", $user_roles.indexOf("ROLE_USER") !== -1);
        $('#roleadmin').prop("checked", $user_roles.indexOf("ROLE_ADMIN") !== -1);

    });
    $('.updateUser').on('click', function () {

    });
    $('.deleteUser').on('click', function () {

    });
    role_user = {

    }
</script>
</body>
</html>
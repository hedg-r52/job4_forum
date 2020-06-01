<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0;">
            ${errorMessage}
    </div>
</c:if>
<div class="container">
    <h2>Login</h2>
    <form name='login' action='<%=request.getContextPath()%>/login' method='POST'>
        <div class="row" style="margin-top: 10px">
            <div class="col-1">
                <label for='name'>UserName:</label>
            </div>
            <div class="col-10">
                <input id='name' type='text' name='username'>
            </div>
        </div>
        <div class="row" style="margin-top: 10px">
            <div class="col-1">
                <label for='password'>Password:</label>
            </div>
            <div class="col-10">
                <input id='password' type='password' name='password'/>
            </div>
        </div>
        <div  class="row">
            <input type="submit" class="btn btn-outline-primary" name="submit" value="Sign in" style="margin: 10px"/>
            <button type="button" class="btn btn-outline-primary" onclick="location.href = '<%=request.getContextPath()%>/reg'" style="margin: 10px">Sign up</button>
        </div>
    </form>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
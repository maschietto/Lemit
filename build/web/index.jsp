<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>ULAZ</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/main.js"></script>
    </head>
    <body>
         <%@include file="jspf/poruka.jspf"%> 
         <div class="container">
               <div class="my_user_form login">    
                <h2>Login</h2>
                <form method="post" action="CommandServlet?cmd=login">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" name="user" class="form-control" id="email" placeholder="Enter Name" required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" name="password" class="form-control" id="pwd" placeholder="Enter password" required>
                    </div>
                    <div class="reg_anchor">
                        <a href="CommandServlet?cmd=editkorisnik">
                         <input type="button" value="Registruj se" class="btn btn-default"/>
                        </a>
                    </div>
                    <div class="checkbox rememberme">
                        <label><input type="checkbox">Remember me</label>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </body>
</html>

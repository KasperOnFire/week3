<%-- 
    Document   : index
    Created on : 26-02-2017, 13:27:25
    Author     : Kasper
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Kaspers Bibliotek</title>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></script>
        <link href="css/main.css" rel="stylesheet">
    </head>
    <body>
        <div class="maindiv">
            <form action="Login" method="POST">
                Username:<br>
                <input type="text" name="username" placeholder="Mickey">
                <br>
                Password:<br>
                <input type="password" name="userpass" placeholder="******">
                <br><br>
                <input type="submit" value="Submit"><br>
            </form>
            <a href="register.html">Ikke en bruger? Registrer her!</a><br>
            <a href="bibliotekar.html">Bibliotekar login</a>
            <script src="sorttable.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        </div>
    </body>
</html>


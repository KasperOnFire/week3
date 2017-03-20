<%-- 
    Document   : LibraryMain
    Created on : 23-02-2017, 15:12:31
    Author     : brein
--%>

<%@page import="libraryInventory.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/main.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Kaspers Bibliotek</title>
    </head>
    <body>
        <div class="maindiv">
        <input type="hidden" name="user" value="">
        <h1>Kaspers Bibliotek</h1>
        <a href="additem.html">Tilføj emne</a>
        <table class="sortable table table-striped table-bordered">
            <tr>
                <th>ISBN</th>
                <th>Titel</th>
                <th>Forfatter</th>
                <th>Sprog</th>
                <th>Type</th>
                <th>Udgiver</th>
                <th>Udg. Dato </th>
            </tr>
            <%
                ArrayList<Item> inventory = (ArrayList<Item>) request.getAttribute("inventory");
                for (Item item : inventory) {
            %>
            <tr>
                <td><%= item.isbn%></td>
                <td><%= item.title%></td>
                <td><%= item.author%></td>
                <td><%= item.language%></td>
                <td><%= item.itype%></td>
                <td><%= item.publisher%></td>
                <td><%= item.publishdate%></td>
            </tr>
            <%
                }
            %>
        </table>
        </div>
        <script src="sorttable.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </body>
</html>

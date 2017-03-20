<%-- 
    Document   : Reserved
    Created on : 23-02-2017, 20:15:22
    Author     : Kasper
--%>

<%@page import="libraryInventory.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Kaspers Bibliotek</title>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></script>
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <div class="maindiv">
            <h1>Kaspers Bibliotek</h1>
            <a href="LibraryServlet">Tilbage til Biblioteket</a>
            <table class="sortable">
                <tr>
                    <th>ISBN</th>
                    <th>Titel</th>
                    <th>Forfatter</th>
                    <th>Sprog</th>
                    <th>Type</th>
                    <th>Udgiver</th>
                    <th>Udg. Dato </th>
                    <th>slet reservation</th>
                </tr>
                <%
                    ArrayList<Item> reserved = (ArrayList<Item>) request.getAttribute("reserved");
                    for (Item item : reserved) {
                %>
                <tr>
                    <td><%= item.isbn%></td>
                    <td><%= item.title%></td>
                    <td><%= item.author%></td>
                    <td><%= item.language%></td>
                    <td><%= item.itype%></td>
                    <td><%= item.publisher%></td>
                    <td><%= item.publishdate%></td>
                    <td>
                        <form action="DeleteReservation" method="post">
                            <input type="hidden" name="ino" value="<%= item.ino%>">
                            <input type="submit" name="submit" value="Slet">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <script src="sorttable.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </body>
</html>

<%@ page import="DAO.dao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style.css">
    <style type="text/css">
    table {
    font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
    font-size: 14px;
    border-collapse: collapse;
    text-align: center;
    }
    th, td:first-child {
    background: #AFCDE7;
    color: white;
    padding: 10px 20px;
    }
    th, td {
    border-style: solid;
    border-width: 0 1px 1px 0;
    border-color: white;
    }
    td {
    background: #D8E6F3;
    }
    th:first-child, td:first-child {
    text-align: left;
    }
    </style>
</head>
<body>
<%
    request.setAttribute("persons", new dao().getPersons());
%>
<div class="container">

    <h2 align="center">Table of users</h2>

    <div class="pure-control-group">
        <button class="pure-button pure-button-primary"
                onclick="addRow('dataTable'); this.disabled = true">Add Row
        </button>

        <a id="deleteURL" href="/?action=delete&id=">
            <button class="pure-button pure-button-primary"
                    onclick="deleteRow('dataTable')">Delete Row
            </button>
        </a>
    </div>
    <br>
    <table id="dataTable" class="pure-table pure-table-bordered">
        <thead>
        <tr>
            <th></th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Passport Series</th>
            <th>Passport Number</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${persons}" var="Person">
            <tr>
                <td><INPUT type="checkbox" onchange="test(this)" name="chk"/>
                    <input type="text" hidden disabled value=<c:out value="${Person.id}"/>>
                <td><c:out value="${Person.id}"/></td>
                <td><c:out value="${Person.name}"/></td>
                <td><c:out value="${Person.surname}"/></td>
                <td><c:out value="${Person.getPassportSeries()}"/></td>
                <td><c:out value="${Person.getPassportNumber()}"/></td>
                <td><a href="/?action=edit&id=<c:out value="${Person.id}"></c:out>">
                    <button class="pure-button pure-button-primary">Edit</button>
                </a>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

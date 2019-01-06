<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns:form="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>tags</title>
        <style>
            table#t02 {
                text-align: center;
                background-color: #C0C0C0;
                border: 2px solid black;
            }
            .button {
                background-color: #f44336;
                border: none;
                color: white;
                padding: 15px 24px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div align="center">
            <h2>Welcome! Please, enter the date range:</h2>
            <form:form method="POST" action="${pageContext.request.contextPath}/visualisation" modelAttribute="formTags">
                <table id="t02">
                    <tr>
                        <td><form:label path="fromDate">From date</form:label></td>
                        <td><form:input type="date" path="fromDate"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="toDate">To date</form:label></td>
                        <td><form:input type="date" path="toDate"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" class="button"/></td>
                    </tr>
                </table>
            </form:form>
        </div>
        <% System.out.println("/form dziala");%>
    </body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns:form="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>tags</title>
    </head>
    <body>
        <h3>Welcome, enter the date details:</h3>
        <form:form method="POST" action="${pageContext.request.contextPath}/addFormTags" modelAttribute="formTags">
            <table>
                <tr>
                    <td><form:label path="fromDate">From Date</form:label></td>
                    <td><form:input path="fromDate"/></td>
                </tr>
                <tr>
                    <td><form:label path="toDate">To Date</form:label></td>
                    <td><form:input path="toDate"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
        <title>visualisation</title>
    </head>
    <body>
        <div id="wykresTagi" align = "center">
            <diva> ${wykres1} </diva>
        </div>
        <div id="wykresPytania" align = "center">
            <diva> ${wykres2} </diva>
        </div>

        <% System.out.println("/visualisation dziala");%>
    </body>
</html>

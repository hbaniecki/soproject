<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
        <title>home</title>
        <style>
            th{
                background-color:#808080;
            }
            th, td {
                padding: 5px;
                text-align: center;
                text-color: white;
                border: 1px solid black;
            }
            table#t01 {
                width: 50%;
                background-color: #C0C0C0;
                border: 2px solid black;
            }
            .button {
                background-color: #f44336;
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
            .img {
                display: block;
                margin-left: auto;
                margin-right: auto;
                width: 50%;
            }
        </style>
    </head>
    <body>
        <img src="/img/stack-overflow.png" alt="logo" class="img">

        <div id="statystyki" align = "center">
            <table id="t01">  ${tabela} </table>
        </div>
        <div align = "center">
            <input type="button" class="button"  onclick="location.href='/form'" value="Get more stats">
        </div>

        <% System.out.println("/home dziala");%>
    </body>
</html>

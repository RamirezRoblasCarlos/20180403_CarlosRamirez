<%-- 
    Document   : Inicio
    Created on : 10-abr-2018, 16:25:42
    Author     : Carlos
--%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../CSS/servlet.css" media="all" />
        <title>Saludo</title>
    </head>
    <body>
        <%
           String salida="";
           Calendar calendario = new GregorianCalendar();
           int hora =calendario.get(Calendar.HOUR_OF_DAY);
           
           if(hora>=8 && hora<=12){
               salida+="Buenos dias ";
           } else if(hora>12 && hora<=21){
               salida+="Buenas tardes ";
           } else{
               salida+="Buenas noches ";
           } 
           salida+=request.getParameter("sexo").equals("hombre")?"señor ":"señora ";
           salida+=request.getParameter("nombre");
            
            %>
            <h1> <%= salida %> </h1>
            <a href="../index.html">Volver al index</a>
    </body>
</html>

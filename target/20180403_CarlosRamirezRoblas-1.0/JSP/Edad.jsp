<%-- 
    Document   : Edad
    Created on : 10-abr-2018, 17:16:10
    Author     : Carlos
--%>


<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../CSS/servlet.css" media="all" />
        <title>Edad</title>
    </head>
    <body>
        <%
           String salida="";
           Boolean error=false;
           Calendar calendario = new GregorianCalendar();
           int dia =calendario.get(Calendar.DAY_OF_MONTH);
           int mes =calendario.get(Calendar.MONTH);
           int year =calendario.get(Calendar.YEAR);
           
           mes+=+1;
           int meses=0;
           String edad = request.getParameter("fecha"); 
           String[] cadena = edad.split("-");
           
           
           if(Integer.parseInt(cadena[0])>year){
               error=true;
           }else if(Integer.parseInt(cadena[0])>=year){
               if(Integer.parseInt(cadena[1])>=mes){
                   if(Integer.parseInt(cadena[2])>dia){
                       error=true;
                   }
               }
           }
           
           if(!error){
           
               if(Integer.parseInt(cadena[1])>mes){
               year+=-1;
               }
               salida+="Tienes "+ (year-Integer.parseInt(cadena[0]))+" años, ";
               
               if(Integer.parseInt(cadena[1])<=mes){
                   salida+=Math.abs(Integer.parseInt(cadena[1])-mes)+" meses, ";
                   }else{
               salida+=Math.abs(mes -Integer.parseInt(cadena[1]))+" meses, ";
               }
               if(Integer.parseInt(cadena[2])<=dia){
                   salida+=Math.abs(Integer.parseInt(cadena[2])-dia)+" dias.";
               }else{
                   salida+=Math.abs(dia -Integer.parseInt(cadena[2]))+" dias.";
               }
           
           }else{
           salida="Hay un error en la fecha";
           }



        %>
        <h1><%= salida %></h1>
        <a href="../index.html">Volver al index</a>
    </body>
</html>

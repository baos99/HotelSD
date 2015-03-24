<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel - Javier Jimenez y German Alonso</title>
    </head>
    <body>
        <h1>Lista de huespedes ${tipo}</h1>
        <table border="1">
            <thead>
                <tr>
                    <td><h3>Nombre</h3></td>
                    <td><h3>Apellidos</h3></td>
                    <td><h3>Nif</h3></td>
                    <td><h3>Fecha</h3></td>
                    <td><h3>Domicilio</h3></td>
                    <td><h3>Telefono</h3></td>
                    <td><h3>Movil</h3></td>
                    <td><h3>Mail</h3></td>
                    <td><h3>Opciones</h3></td>
                </tr>
            </thead>
            <tbody>
          
                <c:forEach items="${huespedes}" var="huesped" varStatus="status">
                    <tr>
                        <td>${huesped.nombre}</td>
                        <td>${huesped.apellidos}</td>
                        <td>${huesped.nif}</td>
                        <td>${huesped.fecha}</td>
                        <td>${huesped.domicilio}</td>
                        <td>${huesped.telefono}</td>
                        <td>${huesped.movil}</td>
                        <td>${huesped.mail}</td>
                        
                        <td>
                            <form action="Cancelar" method="GET">
                                <input type="hidden" name="id" value="${status}">
                                <button>Cancelar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
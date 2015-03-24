<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel - Javier Jimenez y German Alonso</title>
    </head>
    <body>
        <h1>Lista de reservas - ${tipo}</h1>
        <table border="1">
            <thead>
                <tr>
                    <td><h3># Habitacion</h3></td>
                    <td><h3>Cliente</h3></td>
                    <td><h3>Fecha Entrada</h3></td>
                    <td><h3>Fecha Salida</h3></td>
                    <td><h3>Opciones</h3></td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${reservas}" var="reserva" varStatus="status">
                    <tr>
                        <td>${reserva.habitacion}</td>
                        <td>${reserva.cliente.nombre} ${reserva.cliente.apellidos}</td>
                        <td>${reserva.fentrada}</td>
                        <td>${reserva.fsalida}</td>
                        <td>
                            <form action="/Cancelar" method="GET">
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

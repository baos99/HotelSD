<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab-pane <c:if test="${tab=='buscarReserva'}">active</c:if>" id="buscarReserva">
    <h2>reservaaaas</h2>
    
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
    
    
</div>
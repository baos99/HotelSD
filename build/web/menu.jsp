<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<c:if test="${error!=null || true}">
    <!--Mensaje de "modificación"-->
        <div class="alert alert-danger" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <p>${error}</p>
        </div>
        <c:remove var="msg"/>
</c:if>

<ul id="menu" class="nav nav-tabs" data-tabs="tabs">
        <li><a href="#administrarHuespedes" data-toggle="tab">Administrar Huespedes</a></li>
        <li><a href="#administrarReservas" data-toggle="tab">Administrar HReservas</a></li>
        <li><a href="#anadirHuesped" data-toggle="tab">Añadir Huesped</a></li>
        <li><a href="#buscarHuesped" data-toggle="tab">Buscar Huesped</a></li>
        <li><a href="#anadirReserva" data-toggle="tab">Añadir Reserva</a></li>
        <li><a href="#buscarReserva" data-toggle="tab">Buscar Reserva</a></li>
</ul>
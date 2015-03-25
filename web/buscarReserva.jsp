<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab-pane <c:if test="${tab=='buscarReserva'}">active</c:if> " id="buscarReserva">
    <div class="col-md-12" id="form">
        <div class="row">
          <div class="col-md-6" id="form">
                            <form action="ServletBuscarReserva" method="get">
                                <div class="form-group">
                                    <label>NIF</label> <input type="text" name="nif"
				class="form-control" placeholder="NIF" pattern="(\d{8})([A-Za-z]{1})" required/>
                                    <label>Fecha de entrada</label> <input type="text" name="fentrada"
				class="form-control" placeholder="Fecha de entrada" pattern="(\d{2})(/)(\d{2})(/)(\d{4})" required/>
                                </div>
                                <button>Búscar</button>
                            </form>
          </div>
        </div>
    </div>
    <div class="col-md-12" id="form">
        <c:if test="${reserva != null}"> 
            <div class="panel panel-default">
	<div class="panel-body">
	<form action="ServletModificarReserva" method="post">
            <div class="form-group">
                <label>Número habitacion</label> <input type="text" name="numHab"
				class="form-control" placeholder="Número habitacion" pattern="(\d+)"  required value="${reserva.habitacion}"/>
            </div>
            <div class="form-group">
                <label>Nombre Cliente</label> <input type="text" name="name"
                                                     class="form-control" placeholder="Nombre" required value="${reserva.cliente.nombre}" disabled=""/>
            </div>
            <div class="form-group">
                <label>Fecha de entrada</label> <input type="text" name="fentrada"
				class="form-control" placeholder="Fecha de entrada" pattern="(\d{2})(/)(\d{2})(/)(\d{4})" required value="${reserva.fentrada}"/>
            </div>
            <div class="form-group">
                <label>Fecha de salida</label> <input type="text" name="fsalda"
				class="form-control" placeholder="Fecha de salida" pattern="(\d{2})(/)(\d{2})(/)(\d{4})" required value="${reserva.fsalida}"/>
            </div>
            <button type="submit" class="btn btn-success pull-right">
                Modificar Reserva <span class="glyphicon glyphicon glyphicon-plus"	aria-hidden="true"></span>
            </button>
	</form>
            <form action="ServletBorrarReserva" method="post">
                <input type="hidden" name="nif" value="${reserva.cliente.nif}"/>
                <input type="hidden" name="fentrada" value="${reserva.fentrada}"/>
                <button type="submit" class="btn btn-danger pull-right">
                Borrar Reserva <span class="glyphicon glyphicon-trash"	aria-hidden="true"></span>
            </button>
            </form>
	</div>
        </div>
        </c:if> 
    </div>
</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab-pane <c:if test="${tab=='buscarHuesped'}">active</c:if>" id="buscarHuesped">
    
    <div class="col-md-12" id="form">
        <div class="row">
          <div class="col-md-6" id="form">
                            <form action="ServletBuscarHuesped" method="get">
                                <div class="form-group">
                                    <label>NIF</label> <input type="text" name="nif"
				class="form-control" placeholder="NIF" required/>
                                </div>
                                <button>Búscar</button>
                            </form>
          </div>
          <div class="col-md-6" id="form">
                            <form action="ServletBuscarHuesped" method="get">
                                <div class="form-group">
                                    <label for="name">Nombre</label> <input id="name" type="text" name="name"
				class="form-control" placeholder="Nombre" required/>
                                    <label for="sur">Apellidos</label> <input id="sur" type="text" name="surname"
				class="form-control" placeholder="Apellidos" required/>
                                </div>
                                <button>Búscar</button>
                            </form>
          </div>
        </div>
    </div>
    <div class="col-md-12" id="form">
        <c:if test="${cliente != null}"> 
            <div class="panel panel-default">
	<div class="panel-body">
	<form action="ServletModificarHuesped" method="post">
            <div class="form-group">
                <label>Nombre</label> <input type="text" name="name"
				class="form-control" placeholder="Nombre" required value="${cliente.nombre}"/>
            </div>
            <div class="form-group">
                <label>Apellidos</label> <input type="text" name="surname"
				class="form-control" placeholder="Apellidos" required value="${cliente.apellidos}"/>
            </div>
            <div class="form-group">
                <label>DNI</label> <input type="text" name="dni" class="form-control" placeholder="DNI" required disabled value="${cliente.nif}"/>
                <input type="hidden" name="nif" value="${cliente.nif}"/>
            </div>
            <div class="form-group">
                <label>Fecha</label> <input type="text" name="date"
				class="form-control" placeholder="Fecha" required value="${cliente.fecha}" />
            </div>
            <div class="form-group">
                <label>Dirección</label> <input type="text" name="dir"
				class="form-control" placeholder="Dirección" required value="${cliente.direccion}"/>
            </div>
             <div class="form-group">
                <label>Localidad</label> <input type="text" name="loc"
				class="form-control" placeholder="Localidad" required value="${cliente.localidad}"/>
            </div>
             <div class="form-group">
                <label>Codigo Postal</label> <input type="text" name="cp"
				class="form-control" placeholder="Codigo Postal" required value="${cliente.codigo_postal}"/>
            </div>
             <div class="form-group">
                <label>Provincia</label> <input type="text" name="prov"
				class="form-control" placeholder="Provincia" required value="${cliente.provincia}"/>
            </div>
            <div class="form-group">
                <label>Movil</label> <input type="text" name="mov"
				class="form-control" placeholder="movil" value="${cliente.movil}"/>
            </div>
            <div class="form-group">
                <label>Telefono</label> <input type="text" name="tel"
				class="form-control" placeholder="Telefono" value="${cliente.telefono}"/>
            </div>
            <div class="form-group">
                <label>E-mail</label> <input type="text" name="email"
				class="form-control" placeholder="E-mail" value="${cliente.mail}"/>
            </div>
            

            <button type="submit" class="btn btn-success pull-right">
                Modificar Huesped <span class="glyphicon glyphicon glyphicon-plus"	aria-hidden="true"></span>
            </button>
	</form>
            <form action="ServletBorrarHuesped" method="post">
                <input type="hidden" name="nif" value="${cliente.nif}"/>
                <button type="submit" class="btn btn-danger pull-right">
                Borrar Huesped <span class="glyphicon glyphicon-trash"	aria-hidden="true"></span>
            </button>
            </form>
	</div>
        </div>
        </c:if> 
    </div>
    
    
</div>         
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab-pane <c:if test="${tab=='anadirHuesped'}">active</c:if>" id="anadirHuesped"> 
<div class="col-md-12" id="form">
        <h1>Nuevo Huesped</h1>
	<div class="panel panel-default">
	<div class="panel-body">
	<form action="ServletAnadirHuesped" method="post">
            <div class="form-group">
                <label>Nombre</label> <input type="text" name="name"
				class="form-control" placeholder="Nombre" pattern="([A-Za-z ]+)" required/>
            </div>
            <div class="form-group">
                <label>Apellidos</label> <input type="text" name="surname"
				class="form-control" placeholder="Apellidos" pattern="([A-Za-z ]+)"  required/>
            </div>
            <div class="form-group">
                <label>DNI</label> <input type="text" name="dni"
				class="form-control" placeholder="DNI" pattern="(\d{8})([A-Za-z]{1})" required/>
            </div>
            <div class="form-group">
                <label>Fecha</label> <input type="text" name="date"
				class="form-control" placeholder="Fecha" pattern="(\d{2})(/)(\d{2})(/)(\d{4})" required/>
            </div>
            <div class="form-group">
                <label>Direcci�n</label> <input type="text" name="dir"
				class="form-control" placeholder="Direcci�n" required/>
            </div>
             <div class="form-group">
                <label>Localidad</label> <input type="text" name="loc"
				class="form-control" placeholder="Localidad" pattern="([A-Za-z ]+)" required/>
            </div>
             <div class="form-group">
                <label>Codigo Postal</label> <input type="text" name="cp"
				class="form-control" placeholder="Codigo Postal" pattern="(\d{5})" required/>
            </div>
             <div class="form-group">
                <label>Provincia</label> <input type="text" name="prov"
				class="form-control" placeholder="Provincia" pattern="([A-Za-z ]+)" required/>
            </div>
            <div class="form-group">
                <label>Movil</label> <input type="text" name="mov"
				class="form-control" placeholder="movil" pattern="(\d{9})"/>
            </div>
            <div class="form-group">
                <label>Telefono</label> <input type="text" name="tel"
				class="form-control" placeholder="Telefono" pattern="(\d{9})" />
            </div>
            <div class="form-group">
                <label>E-mail</label> <input type="text" name="email"
				class="form-control" placeholder="E-mail" />
            </div>
            

            <button type="submit" class="btn btn-default pull-right">
                A�adir Huesped <span class="glyphicon glyphicon glyphicon-plus"	aria-hidden="true"></span>
            </button>
	</form>
	</div>
        </div>
	</div>
</div>
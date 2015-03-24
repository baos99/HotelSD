<div class="tab-pane" id="anadirReserva">
    <div class="col-md-12" id="form">
        <h1>Nueva Reserva</h1>
	<div class="panel panel-default">
	<div class="panel-body">
	<form action="anadirReserva" method="post">
            <div class="form-group">
                <label>DNI</label> <input type="text" name="nifReserva"
				class="form-control" placeholder="DNI" required/>
            </div>
            <div class="form-group">
                <label>Fecha Entrada</label> <input type="text" name="dateEntrada"
				class="form-control" placeholder="Fecha Entrada" required/>
            </div>
            <div class="form-group">
                <label>Fecha Salida</label> <input type="text" name="dateSalida"
				class="form-control" placeholder="Fecha Salida" required/>
            </div>
          

            <button type="submit" class="btn btn-default pull-right">
                Añadir Reserva <span class="glyphicon glyphicon glyphicon-plus"	aria-hidden="true"></span>
            </button>
	</form>
	</div>
        </div>
	</div>
</div>
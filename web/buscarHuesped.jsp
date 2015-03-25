<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab-pane <c:if test="${tab=='buscarHuesped'}">active</c:if>" id="buscarHuesped">
    
    <div class="col-md-12" id="form">
        <div class="row">
          <div class="col-md-6" id="form">
                            <form action="ServletBuscarHuesped" method="POST">
                                <div class="form-group">
                                    <label>nif</label> <input type="text" name="nif"
				class="form-control" placeholder="NIF" required/>
                                </div>
                                <button>Cancelar</button>
                            </form>
          </div>
          <div class="col-md-6" id="form">
                            <form action="ServletBuscarHuesped" method="POST">
                                <div class="form-group">
                                    <label>nif</label> <input type="text" name="name"
				class="form-control" placeholder="Nombre" required/>
                                </div>
                                <button>Cancelar</button>
                            </form>
          </div>
        </div>
    </div>
    <div class="col-md-12" id="form">
        <c:if test="${cliente != null}"> 
            <div>
                
                <table class="table table-striped">
                <thead>
                <tr>
                    <td>Nombre</td>
                    <td>Apellidos</td>
                    <td>DNI</td>
                    <td>Fecha Nacimiento</td>
                    <td>Direccion</td>
                    <td>Localidad</td>                    
                    <td>Codigo Postal</td>                    
                    <td>Provincia</td>
                    <td>Telefono</td>
                    <td>Movil</td>
                    <td>E-mail</td>
                </tr>
                </thead>
                <tbody>
                
                <tr>
                        <td>${cliente.nombre}</td>
                        <td>${cliente.apellidos}</td>
                        <td>${cliente.nif}</td>
                        <td>${cliente.fecha}</td>
                        <td>${cliente.direccion}</td>
                        <td>${cliente.localidad}</td>
                        <td>${cliente.codigo_postal}</td>
                        <td>${cliente.provincia}</td>
                        <td>${cliente.telefono}</td>
                        <td>${cliente.movil}</td>
                        <td>${cliente.mail}</td>
                </tr>
                </tbody>
                </table>
            </div>
        </c:if> 
    </div>
    
    
</div>         
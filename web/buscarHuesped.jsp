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
            
        </c:if> 
    </div>
    
    
</div>         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-pane" <c:if test="${tab=='administrarReservas'}">active</c:if> id="administrarReservas">
    <h2>cuando este buscar, se hace administrar</h2>
    
    <div class="panel-body">
    <form action="ServletBorrarReserva" method="POST">
                                <div class="form-group">
                                    <label>nif</label> <input type="text" name="nif"
				class="form-control" placeholder="nif" required/>
                                </div>
                                <div class="form-group">
                                    <label>fentrada</label> <input type="text" name="fentrada"
				class="form-control" placeholder="fentrada" required/>
                                </div>
                                <input type="hidden" name="id" value="${status}">
                                <button>Cancelar</button>
                            </form>
                                </div>
</div>
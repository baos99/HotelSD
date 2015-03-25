<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab-pane" <c:if test="${tab=='administrarHuespedes' || tab==null}">active</c:if> id="administrarHuespedes">
    
    <div class="panel-body">
    <form action="ServletBorrarHuesped" method="POST">
                                <div class="form-group">
                                    <label>nif</label> <input type="text" name="nif"
				class="form-control" placeholder="nif" required/>
                                </div>
                                <input type="hidden" name="id" value="${status}">
                                <button>Cancelar</button>
                            </form>
                                </div>
</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/header.jsp" charEncoding="utf-8"/>

<h2>Hotel Sin Nombre</h2>
<div id="content">
<c:import url="/menu.jsp" charEncoding="utf-8"/>

    <div id="contenido" class="tab-content">
        
        <c:import url="/anadirHuesped.jsp" charEncoding="utf-8"/>
        
        <c:import url="/buscarHuesped.jsp" charEncoding="utf-8"/>
        
        <c:import url="/anadirReserva.jsp" charEncoding="utf-8"/>
        
        <c:import url="/buscarReserva.jsp" charEncoding="utf-8"/>
    </div>
</div>
        
<c:import url="/footer.jsp" charEncoding="utf-8"/>

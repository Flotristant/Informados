<%@ page import="informados.usuario.Preferencias" %>



<div class="fieldcontain ${hasErrors(bean: preferenciasInstance, field: 'diarios', 'error')} ">
	<label for="diarios">
		<g:message code="preferencias.diarios.label" default="Diarios" />
		
	</label>
	<g:select name="diarios" from="${informados.noticia.Diario.list()}" multiple="multiple" optionKey="id" size="5" value="${preferenciasInstance?.diarios*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: preferenciasInstance, field: 'secciones', 'error')} ">
	<label for="secciones">
		<g:message code="preferencias.secciones.label" default="Secciones" />
		
	</label>
	<g:select name="secciones" from="${informados.noticia.Seccion.list()}" multiple="multiple" optionKey="id" size="5" value="${preferenciasInstance?.secciones*.id}" class="many-to-many"/>

</div>


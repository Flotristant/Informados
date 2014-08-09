<%@ page import="informados.noticia.Seccion" %>



<div class="fieldcontain ${hasErrors(bean: seccionInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="seccion.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="nombre" from="${seccionInstance.constraints.nombre.inList}" required="" value="${seccionInstance?.nombre}" valueMessagePrefix="seccion.nombre"/>

</div>


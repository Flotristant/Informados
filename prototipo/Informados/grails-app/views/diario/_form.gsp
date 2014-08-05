<%@ page import="informados.noticia.Diario" %>

<div class="fieldcontain ${hasErrors(bean: diarioInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		Nombre
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${diarioInstance?.nombre}"/>

</div>


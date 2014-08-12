<%@ page import="informados.usuario.UsuarioEstudiante" %>



<div class="fieldcontain ${hasErrors(bean: usuarioEstudianteInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="usuarioEstudiante.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${informados.usuario.Persona.list()}" optionKey="id" required="" value="${usuarioEstudianteInstance?.persona?.id}" class="many-to-one"/>

</div>


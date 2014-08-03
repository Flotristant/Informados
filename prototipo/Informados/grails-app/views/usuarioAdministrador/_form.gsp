<%@ page import="informados.UsuarioAdministrador" %>



<div class="fieldcontain ${hasErrors(bean: usuarioAdministradorInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="usuarioAdministrador.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${informados.Persona.list()}" optionKey="id" required="" value="${usuarioAdministradorInstance?.persona?.id}" class="many-to-one"/>

</div>


<%@ page import="informados.usuario.UsuarioAdministrador" %>



<div class="fieldcontain ${hasErrors(bean: usuarioAdministradorInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="usuarioAdministrador.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${informados.usuario.Persona.list()}" optionKey="id" required="" value="${usuarioAdministradorInstance?.persona?.id}" class="many-to-one"/>

</div>


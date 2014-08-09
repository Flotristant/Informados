<%@ page import="informados.usuario.UsuarioProfesional" %>



<div class="fieldcontain ${hasErrors(bean: usuarioProfesionalInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="usuarioProfesional.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${informados.usuario.Persona.list()}" optionKey="id" required="" value="${usuarioProfesionalInstance?.persona?.id}" class="many-to-one"/>

</div>


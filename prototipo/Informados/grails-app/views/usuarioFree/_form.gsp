<%@ page import="informados.UsuarioFree" %>



<div class="fieldcontain ${hasErrors(bean: usuarioFreeInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="usuarioFree.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${informados.Persona.list()}" optionKey="id" required="" value="${usuarioFreeInstance?.persona?.id}" class="many-to-one"/>

</div>


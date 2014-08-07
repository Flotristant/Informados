<%@ page import="informados.noticia.PortalNoticias" %>



<div class="fieldcontain ${hasErrors(bean: portalNoticiasInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="portalNoticias.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${informados.usuario.Persona.list()}" optionKey="id" required="" value="${portalNoticiasInstance?.persona?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: portalNoticiasInstance, field: 'noticiasCantMax', 'error')} required">
	<label for="noticiasCantMax">
		<g:message code="portalNoticias.noticiasCantMax.label" default="Noticias Cant Max" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="noticiasCantMax" type="number" value="${portalNoticiasInstance.noticiasCantMax}" required=""/>

</div>


<%@ page import="informados.noticia.Seccion" %>



<div class="fieldcontain ${hasErrors(bean: seccionInstance, field: 'diario', 'error')} required">
	<label for="diario">
		<g:message code="seccion.diario.label" default="Diario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="diario" name="diario.id" from="${informados.noticia.Diario.list()}" optionKey="id" required="" value="${seccionInstance?.diario?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: seccionInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="seccion.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${seccionInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: seccionInstance, field: 'noticias', 'error')} ">
	<label for="noticias">
		noticias		
	</label>	
<ul class="one-to-many">
<g:each in="${seccionInstance?.noticias?}" var="s">
    <li><g:link controller="noticia" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="noticia" action="create" params="['seccion.id': seccionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'noticia.label', default: 'Noticia')])}</g:link>
</li>
</ul>


</div>


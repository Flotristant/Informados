<%@ page import="informados.noticia.Noticia" %>



<div class="fieldcontain ${hasErrors(bean: noticiaInstance, field: 'RSS', 'error')} required">
	<label for="RSS">
		<g:message code="noticia.RSS.label" default="RSS" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="RSS" required="" value="${noticiaInstance?.RSS}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: noticiaInstance, field: 'copete', 'error')} required">
	<label for="copete">
		<g:message code="noticia.copete.label" default="Copete" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="copete" required="" value="${noticiaInstance?.copete}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: noticiaInstance, field: 'resumen', 'error')} required">
	<label for="resumen">
		<g:message code="noticia.resumen.label" default="Resumen" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="resumen" required="" value="${noticiaInstance?.resumen}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: noticiaInstance, field: 'diario', 'error')} required">
	<label for="diario">
		diario
		<span class="required-indicator">*</span>
	</label>
	<g:select id="diario" name="diario.id" from="${informados.noticia.Diario.list()}" optionKey="id" required="" value="${noticiaInstance?.diario?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: noticiaInstance, field: 'seccion', 'error')} required">
	<label for="seccion">
		<g:message code="noticia.seccion.label" default="Seccion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="seccion" name="seccion.id" from="${informados.noticia.Seccion.list()}" optionKey="id" required="" value="${noticiaInstance?.seccion?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: noticiaInstance, field: 'titulo', 'error')} required">
	<label for="titulo">
		<g:message code="noticia.titulo.label" default="Titulo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="titulo" required="" value="${noticiaInstance?.titulo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: noticiaInstance, field: 'contenido', 'error')} required">
	<label for="contenido">
		contenido
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="contenido" required="" value="${noticiaInstance?.contenido}"/>

</div>


<%@ page import="informados.noticia.Seccion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'seccion.label', default: 'Seccion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-seccion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-seccion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list seccion">
			
				<g:if test="${seccionInstance?.diario}">
				<li class="fieldcontain">
					<span id="diario-label" class="property-label"><g:message code="seccion.diario.label" default="Diario" /></span>
					
						<span class="property-value" aria-labelledby="diario-label"><g:link controller="diario" action="show" id="${seccionInstance?.diario?.id}">${seccionInstance?.diario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${seccionInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="seccion.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${seccionInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seccionInstance?.noticias}">
				<li class="fieldcontain">
					<span id="seccines-label" class="property-label">noticias</span>
					
						<g:each in="${seccionInstance.noticias}" var="s">
						<span class="property-value" aria-labelledby="seccines-label"><g:link controller="noticia" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>

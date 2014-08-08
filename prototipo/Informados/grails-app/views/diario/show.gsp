
<%@ page import="informados.noticia.Diario"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'diario.label', default: 'Diario')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-diario" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="show-diario" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list diario">

		</ol>
		<g:if test="${diarioInstance?.nombre}">
			<li class="fieldcontain"><span id="nombre-label"
				class="property-label">Nombre</span> <span class="property-value"
				aria-labelledby="nombre-label"><g:fieldValue
						bean="${diarioInstance}" field="nombre" /></span></li>
		</g:if>
	</div>
</body>
</html>


<%@ page import="informados.usuario.UsuarioEstudiante" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuarioEstudiante.label', default: 'UsuarioEstudiante')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-usuarioEstudiante" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-usuarioEstudiante" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ul class="property-list usuarioEstudiante">			
				<g:if test="${usuarioEstudianteInstance?.persona}">
				<li class="fieldcontain">
					<span class="property-label" aria-labelledby="persona-label"><g:link controller="persona" action="show" id="${usuarioEstudianteInstance?.persona?.id}">Ver datos personales</g:link></span>
				</li>
				<li>
					<span class="property-label" aria-labelledby="persona-label"><g:link action="showPreferencias" id="${usuarioEstudianteInstance?.id}">Ver Preferencias</g:link></span>
				</li>
				</g:if>			
			</ul>
		</div>
	</body>
</html>

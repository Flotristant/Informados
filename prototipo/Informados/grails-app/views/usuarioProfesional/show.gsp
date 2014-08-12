
<%@ page import="informados.usuario.UsuarioProfesional" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuarioProfesional.label', default: 'UsuarioProfesional')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-usuarioProfesional" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-usuarioProfesional" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list usuarioProfesional">
			
				<g:if test="${usuarioProfesionalInstance?.persona}">
				<li class="fieldcontain">
					<span class="property-label" aria-labelledby="persona-label"><g:link controller="persona" action="show" id="${usuarioProfesionalInstance?.persona?.id}">Ver datos personales</g:link></span>
				</li>
				<li>
					<span class="property-label" aria-labelledby="persona-label"><g:link action="showPreferencias" id="${usuarioProfesionalInstance?.id}">Ver Preferencias</g:link></span>
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>


<%@ page import="informados.usuario.UsuarioFree" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuarioFree.label', default: 'UsuarioFree')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-usuarioFree" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-usuarioFree" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ul class="property-list usuarioFree">
			
				<g:if test="${usuarioFreeInstance?.persona}">
				<li class="fieldcontain">
					<span class="property-label" aria-labelledby="persona-label"><g:link controller="persona" action="show" id="${usuarioFreeInstance?.persona?.id}">Ver datos personales</g:link></span>
				</li>
				<li>
					<span class="property-label" aria-labelledby="persona-label"><g:link action="showPreferencias" id="${usuarioFreeInstance?.id}">Ver Preferencias</g:link></span>
				</li>
				</g:if>
			</ul>
		</div>
	</body>
</html>

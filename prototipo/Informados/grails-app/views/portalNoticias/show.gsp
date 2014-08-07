
<%@ page import="informados.noticia.PortalNoticias" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'portalNoticias.label', default: 'PortalNoticias')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-portalNoticias" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index" controller="diario"><g:message code="default.list.label" args="diarios" /></g:link></li>
			</ul>
		</div>
		<div id="show-portalNoticias" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list portalNoticias">
			
				<g:if test="${portalNoticiasInstance?.persona}">
				<li class="fieldcontain">
					<span id="persona-label" class="property-label"><g:message code="portalNoticias.persona.label" default="Persona" /></span>
					
						<span class="property-value" aria-labelledby="persona-label"><g:link controller="persona" action="show" id="${portalNoticiasInstance?.persona?.id}">${portalNoticiasInstance?.persona?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${portalNoticiasInstance?.noticiasCantMax}">
				<li class="fieldcontain">
					<span id="noticiasCantMax-label" class="property-label"><g:message code="portalNoticias.noticiasCantMax.label" default="Noticias Cant Max" /></span>
					
						<span class="property-value" aria-labelledby="noticiasCantMax-label"><g:fieldValue bean="${portalNoticiasInstance}" field="noticiasCantMax"/></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>

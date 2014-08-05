
<%@ page import="informados.noticia.Noticia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'noticia.label', default: 'Noticia')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-noticia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-noticia" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list noticia">
			
				<g:if test="${noticiaInstance?.RSS}">
				<li class="fieldcontain">
					<span id="RSS-label" class="property-label"><g:message code="noticia.RSS.label" default="RSS" /></span>
					
						<span class="property-value" aria-labelledby="RSS-label"><g:fieldValue bean="${noticiaInstance}" field="RSS"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaInstance?.copete}">
				<li class="fieldcontain">
					<span id="copete-label" class="property-label"><g:message code="noticia.copete.label" default="Copete" /></span>
					
						<span class="property-value" aria-labelledby="copete-label"><g:fieldValue bean="${noticiaInstance}" field="copete"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaInstance?.resumen}">
				<li class="fieldcontain">
					<span id="resumen-label" class="property-label"><g:message code="noticia.resumen.label" default="Resumen" /></span>
					
						<span class="property-value" aria-labelledby="resumen-label"><g:fieldValue bean="${noticiaInstance}" field="resumen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaInstance?.seccion}">
				<li class="fieldcontain">
					<span id="seccion-label" class="property-label"><g:message code="noticia.seccion.label" default="Seccion" /></span>
					
						<span class="property-value" aria-labelledby="seccion-label"><g:link controller="seccion" action="show" id="${noticiaInstance?.seccion?.id}">${noticiaInstance?.seccion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="noticia.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${noticiaInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:noticiaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${noticiaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

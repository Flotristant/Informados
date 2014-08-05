
<%@ page import="informados.noticia.PortalNoticias" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'portalNoticias.label', default: 'PortalNoticias')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-portalNoticias" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-portalNoticias" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="portalNoticias.persona.label" default="Persona" /></th>
					
						<g:sortableColumn property="noticiasCantMax" title="${message(code: 'portalNoticias.noticiasCantMax.label', default: 'Noticias Cant Max')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${portalNoticiasInstanceList}" status="i" var="portalNoticiasInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${portalNoticiasInstance.id}">${fieldValue(bean: portalNoticiasInstance, field: "persona")}</g:link></td>
					
						<td>${fieldValue(bean: portalNoticiasInstance, field: "noticiasCantMax")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${portalNoticiasInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

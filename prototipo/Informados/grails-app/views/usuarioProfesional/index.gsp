
<%@ page import="informados.usuario.UsuarioProfesional" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuarioProfesional.label', default: 'UsuarioProfesional')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-usuarioProfesional" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-usuarioProfesional" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="usuarioProfesional.persona.label" default="Persona" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioProfesionalInstanceList}" status="i" var="usuarioProfesionalInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${usuarioProfesionalInstance.id}">${fieldValue(bean: usuarioProfesionalInstance, field: "persona")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${usuarioProfesionalInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

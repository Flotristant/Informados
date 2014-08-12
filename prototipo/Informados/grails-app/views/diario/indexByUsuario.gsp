
<%@ page import="informados.noticia.Diario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'diario.label', default: 'Diario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<fieldset class="form">
			<g:render template="panel_diarios" bean="${diarioInstanceList}" />
		</fieldset>
	</body>
</html>

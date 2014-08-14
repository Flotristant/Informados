
<%@ page import="informados.noticia.Noticia"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'noticia.label', default: 'Noticia')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-noticia" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="list-noticia" class="content scaffold-list" role="main">
		<h1>
			<g:message code="default.list.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<table>
			<thead>
				<tr>
					<th><g:message code="noticia.seccion.label" default="Seccion" /></th>

					<g:sortableColumn property="titulo"
						title="${message(code: 'noticia.titulo.label', default: 'Titulo')}" />

					<g:sortableColumn property="resumen"
						title="${message(code: 'noticia.resumen.label', default: 'Resumen')}" />

					<g:sortableColumn property="puntos" title="Likes" />

				</tr>
			</thead>
			<tbody>
				<g:each in="${noticiaInstanceList}" status="i" var="noticiaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>
							${fieldValue(bean: noticiaInstance, field: "seccion")}
						</td>
						<td>
							<a href="${noticiaInstance.link}">${fieldValue(bean: noticiaInstance, field: "titulo")}</a>
						</td>
						<td>
							${raw(noticiaInstance.resumen)}
						</td>
						<td>
							${fieldValue(bean: noticiaInstance, field: "puntos")}
						</td>
						<td><td><g:link action="votar" id="${noticiaInstance.id}"
										params="[targetUri: (request.forwardURI - request.contextPath)]">
										<img
											src="${resource(dir: 'images', file: 'FB-ThumbsUp_29.png')}"
											alt="Like" />
									</g:link></td>

					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${noticiaInstanceCount ?: 0}" />
		</div>
	</div>
</body>
</html>

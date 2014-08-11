
<%@ page import="informados.noticia.Noticia"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'noticia.label', default: 'Noticia')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="${resource(dir: 'js', file: 'jquery.upvote.js')}"></script>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.upvote.css')}"
	type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.upvote.css')}"
	type="text/css">
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
					<g:sortableColumn property="titulo"
						title="${message(code: 'noticia.titulo.label', default: 'Titulo')}" />
					<g:sortableColumn property="resumen"
						title="${message(code: 'noticia.resumen.label', default: 'Resumen')}" />
					<th><g:message code="noticia.seccion.label" default="Seccion" /></th>
					<g:sortableColumn property="puntos" title="Likes" />
					<g:sortableColumn property="link" title="Link" />
				</tr>
			</thead>
			<tbody>
				<g:each in="${noticiaInstanceList}" status="i" var="noticiaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${noticiaInstance.id}">
								${fieldValue(bean: noticiaInstance, field: "titulo")}
							</g:link></td>

						<td>
							${raw(noticiaInstance.resumen)}
						</td>

						<td>
							${fieldValue(bean: noticiaInstance, field: "seccion")}
						</td>

						<td><div id="topic" class="upvote">
								<a class="upvote"></a> <span class="count">
									${noticiaInstance.getPuntos()}
								</span> <a class="downvote"></a> <a class="star"></a>
							</div></td>


						<td><a
							href="${fieldValue(bean: noticiaInstance, field: "link")}">Ver
								Noticia</a></td>

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

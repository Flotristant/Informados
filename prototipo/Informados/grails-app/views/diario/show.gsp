
<%@ page import="informados.noticia.Diario"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'diario.label', default: 'Diario')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-diario" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="show-diario" class="content scaffold-show" role="main">
		<h1>
			<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue
					bean="${diarioInstance}" field="nombre" /></span>
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:if test="${noticiasPorSeccion}">
			<g:each var="seccion" status="i" in="${noticiasPorSeccion.keySet()}">
				<table>
					<thead>
						<tr>
							<th><span> ${seccion}
							</span></th>
						</tr>
					</thead>
					<tbody>
						<g:each var="noticia" status="j"
							in="${noticiasPorSeccion.getAt(seccion)}">
							<tr>
								<td><a href="${noticia.link}"> ${noticia.titulo}</a></td>
								<td><g:link action="indexNoticiasRelacionadas" controller="noticia" id="${noticia.id}">Ver Noticias Relacionadas</g:link></td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</g:each>
		</g:if>
	</div>
</body>
</html>


<%@ page import="informados.usuario.Preferencias"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'preferencias.label', default: 'Preferencias')}" />
<title>Tus preferencias</title>
</head>
<body>
	<a href="#show-preferencias" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="show-preferencias" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list preferencias">

			<g:if test="${preferenciasInstance?.diarios}">
				<li class="fieldcontain"><span id="diarios-label"
					class="property-label"><g:message
							code="preferencias.diarios.label" default="Diarios" /></span> <g:each
						in="${preferenciasInstance.diarios}" var="d">
						<span class="property-value" aria-labelledby="diarios-label"><g:link
								controller="diario" action="show" id="${d.id}">
								${d?.encodeAsHTML()}
							</g:link></span>
					</g:each></li>
			</g:if>

			<g:if test="${preferenciasInstance?.secciones}">
				<li class="fieldcontain"><span id="secciones-label"
					class="property-label"><g:message
							code="preferencias.secciones.label" default="Secciones" /></span> <g:each
						in="${preferenciasInstance.secciones}" var="s">
						<span class="property-value" aria-labelledby="secciones-label"><g:link
								controller="seccion" action="show" id="${s.id}">
								${s?.encodeAsHTML()}
							</g:link></span>
					</g:each></li>
			</g:if>

		</ol>
		<fieldset class="buttons">
			<g:link class="edit" action="edit" resource="${preferenciasInstance}">
				Editar preferencias
			</g:link>
		</fieldset>
	</div>
</body>
</html>

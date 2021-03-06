<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'persona.label', default: 'Persona')}" />
<title>Registrate!</title>
</head>
<body>
	<a href="#create-persona" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="create-persona" class="content scaffold-create" role="main">
		<h1>
			<g:message code="default.registrar.label" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${personaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${personaInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:form
			url="[resource:personaInstance, action:'crearNuevaCuenta', controller:'persona']">
			<fieldset>
				<g:render template="register_form" />
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="registrar" class="registrar"
					value="${message(code: 'deafult.button.registrar.label', default: 'Registrarme!')}" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'persona.label', default: 'Persona')}" />
<title>Login</title>
</head>
<body>
	<a href="#create-persona" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="index">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="create-persona" class="content scaffold-create" role="main">
		<h1>
			<g:message code="default.create.label" args="[entityName]" />
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
		<g:form url="[resource:personaInstance, action:'authenticate']">
			<div
				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'userName', 'error')} required">
				<label for="userName"> <g:message
						code="persona.userName.label" default="User Name" /> <span
					class="required-indicator">*</span>
				</label>
				<g:textField name="userName" required=""
					value="${personaInstance?.userName}" />

			</div>

			<div
				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'password', 'error')} required">
				<label for="password"> <g:message
						code="persona.password.label" default="Password" /> <span
					class="required-indicator">*</span>
				</label>
				<g:field type="password" name="password" maxlength="15" required=""
					value="${personaInstance?.password}" />

			</div>
			<fieldset class="buttons">
				<g:submitButton name="login" class="save" value="login" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>

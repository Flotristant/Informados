<%@ page import="informados.usuario.Persona" %>



<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="persona.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${personaInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'apellido', 'error')} required">
	<label for="apellido">
		<g:message code="persona.apellido.label" default="Apellido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellido" required="" value="${personaInstance?.apellido}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="persona.userName.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" required="" value="${personaInstance?.userName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="persona.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" maxlength="15" required="" value="${personaInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'passwordConfirmation', 'error')} required">
	<label for="passwordConfirmation">
		Password Confirmation
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="passwordConfirmation" maxlength="15" required="" value="${personaInstance?.passwordConfirmation}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="persona.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${personaInstance?.email}"/>

</div>

 <div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'suscripcion', 'error')} required">
	<label for="suscripcion">
		<g:message code="persona.suscripcion.label" default="Suscripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="suscripcion" from="${personaInstance.constraints.suscripcion.inList}" required="" value="${personaInstance?.suscripcion}" valueMessagePrefix="persona.suscripcion"/>
</div>  

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'edad', 'error')} required">
	<label for="edad">
		<g:message code="persona.edad.label" default="Edad" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="edad" required="" value="${personaInstance?.edad}"/>

</div>

<g:if test="${session.user?.isAdmin}">
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'isAdmin', 'error')} required">
	<label for="edad"> El usuario es administrador de la página
		<span class="required-indicator">*</span>
	</label>
	<g:checkBox name="isAdmin" value="${personaInstance?.isAdmin}"/>
</div>
</g:if>

<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="Grails" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
<g:layoutHead />
<g:javascript library="application" />
<r:layoutResources />
</head>
<body>
	<g:if
		test="${session.user == null || session.user?.puedeVerPublicidad() }">
		<div class="publicidad-informados">
			<a href="http://www.fi.uba.ar"><img
				src="${resource(dir: 'images', file: 'banner-publicidad.png')}"
				alt="Fiuba" /></a>
		</div>
	</g:if>
	<div>
		<fieldset class="buttons">
			<g:if test="${session.user}">
				<div align="right">
					<div>
						hello
						${session.user.userName}
					</div>
					<g:link controller="persona" action="logout">
						${message(code: 'deafult.button.logout.label', default: 'Salir')}
					</g:link>
				</div>
			</g:if>
			<g:else>
				<div align="right">
					<g:link controller="persona" action="login">
						${message(code: 'deafult.button.login.label', default: 'Login')}
					</g:link>
				</div>
				<div align="right">
					<g:link controller="persona" action="registro">
						${message(code: 'deafult.button.nuevo.usuario.label', default: 'Registrarme!')}
					</g:link>
				</div>
				<div align="right">
					<g:link controller="app" action="">
						${message(code: 'deafult.button.nuevo.usuario.facebook', default: 'Ir a Facebook!')}
					</g:link>
				</div>
			</g:else>

		</fieldset>
	</div>
	<div class="header" id="logo" role="banner">
		<img src="${resource(dir: 'images', file: 'informados_logo_3.png')}"
			alt="Informados" />
	</div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<g:if test="${session.user}">
				<li><g:link class="show" action="showPerfil"
						controller="persona" id="${session.user?.id}">Perfil</g:link></li>
				<li><g:link class="show" action="showRankingNoticias"
						controller="noticia">Ver ranking de Noticias</g:link></li>
				<li><g:link class="show" action="indexByUsuario" controller="diario">Mis Noticias</g:link>
				</li>
			</g:if>
		</ul>
	</div>
	<g:if test="${session.user}">
		<div align="center">
			<g:form url="[action:'buscarNoticia', controller:'noticia']">
				<fieldset class="form">
					<g:textField name="keywords" value="${params[keywords]}" />
					<g:submitButton name="buscar" class="save" value="Buscar Noticia" />
				</fieldset>
			</g:form>
		</div>
	</g:if>
	<g:layoutBody />
	<r:layoutResources />
	<g:if
		test="${session.user == null || session.user?.puedeVerPublicidad() }">
		<div class="publicidad-informados">
			<a href="http://www.fi.uba.ar"><img
				src="${resource(dir: 'images', file: 'fiubaLogo.png')}" alt="Fiuba" /></a>
		</div>
	</g:if>
</body>
</html>

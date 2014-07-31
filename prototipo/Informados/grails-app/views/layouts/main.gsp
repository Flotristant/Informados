<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<g:javascript library="application"/>		
		<r:layoutResources />
	</head>
	<body>
		<div><fieldset class="buttons">
			<g:if test="${session.user}">
				<div align="right">
				<div>hello ${session.user.userName}</div>
				<g:link controller="persona" action="logout">${message(code: 'deafult.button.logout.label', default: 'Salir')}</g:link>
				</div>
			</g:if>
			<g:else>
				<div align="right">
				<g:link controller="persona" action="login">${message(code: 'deafult.button.login.label', default: 'Registrarme!')}</g:link>
				</div>
				<div align="right">
					<g:link controller="persona" action="registro">${message(code: 'deafult.button.nuevo.usuario.label', default: 'Registrarme!')}</g:link>
				</div>
			</g:else>
		</fieldset></div>
		<div class="header" id="logo" role="banner"><img src="${resource(dir: 'images', file: 'informados_logo_3.png')}" alt="Informados"/></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:layoutBody/>
		<r:layoutResources />
		<div class="footer" role="contentinfo"></div>
	</body>
</html>

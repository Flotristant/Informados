<div class="page-header" id="logo" role="banner">
<img src="${resource(dir: 'images', file: 'informados_logo_3.png')}" alt="Fiuba" />
</div>
<div class="row">
	<div class="span12">
		<g:if test="${!facebookContext.app.id}">
			<g:render template="/website/configError" />
		</g:if>
		<g:else>
			<!--
			  We use the Facebook JavaScript SDK to provide a richer user experience. For more info,
			  look here: http://github.com/facebook/facebook-js-sdk
			-->
			<facebook:initJS appId="${facebookContext.app.id}">
				// Put here any JS code to be executed after Facebook JS initialization
			</facebook:initJS>
			
			<g:if test="${!facebookContext.authenticated}">
				<p>
				
				</p>
			</g:if>
			<g:else>
				<h2 class="tab">Your data</h2>
				<h3>Foto de Perfil<img src="https://graph.facebook.com/${user.id}/picture"> nombre:  ${user.name}</h3>
				<h3>Amigos</h3>
				<p>
					<g:each in="${userFriends}" var="friend">
						<img src="https://graph.facebook.com/${friend.id}/picture">
					</g:each>
				</p>
			</g:else>
			<p>&nbsp;</p>
		</g:else>
		<h2 class="tab">Datos usuario</h2>
		<h3><img src="https://graph.facebook.com/benorama/picture"></h3>
		<h3>Bienvenido ${benorama?.name}!</h3>
	    <p>&nbsp;</p>
        <h2 class="tab">Opciones:</h2>
        <script type="text/javascript">
            function addToPage_callback(response) {alert(response && response.tabs_added.length + ' app added')}
            function invite_callback(response) {console.log(response)}
            function publish_callback(response) {if (response && response.success) alert('Published successfully')}
            function send_callback(response) {if (response && response.success) alert('Sent successfully')}
        </script>
        <facebook:addToPageLink callback="addToPage_callback" elementClass="btn">Compartir Noticia con amigos</facebook:addToPageLink><br>
        <facebook:inviteLink callback="invite_callback" elementClass="btn" message="Check this app!">Invitar a Informados en Facebook</facebook:inviteLink><br>
        <facebook:publishLink callback="publish_callback" elementClass="btn">Publicar una noticia</facebook:publishLink><br>
        <facebook:sendLink callback="send_callback" elementClass="btn" link="http://www.google.com" to="594317994">Enviar link noticia a un amigo</facebook:sendLink><br>
        <a href="http://localhost:8080/Informados/">Volver a Inicio</a>
        
    </div>
</div>
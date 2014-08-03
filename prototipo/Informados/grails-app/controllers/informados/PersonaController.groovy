package informados



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonaController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def login = {
		if(session.user != null) {
			if(session.user.isLoggedOn) {
				redirect (action:"show", controller:"persona", id:session.user.id)
			}
		}
	}
	def logout = {
		flash.message = "Goodbye ${session.user.userName}"
		session.user = null
		redirect(action:"login")
	}
	def authenticate = {
		def user =	Persona.findByUserNameAndPassword(params.userName, params.password)
		if(user){
			user.setIsLoggedOn(true)
			user.save()
			if(user.hasErrors()) {
				flash.message = "Error al intentar guardar el usuario, intenta de nuevo por favor."
				redirect(action:"login")
			}
			session.user = user
			flash.message = "Hello ${user.userName}!"
			if (user.isAdmin) {
				createUsuarioAdministradorAndShow(user, flash, params);
				return
			}
			if(user.suscripcion == "Profesional") {
				redirect(controller:"persona", action:"show", id:user.id)
			} else if(user.suscripcion == "Estudiante") {
				redirect(controller:"persona", action:"show", id:user.id)
			} else{
				createPersonaFreeAndShow(user, flash, params)
			}
		}else{
			showErrorMessage(flash, params)
		}
	}

	private createPersonaFreeAndShow(Persona user, Map flash, Map params) {
		UsuarioFree usuarioFree = UsuarioFree.findOrCreateByPersona(user)
		if(usuarioFree.id == null) {
			usuarioFree.save()
			if(usuarioFree.hasErrors()) {
				showErrorMessage(flash, params)
			}
		}
		redirect(controller:"usuarioFree", action:"show", id:usuarioFree.id)
	}
	
	private createUsuarioAdministradorAndShow(Persona user, Map flash, Map params) {
		UsuarioAdministrador usuarioAdministrador = UsuarioAdministrador.findOrCreateByPersona(user)
		if(usuarioAdministrador.id == null) {
			usuarioAdministrador.save()
			if(usuarioAdministrador.hasErrors()) {
				showErrorMessage(flash, params)
			}
		}
		redirect(controller:"usuarioAdministrador", action:"show", id:usuarioAdministrador.id)
	}

	private showErrorMessage(Map flash, Map params) {
		flash.message = "Intenta de nuevo ${params.userName}."
		redirect(action:"login")
	}

	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Persona.list(params), model:[usuarioInstanceCount: Persona.count()]
	}

	def show(Persona usuarioInstance) {
		
		respond usuarioInstance
	}

	def create() {
		respond new Persona(params)
	}

	def registro() {
		respond new Persona(params)
	}

	def crearNuevaCuenta(Persona usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}
		usuarioInstance.isLoggedOn = false
		usuarioInstance.save flush:true
		if(usuarioInstance.hasErrors()){
			respond usuarioInstance.errors, view:'registro'
		}

		if(usuarioInstance.isAdmin) {
			UsuarioAdministrador usuarioAdministrador = new UsuarioAdministrador(usuarioInstance)
			usuarioAdministrador.save flush:true
			if(usuarioAdministrador.hasErrors()) {
				response usuarioAdministrador.errors, view: 'registro'
				return
			}
			redirect(action:"show", controller: "usuarioAdministrador", id: usuarioAdministrador.id)
			return
		}
		usuarioInstance.isAdmin = false
		usuarioInstance.save flush:true
		if(usuarioInstance.hasErrors()){
			respond usuarioInstance.errors, view:'registro'
		}
		switch(usuarioInstance.getSuscripcion()) {
			case "Free":
				UsuarioFree usuarioFree = new UsuarioFree(usuarioInstance)
				usuarioFree.save flush:true
				if(usuarioFree.hasErrors()){
					respond usuarioInstance.errors, view:'registro'
				}
				redirect(action:"login")
				break;
			case "Estudiante":
				UsuarioEstudiante usuarioEstudiante = new UsuarioEstudiante(usuarioInstance)
				usuarioEstudiante.save flush:true
				redirect(action:"login")
				break;
			case "Profesional":
				UsuarioProfesional usuarioProfesional = new UsuarioProfesional(usuarioInstance)
				usuarioProfesional.save flush:true
				redirect(action:"login")
				break;
		}		
	}
	
	def showError(Persona usuarioInstance) {
		respond usuarioInstance.errors, view:'registro'
	}
	

	@Transactional
	def save(Persona usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}

		usuarioInstance.isLoggedOn = false
		usuarioInstance.save flush:true
		if (usuarioInstance.hasErrors()) {
			respond usuarioInstance.errors, view:'create'
			return
		}
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'Persona.label', default: 'Persona'),
					usuarioInstance.id
				])
				redirect usuarioInstance
			}
			'*' { respond usuarioInstance, [status: CREATED] }
		}
	}


	def edit(Persona usuarioInstance) {
		respond usuarioInstance
	}

	@Transactional
	def update(Persona usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}

		if (usuarioInstance.hasErrors()) {
			respond usuarioInstance.errors, view:'edit'
			return
		}

		usuarioInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Usuario.label', default: 'Usuario'),
					usuarioInstance.id
				])
				redirect usuarioInstance
			}
			'*'{ respond usuarioInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Persona usuarioInstance) {

		if (usuarioInstance == null) {
			notFound()
			return
		}

		usuarioInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Usuario.label', default: 'Usuario'),
					usuarioInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'usuario.label', default: 'Usuario'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}

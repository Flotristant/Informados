package informados



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonaController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def login = {}
	def logout = {
		flash.message = "Goodbye ${session.user.userName}"
		session.user = null
		redirect(action:"login")
	}
	def authenticate = {
		def user =	Persona.findByUserNameAndPassword(params.userName, params.password)
		if(user){
			session.user = user
			flash.message = "Hello ${user.userName}!"
			if(user.suscripcion == "Profesional") {
				redirect(controller:"persona", action:"show", id:user.id)
			} else if(user.suscripcion == "Estudiante") {
				redirect(controller:"persona", action:"show", id:user.id)
			} else{
				UsuarioFree rol = UsuarioFree.findByPersona(user)
				redirect(controller:"usuarioFree", action:"show", id:rol.id)
			}
		}else{
			flash.message = "Intenta de nuevo ${params.userName}."
			redirect(action:"login")
		}
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
		if(usuarioInstance.hasErrors()){
			respond usuarioInstance.errors, view:'registro'
		}
		usuarioInstance.save flush:true
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

	@Transactional
	def save(Persona usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}

		if (usuarioInstance.hasErrors()) {
			respond usuarioInstance.errors, view:'create'
			return
		}

		usuarioInstance.save flush:true

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

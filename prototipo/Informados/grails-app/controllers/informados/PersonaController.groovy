package informados



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonaController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def login = {}
	def logout = {}
	def authenticate = {
		def user =
				Persona.findByUserNameAndPassword(params.userName, params.password)
		if(user){
			session.user = user
			flash.message = "Hello ${user.userName}!"
			redirect(controller:"persona", action:"index")
		}else{
			flash.message =
					"Sorry, ${params.userName}. Please try again."
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

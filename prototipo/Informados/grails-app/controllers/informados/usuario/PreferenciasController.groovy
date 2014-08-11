package informados.usuario



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PreferenciasController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Preferencias.list(params), model:[preferenciasInstanceCount: Preferencias.count()]
    }

    def show(Preferencias preferenciasInstance) {
        respond preferenciasInstance
    }

    def create(Usuario usuarioInstance) {
        respond new Preferencias(params)
    }

    @Transactional
    def save(Preferencias preferenciasInstance) {
        if (preferenciasInstance == null) {
            notFound()
            return
        }

        if (preferenciasInstance.hasErrors()) {
            respond preferenciasInstance.errors, view:'create'
            return
        }

        preferenciasInstance.save flush:true
		if(!preferenciasInstance.hasErrors()) {
			List<Usuario> usuarios = Usuario.findAllByPersona(session.user)
			def usuario = usuarios[0]
			usuario.save(flush:true)
			if(usuario.hasErrors()) {
				println usuario.errors
			}
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'preferencias.label', default: 'Preferencias'), preferenciasInstance.id])
                redirect preferenciasInstance
            }
            '*' { respond preferenciasInstance, [status: CREATED] }
        }
    }

    def edit(Preferencias preferenciasInstance) {
        respond preferenciasInstance
    }

    @Transactional
    def update(Preferencias preferenciasInstance) {
        if (preferenciasInstance == null) {
            notFound()
            return
        }

        if (preferenciasInstance.hasErrors()) {
            respond preferenciasInstance.errors, view:'edit'
            return
        }

        preferenciasInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Preferencias.label', default: 'Preferencias'), preferenciasInstance.id])
                redirect preferenciasInstance
            }
            '*'{ respond preferenciasInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Preferencias preferenciasInstance) {

        if (preferenciasInstance == null) {
            notFound()
            return
        }

        preferenciasInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Preferencias.label', default: 'Preferencias'), preferenciasInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'preferencias.label', default: 'Preferencias'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

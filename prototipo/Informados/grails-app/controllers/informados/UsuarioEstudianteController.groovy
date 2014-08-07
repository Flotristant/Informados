package informados



import static org.springframework.http.HttpStatus.*
import informados.usuario.UsuarioEstudiante;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsuarioEstudianteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioEstudiante.list(params), model:[usuarioEstudianteInstanceCount: UsuarioEstudiante.count()]
    }

    def show(UsuarioEstudiante usuarioEstudianteInstance) {
        redirect (controller: "Persona", action: "show", id: usuarioEstudianteInstance.persona.id)
    }

    def create() {
        respond new UsuarioEstudiante(params)
    }

    @Transactional
    def save(UsuarioEstudiante usuarioEstudianteInstance) {
        if (usuarioEstudianteInstance == null) {
            notFound()
            return
        }

        if (usuarioEstudianteInstance.hasErrors()) {
            respond usuarioEstudianteInstance.errors, view:'create'
            return
        }

        usuarioEstudianteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioEstudiante.label', default: 'UsuarioEstudiante'), usuarioEstudianteInstance.id])
                redirect usuarioEstudianteInstance
            }
            '*' { respond usuarioEstudianteInstance, [status: CREATED] }
        }
    }

    def edit(UsuarioEstudiante usuarioEstudianteInstance) {
        respond usuarioEstudianteInstance
    }

    @Transactional
    def update(UsuarioEstudiante usuarioEstudianteInstance) {
        if (usuarioEstudianteInstance == null) {
            notFound()
            return
        }

        if (usuarioEstudianteInstance.hasErrors()) {
            respond usuarioEstudianteInstance.errors, view:'edit'
            return
        }

        usuarioEstudianteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'UsuarioEstudiante.label', default: 'UsuarioEstudiante'), usuarioEstudianteInstance.id])
                redirect usuarioEstudianteInstance
            }
            '*'{ respond usuarioEstudianteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioEstudiante usuarioEstudianteInstance) {

        if (usuarioEstudianteInstance == null) {
            notFound()
            return
        }

        usuarioEstudianteInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'UsuarioEstudiante.label', default: 'UsuarioEstudiante'), usuarioEstudianteInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioEstudiante.label', default: 'UsuarioEstudiante'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

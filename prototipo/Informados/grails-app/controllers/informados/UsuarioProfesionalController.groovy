package informados



import static org.springframework.http.HttpStatus.*
import informados.usuario.UsuarioProfesional;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsuarioProfesionalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioProfesional.list(params), model:[usuarioProfesionalInstanceCount: UsuarioProfesional.count()]
    }

    def show(UsuarioProfesional usuarioProfesionalInstance) {
       respond usuarioProfesionalInstance
    }

    def create() {
        respond new UsuarioProfesional(params)
    }

    @Transactional
    def save(UsuarioProfesional usuarioProfesionalInstance) {
        if (usuarioProfesionalInstance == null) {
            notFound()
            return
        }

        if (usuarioProfesionalInstance.hasErrors()) {
            respond usuarioProfesionalInstance.errors, view:'create'
            return
        }

        usuarioProfesionalInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioProfesional.label', default: 'UsuarioProfesional'), usuarioProfesionalInstance.id])
                redirect usuarioProfesionalInstance
            }
            '*' { respond usuarioProfesionalInstance, [status: CREATED] }
        }
    }

    def edit(UsuarioProfesional usuarioProfesionalInstance) {
        respond usuarioProfesionalInstance
    }

    @Transactional
    def update(UsuarioProfesional usuarioProfesionalInstance) {
        if (usuarioProfesionalInstance == null) {
            notFound()
            return
        }

        if (usuarioProfesionalInstance.hasErrors()) {
            respond usuarioProfesionalInstance.errors, view:'edit'
            return
        }

        usuarioProfesionalInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'UsuarioProfesional.label', default: 'UsuarioProfesional'), usuarioProfesionalInstance.id])
                redirect usuarioProfesionalInstance
            }
            '*'{ respond usuarioProfesionalInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioProfesional usuarioProfesionalInstance) {

        if (usuarioProfesionalInstance == null) {
            notFound()
            return
        }

        usuarioProfesionalInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'UsuarioProfesional.label', default: 'UsuarioProfesional'), usuarioProfesionalInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioProfesional.label', default: 'UsuarioProfesional'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

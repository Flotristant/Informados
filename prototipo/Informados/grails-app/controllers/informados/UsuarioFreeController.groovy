package informados



import static org.springframework.http.HttpStatus.*
import informados.usuario.UsuarioFree;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsuarioFreeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioFree.list(params), model:[usuarioFreeInstanceCount: UsuarioFree.count()]
    }

    def show(UsuarioFree usuarioFreeInstance) {
		respond usuarioFreeInstance
    }

    def create() {
        respond new UsuarioFree(params)
    }

    @Transactional
    def save(UsuarioFree usuarioFreeInstance) {
        if (usuarioFreeInstance == null) {
            notFound()
            return
        }

        if (usuarioFreeInstance.hasErrors()) {
            respond usuarioFreeInstance.errors, view:'create'
            return
        }

        usuarioFreeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioFree.label', default: 'UsuarioFree'), usuarioFreeInstance.id])
                redirect usuarioFreeInstance
            }
            '*' { respond usuarioFreeInstance, [status: CREATED] }
        }
    }

    def edit(UsuarioFree usuarioFreeInstance) {
        respond usuarioFreeInstance
    }

    @Transactional
    def update(UsuarioFree usuarioFreeInstance) {
        if (usuarioFreeInstance == null) {
            notFound()
            return
        }

        if (usuarioFreeInstance.hasErrors()) {
            respond usuarioFreeInstance.errors, view:'edit'
            return
        }

        usuarioFreeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'UsuarioFree.label', default: 'UsuarioFree'), usuarioFreeInstance.id])
                redirect usuarioFreeInstance
            }
            '*'{ respond usuarioFreeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioFree usuarioFreeInstance) {

        if (usuarioFreeInstance == null) {
            notFound()
            return
        }

        usuarioFreeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'UsuarioFree.label', default: 'UsuarioFree'), usuarioFreeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioFree.label', default: 'UsuarioFree'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

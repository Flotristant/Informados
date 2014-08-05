package informados.noticia



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DiarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Diario.list(params), model:[diarioInstanceCount: Diario.count()]
    }

    def show(Diario diarioInstance) {
        respond diarioInstance
    }

    def create() {
        respond new Diario(params)
    }

    @Transactional
    def save(Diario diarioInstance) {
        if (diarioInstance == null) {
            notFound()
            return
        }

        if (diarioInstance.hasErrors()) {
            respond diarioInstance.errors, view:'create'
            return
        }

        diarioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'diario.label', default: 'Diario'), diarioInstance.id])
                redirect diarioInstance
            }
            '*' { respond diarioInstance, [status: CREATED] }
        }
    }

    def edit(Diario diarioInstance) {
        respond diarioInstance
    }

    @Transactional
    def update(Diario diarioInstance) {
        if (diarioInstance == null) {
            notFound()
            return
        }

        if (diarioInstance.hasErrors()) {
            respond diarioInstance.errors, view:'edit'
            return
        }

        diarioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Diario.label', default: 'Diario'), diarioInstance.id])
                redirect diarioInstance
            }
            '*'{ respond diarioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Diario diarioInstance) {

        if (diarioInstance == null) {
            notFound()
            return
        }

        diarioInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Diario.label', default: 'Diario'), diarioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'diario.label', default: 'Diario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

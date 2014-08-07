package informados.noticia



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PortalNoticiasController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PortalNoticias.list(params), model:[portalNoticiasInstanceCount: PortalNoticias.count()]
    }

    def show(PortalNoticias portalNoticiasInstance) {
        respond portalNoticiasInstance
    }

    def create() {
        respond new PortalNoticias(params)
    }

    @Transactional
    def save(PortalNoticias portalNoticiasInstance) {
        if (portalNoticiasInstance == null) {
            notFound()
            return
        }

        if (portalNoticiasInstance.hasErrors()) {
            respond portalNoticiasInstance.errors, view:'create'
            return
        }

        portalNoticiasInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'portalNoticias.label', default: 'PortalNoticias'), portalNoticiasInstance.id])
                redirect portalNoticiasInstance
            }
            '*' { respond portalNoticiasInstance, [status: CREATED] }
        }
    }

    def edit(PortalNoticias portalNoticiasInstance) {
        respond portalNoticiasInstance
    }

    @Transactional
    def update(PortalNoticias portalNoticiasInstance) {
        if (portalNoticiasInstance == null) {
            notFound()
            return
        }

        if (portalNoticiasInstance.hasErrors()) {
            respond portalNoticiasInstance.errors, view:'edit'
            return
        }

        portalNoticiasInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'PortalNoticias.label', default: 'PortalNoticias'), portalNoticiasInstance.id])
                redirect portalNoticiasInstance
            }
            '*'{ respond portalNoticiasInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(PortalNoticias portalNoticiasInstance) {

        if (portalNoticiasInstance == null) {
            notFound()
            return
        }

        portalNoticiasInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'PortalNoticias.label', default: 'PortalNoticias'), portalNoticiasInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'portalNoticias.label', default: 'PortalNoticias'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

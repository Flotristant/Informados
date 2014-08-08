package informados.noticia



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NoticiaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Noticia.list(params), model:[noticiaInstanceCount: Noticia.count()]
    }
	
    def show(Noticia noticiaInstance) {
        respond noticiaInstance
    }

    def create() {
        respond new Noticia(params)
    }
	
	def buscarNoticia(String keywords, Integer max) {
		params.max = Math.min(max ?: 10, 100)
		List<Noticia> noticias = Noticia.findAllByContenidoLike("%"+params.keywords+"%")
		respond noticias.asList()
	}

    @Transactional
    def save(Noticia noticiaInstance) {
        if (noticiaInstance == null) {
            notFound()
            return
        }

        if (noticiaInstance.hasErrors()) {
            respond noticiaInstance.errors, view:'create'
            return
        }

        noticiaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'noticia.label', default: 'Noticia'), noticiaInstance.id])
                redirect noticiaInstance
            }
            '*' { respond noticiaInstance, [status: CREATED] }
        }
    }

    def edit(Noticia noticiaInstance) {
        respond noticiaInstance
    }

    @Transactional
    def update(Noticia noticiaInstance) {
        if (noticiaInstance == null) {
            notFound()
            return
        }

        if (noticiaInstance.hasErrors()) {
            respond noticiaInstance.errors, view:'edit'
            return
        }

        noticiaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Noticia.label', default: 'Noticia'), noticiaInstance.id])
                redirect noticiaInstance
            }
            '*'{ respond noticiaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Noticia noticiaInstance) {

        if (noticiaInstance == null) {
            notFound()
            return
        }

        noticiaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Noticia.label', default: 'Noticia'), noticiaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'noticia.label', default: 'Noticia'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

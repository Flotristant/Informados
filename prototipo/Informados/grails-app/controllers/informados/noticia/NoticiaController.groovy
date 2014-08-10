package informados.noticia



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import ranking.Ranking
import ranking.Voto

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
		respond noticias, model:[noticiaInstanceCount: noticias.size()]
	}
	
	def showRankingNoticias(Integer max) {
		Ranking ranking = new Ranking();
		params.max=Math.min(max ?: 10, 100)
		List<Noticia> noticias = ranking.createRankingSemanal(params.max, params.offset)
		//TODO: pasar la cantidad real de noticias encontradas, por ahora paso el total (noticias.size solo tiene el max pasado)
		respond noticias, model:[noticiaInstanceCount: Noticia.count()]
	}
	
	def votar(Noticia noticiaInstance) {
		Voto voto = new Voto()
		voto.noticia = noticiaInstance
		def users = Usuario.FindAllByPersona(session.user)
		voto.usuario = users[0]
		voto.save flush:true
		if(voto.hasErrors()) {
			flash.message=voto.errors
		}
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

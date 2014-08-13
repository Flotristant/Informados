package informados.noticia



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import ranking.Ranking
import ranking.Voto
import informados.usuario.Persona
import informados.usuario.Usuario

@Transactional(readOnly = true)
class NoticiaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Noticia.list(params), model:[noticiaInstanceCount: Noticia.count(), diarioInstanceList: Diario.list()]
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
	
	def indexNoticiasRelacionadas(Noticia noticiaInstance) {
//		String busqueda=""
//		String limpiezaHTML = noticiaInstance.quitarStopwords().replace("<b>", " ")
//		limpiezaHTML = limpiezaHTML.replace("</b>", "")
//		List<String> palabras = limpiezaHTML.split(" ,.;-")
//		println("busco palabras:" + palabras)
//		for(palabra in palabras) {
//			busqueda += " %"+palabra+"%"
//		}
//		busqueda += palabras[0]
//		println(busqueda)
		def noticiasRelacionadas = Noticia.findAllByContenidoLike(noticiaInstance.contenido)
		respond noticiasRelacionadas
	}
	
	def showRankingNoticias(Integer max) {
		Ranking ranking = new Ranking();
		params.max=Math.min(max ?: 10, 100)
		List<Noticia> noticias = ranking.createRankingSemanal(params.max, params.offset)
		//TODO: pasar la cantidad real de noticias encontradas, por ahora paso el total (noticias.size solo tiene el max pasado)
		respond noticias, model:[noticiaInstanceCount: Noticia.count()]
	}
	
	def votar(Noticia noticiaInstance) {
		Persona persona = session.user
		List<Usuario> usuarios = Usuario.findAllByPersona(persona)
		flash.message = noticiaInstance.votar(usuarios[0])
		respond 
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

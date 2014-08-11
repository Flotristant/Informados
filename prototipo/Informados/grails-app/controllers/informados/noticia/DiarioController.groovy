package informados.noticia



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import informados.usuario.Persona
import informados.usuario.Preferencias
import informados.usuario.Usuario

@Transactional(readOnly = true)
class DiarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Diario.list(params), model:[diarioInstanceCount: Diario.count()]
    }
	
	def votar(Noticia noticiaInstance) {
		Persona persona = session.user
		List<Usuario> usuarios = Usuario.findAllByPersona(persona)
		flash.message = noticiaInstance.votar(usuarios[0])
		def targetUri = params.targetUri ?: "/"
		redirect(uri: targetUri)
	}
	
	def indexByUsuario(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		List<Usuario> usuarios = Usuario.findAllByPersona(session.user)
		Usuario usuario = usuarios[0]
		if(usuario.preferencias != null) {
			Preferencias preferencias = Preferencias.findById(usuario.preferencias.id)
			respond preferencias.diarios.asList()
		} else {
			respond Diario.list()
		}
	}

    def show(Diario diarioInstance) {
		Map<String, List<Noticia>> noticiasPorSeccion = new HashMap<Seccion, List<Noticia>>()
		for(noticia in diarioInstance.noticias) {
			def noticias_seccion = noticiasPorSeccion.getAt(noticia.seccion.nombre)
			if(noticias_seccion == null) {
				noticias_seccion = [noticia]
				noticiasPorSeccion.putAt(noticia.seccion.nombre, noticias_seccion)
			} else {
				noticiasPorSeccion.getAt(noticia.seccion.nombre).add(noticia)
			}
		}		
        respond diarioInstance, model:[noticiasPorSeccion: noticiasPorSeccion]
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

package informados.noticia

import informados.usuario.Usuario
import ranking.Voto

import informados.noticia.Tema

class Noticia {
	String titulo
	String copete
	String resumen
	String contenido
	String RSS
	static belongsTo = [diario:Diario]
	Seccion seccion
	Date fecha = new Date()
	String link
	Integer hash
    Tema tema

    // numero del 1..100 que indica el nivel de positividad de la noticia
    Integer positivismo

	static constraints = {
		contenido blank: false, maxSize:500000
		titulo blank:false
		resumen blank:false, maxSize:500000
		link blank:true, nullable:true
        tema nullable:true
	}
	
	
	public String toString() {
		return titulo + "(positivismo="+positivismo+")"+"["+hash+"]"
	}

	public String votar(Usuario usuario) {
		List votos = Voto.findAllByUsuarioAndNoticia(usuario, this)
		if(votos.empty) {
			Voto voto = new Voto(usuario:usuario, noticia:this)
			voto.save(flush:true)
			if(voto.hasErrors()) {
				return voto.errors
			}
			this.save(flus:true)
			if(this.hasErrors()) {
				return this.errors
			}
		} else {
			return "Hey no puedes volver a votar la misma noticia!"
		}
	}
	
	public Integer getPuntos() {
		return Voto.findAllByNoticia(this).size()
	}
}

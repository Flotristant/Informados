package informados.noticia

import informados.usuario.Usuario
import ranking.Voto

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
	Integer puntos=0

    static constraints = {
		contenido blank: false, maxSize:500000
		titulo blank:false
		resumen blank:false, maxSize:500000
		puntos blank:true
		link blank:true, nullable:true
		
    }
	
	public String toString() {
		return titulo + "( " +copete+" )"
	}
	
	public void votar(Usuario usuario) {
		Voto voto = new Voto(usuario:usuario, noticia:this)
	}
	
}

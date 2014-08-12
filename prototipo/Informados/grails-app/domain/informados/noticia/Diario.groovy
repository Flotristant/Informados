package informados.noticia

class Diario {
	String nombre
	Map<Seccion, String> RSSUrls = new HashMap<String, String>()
	String url
	String pathImagen
	static hasMany = [noticias:Noticia]

    static constraints = {
		nombre blank:false
		url blank:true, nullable:true
		pathImagen blank:true, nullable:true
    }
	
	String toString() {
		return nombre
	}
}

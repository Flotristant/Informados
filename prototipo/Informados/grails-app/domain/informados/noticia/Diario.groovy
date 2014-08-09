package informados.noticia

class Diario {
	String nombre
	Map<Seccion, String> RSSUrls = new HashMap<String, String>()
	static hasMany = [noticias:Noticia]

    static constraints = {
		nombre blank:false
    }
	
	String toString() {
		return nombre
	}
}

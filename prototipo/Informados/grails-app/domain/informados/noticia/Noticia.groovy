package informados.noticia

class Noticia {	
	String titulo
	String copete
	String resumen
	String contenido
	String RSS
	static belongsTo = [seccion:Seccion]

    static constraints = {
		contenido blank: false
		titulo blank:false
		resumen blank:false
    }
	
	public String toString() {
		return titulo + "( " +copete+" )"
	}
}

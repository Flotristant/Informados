package informados.noticia

class Noticia {	
	String titulo
	String copete
	String resumen
	String RSS
	static belongsTo = [seccion:Seccion]

    static constraints = {
    }
	
	public String toString() {
		return titulo + "( " +copete+" )"
	}
}

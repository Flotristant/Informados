package informados.noticia

class Noticia {	
	String titulo
	String copete
	String resumen
	String contenido
	String RSS
	static belongsTo = [diario:Diario]
	Seccion seccion
	Integer puntos=0
    Integer hash

    static constraints = {
		contenido blank: false
		titulo blank:false
		resumen blank:false, maxSize:500000
		puntos blank:true
		
    }
	
	public String toString() {
		return titulo + "( " +copete+" )["+hash+"]"
	}
	
	public void votar() {
		++puntos;
	}
}

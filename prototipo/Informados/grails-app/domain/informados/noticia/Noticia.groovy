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

    static constraints = {
		contenido blank: false
		titulo blank:false
		resumen blank:false
		puntos blank:true
		
    }
	
	public String toString() {
		return titulo + "( " +copete+" )"
	}
	
	public void votar() {
		++puntos;
	}
}

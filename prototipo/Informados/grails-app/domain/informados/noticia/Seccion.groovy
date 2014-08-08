package informados.noticia

class Seccion {
	static hasMany = [noticias:Noticia]
	String nombre
	
	static belongsTo = [diario:Diario]

    static constraints = {
    }
	
	public String toString() {
		return nombre
	}
}

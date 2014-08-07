package informados.noticia

class Seccion {
	static hasMany = [seccines:Noticia]
	String nombre
	
	static belongsTo = [diario:Diario]

    static constraints = {
    }
	
	public String toString() {
		return nombre
	}
}

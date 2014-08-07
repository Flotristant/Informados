package informados.noticia

class Diario {
	String nombre
	static hasMany = [seccines:Seccion]

    static constraints = {
		nombre blank:false
    }
	
	String toString() {
		return nombre
	}
}

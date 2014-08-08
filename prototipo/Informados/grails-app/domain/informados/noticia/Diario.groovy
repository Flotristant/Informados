package informados.noticia

class Diario {
	String nombre
	static hasMany = [noticias:Noticia]

    static constraints = {
		nombre blank:false
    }
	
	String toString() {
		return nombre
	}
}

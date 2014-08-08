package informados.noticia

public class Seccion {
	String nombre;
	static constraints = {
		nombre inList: ["POLITICA", "SOCIEDAD", "ESPECTACULOS", "DEPORTES", "ECONOMIA"]
	}
	
	public String toString() {
		return nombre
	}
}

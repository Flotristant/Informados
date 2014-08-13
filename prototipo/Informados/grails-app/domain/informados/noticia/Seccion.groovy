package informados.noticia

public class Seccion {
	String nombre;
	static constraints = {
		nombre inList: ["POLITICA", "SOCIEDAD", "ESPECTACULOS", "DEPORTES", "ECONOMIA", "POLICIALES", "INFORMACION GENERAL"]
	}
	
	public String toString() {
		return nombre
	}
	
}

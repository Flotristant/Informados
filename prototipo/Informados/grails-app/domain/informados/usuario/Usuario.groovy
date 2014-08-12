package informados.usuario

public class Usuario {
	Persona persona
	Preferencias preferencias 
	
	public Integer getCantidadMaximaDiarios(){}	

    static constraints = {
		preferencias nullable:true, blank:true
    }
	
	public Boolean puedeVerPublicidad() {
		return true
	}
}

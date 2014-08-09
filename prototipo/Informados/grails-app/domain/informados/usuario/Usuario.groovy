package informados.usuario

public abstract class Usuario {
	Persona persona
	Preferencias preferencias 
	
	public abstract Integer getCantidadMaximaDiarios();
	

    static constraints = {
		preferencias nullable:true, blank:true
    }
}

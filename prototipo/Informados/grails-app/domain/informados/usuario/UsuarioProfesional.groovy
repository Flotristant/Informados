package informados.usuario

import informados.noticia.Diario;

class UsuarioProfesional extends Usuario{
	public static final int CANTIDAD_MAXIMA_DIARIOS=50
	Persona persona
	Preferencias preferencias 
	
	public UsuarioProfesional(){
		
	}
	
	public UsuarioProfesional(Persona persona) {
		this.persona 	
	}
	
	public Integer getCantidadMaximaDiarios() {
		return CANTIDAD_MAXIMA_DIARIOS
	}
	
	public Boolean agregarDiario(Diario diario) {
		if(CANTIDAD_MAXIMA_DIARIOS < preferencias.diarios.size()) {
			preferencias.diarios.add(diario)
		}
	}

    static constraints = {
		preferencias nullable:true, blank:true
    }
	
	public Boolean puedeVerPublicidad() {
		return false
	}
}

package informados.usuario

import informados.noticia.Diario

class UsuarioEstudiante extends Usuario {
	public static final int CANTIDAD_MAXIMA_DIARIOS=10
	
	public UsuarioEstudiante() {
		
	}
	
	public UsuarioEstudiante(Persona persona) {
		this.persona = persona
	}
	
	public Boolean agregarDiario(Diario diario) {
		if(CANTIDAD_MAXIMA_DIARIOS < preferencias.diarios.size()) {
			preferencias.diarios.add(diario)
		}
	}
	
	public Integer getCantidadMaximaDiarios() {
		return CANTIDAD_MAXIMA_DIARIOS
	}
	
    static constraints = {
		preferencias nullable:true, blank:true
    }
	
	public Boolean puedeVerPublicidad() {
		return false
	}
}

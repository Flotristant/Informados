package informados.usuario

import informados.noticia.Diario

class UsuarioEstudiante {
	public static final int CANTIDAD_MAXIMA_DIARIOS=10
	Persona persona
	Preferencias preferencias
	
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
	
    static constraints = {
		preferencias nullable:true, blank:true
    }
}

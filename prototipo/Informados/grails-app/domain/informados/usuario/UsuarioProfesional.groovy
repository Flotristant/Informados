package informados.usuario

import informados.noticia.Diario;

class UsuarioProfesional {
	public static final int CANTIDAD_MAXIMA_DIARIOS=50
	Persona persona
	Preferencias preferencias
	
	public UsuarioProfesional(){
		
	}
	
	public UsuarioProfesional(Persona persona) {
		this.persona 	
	}
	
	public Boolean agregarDiario(Diario diario) {
		if(CANTIDAD_MAXIMA_DIARIOS < preferencias.diarios.size()) {
			preferencias.diarios.add(diario)
		}
	}

    static constraints = {
    }
}

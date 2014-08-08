package informados.usuario

import informados.noticia.Diario

class UsuarioAdministrador extends Usuario {
	public static final int CANTIDAD_MAXIMA_DIARIOS=-1
	
	public UsuarioAdministrador() {
		
	}
	
	public UsuarioAdministrador(Persona persona) {
		this.persona = persona
		this.persona.setIsAdmin(true)
	}
	
	public String toString() {
		return persona.toString()
	}
	
	public Boolean agregarDiario(Diario diario) {
		this.preferencias.diarios.add(diario)
	}
	
    static constraints = {
		
    }
	@Override
	public Integer getCantidadMaximaDiarios() {
		return CANTIDAD_MAXIMA_DIARIOS
    }
}


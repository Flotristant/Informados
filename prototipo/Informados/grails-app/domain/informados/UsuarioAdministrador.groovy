package informados

class UsuarioAdministrador {
	Persona persona
	
	public UsuarioAdministrador() {
		
	}
	
	public UsuarioAdministrador(Persona persona) {
		this.persona = persona
		this.persona.setIsAdmin(true)
	}
	
	public String toString() {
		return persona.toString()
	}
	
    static constraints = {
		
    }
	
}

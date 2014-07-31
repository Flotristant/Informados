package informados

class Persona {
	String nombre
	String apellido
	String edad
	String email
	String userName
	String password
	String suscripcion
	
	static constraints = {
		nombre blank:false
		apellido blank:false
		userName blank:false
		password size: 5..15, blank: false, password:true
		email email: true, blank: false
		suscripcion inList: ["Free", "Estudiante", "Profesional"]
	}
	
	public Boolean isAdmin() {
		return suscripcion=="Administrador"
	}

}

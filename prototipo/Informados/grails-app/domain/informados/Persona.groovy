package informados

class Persona {
	String nombre
	String apellido
	String edad
	String email
	String userName
	String password
	String suscripcion
	Boolean isLoggedOn
	Boolean isAdmin
	
	public Persona (String nombre, String apellido, String email, String userName, String password, String suscripcion, Boolean isLoggedOn = false, Boolean isAdmin = false) {
		this.nombre = nombre
		this.apellido = apellido
		this.email = email
		this.userName = userName 
		this.password= password
		this.suscripcion = suscripcion
		this.isLoggedOn = isLoggedOn == null? false : isLoggedOn
		this.isAdmin = isAdmin == null? false : isAdmin
	}
	
	static constraints = {
		nombre blank:false
		apellido blank:false
		userName blank:false
		password size: 5..15, blank: false, password:true
		email email: true, blank: false
		suscripcion inList: ["Free", "Estudiante", "Profesional"]
		isLoggedOn nulleable:true
		isAdmin nulleable:true
	}
	
	static mapping = {
		isLoggedOn defaultValue: false
		isAdmin defaultValue: false
	 }
	
	public Boolean isAdmin() {
		return isAdmin
	}
	
	public String toString() {
		return this.nombre+ " " + this.apellido+ " " + this.edad+ " " + this.email;
	}

}

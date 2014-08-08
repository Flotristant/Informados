package informados.usuario

import grails.transaction.Transactional;


class Persona {

	static constraints = {
		nombre blank:false
		apellido blank:false
		userName blank:false
		password size: 5..15, blank: false, password:true , validator: {password, obj ->	
			def password2 = obj.passwordConfirmation
			password2 == password ? true : ['the passwords don\'t match']
			}
		email email: true, blank: false
		suscripcion inList: ["Free", "Estudiante", "Profesional"]
				isLoggedOn nulleable:true
				isAdmin nulleable:true
				
	}
	
	static mapping = {
		isLoggedOn defaultValue: false
		isAdmin defaultValue: false
	}

	String nombre
	String apellido
	String edad
	String email
	String userName
	String password
	String passwordConfirmation
	String suscripcion
	Boolean isLoggedOn = false
	Boolean isAdmin = false
	
	
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
	
	
	public Boolean isAdmin() {
		return isAdmin
	}
	
	public String toString() {
		return this.nombre+ " " + this.apellido+ " " + this.edad+ " " + this.email;
	}

}

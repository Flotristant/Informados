package informados

class Persona {
	String nombre
	String apellido
	String edad
	String email
	String userName
	String password

	static constraints = {
		nombre blank:false
		apellido blank:false
		userName blank:false
		password size: 5..15, blank: false, password:true
		email email: true, blank: false
	}

}

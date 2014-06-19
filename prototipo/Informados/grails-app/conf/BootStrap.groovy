import informados.Persona

class BootStrap {

	def init = { servletContext ->
		def admin = new Persona(userName:"admin", password:"wordpass", nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Profesional")
		admin.save()
		if(admin.hasErrors()){
			println admin.errors
		}
		def estudiante = new Persona(userName:"estudiante",password:"estudiante",nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Estudiante")
		estudiante.save()
		if(estudiante.hasErrors()){
			println estudiante.errors
		}
		def profesional = new Persona(userName:"profesional",password:"profesional",nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Profesional")
		profesional.save()
		if(profesional.hasErrors()){
			println profesional.errors
		}
		def free = new Persona(userName:"free",password:"123456",nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Free")
		free.save()
		if(free.hasErrors()){
			println free.errors
		}

		def destroy = {
		}
	}
}

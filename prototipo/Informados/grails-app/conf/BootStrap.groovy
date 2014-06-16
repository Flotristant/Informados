import informados.Persona

class BootStrap {

	def init = { servletContext ->
		def admin = new Persona(userName:"admin", password:"wordpass", nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25")
		admin.save()
		if(admin.hasErrors()){
			println admin.errors
		}
		def jdoe = new Persona(userName:"jdoe",password:"password",nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25")
		jdoe.save()
		if(jdoe.hasErrors()){
			println jdoe.errors
		}

		def destroy = {
		}
	}
}

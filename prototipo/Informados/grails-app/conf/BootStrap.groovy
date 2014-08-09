import informados.noticia.Diario
import informados.noticia.Noticia
import informados.noticia.Seccion
import informados.usuario.Persona

class BootStrap {

	def init = { servletContext ->
		/*def profesional = new Persona(userName:"profesional", password:"profesional", nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Profesional")
		profesional.save()
		if(admin.hasErrors()){
			println profesional.errors
		}*/
		def estudiante = new Persona(userName:"estudiante",password:"estudiante",nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Estudiante", isLoggedOn:false, isAdmin:false, passwordConfirmation:"estudiante")
		estudiante.save()
		if(estudiante.hasErrors()){
			println estudiante.errors
		}
		def profesional = new Persona(userName:"profesional",password:"profesional", passwordConfirmation:"profesional",nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Profesional", isLoggedOn:false, isAdmin:false)
		profesional.save()
		if(profesional.hasErrors()){
			println profesional.errors
		}
		def free = new Persona(userName:"free",password:"123456", passwordConfirmation:"123456",nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Free", isLoggedOn:false, isAdmin:false)
		free.save()
		if(free.hasErrors()){
			println free.errors
		}
		def admin = new Persona(userName:"admin",password:"admin", passwordConfirmation:"admin",nombre:"Florencia", apellido:"Tristant", email:"flo@flo.com", edad:"25", suscripcion:"Profesional", isLoggedOn:false, isAdmin:true)
		admin.save()
		if(admin.hasErrors()){
			println admin.errors
		}
		
		
		def diario = new Diario(nombre:"Pagina/12")
		diario.save()
		if(diario.hasErrors()) {
			println diario.errors
		}

		def seccion = new Seccion(nombre:"POLITICA")
		seccion.save()
		if(seccion.hasErrors()) {
			println seccion.errors
		}
		
		def seccionSociedad = new Seccion(nombre:"SOCIEDAD")
		seccionSociedad.save()
		if(seccionSociedad.hasErrors()) {
			println seccionSociedad.errors
		}
		
		def noticia = new Noticia(titulo:"mi noticia",contenido:"mi contenido", resumen:"mi resumen", copete:"mi copete", RSS:"mi rss", puntos:25)
		noticia.seccion = seccion
		noticia.diario=diario
		noticia.save()
		if(noticia.hasErrors()){
			println noticia.errors
		}

		def noticia2 = new Noticia(titulo:"mi noticia",contenido:"mi contenido de sociedades", resumen:"mi resumen", copete:"mi copete", RSS:"mi rss", puntos:1)
		noticia2.seccion = seccionSociedad
		noticia2.diario=diario
		noticia2.save()
		if(noticia2.hasErrors()){
			println noticia.errors
		}
		
		def noticia3 = new Noticia(titulo:"mi noticia",contenido:"mi contenido", resumen:"mi resumen", copete:"mi copete", RSS:"mi rss", puntos:150)
		noticia3.seccion = seccion
		noticia3.diario=diario
		noticia3.save()
		if(noticia3.hasErrors()){
			println noticia.errors
		}
		
		diario.noticias = [noticia, noticia2, noticia3]
		diario.save()
		if(diario.hasErrors()) {
			println diario.errors
		}
		
		

		def destroy = {
		}
	}
}

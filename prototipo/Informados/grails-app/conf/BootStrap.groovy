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


		Diario diario = new Diario(nombre:"infobae")
		diario.save()
		if(diario.hasErrors()) {
			println diario.errors
		}


		// ["POLITICA", "SOCIEDAD", "ESPECTACULOS", "DEPORTES", "ECONOMIA"]

		def seccionPolitica = new Seccion(nombre:"POLITICA")
		seccionPolitica.save()
		if(seccionPolitica.hasErrors()) {
			println seccionPolitica.errors
		}

		def seccionSociedad = new Seccion(nombre:"SOCIEDAD")
		seccionSociedad.save()
		if(seccionSociedad.hasErrors()) {
			println seccionSociedad.errors
		}

		def seccionEconomia = new Seccion(nombre:"ECONOMIA")
		seccionEconomia.save()
		if(seccionEconomia.hasErrors()) {
			println seccionEconomia.errors
		}

		def seccionDeportes= new Seccion(nombre:"DEPORTES")
		seccionDeportes.save()
		if(seccionDeportes.hasErrors()) {
			println seccionDeportes.errors
		}

		def seccionEspectaculos = new Seccion(nombre:"ESPECTACULOS")
		seccionEspectaculos.save()
		if(seccionEspectaculos.hasErrors()) {
			println seccionEspectaculos.errors
		}

//		diario.RSSUrls=new HashMap<Seccion, String>()
		diario.RSSUrls.put(seccionPolitica.nombre, "http://cdn01.am.infobae.com/adjuntos/163/rss/politica.xml")
		diario.RSSUrls.put(seccionDeportes.nombre, "http://cdn01.am.infobae.com/adjuntos/163/rss/deportes.xml")

		/*def noticia = new Noticia(titulo:"mi noticia",contenido:"mi contenido", resumen:"mi resumen", copete:"mi copete", RSS:"mi rss", puntos:25)
		noticia.seccion = seccionPolitica
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
		noticia3.seccion = seccionPolitica
		noticia3.diario=diario
		noticia3.save()
		if(noticia3.hasErrors()){
			println noticia.errors
		}

		diario.noticias = [noticia, noticia2, noticia3]*/
		diario.save()
		if(diario.hasErrors()) {
			println diario.errors
		}


	}
	def destroy = {
	}
}

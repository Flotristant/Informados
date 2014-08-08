package informados.noticia

import informados.usuario.Persona

class PortalNoticias {
	def resumenesNoticias = [ResumenNoticia]
	Persona persona
	

    static constraints = {
		resumenesNoticias nulleable:true, blank:true
		persona nulleable:false, blank:false
    }
	

	
}

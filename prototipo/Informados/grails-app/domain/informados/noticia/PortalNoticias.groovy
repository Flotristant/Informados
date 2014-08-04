package informados.noticia

import informados.usuario.Persona

class PortalNoticias {
	def resumenesNoticias = [ResumenNoticia]
	Persona persona
	Integer noticiasCantMax 

    static constraints = {
		resumenesNoticias nulleable:true, blank:true
		persona nulleable:false, blank:false
    }
	
	public Integer getNoticiasCantMax() {
		
	}
	
	
}

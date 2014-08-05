package informados.noticia

class ResumenNoticia {
	Noticia noticia
	
    static constraints = {
		noticia nulleable:true
    }
	
	public String solicitarRss() {
		//TODO: obtener RSS asociado
	}
}

package ranking

import informados.noticia.Noticia
import informados.usuario.Usuario

class Voto {
	Date fecha = new Date()
	Noticia noticia
	Usuario usuario

    public Voto(Noticia noticia, Usuario usuario) {
//		this.fecha = new Date()
		this.noticia = noticia
		this.usuario = usuario
	}
}

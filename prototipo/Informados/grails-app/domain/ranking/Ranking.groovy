package ranking

import informados.noticia.Diario
import informados.noticia.Noticia
import informados.usuario.Usuario

class Ranking {
	
	public List<Noticia> createRankingSemanal(max, offset) {
		Date fecha_actual = new Date();
		Date semana_anterior = fecha_actual-7
		List<Noticia> noticias;
		if(offset != null) {
			noticias = Noticia.findAllByFechaBetween(semana_anterior, fecha_actual, [max:max, offset:offset])
		} else {	
			noticias = Noticia.findAllByFechaBetween(semana_anterior, fecha_actual, [max:max])
		}
		
		noticias.sort { -it.getPuntos() }
		
		return noticias	
	}

    static constraints = {
    }
}

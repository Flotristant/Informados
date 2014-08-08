package informados.usuario

import informados.noticia.Diario
import informados.noticia.Seccion

class Preferencias {
	static hasMany=[diarios:Diario, secciones:Seccion]

    static constraints = {
		
    }
}

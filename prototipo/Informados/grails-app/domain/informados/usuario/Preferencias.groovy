package informados.usuario

import informados.noticia.Diario
import informados.noticia.Seccion

class Preferencias {
	List<Diario> diarios
	List<Seccion> secciones

    static constraints = {
		
    }
	
	public void agregarDiario(Diario diario) {
		this.diarios.add(diario)
	}
	
	public void agregarSeccion(Seccion seccion) {
		this.secciones.add(seccion)
	}
}

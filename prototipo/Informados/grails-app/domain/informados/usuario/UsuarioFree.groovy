package informados.usuario

import informados.noticia.Diario;

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

class UsuarioFree extends Usuario{
	public static final int CANTIDAD_MAXIMA_DIARIOS=5
	
	public UsuarioFree(){
		
	}

	public UsuarioFree(Persona persona) {
		this.persona=persona;
	}
	
	public Boolean agregarDiario(Diario diario) {
		if(CANTIDAD_MAXIMA_DIARIOS < preferencias.diarios.size()) {
			preferencias.diarios.add(diario)
		}
	}
	public Integer getCantidadMaximaDiarios() {
		return CANTIDAD_MAXIMA_DIARIOS
	}
	
    static constraints = {
		preferencias nullable:true, blank:true
    }
}

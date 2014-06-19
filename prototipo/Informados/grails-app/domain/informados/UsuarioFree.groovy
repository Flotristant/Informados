package informados

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

class UsuarioFree {
	private static CANTIDAD_DIARIOS_MAX=50
	Persona persona
	
	public UsuarioFree(){
		
	}

	public UsuarioFree(Persona persona) {
		this.persona=persona;
	}
	
    static constraints = {
		
    }
}

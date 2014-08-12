package informados

class SecutiryFIltersFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				/*if  ((actionName == null) || actionName.equals('index') && controllerName.equals('main') || (actionName.equals('registro')) || (actionName.equals('crearNuevaCuenta')) || (actionName.equals('create') || (actionName.equals('save')))) {
					return true
				}
				if (!session.user && !(actionName.equals('login') || actionName.equals('authenticate'))) {
					redirect(action: 'login', controller:'persona')
					return false
				}
				return true*/
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}

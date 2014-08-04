package informados

class SecutiryFIltersFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				if  ((actionName == null) || (actionName.equals('index') && controllerName.equals('main') || (actionName.equals('registro') ))) {
					return true
				}
				if (!session.user && !actionName.equals('login')) {
					redirect(action: 'login', controller:'persona')
					return false
				}
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}

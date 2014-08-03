package informados

class LoginTagLib {
	static defaultEncodeAs = 'html'
	//static encodeAsForTags = [tagName: 'raw']
	def loginControl = { attrs, body->
		if(request.getSession(false) && session.user){
			out << "Hello ${session.user.userName} "
			//out << """g.link(controller: 'persona' action: 'logout') {"Logout"}"""
			out << """${link(action:"logout",controller:"persona") {"Logout"}}"""
		} else {
			//out << """[${createLink(action:"login",controller:"persona"){"Login"}}]"""
			out << "g.link(controller: 'persona' action: 'login'){Login}"
		}
	}

	def mitag = { attrs, body ->
		out << body()
	}
}

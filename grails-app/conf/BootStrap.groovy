class BootStrap {

	def simpleService
	
    def init = { servletContext ->
    	println 'calling service method'
    	simpleService.serviceMethod()
    	println 'ending call!'
    }
    
    def destroy = {
    }
}

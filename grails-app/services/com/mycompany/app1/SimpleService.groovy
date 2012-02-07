package com.mycompany.app1


class SimpleService {

    def serviceMethod() {
      //println 'in a world of us'
      10.times {
      	new Foo(name:'some-name-' + new Date()).save(flush:true)
      }
      
      println 'finished saving 10 foos'
    }
}

package com.mycompany.app1



import org.junit.*
import grails.test.mixin.*

@TestFor(FooController)
@Mock(Foo)
class FooControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/foo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fooInstanceList.size() == 0
        assert model.fooInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.fooInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fooInstance != null
        assert view == '/foo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/foo/show/1'
        assert controller.flash.message != null
        assert Foo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/foo/list'


        populateValidParams(params)
        def foo = new Foo(params)

        assert foo.save() != null

        params.id = foo.id

        def model = controller.show()

        assert model.fooInstance == foo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/foo/list'


        populateValidParams(params)
        def foo = new Foo(params)

        assert foo.save() != null

        params.id = foo.id

        def model = controller.edit()

        assert model.fooInstance == foo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/foo/list'

        response.reset()


        populateValidParams(params)
        def foo = new Foo(params)

        assert foo.save() != null

        // test invalid parameters in update
        params.id = foo.id
        //TODO: add invalid values to params object
		params.name = null

        controller.update()

        assert view == "/foo/edit"
        assert model.fooInstance != null

        foo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/foo/show/$foo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        foo.clearErrors()

        populateValidParams(params)
        params.id = foo.id
        params.version = -1
        controller.update()

        assert view == "/foo/edit"
        assert model.fooInstance != null
        assert model.fooInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/foo/list'

        response.reset()

        populateValidParams(params)
        def foo = new Foo(params)

        assert foo.save() != null
        assert Foo.count() == 1

        params.id = foo.id

        controller.delete()

        assert Foo.count() == 0
        assert Foo.get(foo.id) == null
        assert response.redirectedUrl == '/foo/list'
    }
}

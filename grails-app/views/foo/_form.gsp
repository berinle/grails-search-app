<%@ page import="com.mycompany.app1.Foo" %>



<div class="fieldcontain ${hasErrors(bean: fooInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="foo.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${fooInstance?.name}"/>
</div>


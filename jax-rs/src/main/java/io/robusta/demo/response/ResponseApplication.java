package io.robusta.demo.response;

import io.robusta.demo.InjectionResource;
import io.robusta.demo.providers.RraProvider;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@ApplicationPath("response")
public class ResponseApplication  extends Application{

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(ResponseResource.class);		
		s.add(RraProvider.class);
		
		return s;
	}
}

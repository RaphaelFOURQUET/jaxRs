package io.robusta.demo.jaxb;

import io.robusta.demo.providers.RraProvider;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("jaxb")
public class JaxbApplication extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(ThingResource.class);
		s.add(LoopResource.class);
		s.add(RraResource.class);
		s.add(RraProvider.class);
		return s;
	}
}

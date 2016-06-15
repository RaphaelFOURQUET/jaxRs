package io.robusta.demo.providers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Cacheable {

	
	/**
	 * @return time in minutes
	 * 0 = no cache; -1 = for ever
	 * Used for Expires, not for Validation
	 * Absolutely Not recommanded for Web Services
	 */
	int time() default 0;
	
	boolean etag() default false;
	
	boolean date() default false;
	
}

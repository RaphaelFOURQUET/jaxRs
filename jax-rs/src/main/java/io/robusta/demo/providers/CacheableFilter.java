package io.robusta.demo.providers;

import io.robusta.demo.cache.PeopleStory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



@Cacheable
@Provider
public class CacheableFilter implements ContainerResponseFilter {

	private static final String RFC1123_DATE_FORMAT_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";
	SimpleDateFormat dateFormat = new SimpleDateFormat(RFC1123_DATE_FORMAT_PATTERN, Locale.US);
	
	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		
		System.out.println("HAVING CACHEABLE !!!");
		
		
		Object o = responseContext.getEntity();
		System.out.println(o);
		PeopleStory story =null;
		if (o == null || ! (o instanceof PeopleStory)){
			System.out.println("Not a People Stroy, filter returns as usual");
			return;
		}else{
			story = (PeopleStory) o;
		}
		
		
       MultivaluedMap<String, Object> responseHeaders = responseContext.getHeaders();
       responseHeaders.putSingle("Cache-Control", "private");
       String lastModifiedString = dateFormat.format(story.getDate());
		responseHeaders.putSingle("Last-Modified",lastModifiedString);

		
		String ifModifiedSince = requestContext.getHeaderString("If-Modified-Since");
		System.out.println("Headers : "+ifModifiedSince);
		Date ifDate = new Date();
		
		
		boolean hasIfModifiedHeaders = false;
		boolean parseDateOK = true;
		if (ifModifiedSince != null && !ifModifiedSince.trim().equals("")){
			hasIfModifiedHeaders = true;
			try {
				ifDate = dateFormat.parse(ifModifiedSince);
				System.out.println("Client sends date : "+ifDate);
				
			} catch (ParseException e) {
				parseDateOK = false;
			}
		}
		
		
		boolean hasBeenModified = story.getTimestamp() > ifDate.getTime();
       
		if (parseDateOK && hasIfModifiedHeaders && hasBeenModified){// We found the same etag
			System.out.println("Using @Cacheable, we have found the date");
			System.out.println("NOTHING TO SEND");
			responseContext.setStatus(304);
			responseContext.setEntity("");
           
       }else{
       	//we add the tag in our Response
       	System.out.println("Sending full request");
       }

		
		
	}

}

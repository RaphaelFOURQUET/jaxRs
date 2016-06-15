package io.robusta.demo.providers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import io.robusta.demo.cache.PeopleStory;
import io.robusta.rra.representation.implementation.GsonRepresentation;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Deprecated
//@Provider
@Produces("application/json")
public class CacheableProvider implements MessageBodyWriter<PeopleStory> {

	@Context Request request;
	@Context Response response;
	@Context HttpHeaders headers;
	
	private static final String RFC1123_DATE_FORMAT_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";
	SimpleDateFormat dateFormat = new SimpleDateFormat(RFC1123_DATE_FORMAT_PATTERN, Locale.US);
	
	@Override
	public long getSize(PeopleStory arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		//BEWARE !! don't forget this
		return true;
	}

	@Override
	public void writeTo(PeopleStory story, Class<?> type,
			Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		
		System.out.println("in custom provider");
	

		// Setting Http cacheable
		for (Annotation annotation : annotations) {
			System.out.println("Having annotation "+annotation);
			if (annotation.annotationType().equals(Cacheable.class)) {
				System.out.println("HAVING CACHEABLE !!!");
				
				 //Create cache control header
		        CacheControl cc = new CacheControl();
		        
		        httpHeaders.putSingle("Cache-Control", "private");
				//Date: Thu, 14 Nov 2013 10:12:46 GMT
		        String lastModifiedString = dateFormat.format(story.getDate());
				httpHeaders.putSingle("Last-Modified",lastModifiedString);

				//Checking if request etag is matching the same
				String ifModifiedSince = headers.getHeaderString("If-Modified-Since");
				System.out.println("Headers : "+ifModifiedSince);
				//Date ifDate = new Date();
				Date ifDate =null ;
				
				boolean hasIfModifiedHeaders = false;
				if (ifModifiedSince != null && !ifModifiedSince.trim().equals("")){
					hasIfModifiedHeaders = true;
					try {
						ifDate=dateFormat.parse(ifModifiedSince.trim());
						//ifDate = HttpHeaderReader.readDate(ifModifiedSince);
						Date otherDate = dateFormat.parse(ifModifiedSince);
						System.out.println("GF parsing date : "+ifDate);
						System.out.println("GF parsing date : "+ifDate);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				boolean hasBeenModified = ifDate ==null
						|| story.getTimestamp() > ifDate.getTime();
	            
				if (hasIfModifiedHeaders && hasBeenModified){// We found the same etag
					System.out.println("Using @Cacheable, we have found the date");
					System.out.println("NOTHING TO SEND");
					response.status(304);
		            return ;
		        }else{
		        	//we add the tag in our Response
		        	System.out.println("Sending full request");
		        }
			}
		}

		
		GsonRepresentation representation = new GsonRepresentation(story);
		JsonWriter writer = null;
		try {
			writer = new JsonWriter(new OutputStreamWriter(entityStream));
			Gson gson = new Gson();
			gson.toJson(representation.getDocument(), writer);

		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		
	}

}

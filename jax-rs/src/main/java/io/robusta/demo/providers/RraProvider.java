package io.robusta.demo.providers;

import io.robusta.rra.representation.implementation.GsonRepresentation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

@Provider
@Produces("application/json")
public class RraProvider implements MessageBodyWriter<GsonRepresentation> {

	@Context Request request;
	
	@Override
	public long getSize(GsonRepresentation t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {

		return true;
	}

	@Override
	public void writeTo(GsonRepresentation representation, Class<?> type,
			Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		
			System.out.println("Request null ? " + request);

			// Setting Http cacheable
			for (Annotation annotation : annotations) {

				if (annotation.annotationType().equals(Cacheable.class)) {
					System.out.println("HAVING CACHEABLE !!!");
					
					 //Create cache control header
			        CacheControl cc = new CacheControl();
			        //Set max age to one day
			     //   cc.setMaxAge(86400);
			        
			        
			        String etagValue = getFastEtag(representation.toString());
					EntityTag etag = new EntityTag(etagValue);
					httpHeaders.putSingle("Cache-Control", "private");
					httpHeaders.putSingle("ETag",etagValue);

					//Checking if request etag is matching the same
					ResponseBuilder rb = request.evaluatePreconditions(etag);
					if (rb != null) 
			        {// We found the same etag
						System.out.println("Using @Cacheable, we have found the same etag" + etagValue);
						System.out.println("NOTHING TO SEND");
			            return ;
			        }else{
			        	//we add the tag in our Response
			        	System.out.println("Adding etag :"+ etagValue);
			        	System.out.println("Sending full request");
			        }
				}
			}

			System.out.println("in custom provider");
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
	
	public String getFastEtag(String input){
		 final java.nio.ByteBuffer buf = java.nio.charset.StandardCharsets.UTF_8.encode(input);
		    MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA1");
				buf.mark();
			    digest.update(buf);
			    buf.reset();
			    return  DatatypeConverter.printHexBinary(digest.digest());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

}

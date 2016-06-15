package io.robusta.demo.cache;

import io.robusta.business.EtagBusiness;
import io.robusta.demo.providers.Cacheable;
import io.robusta.rra.representation.implementation.GsonRepresentation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("")
public class CacheResource {

	StoryService service = new StoryService();

	@GET
	@Path("test")
	public Response test() {

		return Response.ok("test").build();

	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStory(@PathParam("id") int id, @Context Request req) {

		// Create cache control header
		CacheControl cc = new CacheControl();
		cc.setPrivate(true);
		// Set max age to one day
		// cc.setMaxAge(86400);

		PeopleStory story = service.getStory(id);

		if (story != null) {

			// Creating tag from the fetched story
			String etagValue = story.getEtag();
			EntityTag etag = new EntityTag(etagValue);

			// Checking if request etag is matching the same
			ResponseBuilder rb = req.evaluatePreconditions(etag);
			if (rb != null) {// We found the same etag
				System.out.println("We have found the same etag" + etagValue);
				return rb.status(304).cacheControl(cc).tag(etag).build();
			} else {
				// we add the tag in our Response
				System.out.println("Adding etag :" + etagValue);
				return Response.ok(story.getContent()).cacheControl(cc)
						.tag(etag).build();
			}

		} else {
			return Response.status(404).entity("Not found").build();
		}

	}

	
	@GET
	@Path("cacheable/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//@Cacheable
	public GsonRepresentation getCacheableStory(@PathParam("id") int id,
			@Context Request req) {
		System.out.println("inside Resource : " + req.toString());

		GsonRepresentation representation = new GsonRepresentation(
				service.getStory(id));
		String etagValue = new EtagBusiness().getFastEtag(representation.toString());
		EntityTag etag = new EntityTag(etagValue);
		ResponseBuilder rb = req.evaluatePreconditions(etag);

		System.out.println("RB null in Resource ? " + (rb == null));

		return representation;

	}
	
	
	@GET
	@Path("date/{id}")
	@Cacheable
	public PeopleStory getDateStory(@PathParam("id") int id) {
		System.out.println("in date story");
		PeopleStory story =service.getStory(id); 	

		return story;

	}
	
	
	


	
}

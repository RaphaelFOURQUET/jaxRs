package io.robusta.forum;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.robusta.ForaDataSource;
import io.robusta.business.TopicBusiness;
import io.robusta.domain.Topic;

@Path("topics")
public class TopicsApi {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Topic> getTopics() {
		return ForaDataSource.getInstance().getTopics();
	}
	
	//POST
	//generateTopics
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Topic generateTopic(Topic topic) {
		TopicBusiness topicBusiness = new TopicBusiness();
		return topicBusiness.createTopic(topic);
	}

}

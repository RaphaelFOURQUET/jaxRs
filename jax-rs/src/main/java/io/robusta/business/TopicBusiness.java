package io.robusta.business;

import io.robusta.ForaDataSource;
import io.robusta.domain.Topic;
import io.robusta.domain.User;

import java.util.List;
import java.util.logging.Logger;

public class TopicBusiness {

	private final static Logger logger = Logger.getLogger(TopicBusiness.class
			.getName());

	ForaDataSource fora = ForaDataSource.getInstance();

	public Topic getTopicById(long id) {
		for (Topic s : fora.getTopics()) {
			if (s.getId() == id) {
				return s;
			}

		}

		return null;// or throw exception
	}

	public List<Topic> getAllTopics() {
		return fora.getTopics();

	}

	public int countTopics() {

		return fora.getTopics().size();

	}
	
	public Topic createTopic(Topic topic){
		Topic t = new Topic();
		t.setComments(topic.getComments());
		t.setTags(topic.getTags());
		t.setTitle(topic.getTitle());
		t.setUser(topic.getUser());
		t.setId(this.countTopics()+1);
		fora.getTopics().add(t);
		return t;
	}
}

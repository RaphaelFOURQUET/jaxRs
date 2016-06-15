package io.robusta.demo.cache;

public class StoryService {

	static PeopleStory story1 = new PeopleStory(1, "Hello World");
	static PeopleStory story2 = new PeopleStory(
			2,
			"Accenderat super his incitatum propositum ad nocendum aliqua mulier vilis, quae ad palatium ut poposcerat intromissa insidias ei latenter obtendi prodiderat a militibus obscurissimis. quam Constantina exultans ut in tuto iam locata mariti salute muneratam vehiculoque inpositam per regiae ianuas emisit in publicum, ut his inlecebris alios quoque ad indicanda proliceret paria vel maiora");

	public PeopleStory getStory(int id) {

		switch (id) {
		case 1:
			return story1;

		case 2:
			return story2;
		default:
			return null;
		}

	}
}

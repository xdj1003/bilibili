package bilibili.relation;
import bilibili.friend.*;

public class RelationshipFactory {
	public Relationship getRelationship(String relationship) {
		if(relationship.equalsIgnoreCase("Friend")) {
			return new Friend();
		}else if(relationship.equalsIgnoreCase("Group")) {
			return new Group();
		}
		return null;
	}
}

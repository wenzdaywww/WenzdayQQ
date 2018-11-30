package www.model;

public class FriendGroup {
	
	private String userId;
	private String groupId;
	private String groupName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Override
	public String toString() {
		return "FriendGroup [userId=" + userId + ", groupId=" + groupId + ", groupName=" + groupName + "]";
	}
	
}

package www.model;

public class QQGroup {

	private String qqgroupId;
	private String creatorId;
	private String qqgroupName;
	public String getQqgroupId() {
		return qqgroupId;
	}
	public void setQqgroupId(String qqgroupId) {
		this.qqgroupId = qqgroupId;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getQqgroupName() {
		return qqgroupName;
	}
	public void setQqgroupName(String qqgroupName) {
		this.qqgroupName = qqgroupName;
	}
	@Override
	public String toString() {
		return "QQGroup [qqgroupId=" + qqgroupId + ", creatorId=" + creatorId + ", qqgroupName=" + qqgroupName + "]";
	}
}

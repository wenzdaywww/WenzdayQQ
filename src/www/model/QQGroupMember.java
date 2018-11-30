package www.model;

public class QQGroupMember {
	
	private String qqgrouId;
	private String memberId;
	
	public String getQqgrouId() {
		return qqgrouId;
	}
	public void setQqgrouId(String qqgrouId) {
		this.qqgrouId = qqgrouId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "QQGroupMember [qqgrouId=" + qqgrouId + ", memberId=" + memberId + "]";
	}
}

package www.model;

public class ChatMessage {
	
	private String sendId;
	private String receiveId;
	private String chatTime;
	private String chatMessage;
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public String getChatMessage() {
		return chatMessage;
	}
	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}
	@Override
	public String toString() {
		return "ChatMessage [sendId=" + sendId + ", receiveId=" + receiveId + ", chatTime=" + chatTime
				+ ", chatMessage=" + chatMessage + "]";
	}
	
	
}

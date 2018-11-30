package www.model;

import java.util.ArrayList;

import net.sf.json.JSONObject;

public class JSONMsg {
	public static final String FAIL="Fail";
	public static final String LOGIN="Login";
	public static final String MESSAGE="Message";
	public static final String SUCCESS="Success";
	public static final String FRIENDS="Friends";
	public static final String REGISTER="Register";
	
	private User user;
	private String pwd;
	private String type;
	private String userId;
	private String registId;
	private String result;
	private String sendId;
	private String receiveId;
	private String chatTime;
	private String chatMsg;
	private ArrayList<User> friendList;
	
	public JSONMsg(){
	}
	
	public JSONMsg(String type, String userId, String pwd) {
		super();
		this.type = type;
		this.userId = userId;
		this.pwd = pwd;
	}
	
	public JSONMsg(String type, String userId) {
		super();
		this.type = type;
		this.userId = userId;
	}


	public JSONMsg(String type, String userId, ArrayList<User> friendList) {
		super();
		this.type = type;
		this.userId = userId;
		this.friendList = friendList;
	}
	

	public JSONMsg(String type, String sendId, String receiveId, String chatTime, String chatMsg) {
		super();
		this.type = type;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.chatTime = chatTime;
		this.chatMsg = chatMsg;
	}

	public JSONMsg(String type, String result, User user) {
		super();
		this.type = type;
		this.result = result;
		this.user = user;
	}

	public String toMsg(){
		JSONObject obj=JSONObject.fromObject(this);
		return obj.toString();
	}
	public static JSONMsg toJSONMsg(String jsonStr){
		JSONObject obj=JSONObject.fromObject(jsonStr);
		JSONMsg jMsg=(JSONMsg) JSONObject.toBean(obj, JSONMsg.class);
		return jMsg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ArrayList<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(ArrayList<User> friendList) {
		this.friendList = friendList;
	}

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

	public String getChatMsg() {
		return chatMsg;
	}

	public void setChatMsg(String chatMsg) {
		this.chatMsg = chatMsg;
	}

	public String getRegistId() {
		return registId;
	}

	public void setRegistId(String registId) {
		this.registId = registId;
	}
	
}

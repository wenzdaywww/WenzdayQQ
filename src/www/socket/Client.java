package www.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import www.ctrl.uictrl.ChatFrameCtrl;
import www.model.ChatMessage;
import www.model.JSONMsg;
import www.model.User;
import www.ui.ChatFrame;
import www.util.Constants;

public class Client extends Thread{

	private User user;
	private String registId="";
	private static Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private static Client client;
	private ChatMessage chatMsg;
	private ArrayList<User> friendIdList;

	public static Client getCL(){
		if (client==null) {
			client=new Client();
			client.start();
		}
		return client;
	}
	private Client(){

	}
	@Override
	public void run() {
		try {
			socket=new Socket(Constants.IP, Constants.PORT);
			writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg;
			while ((msg=reader.readLine())!=null) {
				JSONMsg obj=JSONMsg.toJSONMsg(msg);
				if (obj.getType().equals(JSONMsg.LOGIN)) {
					getUserInfo(obj);
				}else if (obj.getType().equals(JSONMsg.MESSAGE)) {
					receiveMsg(obj);
					ChatFrameCtrl.getCFC();
					ChatFrame.getCF().setTitle(getFriendName(chatMsg.getSendId())+"("+chatMsg.getSendId()+")");
					ChatFrame.getCF().appendText(getFriendName(chatMsg.getSendId())+" "+chatMsg.getChatTime());
					ChatFrame.getCF().appendText(chatMsg.getChatMessage());
				}else if (obj.getType().equals(JSONMsg.FRIENDS)) {
					getFriendInfo(obj);
				}else if (obj.getType().equals(JSONMsg.REGISTER)) {
					getResigetId(obj);
				}
			}
			reader.close();
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "请确认服务器是否已打开！");
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	};
	public void login(String userId,String pwd){
		JSONMsg msg=new JSONMsg(JSONMsg.LOGIN, userId, pwd);
		send(msg.toMsg());
	}
	private void getUserInfo(JSONMsg obj){
		if (obj.getResult().equals(JSONMsg.SUCCESS)) {
			user=new User();
			user.setUserId(obj.getUser().getUserId());
			user.setUserName(obj.getUser().getUserName());
			user.setUserPwd(obj.getUser().getUserPwd());
			user.setUserAge(obj.getUser().getUserAge());
			user.setUserSex(obj.getUser().getUserSex());
			user.setUserBirthday(obj.getUser().getUserBirthday());
		}
	}
	public void getFriends(String userId){
		JSONMsg msg=new JSONMsg(JSONMsg.FRIENDS, userId);
		send(msg.toMsg());
	}
	private void getFriendInfo(JSONMsg msg){
		friendIdList=new ArrayList<User>();
		JSONObject obj=JSONObject.fromObject(msg.toMsg());
		//先将集合字符串转换成JSONArray数组
		JSONArray array=obj.getJSONArray("friendList");
		//遍历JSONArray数据，获取数组中的JSONObject对象
		for (int i = 0; i < array.size(); i++) {
			JSONObject object=array.getJSONObject(i);
			User user=(User) JSONObject.toBean(object,User.class);
			friendIdList.add(user);
		}
	}
	public String getFriendName(String id){
		String name="";
		for (int j = 0; j < friendIdList.size(); j++) {
			if (friendIdList.get(j).getUserId().equals(id)) {
				name=friendIdList.get(j).getUserName();
				break;
			}
		}
		return name;
	}
	public void register(String name,String pwd,String age,String sex,String birthday){
		User regist=new User();
		regist.setUserName(name);
		regist.setUserPwd(pwd);
		regist.setUserAge(age);
		regist.setUserSex(sex);
		regist.setUserBirthday(birthday);
		JSONMsg msg=new JSONMsg(JSONMsg.REGISTER, null, regist);
		send(msg.toMsg());
	}
	private void getResigetId(JSONMsg obj){
		if (obj.getResult().equals(JSONMsg.SUCCESS)) {
			registId=obj.getUser().getUserId();
		}
	}
	public void sendMsg(String sendId,String receiveId,String chatTime,String chatMsg){
		JSONMsg msg=new JSONMsg(JSONMsg.MESSAGE, sendId, receiveId, chatTime, chatMsg);
		send(msg.toMsg());
	}
	public void receiveMsg(JSONMsg obj){
		chatMsg=new ChatMessage();
		chatMsg.setSendId(obj.getSendId());
		chatMsg.setReceiveId(obj.getReceiveId());
		chatMsg.setChatTime(obj.getChatTime());
		chatMsg.setChatMessage(obj.getChatMsg());
	}
	public void send(String out) {
		if (writer!=null) {
			try {
				writer.write(out+"\n");
				writer.flush();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "数据发送失败！");
			}
		}
	}
	public User getUser() {
		return user;
	}
	public ChatMessage getChatMsg() {
		return chatMsg;
	}
	public ArrayList<User> getFriendIdList() {
		return friendIdList;
	}
	public String getRegistId() {
		return registId;
	}
	public void setRegistId(String registId) {
		this.registId = registId;
	}
}

package www.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;

import www.ctrl.dbdao.UserDao;
import www.model.JSONMsg;
import www.model.User;
import www.util.Constants;

public class ChatSocket extends Thread{
	
	private Socket socket;
	private String name;
	
	public ChatSocket(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));
			String msg;
			while ((msg=reader.readLine())!=null) {
				JSONMsg obj=JSONMsg.toJSONMsg(msg);
				if (obj.getType().equals(JSONMsg.LOGIN)) {
					login(obj);
				}else if (obj.getType().equals(JSONMsg.MESSAGE)) {
					sendFriendMsg(obj);
				}else if (obj.getType().equals(JSONMsg.FRIENDS)) {
					getFriends(obj);
				}else if (obj.getType().equals(JSONMsg.REGISTER)) {
					register(obj);
				}
			}
			reader.close();
		} catch (UnsupportedEncodingException e) {
			synchronized (ChatSocket.class) {
				ChatManager.getCM().deleteSocket(this);
			}
		} catch (IOException e) {
			synchronized (ChatSocket.class) {
				ChatManager.getCM().deleteSocket(this);
			}
		}
	}
	private void login(JSONMsg obj){
		User user=new UserDao().login(obj.getUserId(), obj.getPwd());
		JSONMsg msg;
		if (user==null) {
			msg=new JSONMsg(JSONMsg.LOGIN, JSONMsg.FAIL, user);
			send(msg.toMsg());
		}else {
			name=obj.getUserId();
			msg=new JSONMsg(JSONMsg.LOGIN, JSONMsg.SUCCESS, user);
			send(msg.toMsg());
		}
	}
	private void getFriends(JSONMsg obj){
		ArrayList<User> list=new UserDao().getFriend(obj.getUserId());
		JSONMsg msg=new JSONMsg(JSONMsg.FRIENDS, obj.getUserId(), list);
		send(msg.toMsg());
	}
	private void register(JSONMsg obj){
		JSONMsg msg;
		User regist=new User();
		if (new UserDao().registUser(obj.getUser().getUserName(), obj.getUser().getUserPwd(), obj.getUser().getUserAge(), obj.getUser().getUserSex(), obj.getUser().getUserBirthday())!=0) {
			regist.setUserId(Constants.REGIST_ID);
			msg=new JSONMsg(JSONMsg.REGISTER, JSONMsg.SUCCESS, regist);
		}else {
			regist.setUserId(Constants.REGIST_ID);
			msg=new JSONMsg(JSONMsg.REGISTER, JSONMsg.FAIL, regist);
		}
		send(msg.toMsg());
	}
	private void sendFriendMsg(JSONMsg obj){
		JSONMsg msg=new JSONMsg(JSONMsg.MESSAGE, obj.getSendId(), obj.getReceiveId(), obj.getChatTime(), obj.getChatMsg());
		ChatManager.getCM().sendFriendMsg(obj.getReceiveId(), msg.toMsg());
	}
	
	public void send(String out){
		try {
			socket.getOutputStream().write((out+"\n").getBytes("GBK"));
		} catch (IOException e) {
			synchronized (ChatSocket.class) {
				ChatManager.getCM().deleteSocket(this);
			}
		}
	}

	public String getUsrName() {
		return name;
	}

	
}

package www.socket;

import java.util.Vector;

public class ChatManager {
	
	private static ChatManager chatManager;
	private Vector<ChatSocket> vector=new Vector<ChatSocket>();
	
	public static ChatManager getCM(){
		if (chatManager==null) {
			chatManager=new ChatManager();
		}
		return chatManager;
	}
	private ChatManager(){
		
	}
	public void addSocket(ChatSocket socket){
		vector.add(socket);
	}
	public void deleteSocket(ChatSocket socket){
		vector.remove(socket);
	}
	public void sendAllMsg(ChatSocket socket,String out){
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket cs=vector.get(i);
			if (!socket.equals(cs)) {
				cs.send(out);
			}
		}
	}
	public void sendServerMsg(ChatSocket socket,String out){
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket cs=vector.get(i);
			if (socket.equals(cs)) {
				cs.send(out);
			}
		}
	}
	public void sendFriendMsg(String friendId,String msg){
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket cs=vector.get(i);
			if (cs.getUsrName().equals(friendId)) {
				cs.send(msg);
			}
		}
	}
}

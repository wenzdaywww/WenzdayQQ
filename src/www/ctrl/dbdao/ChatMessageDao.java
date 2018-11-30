package www.ctrl.dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import www.model.ChatMessage;

public class ChatMessageDao extends BaseDao {
	
	/**
	 * 添加消息记录
	 * @param sendId
	 * @param receiveId
	 * @param chatTime
	 * @param chatMessage
	 * @return
	 */
	public int addMessage(String sendId,String receiveId,String chatTime,String chatMessage){
		int row=0;
		String sql="insert into table_chat_message values (null,"+sendId+","+receiveId+",'"+chatTime+"','"+chatMessage+"')";
		row=update(sql);
		return row;
	}
	/**
	 * 获取用户所有消息记录
	 * @param userId
	 * @return
	 */
	public ArrayList<ChatMessage> getUserMessage(String userId){
		ResultSet set=null;
		String sql="select send_id,receive_id,chat_time,message from table_chat_message where send_id="+userId+" or receive_id="+userId;
		set=select(sql);
		return getMessage(set);
	}
	private ArrayList<ChatMessage> getMessage(ResultSet set){
		ArrayList<ChatMessage> cArrayList=new ArrayList<ChatMessage>();
		try {
			while (set.next()) {
				ChatMessage chatMessage=new ChatMessage();
				chatMessage.setSendId(set.getString("send_id"));
				chatMessage.setReceiveId(set.getString("receive_id"));
				chatMessage.setChatTime(set.getString("chat_time"));
				chatMessage.setChatMessage(set.getString("message"));
				cArrayList.add(chatMessage);
			}
		} catch (SQLException e) {
		}
		return cArrayList;
	}
}

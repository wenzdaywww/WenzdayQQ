package www.ctrl.uictrl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import www.socket.Client;
import www.ui.ChatFrame;
import www.util.TimeUtil;

public class ChatFrameCtrl {
	
	private ChatFrame chatFrame=ChatFrame.getCF();
	private static ChatFrameCtrl chatFrameCtrl;
	public static ChatFrameCtrl getCFC(){
		if (chatFrameCtrl==null) {
			chatFrameCtrl=new ChatFrameCtrl();
		}
		ChatFrame.getCF().setVisible(true);
		return chatFrameCtrl;
	}
	
	public ChatFrameCtrl() {
		listenerCtrl();
	}
	private void listenerCtrl(){
		chatFrame.getBtnSend().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!chatFrame.getTextSend().getText().equals("")) {
					Client.getCL().sendMsg(Client.getCL().getUser().getUserId(), getId(chatFrame.getTitle()), 
							TimeUtil.getDateTime(), chatFrame.getTextSend().getText());
					chatFrame.appendText(Client.getCL().getUser().getUserName()+" "+TimeUtil.getDateTime());
					chatFrame.appendText(chatFrame.getTextSend().getText());
					chatFrame.getTextSend().setText("");
				}
			}
		});
	}
	private String getId(String name){
		return name.substring(name.indexOf("(")+1, name.indexOf(")"));
	}
}

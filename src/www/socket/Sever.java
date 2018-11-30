package www.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever extends Thread{
	private ServerSocket server;
	@Override
	public void run() {
		try {
			server=new ServerSocket(8888);
			while (true) {
				Socket socket=server.accept();
				ChatSocket chatSocket=new ChatSocket(socket);
				chatSocket.start();
				synchronized (ChatSocket.class) {
					ChatManager.getCM().addSocket(chatSocket);
				}
				System.out.println("�ͻ������ӵ��˱�����8888�˿ڡ�");
//				JOptionPane.showMessageDialog(null, "�ͻ������ӵ��˱�����8888�˿ڡ�");	
			}
		} catch (IOException e) {
		}
	}
}

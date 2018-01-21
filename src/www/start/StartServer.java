package www.start;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import www.socket.Sever;

public class StartServer {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Sever().start();
	}

}

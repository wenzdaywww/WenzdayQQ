package www.start;

import java.awt.AWTException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import www.ctrl.uictrl.LoginDialogCtrl;
import www.ui.LoginDialog;

public class StartQQ {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginDialog loginDialog = new LoginDialog();
					new LoginDialogCtrl(loginDialog);
				} catch (AWTException e) {
					
				}
			}
		});
	}

}

package www.ctrl.uictrl;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import www.socket.Client;
import www.ui.LoginDialog;
import www.ui.MainDialog;
import www.ui.RegistDialog;
import www.util.LoginData;

public class LoginDialogCtrl {
	/**@see ��ȡ�����ק֮ǰ��X����*/
	private int mx=0;
	/**@see ��ȡ�����ק֮ǰ��Y����*/
	private int my=0;				
	/**@see ע�ᴰ���X����*/
	private int jdx=0;				
	/**@see ע�ᴰ���Y����*/
	private int jdy=0;	
	private boolean canMove=false;
	private LoginDialog loginDialog;

	public LoginDialogCtrl(LoginDialog loginDialog){
		this.loginDialog=loginDialog;
		new MainDialogCtrl();
		getData();
		listenerCtrl();
	}
	private void listenerCtrl(){
		//==========ע�ᴰ��������ѱ����أ�����������ƶ�����=========
		loginDialog.loginPanel.addMouseMotionListener(new MouseMotionAdapter() {//�������˶��������������ƶ�����
			@Override
			public void mouseDragged(MouseEvent e) {
				if (canMove) {
					loginDialog.setLocation(jdx+e.getXOnScreen()-mx, jdy+e.getYOnScreen()-my);
				}
			}
		});
		//==========��ȡ�������������==========
		loginDialog.loginPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx=e.getXOnScreen();
				my=e.getYOnScreen();
				jdx=loginDialog.getX();
				jdy=loginDialog.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				canMove=false;
				if (loginDialog.getY()<0&&loginDialog.getX()>0) {
					loginDialog.setLocation(jdx+e.getXOnScreen()-mx, 0);
				}else if (loginDialog.getY()>0&&loginDialog.getX()<0) {
					loginDialog.setLocation(0, jdy+e.getYOnScreen()-my);
				}else if (loginDialog.getY()<0&&loginDialog.getX()<0) {
					loginDialog.setLocation(0, 0);
				}else {
					canMove=true;
				}
			}
		});
		//�˻������ı߿���
		loginDialog.textFieldUser.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				loginDialog.textFieldUser.setBorder(BorderFactory.createLineBorder(Color.gray));
			}
			@Override
			public void focusGained(FocusEvent e) {
				loginDialog.textFieldUser.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
		});
		//�˻������ı߿���
		loginDialog.textFieldUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginDialog.textFieldUser.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginDialog.textFieldUser.setBorder(BorderFactory.createLineBorder(Color.gray));
			}
		});
		//���������ı߿���
		loginDialog.passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				loginDialog.passwordField.setBorder(BorderFactory.createLineBorder(Color.gray));
			}
			@Override
			public void focusGained(FocusEvent e) {
				loginDialog.passwordField.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
		});
		//���������ı߿���
		loginDialog.passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginDialog.passwordField.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginDialog.passwordField.setBorder(BorderFactory.createLineBorder(Color.gray));
			}
		});
		//�������رհ�ť�¼�����
		loginDialog.btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginDialog.systemTray.remove(loginDialog.trayIcon);
				System.exit(0);
			}
		});
		//���������С����ť�¼�����
		loginDialog.btnMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginDialog.setVisible(false);
			}
		});
		//ϵͳ����˫���򿪵������
		loginDialog.trayIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					loginDialog.setVisible(true);
				}
			}
		});
		//ϵͳ�����һ��򿪵������
		loginDialog.showItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginDialog.setVisible(true);
			}
		});
		//ϵͳ�����һ��˳�
		loginDialog.exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginDialog.systemTray.remove(loginDialog.trayIcon);
				System.exit(0);
			}
		});
		//ע���˺�
		loginDialog.labelRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginDialog.labelRegister.setForeground(new Color(20, 20, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginDialog.labelRegister.setForeground(new Color(20, 100, 255, 255));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistDialogCtrl.getRDC();
				RegistDialog.getRet().setVisible(true);
			}
		});
		//�һ�����
		loginDialog.labelRetrievePwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginDialog.labelRetrievePwd.setForeground(new Color(20, 20, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginDialog.labelRetrievePwd.setForeground(new Color(20, 100, 255, 255));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		loginDialog.chckbxRememberPwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!loginDialog.chckbxRememberPwd.isSelected()) {
					loginDialog.chckbxAutoOnline.setSelected(false);
				}
//				saveData();
			}
		});
		loginDialog.chckbxAutoOnline.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (loginDialog.chckbxAutoOnline.isSelected()) {
					loginDialog.chckbxRememberPwd.setSelected(true);
				}
//				saveData();
			}
		});
		//���밴ť
		loginDialog.btnLogin.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "deprecation", "unchecked" })
			@Override
			public void mouseClicked(MouseEvent e) {
				Client.getCL().login(loginDialog.textFieldUser.getText(), loginDialog.passwordField.getText());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
				}
				if (Client.getCL().getUser()!=null) {
					MainDialog.getMain().lblUserName.setText(Client.getCL().getUser().getUserName());
					Client.getCL().getFriends(loginDialog.textFieldUser.getText());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
					}
					if (Client.getCL().getFriendIdList()!=null) {
						int i=Client.getCL().getFriendIdList().size();
						while (i>0) {
							i--;
							MainDialog.getMain().mListModel.addElement(Client.getCL().getFriendIdList().get(i).getUserName()+"("+
									Client.getCL().getFriendIdList().get(i).getUserId()+")");
						}
					}
					loginDialog.systemTray.remove(loginDialog.trayIcon);
					loginDialog.setVisible(false);
					MainDialog.getMain().setVisible(true);
					try {
						MainDialog.getMain().systemTray.add(MainDialog.getMain().trayIcon);
					} catch (AWTException e1) {
						JOptionPane.showConfirmDialog(loginDialog, "ϵͳ����ͼ�����ʧ�ܣ�", "��ʾ", JOptionPane.CLOSED_OPTION);
					}
				}else {
					JOptionPane.showConfirmDialog(loginDialog, "�˻����������", "��ʾ", JOptionPane.CLOSED_OPTION);
				}
			}
		});
	}
//	@SuppressWarnings("deprecation")
//	private void saveData(){
//		try {
//			if (!loginDialog.textFieldUser.getText().equals("")&&!loginDialog.passwordField.getText().equals("")) {
//				UserDao userDao=new UserDao();
//				User user=userDao.login(loginDialog.textFieldUser.getText(), loginDialog.passwordField.getText());
//				if (user!=null) {
//					LoginData.saveData(Integer.parseInt(loginDialog.textFieldUser.getText()), loginDialog.passwordField.getText(), 
//							loginDialog.chckbxRememberPwd.isSelected(), loginDialog.chckbxAutoOnline.isSelected(), "user.dat");
//				}
//			}
//		} catch (NumberFormatException | IOException e) {
//			JOptionPane.showConfirmDialog(loginDialog, "�����û�����ʧ�ܣ�", "��ʾ", JOptionPane.CLOSED_OPTION);
//		}
//	}
	//	private void enterMainDialog(){
	//		UserDao userDao=new UserDao();
	//		User user=userDao.login(loginDialog.textFieldUser.getText(), loginDialog.passwordField.getText());
	//		if (user!=null) {
	//			new MainDialogCtrl();
	//			ArrayList<User> friendList=userDao.showFriend();
	//			int i=friendList.size();
	//			while (i>0) {
	//				i-=1;
	//				MainDialog.getMain().mListModel.addElement(friendList.get(i));
	//			}
	//			try {
	//				MainDialog.getMain().systemTray.add(MainDialog.getMain().trayIcon);
	//			} catch (AWTException e1) {
	//				JOptionPane.showConfirmDialog(loginDialog, "ϵͳ����ͼ�����ʧ�ܣ�", "��ʾ", JOptionPane.CLOSED_OPTION);
	//			}
	//			loginDialog.systemTray.remove(loginDialog.trayIcon);
	//			loginDialog.setVisible(false);
	//			MainDialog.getMain().setVisible(true);
	//		}else {
	//			JOptionPane.showConfirmDialog(loginDialog, "�˻����������", "��ʾ", JOptionPane.CLOSED_OPTION);
	//		}
	//	}
	private void getData(){
		File userData=new File("user.dat");
		if (userData.exists()) {
			try {
				String id=Integer.toString(LoginData.getId("user.dat"));
				String pwd=LoginData.getPwd("user.dat");
				boolean isRemember=LoginData.isReberPwd("user.dat");
				boolean isAuto=LoginData.isAutoLink("user.dat");
				loginDialog.textFieldUser.setText(id);
				loginDialog.passwordField.setText(pwd);
				loginDialog.chckbxRememberPwd.setSelected(isRemember);
				loginDialog.chckbxAutoOnline.setSelected(isAuto);
			} catch (IOException e) {
			}
		}
	}
//		private void loginning(){
//			loginDialog.btnLogin.setBtnText("ȡ��");
//			loginDialog.userPhotoPanel.setLocation(160, 15);
//			loginDialog.textFieldUser.setVisible(false);
//			loginDialog.passwordField.setVisible(false);
//			loginDialog.chckbxRememberPwd.setVisible(false);
//			loginDialog.chckbxAutoOnline.setVisible(false);
//			loginDialog.labelRegister.setVisible(false);
//			loginDialog.labelRetrievePwd.setVisible(false);
//		}
	//	private void waitLogin(){
	//		loginDialog.btnLogin.setBtnText("��¼");
	//		loginDialog.userPhotoPanel.setLocation(40, 15);
	//		loginDialog.textFieldUser.setVisible(true);
	//		loginDialog.passwordField.setVisible(true);
	//		loginDialog.chckbxRememberPwd.setVisible(true);
	//		loginDialog.chckbxAutoOnline.setVisible(true);
	//		MainDialog.getMain().setVisible(false);
	//	}
}

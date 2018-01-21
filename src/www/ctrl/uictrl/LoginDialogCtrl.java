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
	/**@see 获取鼠标拖拽之前的X坐标*/
	private int mx=0;
	/**@see 获取鼠标拖拽之前的Y坐标*/
	private int my=0;				
	/**@see 注册窗体的X坐标*/
	private int jdx=0;				
	/**@see 注册窗体的Y坐标*/
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
		//==========注册窗体标题栏已被隐藏，添加容器的移动方法=========
		loginDialog.loginPanel.addMouseMotionListener(new MouseMotionAdapter() {//添加鼠标运动侦听器，便于移动窗体
			@Override
			public void mouseDragged(MouseEvent e) {
				if (canMove) {
					loginDialog.setLocation(jdx+e.getXOnScreen()-mx, jdy+e.getYOnScreen()-my);
				}
			}
		});
		//==========获取容器的鼠标坐标==========
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
		//账户输入框的边框处理
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
		//账户输入框的边框处理
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
		//密码输入框的边框处理
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
		//密码输入框的边框处理
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
		//登入界面关闭按钮事件监听
		loginDialog.btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginDialog.systemTray.remove(loginDialog.trayIcon);
				System.exit(0);
			}
		});
		//登入界面最小化按钮事件监听
		loginDialog.btnMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginDialog.setVisible(false);
			}
		});
		//系统托盘双击打开登入界面
		loginDialog.trayIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					loginDialog.setVisible(true);
				}
			}
		});
		//系统托盘右击打开登入界面
		loginDialog.showItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginDialog.setVisible(true);
			}
		});
		//系统托盘右击退出
		loginDialog.exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginDialog.systemTray.remove(loginDialog.trayIcon);
				System.exit(0);
			}
		});
		//注册账号
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
		//找回密码
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
		//登入按钮
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
						JOptionPane.showConfirmDialog(loginDialog, "系统托盘图标加载失败！", "提示", JOptionPane.CLOSED_OPTION);
					}
				}else {
					JOptionPane.showConfirmDialog(loginDialog, "账户或密码错误！", "提示", JOptionPane.CLOSED_OPTION);
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
//			JOptionPane.showConfirmDialog(loginDialog, "保存用户数据失败！", "提示", JOptionPane.CLOSED_OPTION);
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
	//				JOptionPane.showConfirmDialog(loginDialog, "系统托盘图标加载失败！", "提示", JOptionPane.CLOSED_OPTION);
	//			}
	//			loginDialog.systemTray.remove(loginDialog.trayIcon);
	//			loginDialog.setVisible(false);
	//			MainDialog.getMain().setVisible(true);
	//		}else {
	//			JOptionPane.showConfirmDialog(loginDialog, "账户或密码错误！", "提示", JOptionPane.CLOSED_OPTION);
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
//			loginDialog.btnLogin.setBtnText("取消");
//			loginDialog.userPhotoPanel.setLocation(160, 15);
//			loginDialog.textFieldUser.setVisible(false);
//			loginDialog.passwordField.setVisible(false);
//			loginDialog.chckbxRememberPwd.setVisible(false);
//			loginDialog.chckbxAutoOnline.setVisible(false);
//			loginDialog.labelRegister.setVisible(false);
//			loginDialog.labelRetrievePwd.setVisible(false);
//		}
	//	private void waitLogin(){
	//		loginDialog.btnLogin.setBtnText("登录");
	//		loginDialog.userPhotoPanel.setLocation(40, 15);
	//		loginDialog.textFieldUser.setVisible(true);
	//		loginDialog.passwordField.setVisible(true);
	//		loginDialog.chckbxRememberPwd.setVisible(true);
	//		loginDialog.chckbxAutoOnline.setVisible(true);
	//		MainDialog.getMain().setVisible(false);
	//	}
}

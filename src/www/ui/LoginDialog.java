package www.ui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import www.util.PictureRes;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog {

	public MyButton btnMin;
	public MenuItem showItem;
	public MenuItem exitItem;
	public JPanel loginPanel;
	public MyButton btnExit;
	public MyButton btnLogin;
	public ImageIcon trayImage;
	public JLabel labelRegister;
	public SystemTray systemTray;
	public TrayIcon trayIcon=null;
	public MyPanel userPhotoPanel;
	public JLabel labelRetrievePwd;
	public JTextField textFieldUser;
	public JCheckBox chckbxAutoOnline;
	public JCheckBox chckbxRememberPwd;
	public JPasswordField passwordField;

	public LoginDialog()throws AWTException{
		initUI();
		initTrayIcon();
	}
	private void initUI(){
		setSize(400, 300);
		setUndecorated(true);
		setVisible(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

		loginPanel=new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				Image image=Toolkit.getDefaultToolkit().getImage(PictureRes.LOGIN_BACKGROUP);
				g.drawImage(image, 0, 0, this);
			}
		};
		setContentPane(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.setSize(400, 300);

		btnExit=new MyButton("×");
		btnExit.setToolTipText("关闭");
		btnExit.setTextFont(new Font("幼圆", Font.PLAIN, 20));
		btnExit.setNormalColor(Color.white, 3, 17, new Color(0,0,0,0), 0);
		btnExit.setFoucesdColor(new Color(232,37,51,255));
		btnExit.setBounds(375, 0, 25, 25);
		loginPanel.add(btnExit);

		btnMin=new MyButton("_");
		btnMin.setToolTipText("最小化");
		btnMin.setTextFont(new Font("幼圆", Font.PLAIN, 20));
		btnMin.setNormalColor(Color.white, 8, 13, new Color(0,0,0,0), 0);
		btnMin.setFoucesdColor(new Color(9, 163, 200, 200));
		btnMin.setBounds(350, 0, 25, 25);
		loginPanel.add(btnMin);

		JPanel bottomPanel=new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(new Color(200, 200, 255, 255));
				g.fillRect(0, 0, 400, 150);
			}
		};
		bottomPanel.setBounds(0, 150, 400, 150);
		bottomPanel.setLayout(null);
		loginPanel.add(bottomPanel);

		userPhotoPanel=new MyPanel();
		userPhotoPanel.setOpaque(true);
		userPhotoPanel.setBounds(40, 15, 80, 80);
		userPhotoPanel.setImage(PictureRes.USER_PHOTO);
		bottomPanel.add(userPhotoPanel);

		textFieldUser=new JTextField();
		textFieldUser.setBounds(130, 15, 150, 30);
		textFieldUser.setFont(new Font("宋楷", Font.PLAIN, 15));
		textFieldUser.setBorder(BorderFactory.createLineBorder(Color.gray));
		bottomPanel.add(textFieldUser);

		passwordField=new JPasswordField();
		passwordField.setBounds(130, 44, 150, 30);
		passwordField.setBorder(BorderFactory.createLineBorder(Color.gray));
		bottomPanel.add(passwordField);

		chckbxRememberPwd=new JCheckBox("记住密码");
		chckbxRememberPwd.setBounds(126, 75, 75, 20);
		chckbxRememberPwd.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxRememberPwd.setForeground(new Color(101, 101, 101));
		chckbxRememberPwd.setOpaque(false);
		chckbxRememberPwd.setFocusable(false);
		bottomPanel.add(chckbxRememberPwd);

		chckbxAutoOnline=new JCheckBox("自动登录");
		chckbxAutoOnline.setBounds(212, 75, 75, 20);
		chckbxAutoOnline.setFont(new Font("宋体", Font.PLAIN, 12));
		chckbxAutoOnline.setForeground(new Color(101, 101, 101));
		chckbxAutoOnline.setOpaque(false);
		chckbxAutoOnline.setFocusable(false);
		bottomPanel.add(chckbxAutoOnline);

		btnLogin=new MyButton("登 录");
		btnLogin.setBounds(130, 105, 150, 30);
		btnLogin.setTextFont(new Font("宋体", Font.PLAIN, 15));
		btnLogin.setNormalColor(new Color(255, 255, 255, 255), 55, 19, new Color(9, 163, 200, 255), 10);
		btnLogin.setFoucesdColor(new Color(9, 163, 200, 150));
		bottomPanel.add(btnLogin);

		labelRegister=new JLabel("注册账号");
		labelRegister.setBounds(290, 13, 60, 30);
		labelRegister.setFont(new Font("宋体", Font.PLAIN, 13));
		labelRegister.setForeground(new Color(20, 100, 255, 255));
		bottomPanel.add(labelRegister);

		labelRetrievePwd=new JLabel("找回密码");
		labelRetrievePwd.setBounds(290, 42, 60, 30);
		labelRetrievePwd.setFont(new Font("宋体", Font.PLAIN, 13));
		labelRetrievePwd.setForeground(new Color(20, 100, 255, 255));
		bottomPanel.add(labelRetrievePwd);
	}
	private void initTrayIcon()throws AWTException{
		if (SystemTray.isSupported()) {
			systemTray=SystemTray.getSystemTray();
			PopupMenu popupMenu=new PopupMenu();
			showItem=new MenuItem("打开登录面板");
			exitItem=new MenuItem("退出");
			popupMenu.add(showItem);
			popupMenu.add(exitItem);
			
			trayImage=new ImageIcon(PictureRes.OFF_LINE);
			trayIcon=new TrayIcon(trayImage.getImage(), "打开QQ登录",popupMenu);
			trayIcon.setImageAutoSize(true);
//			try {
				systemTray.add(trayIcon);
//			} catch (AWTException e) {
//			}	
		}
	}
}

package www.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.alee.laf.tabbedpane.TabStretchType;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;

import www.util.Constants;
import www.util.PictureRes;

@SuppressWarnings("serial")
public class MainDialog extends JDialog {

	public JList friendList;
	public MenuItem showItem;
	public JPanel titlePanel;
	public MyButton btnMin;
	public MyButton btnExit;
	public JLabel btnSkin;
	public JPanel mainPanel;
	public JPanel functionPanel;
	public JPanel groupPanel;
	public JPanel recentPanel;
	public MenuItem exitItem;
	public ImageIcon trayImage;
	public SystemTray systemTray;
	public TrayIcon trayIcon=null;
	public MyPanel userPhotoPanel;
	public DefaultListModel mListModel;
	private static MainDialog mainDialog;
	public WebTabbedPane typeInfo;
	public JLabel lblUserName;
	public static MainDialog getMain(){
		if (mainDialog==null) {
			mainDialog=new MainDialog();
		}
		return mainDialog;
	}
	private MainDialog(){
		initUI();
		initTrayIcon();
	}
	private void initUI(){
		setUndecorated(true);
		setVisible(false);
		setAlwaysOnTop(true);
		setSize(280, 528);
		setMinimumSize(new Dimension(280,528));
		setLocationRelativeTo(null);

		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		setContentPane(mainPanel);

		titlePanel=new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				Image image=Constants.SKIN;
				g.drawImage(image, 0, 0, this);
			}
		};
		titlePanel.setLayout(null);
		titlePanel.setBounds(0, 0, 280, 140);
		mainPanel.add(titlePanel);
		// 类型面板（好友、联系人、会话）
		mListModel=new DefaultListModel();
		friendList=new JList(mListModel);
		typeInfo = new WebTabbedPane();
		typeInfo.setBounds(0, 140, 280, 328);
		typeInfo.setOpaque(false);
		typeInfo.setTabbedPaneStyle(TabbedPaneStyle.attached);//不高亮边框
		typeInfo.setTabStretchType(TabStretchType.always);//适应宽度
		typeInfo.setTopBg(new Color(240, 240, 240, 60));
		typeInfo.setBottomBg(new Color(255, 255, 255, 160));
		typeInfo.setSelectedTopBg(new Color(240, 240, 255, 50));
		typeInfo.setSelectedBottomBg(new Color(240, 240, 255, 50));
		typeInfo.setBackground(new Color(255, 255, 255, 200));

		ImageIcon friendIco=new ImageIcon(PictureRes.FRIEND);
		friendIco.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		typeInfo.addTab(null, friendIco, friendList);
		groupPanel=new JPanel();
		ImageIcon groupIco=new ImageIcon(PictureRes.GROUP);
		groupIco.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		typeInfo.addTab(null, groupIco, groupPanel);
		recentPanel=new JPanel();
		ImageIcon recentIco=new ImageIcon(PictureRes.RECENT);
		recentIco.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		typeInfo.addTab(null, recentIco, recentPanel);
		mainPanel.add(typeInfo);
		//底部功能面板
		functionPanel=new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(new Color(243, 230, 217, 255));
				g.fillRect(0, 0, getWidth(), 100);
			}
		};
		functionPanel.setBounds(0, 468, 280, 60);
		mainPanel.add(functionPanel);

		btnExit=new MyButton("×");
		btnExit.setToolTipText("关闭");
		btnExit.setTextFont(new Font("幼圆", Font.BOLD, 20));
		btnExit.setNormalColor(Color.white, 1, 17, new Color(0,0,0,0), 0);
		btnExit.setFoucesdColor(new Color(232,37,51,255));
		btnExit.setBounds(getWidth()-25, 0, 25, 25);
		titlePanel.add(btnExit);
		btnMin=new MyButton("_");
		btnMin.setToolTipText("最小化");
		btnMin.setTextFont(new Font("幼圆", Font.BOLD, 20));
		btnMin.setNormalColor(Color.white, 7, 13, new Color(0,0,0,0), 0);
		btnMin.setFoucesdColor(new Color(9, 163, 200, 200));
		btnMin.setBounds(getWidth()-50, 0, 25, 25);
		titlePanel.add(btnMin);
		btnSkin=new JLabel(new ImageIcon(PictureRes.SKIN_BTN));
		btnSkin.setBounds(getWidth()-75, 0, 25, 25);
		btnSkin.setToolTipText("更换外观");
		titlePanel.add(btnSkin);

		JLabel titleLable=new JLabel("QQ");
		titleLable.setFont(new Font("幼圆", Font.BOLD, 17));
		titleLable.setForeground(new Color(255, 255, 255, 220));
		titleLable.setBounds(2, 0, 100, 25);
		titleLable.setIcon(new ImageIcon(PictureRes.TITLE_ICO));
		titlePanel.add(titleLable);

		userPhotoPanel=new MyPanel();
		userPhotoPanel.setOpaque(true);
		userPhotoPanel.setBounds(10, 35, 60, 60);
		userPhotoPanel.setImage(PictureRes.USER_PHOTO);
		titlePanel.add(userPhotoPanel);

		lblUserName = new JLabel("微软雅黑");
		lblUserName.setForeground(Constants.FONT_COLOR);
		lblUserName.setFont(Constants.FONT);
		lblUserName.setBounds(80, 35, 100, 25);
		titlePanel.add(lblUserName);
	}
	public void setUserNameFont(){
		lblUserName.setForeground(Constants.FONT_COLOR);
		lblUserName.setFont(Constants.FONT);
	}
	private void initTrayIcon(){
		if (SystemTray.isSupported()) {
			systemTray=SystemTray.getSystemTray();
			PopupMenu popupMenu=new PopupMenu();
			showItem=new MenuItem("打开主面板");
			exitItem=new MenuItem("退出");
			popupMenu.add(showItem);
			popupMenu.add(exitItem);
			trayImage=new ImageIcon(PictureRes.ON_LINE);
			trayIcon=new TrayIcon(trayImage.getImage(), "打开QQ登录",popupMenu);
			trayIcon.setImageAutoSize(true);
		}
	}
}


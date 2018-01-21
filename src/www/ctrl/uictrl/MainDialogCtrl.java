package www.ctrl.uictrl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;

import www.ui.ChatFrame;
import www.ui.MainDialog;
import www.ui.SkinDialog;
import www.util.SkinUtil;

public class MainDialogCtrl {
	private int tmx=0;
	private int tmy=0;				
	private int tdx=0;				
	private int tdy=0;
	private int mouseDerection=0;
	private boolean canMove=false;
	private MainDialog mainDialog=MainDialog.getMain();
	public MainDialogCtrl(){
		listenerCtrl();
	}
	private void listenerCtrl(){
		//���λ�ڱ߿��Ǹı������ʽ�������촰��
		mainDialog.mainPanel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(e.getPoint().getY()==0&&e.getPoint().getX()<mainDialog.getWidth()-5){//����ڴ�������Ϸ���e.getPoint().getY()������ڴ��������
					mainDialog.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					mouseDerection=1;
				}else if(Math.abs(e.getPoint().getY()- mainDialog.getSize().getHeight())==1){//����ڴ�������·���
					mainDialog.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
					mouseDerection=2;
				}else if(e.getPoint().getX()==0&&e.getPoint().getY()<mainDialog.getHeight()-5){//����ڴ�������󷽡�
					mainDialog.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
					mouseDerection=3;
				}else if(Math.abs(e.getPoint().getX()- mainDialog.getSize().getWidth())==1){//����ڴ�������ҷ���
					mainDialog.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
					mouseDerection=4;
				}else if (e.getPoint().getX()>mainDialog.getWidth()-5&&e.getPoint().getY()>mainDialog.getHeight()-5) {
					mainDialog.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
					mouseDerection=5;
				}else{	//����ڴ��������λ��
					mouseDerection=0;
					mainDialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				Dimension dimension = mainDialog.getSize();
				switch (mouseDerection) {
				case 1:	//����ڴ������Ϸ����촰�塣
					dimension.setSize(dimension.getWidth() ,dimension.getHeight()-e.getY());
					if (dimension.getHeight()>700) {
						mainDialog.setResizable(false);
						dimension.setSize(dimension.getWidth() ,700);
					}else if(dimension.getHeight()>549&&dimension.getHeight()<701){
						mainDialog.setResizable(true);
						mainDialog.setLocation(mainDialog.getLocationOnScreen().x, mainDialog.getLocationOnScreen().y + e.getY());
					}
					mainDialog.setSize(dimension);
					break;
				case 2://����ڴ������·����촰�塣
					dimension.setSize(dimension.getWidth() ,e.getY());
					if (dimension.getHeight()>700) {
						mainDialog.setResizable(false);
						dimension.setSize(dimension.getWidth() ,700);
					}else if(dimension.getHeight()>549&&dimension.getHeight()<701){
						mainDialog.setResizable(true);
					}
					mainDialog.setSize(dimension);
					break;
				case 3://����ڴ����������촰�塣
					dimension.setSize(dimension.getWidth()-e.getX() ,dimension.getHeight());
					if (dimension.getWidth()>600) {
						mainDialog.setResizable(false);
						dimension.setSize(600 ,dimension.getHeight());
					}else if(dimension.getWidth()>279&&dimension.getWidth()<601){
						mainDialog.setResizable(true);
						mainDialog.setLocation(mainDialog.getLocationOnScreen().x + e.getX(),mainDialog.getLocationOnScreen().y );
					}
					mainDialog.setSize(dimension);
					break;
				case 4://����ڴ������ҷ����촰�塣
					dimension.setSize(e.getX(),dimension.getHeight());
					if (dimension.getWidth()>600) {
						mainDialog.setResizable(false);
						dimension.setSize(600, dimension.getHeight());
					}
					mainDialog.setSize(dimension);
					break;
				case 5://����ڴ������½����촰�塣
					dimension.setSize(e.getX(),e.getY());
					if (dimension.getWidth()>600&&dimension.getHeight()>700) {
						mainDialog.setResizable(false);
						dimension.setSize(600, 700);
					}else if(dimension.getWidth()>600){
						mainDialog.setResizable(false);
						dimension.setSize(600, e.getY());
					}else if(dimension.getHeight()>700){
						mainDialog.setResizable(false);
						dimension.setSize(e.getX(), 700);
					}
					mainDialog.setSize(dimension);
					break;
				default:
					break;
				}
				mainDialog.btnExit.setLocation(mainDialog.getWidth()-25, 0);
				mainDialog.btnMin.setLocation(mainDialog.getWidth()-50, 0);
				mainDialog.btnSkin.setLocation(mainDialog.getWidth()-75, 0);
				mainDialog.titlePanel.setSize(mainDialog.getWidth(), 140);
				mainDialog.typeInfo.setSize(mainDialog.getWidth(), mainDialog.getHeight()-200);
				mainDialog.functionPanel.setBounds(0,mainDialog.getHeight()-60,mainDialog.getWidth(), 60);
			}
		});
		mainDialog.titlePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (canMove) {
					mainDialog.setLocation(tdx+e.getXOnScreen()-tmx, tdy+e.getYOnScreen()-tmy);
				}
			}
		});
		mainDialog.titlePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tmx=e.getXOnScreen();
				tmy=e.getYOnScreen();
				tdx=mainDialog.getX();
				tdy=mainDialog.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				canMove=false;
				if (mainDialog.getY()<0&&mainDialog.getX()>0) {
					mainDialog.setLocation(tdx+e.getXOnScreen()-tmx, 0);
				}else if (mainDialog.getY()>0&&mainDialog.getX()<0) {
					mainDialog.setLocation(0, tdy+e.getYOnScreen()-tmy);
				}else if (mainDialog.getY()<0&&mainDialog.getX()<0) {
					mainDialog.setLocation(0, 0);
				}else {
					canMove=true;
				}
			}
		});
		//�������رհ�ť�¼�����
		mainDialog.btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainDialog.systemTray.remove(mainDialog.trayIcon);
				System.exit(0);
			}
		});
		//���������С����ť�¼�����
		mainDialog.btnMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainDialog.setVisible(false);
			}
		});
		//Ƥ��������ť
		mainDialog.btnSkin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SkinDialogCtrl.getSDC();
				SkinDialog.getSD().setVisible(true);
				SkinDialog.getSD().panelSkin1.setImage(SkinUtil.getImage("skin/skin1.skin"));
				SkinDialog.getSD().panelSkin2.setImage(SkinUtil.getImage("skin/skin2.skin"));
				SkinDialog.getSD().panelSkin3.setImage(SkinUtil.getImage("skin/skin3.skin"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				mainDialog.btnSkin.setBorder(BorderFactory.createLineBorder(new Color(86, 92, 58, 100)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mainDialog.btnSkin.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
			}
		});
		//ϵͳ����˫���򿪵������
		mainDialog.trayIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					mainDialog.setVisible(true);
				}
			}
		});
		//ϵͳ�����һ��򿪵������
		mainDialog.showItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainDialog.setVisible(true);
			}
		});
		//ϵͳ�����һ��˳�
		mainDialog.exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainDialog.systemTray.remove(mainDialog.trayIcon);
				System.exit(0);
			}
		});
		mainDialog.userPhotoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mainDialog.userPhotoPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mainDialog.userPhotoPanel.setBorder(BorderFactory.createCompoundBorder());
			}
		});
		mainDialog.friendList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					if (!mainDialog.friendList.isSelectionEmpty()) {
						ChatFrameCtrl.getCFC();
						ChatFrame.getCF().setTitle((String)mainDialog.friendList.getSelectedValue());
					}
				}
			}
		});
	}
}

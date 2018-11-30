package www.ctrl.uictrl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JOptionPane;

import www.socket.Client;
import www.ui.RegistDialog;

public class RegistDialogCtrl {
	private int mx=0;
	private int my=0;				
	private int jdx=0;				
	private int jdy=0;
	private boolean canMove=false;
	private static RegistDialogCtrl rDialogCtrl;
	private RegistDialog registDialog=RegistDialog.getRet();
	
	public static RegistDialogCtrl getRDC(){
		if (rDialogCtrl==null) {
			rDialogCtrl=new RegistDialogCtrl();
		}
		return rDialogCtrl;
	}
	private RegistDialogCtrl() {
		listenerCtrl();
	}
	private void listenerCtrl(){
		//==========注册窗体标题栏已被隐藏，添加容器的移动方法=========
		registDialog.mainPanel.addMouseMotionListener(new MouseMotionAdapter() {//添加鼠标运动侦听器，便于移动窗体
			@Override
			public void mouseDragged(MouseEvent e) {
				if (canMove) {
					registDialog.setLocation(jdx+e.getXOnScreen()-mx, jdy+e.getYOnScreen()-my);
				}
			}
		});
		//==========获取容器的鼠标坐标==========
		registDialog.mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx=e.getXOnScreen();
				my=e.getYOnScreen();
				jdx=registDialog.getX();
				jdy=registDialog.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				canMove=false;
				if (registDialog.getY()<0&&registDialog.getX()>0) {
					registDialog.setLocation(jdx+e.getXOnScreen()-mx, 0);
				}else if (registDialog.getY()>0&&registDialog.getX()<0) {
					registDialog.setLocation(0, jdy+e.getYOnScreen()-my);
				}else if (registDialog.getY()<0&&registDialog.getX()<0) {
					registDialog.setLocation(0, 0);
				}else {
					canMove=true;
				}
			}
		});
		registDialog.btnRegist.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isRight()) {
					registDialog.setVisible(false);
					String sex="男";
					if (registDialog.rdbtnMan.isSelected()) {
						sex="男";
					}else {
						sex="女";
					}
					Client.getCL().register(registDialog.textName.getText(), registDialog.textSurePwd.getText(), 
								registDialog.textAge.getText(), sex, registDialog.textBirthday.getText());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					if (Client.getCL().getRegistId().equals("")) {
						JOptionPane.showConfirmDialog(registDialog, "注册失败！", "提示", JOptionPane.CLOSED_OPTION);
					}else {
						JOptionPane.showConfirmDialog(registDialog, "注册成功！你的QQ账号是："+Client.getCL().getRegistId()+"，要牢记哦！", "提示", JOptionPane.CLOSED_OPTION);
						Client.getCL().setRegistId("");
					}
				}else {
					JOptionPane.showConfirmDialog(registDialog, "注册信息有误！", "提示", JOptionPane.CLOSED_OPTION);
				}
			}
		});
		registDialog.btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registDialog.setVisible(false);
			}
		});
		//昵称输入限制
		registDialog.textName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if ((registDialog.textName.getText().length()<=20)) {
				} else {
				e.consume(); 
				}
			}
		});
		//密码输入限制
		registDialog.textPwd.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				if ((registDialog.textPwd.getText().length()<=16)) {
				} else {
					e.consume(); 
				}
			}
		});
		registDialog.textSurePwd.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				if ((registDialog.textSurePwd.getText().length()<=16)) {
				} else {
					e.consume(); 
				}
			}
		});
		//年龄输入限制
		registDialog.textAge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//设置定时只能输入1~999分钟
				int keyChar=e.getKeyChar();
				if ((keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9)&&(registDialog.textAge.getText().length()<2)) {
				} else {
				e.consume(); 
				}
			}
		});
		//生日输入限制
		registDialog.textBirthday.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if ((registDialog.textBirthday.getText().length()<=10)) {
				} else {
					e.consume(); 
				}
			}
		});
	}
	@SuppressWarnings("deprecation")
	private boolean isRight(){
		boolean isRight=false;
		if (!registDialog.textName.getText().equals("")) {
			if (registDialog.textPwd.getText().length()>=6) {
				if (registDialog.textSurePwd.getText().length()>=6) {
					if (registDialog.textSurePwd.getText().equals(registDialog.textPwd.getText())) {
						if (Integer.parseInt(registDialog.textAge.getText())>0) {
							if (registDialog.textBirthday.getText().length()==10) {
								isRight=true;
							}
						}
					}
				}
			}
		}
		return isRight;
	}
}

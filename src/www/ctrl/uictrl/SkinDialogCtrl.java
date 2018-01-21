package www.ctrl.uictrl;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import www.ui.MainDialog;
import www.ui.SkinDialog;
import www.util.Constants;
import www.util.SkinUtil;

public class SkinDialogCtrl {
	private int mx=0;
	private int my=0;				
	private int jdx=0;				
	private int jdy=0;
	private boolean canMove=false;
	private static SkinDialogCtrl skinDialogCtrl;
	private SkinDialog skinDialog=SkinDialog.getSD();
	
	public static SkinDialogCtrl getSDC(){
		if (skinDialogCtrl==null) {
			skinDialogCtrl=new SkinDialogCtrl();
		}
		return skinDialogCtrl;
	}
	
	private SkinDialogCtrl() {
		listenerCtrl();
	}
	private void listenerCtrl(){
		skinDialog.panelSkin1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				skinDialog.panelSkin1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255, 200)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				skinDialog.panelSkin1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					Constants.SKIN=SkinUtil.getImage("skin/skin1.skin");
					Constants.FONT=SkinUtil.getFont("skin/skin1.skin");
					Constants.FONT_COLOR=SkinUtil.getFontColor("skin/skin1.skin");
				}
				MainDialog.getMain().titlePanel.repaint();
				MainDialog.getMain().setUserNameFont();
			}
		});
		skinDialog.panelSkin2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				skinDialog.panelSkin2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255, 200)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				skinDialog.panelSkin2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					Constants.SKIN=SkinUtil.getImage("skin/skin2.skin");
					Constants.FONT=SkinUtil.getFont("skin/skin2.skin");
					Constants.FONT_COLOR=SkinUtil.getFontColor("skin/skin2.skin");
				}
				MainDialog.getMain().titlePanel.repaint();
				MainDialog.getMain().setUserNameFont();
			}
		});
		skinDialog.panelSkin3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				skinDialog.panelSkin3.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255, 200)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				skinDialog.panelSkin3.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					Constants.SKIN=SkinUtil.getImage("skin/skin3.skin");
					Constants.FONT=SkinUtil.getFont("skin/skin3.skin");
					Constants.FONT_COLOR=SkinUtil.getFontColor("skin/skin3.skin");
				}
				MainDialog.getMain().titlePanel.repaint();
				MainDialog.getMain().setUserNameFont();
			}
		});
		skinDialog.btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				skinDialog.setVisible(false);
			}
		});
		skinDialog.mainPanel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (canMove) {
					skinDialog.setLocation(jdx+e.getXOnScreen()-mx, jdy+e.getYOnScreen()-my);
				}
			}
		});
		skinDialog.mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx=e.getXOnScreen();
				my=e.getYOnScreen();
				jdx=skinDialog.getX();
				jdy=skinDialog.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				canMove=false;
				if (skinDialog.getY()<0&&skinDialog.getX()>0) {
					skinDialog.setLocation(jdx+e.getXOnScreen()-mx, 0);
				}else if (skinDialog.getY()>0&&skinDialog.getX()<0) {
					skinDialog.setLocation(0, jdy+e.getYOnScreen()-my);
				}else if (skinDialog.getY()<0&&skinDialog.getX()<0) {
					skinDialog.setLocation(0, 0);
				}else {
					canMove=true;
				}
			}
		});
	}
}

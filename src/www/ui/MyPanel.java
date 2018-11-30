package www.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	private int x=0;
	private int y=0;
	private int width;
	private int height;
	private Image panelImage;
	public MyPanel() {
		super();
		setOpaque(false); //���ò�͸��
	}
	//��ָ��·����ȡͼƬ
	public void setImage(String setImage) {
		panelImage = Toolkit.getDefaultToolkit().getImage(setImage); 
		repaint();
	}
	//��ָ��·����ȡͼƬ
	public void setImage(Image setImage) {
		panelImage = setImage;
		repaint();
	}
	public void setImageLocation(int x,int y){
		this.x=x;
		this.y=y;
		repaint();
	}
	public void setImageCenter(){
		x = (int) (((double) (this.getWidth() - width)) / 2.0);
		y = (int) (((double) (this.getHeight() - height)) / 2.0);
		repaint();
	}
	//�ж�����Ĵ�С����ͼƬ���
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (panelImage != null) {
			height = panelImage.getHeight(this);
			width = panelImage.getWidth(this);
			if (height != -1 && height > this.getHeight())
				height = this.getHeight();
			if (width != -1 && width > this.getWidth())
				width = this.getWidth();
		}
		g.drawImage(panelImage, x, y, width, height, this);
	}
}

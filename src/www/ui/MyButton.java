package www.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
	/**
	 * ��ť�ػ���
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class MyButton extends JButton {
	/** @see ��ť�ı���ʾ��x���*/
	private int X;
	/** @see ��ť�ı���ʾ��y���*/
	private int Y;
	/** @see ��ť�߽ǵĻ���*/
	private int radian;
	/** @see ��ť���ı�*/
	private String text;
	/** @see ���ð�ťδѡ��ʱ����ɫ��͸����*/
	private Color norColor;
	/** @see ���ð�ťѡ��ʱ����ɫ��͸����*/
	private Color fouColor;
	/** @see ���ð�ť�ı�������ɫ*/
	private Color fontColor;
	/** @see ���ð�ť�ı�����*/
	private Font textFont=new Font("��Բ", Font.PLAIN, 14);
	/** @see ��ť������¼�*/
	private String state = "normal";
	
	/**
	 * �޲ι���
	 */
	public MyButton() {
		setOpaque(false);
		setBorderPainted(false);
		addMouseListener(new MouseAdapter() {  
			public void mouseEntered(MouseEvent e) { //��ȡ���ѡ�е�״̬
				state = "focused";  
				repaint();  //�ú������ػ����
			}  
			public void mouseExited(MouseEvent e) {  //��ȡ���δѡ�е�״̬
				state = "normal";  
				repaint();  
			}  
		});
	}
	/**
	 * �вι���
	 */
	public MyButton(String text) {
		this.text=text;
		setOpaque(false);
		setBorderPainted(false);
		addMouseListener(new MouseAdapter() {  
			public void mouseEntered(MouseEvent e) { //��ȡ���ѡ�е�״̬
				state = "focused";  
				repaint();  //�ú������ػ����
			}  
			public void mouseExited(MouseEvent e) {  //��ȡ���δѡ�е�״̬
				state = "normal";  
				repaint();  
			}  
		});
	}
	/**
	 * ���ð�ť�ı�
	 * @param text ��ť�ı�
	 */
	public void setBtnText(String text) {
		this.text=text;
		this.repaint();
	}
	/**
	 * ��ȡ��ť���ı�
	 * @return ��ť���ı�
	 */
	public String getBtnText() {
		return text;
	}
	/**
	 * �����ı�����
	 * @param X �ı�X����
	 * @param Y �ı�Y����
	 */
	public void setBtnTextXY(int X,int Y){
		this.X=X;
		this.Y=Y;
		this.repaint();
	}
	/**
	 * ���ð�ť����
	 * @param radian ��ť����
	 */
	public void setRadian(int radian) {
		this.radian = radian;
		this.repaint();
	}
	/**
	 * ��ť����ʱ��״̬
	 * @param fontColor	�ı�������ɫ
	 * @param X	�ı�X����
	 * @param Y	�ı�Y����
	 * @param norColor ����״̬����ɫ
	 * @param radian ��ť����
	 */
	public void setNormalColor(Color fontColor,int X,int Y,Color norColor,int radian) {
		this.fontColor=fontColor;
		this.X=X;
		this.Y=Y;
		this.norColor=norColor;
		this.radian=radian;
		this.repaint();
	}
	/**
	 * ���ð�ťѡ�е���ʾ����ɫ
	 * @param fouColor ��ȡ�������ɫ
	 */
	public void setFoucesdColor(Color fouColor) {
		this.fouColor=fouColor;
		this.repaint();
	}
	/**
	 * ����������ɫ
	 * @param fontColor ������ɫ
	 */
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
		this.repaint();
	}
	/**
	 * �����ı�����
	 * @param textFont �ı�����
	 */
	public void setTextFont(Font textFont) {
		this.textFont = textFont;
		this.repaint();
	}
	/**
	 * ��ť�ػ�
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//�߿�ȥ���
		if (state=="normal") {	//��ȡ���ѡ��ʱ�Ļ���
			g2d.setColor(norColor);
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radian, radian);	
		}
		else if (state=="focused") {	//���δѡ���ǵĻ���
			g2d.setColor(fouColor);
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radian, radian);
		}
		g2d.setFont(textFont);//��������ʹ�С
		g2d.setColor(fontColor);//����������ɫ
		g2d.drawString(text, X, Y);//�����ı�����ʾ��λ��
	}
}

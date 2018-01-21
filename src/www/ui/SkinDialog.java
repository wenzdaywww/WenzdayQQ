package www.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SkinDialog extends JDialog {
	public JPanel mainPanel;
	public MyButton btnExit;
	private static SkinDialog skinDialog;
	public MyPanel panelSkin2;
	public MyPanel panelSkin3;
	public MyPanel panelSkin1;
	
	public static SkinDialog getSD(){
		if (skinDialog==null) {
			skinDialog=new SkinDialog();
		}
		return skinDialog;
	}
	/**
	 * Create the dialog.
	 */
	private SkinDialog() {
		setUndecorated(true);
		setVisible(true);
		setSize(300,150);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		mainPanel=new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(new Color(185, 153, 90));
				g.fillRect(0, 0, getWidth(), 25);
				g.setColor(new Color(255, 255, 255));
				g.fillRect(0, 25, getWidth(), getHeight()-25);
			}
		};
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(214, 214, 214, 255)));
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
		JLabel titleLabel=new JLabel("Ñ¡ÔñÆ¤·ô");
		titleLabel.setFont(new Font("Ó×Ô²", Font.PLAIN, 15));
		titleLabel.setForeground(Color.white);
		titleLabel.setBounds(5, 1, 100, 25);
		mainPanel.add(titleLabel);
		
		btnExit=new MyButton("¡Á");
		btnExit.setToolTipText("¹Ø±Õ");
		btnExit.setTextFont(new Font("Ó×Ô²", Font.BOLD, 20));
		btnExit.setNormalColor(Color.white, 2, 18, new Color(0,0,0,0), 0);
		btnExit.setFoucesdColor(new Color(232,37,51,255));
		btnExit.setBounds(getWidth()-25, 0, 25, 25);
		mainPanel.add(btnExit);
		
		panelSkin1 = new MyPanel();
		panelSkin1.setBounds(10, 48, 80, 80);
		mainPanel.add(panelSkin1);
		
		panelSkin2 = new MyPanel();
		panelSkin2.setBounds(110, 48, 80, 80);
		mainPanel.add(panelSkin2);
		
		panelSkin3 = new MyPanel();
		panelSkin3.setBounds(210, 48, 80, 80);
		mainPanel.add(panelSkin3);
	}
}

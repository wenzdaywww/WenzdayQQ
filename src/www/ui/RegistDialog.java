package www.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import www.util.PictureRes;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class RegistDialog extends JDialog {
	
	public MyPanel mainPanel;
	public JTextField textName;
	public JTextField textAge;
	public MyButton btnCancel;
	public MyButton btnRegist;
	public JTextField textBirthday;
	private static RegistDialog registerDialog;
	private JLabel lblSurePwd;
	public JRadioButton rdbtnMan;
	public JRadioButton rdbtnWomen;
	public JPasswordField textPwd;
	public JPasswordField textSurePwd;
	
	public static RegistDialog getRet(){
		if (registerDialog==null) {
			registerDialog=new RegistDialog();
		}
		return registerDialog;
	}
	
	private RegistDialog(){
		setUndecorated(true);
		setVisible(true);
		setSize(350, 350);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		mainPanel=new MyPanel();
		mainPanel.setImage(PictureRes.REGISTER_BACKGROUP);
		setContentPane(mainPanel);
		
		JLabel lblTitle = new JLabel("×¢²áÕËºÅ");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Ó×Ô²", Font.BOLD, 25));
		lblTitle.setBounds(125, 5, 104, 50);
		
		JLabel lblName = new JLabel("êÇ    ³Æ£º");
		lblName.setForeground(Color.BLUE);
		lblName.setFont(new Font("Ó×Ô²", Font.PLAIN, 13));
		lblName.setBounds(40, 65, 80, 15);
		
		textName = new JTextField();
		textName.setText("ÎâÎ°ÎÄ");
		textName.setFont(new Font("Ó×Ô²", Font.PLAIN, 16));
		textName.setForeground(Color.BLUE);
		textName.setOpaque(false);
		textName.setBounds(111, 60, 173, 21);
		textName.setColumns(10);
		
		JLabel lblPwd = new JLabel("ÃÜ    Âë£º");
		lblPwd.setForeground(Color.BLUE);
		lblPwd.setFont(new Font("Ó×Ô²", Font.PLAIN, 13));
		lblPwd.setBounds(40, 103, 80, 15);
		
		JLabel lblSex = new JLabel("ÐÔ    ±ð£º");
		lblSex.setFont(new Font("Ó×Ô²", Font.PLAIN, 13));
		lblSex.setForeground(Color.BLUE);
		lblSex.setBounds(40, 183, 80, 15);
		
		JLabel lblAge = new JLabel("Äê    Áä£º");
		lblAge.setFont(new Font("Ó×Ô²", Font.PLAIN, 13));
		lblAge.setForeground(Color.BLUE);
		lblAge.setBounds(40, 223, 80, 15);
		
		textAge = new JTextField();
		textAge.setText("23");
		textAge.setFont(new Font("Ó×Ô²", Font.PLAIN, 16));
		textAge.setForeground(Color.BLUE);
		textAge.setOpaque(false);
		textAge.setBounds(111, 220, 173, 21);
		textAge.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Éú    ÈÕ£º");
		lblBirthday.setFont(new Font("Ó×Ô²", Font.PLAIN, 13));
		lblBirthday.setForeground(Color.BLUE);
		lblBirthday.setBounds(40, 263, 80, 15);
		
		textBirthday = new JTextField();
		textBirthday.setText("2016-04-27");
		textBirthday.setFont(new Font("Ó×Ô²", Font.PLAIN, 16));
		textBirthday.setForeground(Color.BLUE);
		textBirthday.setOpaque(false);
		textBirthday.setBounds(111, 260, 173, 21);
		
		btnRegist = new MyButton("×¢²á");
		btnRegist.setTextFont(new Font("ËÎÌå", Font.PLAIN, 15));
		btnRegist.setNormalColor(new Color(255, 255, 255, 255), 33, 17, new Color(9, 163, 200, 255), 10);
		btnRegist.setFoucesdColor(new Color(9, 163, 200, 150));
		btnRegist.setBounds(30, 315, 93, 23);
		
		btnCancel = new MyButton("È¡Ïû");
		btnCancel.setTextFont(new Font("ËÎÌå", Font.PLAIN, 15));
		btnCancel.setNormalColor(new Color(255, 255, 255, 255), 33, 17, new Color(9, 163, 200, 255), 10);
		btnCancel.setFoucesdColor(new Color(9, 163, 200, 150));
		btnCancel.setBounds(213, 315, 93, 23);
		
		mainPanel.setLayout(null);
		mainPanel.add(lblSex);
		mainPanel.add(lblAge);
		mainPanel.add(lblBirthday);
		mainPanel.add(lblName);
		mainPanel.add(lblPwd);
		mainPanel.add(textName);
		mainPanel.add(textBirthday);
		mainPanel.add(textAge);
		mainPanel.add(lblTitle);
		mainPanel.add(btnRegist);
		mainPanel.add(btnCancel);
		
		lblSurePwd = new JLabel("È·ÈÏÃÜÂë£º");
		lblSurePwd.setForeground(Color.BLUE);
		lblSurePwd.setFont(new Font("Ó×Ô²", Font.PLAIN, 13));
		lblSurePwd.setBounds(40, 144, 65, 15);
		mainPanel.add(lblSurePwd);
		
		ButtonGroup bGroup=new ButtonGroup();
		
		rdbtnMan = new JRadioButton("ÄÐ");
		rdbtnMan.setForeground(Color.BLUE);
		rdbtnMan.setFont(new Font("Ó×Ô²", Font.PLAIN, 13));
		rdbtnMan.setFocusable(false);
		rdbtnMan.setSelected(true);
		rdbtnMan.setOpaque(false);
		rdbtnMan.setBounds(110, 179, 65, 23);
		mainPanel.add(rdbtnMan);
		
		rdbtnWomen = new JRadioButton("Å®");
		rdbtnWomen.setForeground(Color.BLUE);
		rdbtnWomen.setFont(new Font("Ó×Ô²", Font.PLAIN, 13));
		rdbtnWomen.setFocusable(false);
		rdbtnWomen.setOpaque(false);
		rdbtnWomen.setBounds(219, 179, 65, 23);
		mainPanel.add(rdbtnWomen);
		
		bGroup.add(rdbtnMan);
		bGroup.add(rdbtnWomen);
		
		textPwd = new JPasswordField();
		textPwd.setOpaque(false);
		textPwd.setBounds(111, 100, 173, 21);
		mainPanel.add(textPwd);
		
		textSurePwd = new JPasswordField();
		textSurePwd.setOpaque(false);
		textSurePwd.setBounds(111, 140, 173, 21);
		mainPanel.add(textSurePwd);
	}
}

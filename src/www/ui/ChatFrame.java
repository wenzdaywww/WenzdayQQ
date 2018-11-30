package www.ui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import www.util.Constants;
import www.util.PictureRes;

import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class ChatFrame extends JFrame {
	private JTextArea txtShow;
	private JButton btnSend;
	private JPanel contentPane;
	private JTextArea textSend;
	private static ChatFrame chatFrame;
	public static ChatFrame getCF(){
		if (chatFrame==null) {
			chatFrame=new ChatFrame();
		}
		return chatFrame;
	}

	public ChatFrame() {
		setAlwaysOnTop(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setTitle("�ͻ���");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PictureRes.USER_PHOTO));
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(500, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txtShow = new JTextArea();
		txtShow.setEditable(false);
		txtShow.setTabSize(13);
		txtShow.setFont(new Font("��Բ", Font.PLAIN, 13));
		txtShow.setColumns(10);
		JScrollPane scrollPane=new JScrollPane(txtShow);

		btnSend = new JButton("����");
		
		JScrollPane scrollPane1 = new JScrollPane((Component) null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(480, Short.MAX_VALUE)
					.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
				.addComponent(scrollPane1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSend))
		);
		
		textSend = new JTextArea();
		scrollPane1.setViewportView(textSend);
		contentPane.setLayout(gl_contentPane);
	}
	//�ı������
	public void appendText(String in) {
		txtShow.append(in+"\n");
		txtShow.setCaretPosition(txtShow.getText().length());//���û������Զ�����ײ�
	}

	public JTextArea getTxtShow() {
		return txtShow;
	}

	public JButton getBtnSend() {
		return btnSend;
	}

	public JTextArea getTextSend() {
		return textSend;
	}
}

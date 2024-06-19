package Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class SigninFrame extends JFrame{
	
	private JLabel frame;
	private JTextField idField;
	private JTextField pwField;
	private JTextField nameField;
	private JButton signIn;
	
	private JTextPane testIdField;
	private JTextPane testPwField;
	
	public SigninFrame() {
		initData();
		setInitLayout();
		addAction();
	}
		
	public void initData() {
		setTitle("Sign-In");
		frame = new JLabel(new ImageIcon("img/signinFrame.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(frame);
		setSize(1280, 900);
		
//		idField = new JTextField();
//		idField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//		idField.setBounds(500, 250, 220, 40);
		
		testIdField = new JTextPane();
		testIdField.insertIcon(new ImageIcon("img/textField.png"));
		testIdField.setBounds(480, 260, 300, 80);
		
		testPwField = new JTextPane();
		testPwField.insertIcon(new ImageIcon("img/textField.png"));
		testPwField.setBounds(480, 460, 300, 80);
		
		pwField = new JTextField();
		pwField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pwField.setBounds(500, 450, 220, 40);
		
		
		signIn = new JButton(new ImageIcon("img/signinButton190_68.png"));
		signIn.setBounds(520, 730, 220, 80);
		signIn.setBorderPainted(false);
		signIn.setBackground(new Color(255,255,255));
	}
	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(signIn);
//		add(idField);
//		add(pwField);
		add(testIdField);
		add(testPwField);
		
		setVisible(true);
	}
	
	public void addAction() {
		signIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		new SigninFrame();
	}
}

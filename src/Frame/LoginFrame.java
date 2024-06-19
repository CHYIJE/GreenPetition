package Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{

	private JLabel frame;
	private JTextField text_id;
	private JPasswordField text_pw;
	private JButton login_button;
	private JButton join_button;
	
	public LoginFrame() {
		initData();
		setInitLayout();
		addAction();
	}
	
	public void initData() {
		setTitle("Login_Frame");
		frame = new JLabel(new ImageIcon("img/loginFrame.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(frame);
		setSize(1280, 900);
		
		text_id = new JTextField();
		text_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		text_id.setBounds(500, 300, 260, 30);
		
		text_pw = new JPasswordField();
		text_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		text_pw.setBounds(500, 570, 260, 30);
		
		login_button = new JButton(new ImageIcon("img/loginButton.png"));
		login_button.setBounds(640, 740, 220 ,80);
		login_button.setBorderPainted(false);
		login_button.setBackground(new Color(255,255,255));
		
		join_button = new JButton(new ImageIcon("img/signinButton190_68.png"));
		join_button.setBounds(380, 740, 220, 80);
		join_button.setBorderPainted(false);
		join_button.setBackground(new Color(255,255,255));
	}
	
	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(text_id);
		add(text_pw);
		add(login_button);
		add(join_button);
		
		setVisible(true);
	}
	
	public void addAction() {
		join_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입 창으로 이동합니다.");
				new SigninFrame();
			}
		});
		login_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text_id.getText().equals("")) {
					System.out.println("입력되지 않음");
				}
				System.out.println(text_id.getText());
				new MainFrame();
				
			}
		});
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
	
}

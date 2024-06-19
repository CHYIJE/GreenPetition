package Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	private JLabel frame;
	private JTextField text_id;
	private JPasswordField text_pw;
	private JButton login_button;
	private JButton join_button;
	
	public LoginFrame() {

		JPanel panel = new JPanel();
		JLabel label_id = new JLabel("ID : ");
		JLabel label_password = new JLabel("Password : ");
		JTextField text_id = new JTextField(10);
		JPasswordField text_password = new JPasswordField(10);
		JButton login_button = new JButton("로그인");
		JButton join_button = new JButton("회원가입");

		panel.add(label_id);
		panel.add(text_id);
		panel.add(label_password);
		panel.add(text_password);
		panel.add(login_button);
		panel.add(join_button);

		login_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = "아이디";
				String password = "비밀번호";

				if (id.equals(text_id.getText()) && password.equals(text_password.getText())) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}

			}
		});

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

				
				// 충돌 시험
				JOptionPane.showMessageDialog(null, "회원가입 창으로 이동합니다.");
				new SigninFrame();
			}
		});
		login_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainFrame();
				
			}
		});
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
}

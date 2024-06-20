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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ver1.ObjectDAO.LoginDAO;
import ver1.models.UserDTO;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class LoginFrame extends JFrame {

	LoginDAO dao;
	UserDTO dto;

	LoginFrame mcontext = this;

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
		login_button.setBounds(640, 740, 220, 80);
		login_button.setBorderPainted(false);
		login_button.setBackground(new Color(255, 255, 255));

		join_button = new JButton(new ImageIcon("img/signinButton190_68.png"));
		join_button.setBounds(380, 740, 220, 80);
		join_button.setBorderPainted(false);
		join_button.setBackground(new Color(255, 255, 255));
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

				// 수정중
//				if (text_id != null && text_pw != null) {
//					new MainFrame();
//				}
				

				if (text_id.getText().equals("")) {
					System.out.println("아이디 입력되지 않음");
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
					return;
				} else if(text_pw.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
					System.out.println("비밀번호 입력되지 않음");
					return;
				} else {
					System.out.println(text_id.getText());
					new MainFrame();	
					dispose();
				}
			}
		});

		login_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = "아이디";
				String password = "비밀번호";

				if (id.equals(text_id.getText()) && password.equals(text_pw.getText())) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}

			}
		});
	}

	public static void main(String[] args) {
		new LoginFrame();
	}
}

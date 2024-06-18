package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{

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
		
		join_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 충돌 시험
			}
		});

		this.add(panel);

		this.setVisible(true);
		this.setTitle("로그인");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new LoginFrame();
}
}

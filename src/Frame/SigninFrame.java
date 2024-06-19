package Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lombok.Getter;
import ver1.ObjectDAO.JoinDAO;
import ver1.models.UserDTO;

@Getter
public class SigninFrame extends JFrame{
	
	JoinDAO dao;
	UserDTO dto;
	
	SigninFrame mContext = this;
	
	private JLabel frame;
	private JTextField idField;
	private JTextField pwField;
	private JTextField nameField;
	private JButton signIn;

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
		
		mContext.idField = new JTextField();
		idField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		idField.setBounds(500, 270, 260, 30);
		
		mContext.pwField = new JTextField();
		pwField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pwField.setBounds(500, 460, 260, 30);
		
		mContext.nameField = new JTextField();
		nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nameField.setBounds(500, 640, 260, 30);
		
		signIn = new JButton(new ImageIcon("img/signinButton190_68.png"));
		signIn.setBounds(520, 770, 220, 80);
		signIn.setBorderPainted(false);
		signIn.setBackground(new Color(255,255,255));
		
	}
	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(idField);
		add(pwField);
		add(nameField);
		add(signIn);
		
		setVisible(true);
	}
	
	public void addAction() {
		signIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!mContext.idField.getText().equals("") && !mContext.pwField.getText().equals("") && !mContext.nameField.getText().equals("")) {
						try {
							dao = new JoinDAO(dto, mContext); 
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					
						
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
						dispose();
						
					
				} else {
					JOptionPane.showMessageDialog(null, "ID/PW/NAME 입력해주세요.");
				}
			}
			
		});
		
	}
	public static void main(String[] args) {
		new SigninFrame();
	}
	
}

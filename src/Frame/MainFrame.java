package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import ver1.DBConnectionManager;
import ver1.ObjectDAO.BoardDAO;

public class MainFrame extends JFrame{
	
	private static final String VIEW_ALL = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id ";
	private static final String VIEW_FACILITY = " select * from petition where category id = facility ";
	private static final String VIEW_TEACHER = " select * from petition where category id = teacher ";

	
	private JLabel frame;
	private JButton facilityButton;
	private JButton teacherButton;
	private JButton articleButton;
	private JTextArea body;
	
	private String checker1;
	private String checker2;
	private String checker3;
	
	private int log;

	
	public MainFrame() {
		initData();
		setInitLayout();
		addAction();
		body();
	}
	
	public void body() {
		body = new JTextArea();
		body.setBounds(250, 110, 1000, 700);
		body.setBackground(new Color(213,222,232));
		
		add(body);
		
		
		
		body.setText("");
		//ArrayList<BoardDAO> arr = new ArrayList<BoardDao>();
		Font bodyfont = new Font("D2CODING", Font.BOLD, 22); 
		body.setFont(bodyfont);
		
		body.append("\t"+"no"+"\t"+"\t"+"\t"+"\t"+"제목"+"\t"+"\t"+"\t"+"\t"+"\t"+"작성자"+"\t"+"\n");
		body.append("-----------------------------------------------------------------------------------------"+"\n");
		try (Connection conn = DBConnectionManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(VIEW_ALL)){
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				
				log = resultSet.getInt("id");
				checker1 = Integer.toString(log);
				checker2 = resultSet.getString("title");
				checker3 = resultSet.getString("acc_id");
				body.append("\t"+ checker1 + "\t"+"\t"+"\t"+ checker2 +"\t"+"\t"+"\t"+"\t"+ "\s" +checker3 + "\t"+ "\n");
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void initData() {
		setTitle("Main-Frame");
		frame = new JLabel(new ImageIcon("img/mainFrame.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(frame);
		setSize(1280, 900);
		
		
		
		facilityButton = new JButton(new ImageIcon("img/facilityText.png"));
		facilityButton.setBounds(35, 180, 125, 40);
		facilityButton.setBorderPainted(false);
		facilityButton.setBackground(new Color(255,255,255));
		
		teacherButton = new JButton(new ImageIcon("img/teacherText.png"));
		teacherButton.setBounds(34, 290, 125, 40);
		teacherButton.setBorderPainted(false);
		teacherButton.setBackground(new Color(255,255,255));
		
		articleButton = new JButton(new ImageIcon("img/writeArticleButton.png"));
		articleButton.setBounds(10, 50, 200, 100);
		articleButton.setBorderPainted(false);
		articleButton.setBackground(new Color(255,255,255));
	}
	
	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(facilityButton);
		add(teacherButton);
		add(articleButton);
		
		setVisible(true);
		
	}
	public void addAction() {
		facilityButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("시설 버튼");
				
			}
		});
		
		teacherButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("강사 버튼");
			}
		});
		articleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("글 작성 버튼");
			}
		});
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}

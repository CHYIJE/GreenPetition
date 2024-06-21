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
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Getter;

import ver1.DBConnectionManager;
import ver1.ObjectDAO.LoginDAO;
import ver1.models.PatitionDTO;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

	private static final String VIEW_ALL = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.id >= ? and petition.id <= ? order by petition.id ASC ";
	private static final String VIEW_SELECT = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.id > ? and petition.id <= ? order by petition.id ASC";
	private static final String VIEW_FACILITY = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.category = 'facility' ";
	private static final String VIEW_TEACHER = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.category = 'teacher' ";

	private JLabel frame;
	private JButton facilityButton;
	private JButton teacherButton;
	private JButton articleButton;

	AllArticle article;
	JScrollPane scroll;
	private JTable table;
	
	LoginFrame mContext;

	String getUserName;
	private JLabel check;

	private LoginDAO mcontext;

	public MainFrame(LoginDAO mcontext) {
		this.mcontext = mcontext;
		initData();
		setInitLayout();
		addAction();
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
		facilityButton.setBackground(new Color(255, 255, 255));

		teacherButton = new JButton(new ImageIcon("img/teacherText.png"));
		teacherButton.setBounds(34, 290, 125, 40);
		teacherButton.setBorderPainted(false);
		teacherButton.setBackground(new Color(255, 255, 255));

		articleButton = new JButton(new ImageIcon("img/writeArticleButton.png"));
		articleButton.setBounds(10, 50, 200, 100);
		articleButton.setBorderPainted(false);
		articleButton.setBackground(new Color(255, 255, 255));
		
		check = new JLabel();
		
		article = new AllArticle();
		table = article.insertData();
		scroll = new JScrollPane(table);
		scroll.setViewportView(table);
		scroll.setBounds(270, 150, 780, 600);
	}

	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		add(facilityButton);
		add(teacherButton);
		add(articleButton);
		getContentPane().add(scroll);

		add(check);
		getContentPane().add(check);
		check.setText(mcontext.getUserId() + " 님");
		
		Font bodyfont = new Font("D2CODING", Font.BOLD, 25);
		check.setFont(bodyfont);
		check.setBounds(1200, 40, 350, 100);
		setVisible(true);

	}
	
	public void addAction() {
		
		facilityButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 시설 카테고리의 글들 조회
				
			}
		});
		
		teacherButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 강사 카테고리의 글들 조회
				
			}
		});
		
		articleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("인식");
				new WriterFrame(mcontext);
				
			}
		});
	}

	
}
	
			

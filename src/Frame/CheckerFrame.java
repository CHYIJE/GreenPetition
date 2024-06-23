package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ver1.DBConnectionManager;
import ver1.ObjectDAO.CheckerDAO;
import ver1.ObjectDAO.CommentWriterDAO;
import ver1.models.CheckerDTO;
import ver1.models.Vote;

public class CheckerFrame extends JFrame {

	private static final String SELECT = "select u.id, p.id, p.title, p.content, p.date, p.agree, p.disagree, u.name from petition as p join user as u on p.user_id = u.id where p.id = ?";
//	private static final String UPVOTE = ""
//	private static final String DOWNVOTE =S

	CheckerDAO checkerDAO;
	CheckerDTO dto;
	CheckerFrame mContext = this;
	MainFrame main;
	Vote vote;

	private CommentWriterDAO commentDao;
	private JLabel frame;
	private JTextField title;
	private JTextField name;
	private JTextField date;
	private JTextField comment;
	private JTextArea content;
	private JButton back;
	private JButton w;
	private JButton l;

	
	private JTable replyTable;
	private Reply reply;
	private JScrollPane replyScroll;

	private JButton commentButton;
	private JPanel contentpane;
	private JScrollPane sp;
	private int userId;
	private int patitionId;
	private String comment1;
//	private JTable replyTable;
//	private Reply reply;
//	private JScrollPane replyScroll;


	public CheckerFrame(int id) {
		this.patitionId = id;
		getInfo();
		initData();
		setInitLayout();
		addAction();
	}

	public void initData() {

		setTitle("New Petition");
		frame = new JLabel(new ImageIcon("img/writepage.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(frame);
		setSize(1280, 900);

		mContext.title = new JTextField();
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		title.setBounds(80, 50, 1120, 25);
		title.setBackground(new Color(213, 222, 232));
		title.setEditable(false);
		title.setText(dto.getTitle());

		mContext.name = new JTextField();
		name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		name.setBounds(80, 75, 560, 25);
		name.setBackground(new Color(255, 255, 255));
		name.setEditable(false);
		name.setText(dto.getName());

		mContext.date = new JTextField();
		date.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		date.setBounds(640, 75, 560, 25);
		date.setBackground(new Color(255, 255, 255));
		date.setEditable(false);
		date.setText(dto.getDate());

		mContext.comment = new JTextField();
		comment.setBorder(BorderFactory.createEmptyBorder());
		comment.setBounds(80, 600, 1000, 25);
		comment.setBackground(new Color(213, 222, 232));

		mContext.content = new JTextArea();
		content.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		content.setBounds(80, 100, 1120, 550);
		content.setBackground(new Color(213, 222, 232));
		content.setEditable(false);
		content.setLineWrap(true);
		content.setText(dto.getContent());
		
		reply = new Reply();
		replyTable = reply.insertReply();
		
		replyScroll = new JScrollPane(replyTable);
		replyScroll.setViewportView(replyTable);
		replyScroll.setBounds(270, 800, 780, 100);
		
		replyTable.getTableHeader().setReorderingAllowed(false);
		replyTable.getTableHeader().setResizingAllowed(false);
		replyTable.setRowSelectionAllowed(false);
		replyTable.getColumn("id").setPreferredWidth(3);
		replyTable.getColumn("content").setPreferredWidth(780);
		replyTable.setShowVerticalLines(false);
		replyTable.setShowHorizontalLines(false);
		

		mContext.contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentpane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentpane);
		sp = new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(80, 100, 1120, 350);
		sp.setBackground(new Color(238, 238, 238));

		w = new JButton(new ImageIcon("img/facilityButton.png"));
		w.setBounds(80, 700, 220, 80);
		w.setBorderPainted(false);
		w.setBackground(new Color(238, 238, 238));

		l = new JButton(new ImageIcon("img/teacherButton.png"));
		l.setBounds(380, 700, 220, 80);
		l.setBorderPainted(false);
		l.setBackground(new Color(238, 238, 238));

//		reply = new Reply();
//		replyTable = reply.insertReply();
//
//		replyScroll = new JScrollPane(replyTable);
//		replyScroll.setViewportView(replyTable);
//		replyScroll.setBounds(270, 800, 780, 100);
//
//		replyTable.getTableHeader().setReorderingAllowed(false);
//		replyTable.getTableHeader().setResizingAllowed(false);
//		replyTable.setRowSelectionAllowed(false);
//		replyTable.getColumn("id").setPreferredWidth(3);
//		replyTable.getColumn("content").setPreferredWidth(780);
//		replyTable.setShowVerticalLines(false);
//		replyTable.setShowHorizontalLines(false);

		commentButton = new JButton();
		commentButton.setBounds(1080, 600, 120, 25);
		commentButton.setBorderPainted(false);
		commentButton.setBackground(new Color(190, 190, 190));
		commentButton.setText("댓글 달기");

		back = new JButton(new ImageIcon("img/writeArticleButton.png"));
		back.setBounds(980, 700, 220, 80);
		back.setBorderPainted(false);
		back.setBackground(new Color(238, 238, 238));

	}

	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		add(title);
		add(name);
		add(date);
		add(w);
		add(l);
		add(sp);
		add(comment);
		add(commentButton);
		add(back);

		setVisible(true);
	}

	public void addAction() {
		w.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 찬성수 올라가게
				vote.upvote(dto.getPetition_id(), dto.getUser_id(), dto.getAgree());

			}

		});
		l.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 반대수 올라가게
				vote.downvote(dto.getPetition_id(), dto.getUser_id(), dto.getDisagree());
			}

		});
		commentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comment1 = comment.getText();
				commentDao = new CommentWriterDAO(userId, patitionId, comment1);
			}

		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
	}

	private void getInfo() {
		try (Connection conn = DBConnectionManager.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT)) {
			pstmt.setInt(1, patitionId);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				dto = CheckerDTO.builder().user_id(resultSet.getInt("u.id")).petition_id(resultSet.getInt("p.id"))
						.name(resultSet.getString("name")).date(resultSet.getString("date"))
						.title(resultSet.getString("title")).content(resultSet.getString("content"))
						.agree(resultSet.getInt("agree")).disagree(resultSet.getInt("disagree")).build();
				vote = Vote.builder().build();
				userId = dto.getUser_id();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

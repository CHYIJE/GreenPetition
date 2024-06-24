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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lombok.Data;
import ver1.DBConnectionManager;
import ver1.ObjectDAO.CheckerDAO;
import ver1.ObjectDAO.CommentWriterDAO;
import ver1.ObjectDAO.LoginDAO;
import ver1.models.CheckerDTO;
import ver1.models.Vote;


public class CheckerFrame extends JFrame {

	private static final String SELECT = "select u.id, p.id, p.title, p.content, p.date, p.agree, p.disagree, u.name from petition as p join user as u on p.user_id = u.id where p.id = ?";

	CheckerDAO checkerDAO;
	CheckerDTO dto;
	CheckerFrame mContext = this;
	MainFrame main;
	Vote vote;
	LoginDAO login;

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
	private JButton fix;

	private JTable replyTable;
	private Reply reply;
	private JScrollPane replyScroll;

	private JButton commentButton;
	private JPanel contentpane;
	private JScrollPane sp;
	private JScrollPane rp;
	private int userId;
	private int petitionId;
	private String comment1;
//	private JTable replyTable;
//	private Reply reply;
//	private JScrollPane replyScroll;
	
	private String titleBar;
	private String contentBar;
	private int formID;
	
	

	public CheckerFrame(int id,LoginDAO login) {
		this.petitionId = id;
		this.login = login;
		formID = login.getUserId();
		getInfo();
		initData();
		setInitLayout();
		addAction();
	}

	public int getPetitionId() {
		return petitionId;
	}


	public static String getSelect() {
		return SELECT;
	}

	public CheckerDAO getCheckerDAO() {
		return checkerDAO;
	}

	public CheckerDTO getDto() {
		return dto;
	}

	public CheckerFrame getmContext() {
		return mContext;
	}

	public MainFrame getMain() {
		return main;
	}

	public Vote getVote() {
		return vote;
	}

	public CommentWriterDAO getCommentDao() {
		return commentDao;
	}

	public JLabel getFrame() {
		return frame;
	}

	public JTextField getDate() {
		return date;
	}

	public JTextField getComment() {
		return comment;
	}

	public JTextArea getContent() {
		return content;
	}

	public JButton getBack() {
		return back;
	}

	public JButton getW() {
		return w;
	}

	public JButton getL() {
		return l;
	}

	public JTable getReplyTable() {
		return replyTable;
	}

	public Reply getReply() {
		return reply;
	}

	public JScrollPane getReplyScroll() {
		return replyScroll;
	}

	public JButton getCommentButton() {
		return commentButton;
	}

	public JPanel getContentpane() {
		return contentpane;
	}

	public JScrollPane getSp() {
		return sp;
	}

	public JScrollPane getRp() {
		return rp;
	}

	public int getUserId() {
		return userId;
	}

	public String getComment1() {
		return comment1;
	}

	public String getTitleBar() {
		return titleBar;
	}
	public String getContentBar() {
		return contentBar;
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
		titleBar = title.getText();

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
		contentBar = content.getText();

		mContext.contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentpane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentpane);

		sp = new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(80, 100, 1120, 350);
		sp.setBackground(new Color(238, 238, 238));

		reply = new Reply();
		replyTable = reply.insertReply(petitionId);
//
//		replyTable.getTableHeader().setReorderingAllowed(false);
//		replyTable.getTableHeader().setResizingAllowed(false);
		replyTable.setRowSelectionAllowed(false);
		replyTable.getColumn("name").setPreferredWidth(20);
		replyTable.getColumn("comment").setPreferredWidth(750);
//		replyTable.setShowVerticalLines(false);
//		replyTable.setShowHorizontalLines(false);
		
		rp = new JScrollPane(replyTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rp.setBounds(80, 450, 1120, 150);
		rp.setBackground(new Color(238, 238, 238));

		w = new JButton(new ImageIcon("img/agreeButton.png"));
		w.setBounds(80, 700, 220, 80);
		w.setBorderPainted(false);
		w.setBackground(new Color(238, 238, 238));

		l = new JButton(new ImageIcon("img/disagreeButton.png"));
		l.setBounds(380, 700, 220, 80);
		l.setBorderPainted(false);
		l.setBackground(new Color(238, 238, 238));

		commentButton = new JButton();
		commentButton.setBounds(1080, 600, 120, 25);
		commentButton.setBorderPainted(false);
		commentButton.setBackground(new Color(190, 190, 190));
		commentButton.setText("댓글 달기");

		back = new JButton(new ImageIcon("img/exitButton.png"));
		back.setBounds(980, 700, 220, 80);
		back.setBorderPainted(false);
		back.setBackground(new Color(238, 238, 238));
		
		fix = new JButton(new ImageIcon("img/editButton.png"));
		fix.setBounds(700, 700, 220, 80);
		fix.setBorderPainted(false);
		fix.setBackground(new Color(238, 238, 238));

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
		add(rp);
		add(comment);
		add(commentButton);
		add(back);
		add(fix);

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
				commentDao = new CommentWriterDAO(userId, petitionId, comment1);
			}

		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		
		fix.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userId == formID) {
					new WriterFrame(login,petitionId,mContext);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "작성자가 아닙니다!");
				}
				
				
				
			}
		});
	}

	private void getInfo() {
		try (Connection conn = DBConnectionManager.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT)) {
			pstmt.setInt(1, petitionId);
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

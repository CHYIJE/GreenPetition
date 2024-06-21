package Frame;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ver1.DBConnectionManager;
import ver1.ObjectDAO.CheckerDAO;
import ver1.models.CheckerDTO;
import ver1.models.Vote;

public class CheckerFrame extends JFrame {

	private static final String SELECT = "select p.id, p.title, p.content, p.date, p.agree, p.disagree, u.name from petition as p join user as u on p.user_id = u.id where p.id = ?";
//	private static final String UPVOTE = ""
//	private static final String DOWNVOTE =S

	CheckerDAO checkerDAO;
	CheckerDTO dto;
	CheckerFrame mContext = this;
	MainFrame main;
	Vote vote;

	private JLabel frame;
	private JTextField title;
	private JTextField name;
	private JTextField date;
	private JTextArea content;
	private JButton back;
	private JButton w;
	private JButton l;
	private JButton comment;
	private JTable replyTable;
	private Reply reply;
	private JScrollPane replyScroll;

	public CheckerFrame() {
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

		mContext.content = new JTextArea();
		content.setBorder(BorderFactory.createEmptyBorder());
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
		

		w = new JButton(new ImageIcon("img/facilityButton.png"));
		w.setBounds(80, 700, 220, 80);
		w.setBorderPainted(false);
		w.setBackground(new Color(255, 255, 255));

		l = new JButton(new ImageIcon("img/teacherButton.png"));
		l.setBounds(380, 700, 220, 80);
		l.setBorderPainted(false);
		l.setBackground(new Color(255, 255, 255));

		comment = new JButton(new ImageIcon("img/writeArticleButton.png"));
		comment.setBounds(680, 700, 220, 80);
		comment.setBorderPainted(false);
		comment.setBackground(new Color(255, 255, 255));
		
		back = new JButton(new ImageIcon("img/writeArticleButton.png"));
		back.setBounds(980, 700, 220, 80);
		back.setBorderPainted(false);
		back.setBackground(new Color(255, 255, 255));

	}

	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		add(title);
		add(name);
		add(date);
		add(content);
		add(w);
		add(l);
		add(comment);
		add(back);

		setVisible(true);
	}

	public void addAction() {
		w.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 찬성수 올라가게

			}

		});
		l.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 반대수 올라가게
				vote.upvote();
			}

		});
		comment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainFrame(null);
			}
			
		});
	}

	private void getInfo() {
		try (Connection conn = DBConnectionManager.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT)) {
			pstmt.setInt(1, /* TODO 메인 > 글 id 가져와야함 */4);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				dto = CheckerDTO.builder().petition_id(resultSet.getInt("id")).name(resultSet.getString("name"))
						.date(resultSet.getString("date")).title(resultSet.getString("title"))
						.content(resultSet.getString("content")).agree(resultSet.getInt("agree"))
						.disagree(resultSet.getInt("disagree")).build();
				vote = Vote.builder().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new CheckerFrame();
	}
}

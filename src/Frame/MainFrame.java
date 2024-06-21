package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
=======
import java.util.List;
>>>>>>> cyj

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ver1.ObjectDAO.LoginDAO;
<<<<<<< HEAD
import ver1.models.PatitionDTO;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
=======
import ver1.ObjectDAO.SearchDAO;
import ver1.models.PatitionDTO;
>>>>>>> cyj

public class MainFrame extends JFrame {

	private static final String VIEW_ALL = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.id >= ? and petition.id <= ? order by petition.id ASC ";
	private static final String VIEW_SELECT = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.id > ? and petition.id <= ? order by petition.id ASC";
	private static final String VIEW_FACILITY = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.category = 'facility' ";
	private static final String VIEW_TEACHER = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.category = 'teacher' ";

	private JLabel frame;
	private JButton facilityButton;
	private JButton teacherButton;
	private JButton articleButton;
<<<<<<< HEAD
=======
	private SearchDAO searchDAO;
	private JTextField searchField; // 검색 필드 추가
	private JButton searchButton;
>>>>>>> cyj

	AllArticle article;
	JScrollPane scroll;
	private JTable table;
	
	LoginFrame mContext;

	String getUserName;
	private JLabel check;

	private LoginDAO mcontext;

	public MainFrame(LoginDAO mcontext) {
		searchDAO = new SearchDAO(); // SearchDAO 초기화
		this.mcontext = mcontext;
		initData();
		setInitLayout();
		addAction();
<<<<<<< HEAD
<<<<<<< HEAD
=======
		body();
		checkId();

	}

	public void checkId() {

		check = new JLabel();
		getContentPane().add(check);

		add(check);
		check.setText(mcontext.getUserAccId() + " 님 접속중입니다!");
		Font bodyfont = new Font("D2CODING", Font.BOLD, 25);
		check.setFont(bodyfont);
		check.setBounds(900, 40, 350, 100);

	}

	public void body() {

		body = new JTextArea();
		body.setEditable(false);
		body.setBounds(250, 110, 1000, 500);
		body.setBackground(new Color(213, 222, 232));

		getContentPane().add(body);

		body.setText("");
		// ArrayList<BoardDAO> arr = new ArrayList<BoardDao>();
		Font bodyfont = new Font("D2CODING", Font.BOLD, 22);
		body.setFont(bodyfont);

		body.append("\t" + "\t" + "no" + "\t" + "\t" + "\t" + "제목" + "\t" + "\t" + "\t" + "\t" + "\t" + "작성자" + "\t"
				+ "\n");
		body.append("-----------------------------------------------------------------------------------------" + "\n");
		try (Connection conn = DBConnectionManager.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(VIEW_ALL)) {

			pstmt.setInt(1, 1);
			pstmt.setInt(2, 10);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				log = resultSet.getInt("id");
				checker1 = Integer.toString(log);
				checker2 = resultSet.getString("title");
				checker3 = resultSet.getString("acc_id");

				if (checker2.length() <= 10) {
					body.append("\t" + "\t" + checker1 + "\t" + "\t" + "\t" + checker2 + "\t" + "\t" + "\t" + "\s"
							+ "\s" + "\s" + "\s" + "\s" + "\s" + "\s" + "\t" + "\t" + checker3 + "\t" + "\t" + "\t"
							+ "\n");
				} else {
					body.append("\t" + "\t" + checker1 + "\t" + "\t" + " " + " " + checker2 + "\t" + "\t" + "\t" + "\t"
							+ checker3 + "\t" + "\t" + "\t" + "\n");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

>>>>>>> c1c3b51f584b1209562fb97ca773d6a36b27c9fe
=======
>>>>>>> cyj
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
<<<<<<< HEAD
		scroll = new JScrollPane(table);
		scroll.setViewportView(table);
		scroll.setBounds(270, 150, 780, 600);
=======
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
//		table.getColumnModel().setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		table.getColumn("id").setPreferredWidth(3);
		
		
		scroll = new JScrollPane(table);
		scroll.setViewportView(table);
		scroll.setBounds(270, 150, 780, 600);

		searchField = new JTextField(20); // 검색 필드 추가
        searchField.setBounds(500, 800, 300, 40);
		
		searchButton = new JButton("검색"); // 검색 버튼
		searchButton.setBounds(800, 800, 100, 40);
		searchButton.setBorderPainted(false);
		searchButton.setBackground(new Color(255, 255, 255));
>>>>>>> cyj
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
<<<<<<< HEAD
		setVisible(true);

	}
<<<<<<< HEAD
=======

=======

		getContentPane().add(searchField); // 검색 필드 추가
        getContentPane().add(searchButton); // 검색 버튼 추가 
		setVisible(true);

	}
	
>>>>>>> cyj
	public void addAction() {
		
		facilityButton.addActionListener(new ActionListener() {
<<<<<<< HEAD

			String a;
			int num;

			@Override
			public void actionPerformed(ActionEvent e) {

				body.setText("");

				body.append("\t" + "\t" + "no" + "\t" + "\t" + "\t" + "제목" + "\t" + "\t" + "\t" + "\t" + "\t" + "작성자"
						+ "\t" + "\n");
				body.append("-----------------------------------------------------------------------------------------"
						+ "\n");
				if (num == 0) {
					a = VIEW_FACILITY;
					num = 1;
				} else {
					a = VIEW_ALL;
					num = 0;
				}

				try (Connection conn = DBConnectionManager.getInstance().getConnection();
						PreparedStatement pstmt = conn.prepareStatement(a)) {
					ResultSet resultSet = pstmt.executeQuery();
					while (resultSet.next()) {

						log = resultSet.getInt("id");
						checker1 = Integer.toString(log);
						checker2 = resultSet.getString("title");
						checker3 = resultSet.getString("acc_id");
						if (checker2.length() <= 10) {
							body.append("\t" + "\t" + checker1 + "\t" + "\t" + "\t" + checker2 + "\t" + "\t" + "\t"
									+ "\s" + "\s" + "\s" + "\s" + "\s" + "\s" + "\s" + "\t" + "\t" + checker3 + "\t"
									+ "\t" + "\t" + "\n");
						} else {
							body.append("\t" + "\t" + checker1 + "\t" + "\t" + " " + " " + checker2 + "\t" + "\t" + "\t"
									+ "\t" + checker3 + "\t" + "\t" + "\t" + "\n");
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		teacherButton.addActionListener(new ActionListener() {

			String a;
			int num;

			@Override
			public void actionPerformed(ActionEvent e) {

				body.setText("");

				body.append("\t" + "\t" + "no" + "\t" + "\t" + "\t" + "제목" + "\t" + "\t" + "\t" + "\t" + "\t" + "작성자"
						+ "\t" + "\n");
				body.append("-----------------------------------------------------------------------------------------"
						+ "\n");
				if (num == 0) {
					a = VIEW_TEACHER;
					num = 1;
				} else {
					a = VIEW_ALL;
					num = 0;
				}

				try (Connection conn = DBConnectionManager.getInstance().getConnection();
						PreparedStatement pstmt = conn.prepareStatement(a)) {
					ResultSet resultSet = pstmt.executeQuery();
					while (resultSet.next()) {

						log = resultSet.getInt("id");
						checker1 = Integer.toString(log);
						checker2 = resultSet.getString("title");
						checker3 = resultSet.getString("acc_id");
						if (checker2.length() <= 10) {
							body.append("\t" + "\t" + checker1 + "\t" + "\t" + "\t" + checker2 + "\t" + "\t" + "\t"
									+ "\s" + "\s" + "\s" + "\s" + "\s" + "\s" + "\s" + "\t" + "\t" + checker3 + "\t"
									+ "\t" + "\t" + "\n");
						} else {
							body.append("\t" + "\t" + checker1 + "\t" + "\t" + " " + " " + checker2 + "\t" + "\t" + "\t"
									+ "\t" + checker3 + "\t" + "\t" + "\t" + "\n");
						}

						if (log % 10 == 0 && log != 0) {
							max++;
						}

					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		articleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("글 작성 버튼");
				new WriterFrame(mcontext);
			}

		});
		
	}
	class PetitionTable {
		JTable table;
		Vector data = new Vector<>();
		Vector<String> colName = new Vector<>();
		
		public void setData(){
			colName.add("id");
			colName.add("acc_id");
			colName.add("acc_pw");
		}
		public void setTable() {
			String[] header = new String[5];
			Object[][] petetion = new String[header.length][15];
			table = new JTable(petetion, header);
=======
>>>>>>> cyj
			
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

		searchButton.addActionListener(new ActionListener() {
//
			@Override
			public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                List<PatitionDTO> searchResults = searchDAO.titleSearch(searchTerm);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // 기존 데이터 초기화

                for (PatitionDTO result : searchResults) {
                    model.addRow(new Object[]{
                        result.getId(),
                        result.getUser_id(),
                        result.getCategory(),
                        result.getTitle(),
                        result.getContent(),
                        result.getDate()
                    });
                }
            }
        });
    }
	}
<<<<<<< HEAD
>>>>>>> c1c3b51f584b1209562fb97ca773d6a36b27c9fe
	
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
	
=======

	

	
>>>>>>> cyj
			

package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import lombok.Getter;
import ver1.DBConnectionManager;
import ver1.ObjectDAO.AllArticle;
import ver1.ObjectDAO.FacilityDAO;
import ver1.ObjectDAO.LoginDAO;
import ver1.ObjectDAO.SearchDAO;
import ver1.ObjectDAO.TeacherDAO;
import ver1.models.PatitionDTO;

@Getter
public class MainFrame extends JFrame {

	// Information
	LoginFrame mContext;
	private JLabel frame;
	private LoginDAO mcontext;
	private SearchDAO searchDAO;

	// Button
	private JButton facilityButton;
	private JButton teacherButton;
	private JButton articleButton;
	private JButton searchButton;
	private JButton refreshButton;
	private JButton backButton;

	// Table Setting
	AllArticle article;
	JScrollPane scroll;
	private JTable table;

	String getUserName;
	private JLabel check;
	private JTextField searchField;
	// Var Setting
	private boolean teacher = false;
	private boolean facility = false;
	private boolean search = false;
	private int currentUser;

	public MainFrame(LoginDAO mcontext) {
		searchDAO = new SearchDAO(); // SearchDAO 초기화
		this.mcontext = mcontext;
		currentUser = mcontext.getUserId();
		initData();
		setInitLayout();
		addAction();

	}

	public void initData() {
		// Frame Setting
		setTitle("Main-Frame");
		frame = new JLabel(new ImageIcon("img/mainFrame.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(frame);
		setSize(1280, 900);

		// Button Setting
		facilityButton = new JButton(new ImageIcon("img/facilityText.png"));
		facilityButton.setBounds(35, 180, 125, 40);
		facilityButton.setBorderPainted(false);
		facilityButton.setBackground(Color.WHITE);

		teacherButton = new JButton(new ImageIcon("img/teacherText.png"));
		teacherButton.setBounds(34, 290, 125, 40);
		teacherButton.setBorderPainted(false);
		teacherButton.setBackground(Color.WHITE);

		articleButton = new JButton(new ImageIcon("img/writeArticleButton.png"));
		articleButton.setBounds(10, 50, 200, 100);
		articleButton.setBorderPainted(false);
		articleButton.setBackground(Color.WHITE);

		refreshButton = new JButton(new ImageIcon("img/refreshButton.png"));
		refreshButton.setBounds(1150, 50, 62, 62);
		refreshButton.setBorderPainted(false);
		refreshButton.setBackground(Color.WHITE);

		check = new JLabel();
		check.setText(mcontext.getUserName() + " 님");
		Font bodyfont = new Font("D2CODING", Font.BOLD, 25);
		check.setFont(bodyfont);
		check.setBounds(1000, 40, 350, 100);

		// Table Setting
		article = new AllArticle();
		table = article.insertData();

		scroll = new JScrollPane(table);
		scroll.setViewportView(table);
		scroll.setBounds(270, 150, 900, 600);

		DefaultTableCellRenderer cellAlignCenter = new DefaultTableCellRenderer();
		cellAlignCenter.setHorizontalTextPosition(SwingConstants.CENTER);

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setAutoCreateRowSorter(true);
		table.getColumn("id").setPreferredWidth(3);
		table.getColumn("id").setCellRenderer(cellAlignCenter);
		table.getColumn("title").setPreferredWidth(300);
		table.getColumn("acc_id").setPreferredWidth(60);
		table.getColumn("category").setPreferredWidth(30);
		table.getColumn("agree").setPreferredWidth(50);
		table.getColumn("disagree").setPreferredWidth(50);
		table.getColumn("date").setPreferredWidth(100);

		searchField = new JTextField(20); // 검색 필드 추가
		searchField.setBounds(500, 800, 300, 40);

		searchButton = new JButton("검색"); // 검색 버튼
		searchButton.setBounds(800, 800, 100, 40);
		searchButton.setBorderPainted(false);
		searchButton.setBackground(new Color(255, 255, 255));
	}

	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		add(facilityButton);
		add(teacherButton);
		add(articleButton);
		add(refreshButton);
		getContentPane().add(scroll);

		add(check);
		getContentPane().add(check);

		getContentPane().add(searchField); // 검색 필드 추가
		getContentPane().add(searchButton); // 검색 버튼 추가

		setVisible(true);

	}

	public void addAction() {

		teacherButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TeacherDAO teacherDAO = new TeacherDAO();

				if (teacher == false) {
					teacher = true;
					facility = false;

					if (search == true) {
						String searchTerm = searchField.getText();
						List<PatitionDTO> searchResults = searchDAO.titleSearch(searchTerm);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0); // 기존 데이터 초기화

						for (PatitionDTO result : searchResults) {
							if (result.getCategory().toString() == "teacher") {
								model.addRow(new Object[] { result.getId(), result.getTitle(), result.getAcc_id(),
										result.getCategory(), result.getAgree(), result.getDisagree(),
										result.getDate() });
							} else {
								continue;
							}
						}
					} else {
						table.setModel(teacherDAO.insertData().getModel());
					}
				}

				else {
					teacher = false;
					if (search == true) {

						String searchTerm = searchField.getText();
						List<PatitionDTO> searchResults = searchDAO.titleSearch(searchTerm);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0); // 기존 데이터 초기화

						for (PatitionDTO result : searchResults) {
							model.addRow(new Object[] { result.getId(), result.getTitle(), result.getAcc_id(),
									result.getCategory(), result.getAgree(), result.getDisagree(), result.getDate() });
						}

					} else {

						table.setModel(article.insertData().getModel());
					}
				}

			}
		});

		facilityButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FacilityDAO facilityDAO = new FacilityDAO();

				if (facility == false) {
					facility = true;
					teacher = false;

					if (search == true) {
						String searchTerm = searchField.getText();
						List<PatitionDTO> searchResults = searchDAO.titleSearch(searchTerm);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0); // 기존 데이터 초기화

						for (PatitionDTO result : searchResults) {
							if (result.getCategory().toString() == "facility") {
								model.addRow(new Object[] { result.getId(), result.getTitle(), result.getAcc_id(),
										result.getCategory(), result.getAgree(), result.getDisagree(),
										result.getDate() });
							} else {
								continue;
							}
						}
					} else {
						table.setModel(facilityDAO.insertData().getModel());
					}
				} else {
					facility = false;
					if (search == true) {

						String searchTerm = searchField.getText();
						List<PatitionDTO> searchResults = searchDAO.titleSearch(searchTerm);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0); // 기존 데이터 초기화

						for (PatitionDTO result : searchResults) {
							model.addRow(new Object[] { result.getId(), result.getTitle(), result.getAcc_id(),
									result.getCategory(), result.getAgree(), result.getDisagree(), result.getDate() });
						}

					} else {

						table.setModel(article.insertData().getModel());
					}
				}

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
				search = true;
				String searchTerm = searchField.getText();
				List<PatitionDTO> searchResults = searchDAO.titleSearch(searchTerm);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0); // 기존 데이터 초기화

				for (PatitionDTO result : searchResults) {
					model.addRow(new Object[] { result.getId(), result.getTitle(), result.getAcc_id(),
							result.getCategory(), result.getAgree(), result.getDisagree(), result.getDate() });
				}
			}
		});

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					int row = table.getSelectedRow();

					if (row == -1) {
						return;
					}

					int id = (int) table.getValueAt(row, 0);

					getValueFromDatabase(id);

					new CheckerFrame(currentUser, id, mcontext);
				}
			}
		});
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				autoRefresh();
			}
		});
	}

	private Object getValueFromDatabase(int id) {
		String query = " SELECT id FROM petition WHERE id = ? ";
		Object value = null;

		try (Connection conn = DBConnectionManager.getInstance().getConnection();) {
			PreparedStatement ptmt = conn.prepareStatement(query);
			ptmt.setInt(1, id);
			ResultSet rs = ptmt.executeQuery();

			if (rs.next()) {
				value = rs.getInt("id");
			}
			rs.close();
			ptmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;

	}

	public void autoRefresh() {
		TeacherDAO teacherDAO = new TeacherDAO();
		FacilityDAO facilityDAO = new FacilityDAO();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		if (teacher == true) {
			table.setModel(teacherDAO.insertData().getModel());
		} else if (facility == true) {
			table.setModel(facilityDAO.insertData().getModel());
		} else if (search == true) {
			String searchTerm = searchField.getText();
			List<PatitionDTO> searchResults = searchDAO.titleSearch(searchTerm);
			model.setRowCount(0); // 기존 데이터 초기화

			for (PatitionDTO result : searchResults) {
				model.addRow(new Object[] { result.getId(), result.getTitle(), result.getAcc_id(), result.getCategory(),
						result.getAgree(), result.getDisagree(), result.getDate() });
			}
		}

		else {
			table.setModel(article.insertData().getModel());
		}
	}

}

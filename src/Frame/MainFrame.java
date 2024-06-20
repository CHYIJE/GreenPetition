package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ver1.DBConnectionManager;

@Getter

@AllArgsConstructor
public class MainFrame extends JFrame {

	private static final String VIEW_ALL = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id ";
	private static final String VIEW_FACILITY = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.category = 'facility' ";
	private static final String VIEW_TEACHER = " select petition.id, petition.title, user.acc_id from petition join user on petition.user_id = user.id where petition.category = 'teacher' ";

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
		body.setRows(3);
		body.setColumns(12);
		body.setEditable(false);
		body.setBounds(250, 110, 1000, 700);
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
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				log = resultSet.getInt("id");
				checker1 = Integer.toString(log);
				checker2 = resultSet.getString("title");
				checker3 = resultSet.getString("acc_id");

				if (checker2.length() <= 6) {
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
	}

	public void setInitLayout() {
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		getContentPane().add(facilityButton);
		getContentPane().add(teacherButton);
		getContentPane().add(articleButton);

		setVisible(true);

	}

	public void addAction() {

		facilityButton.addActionListener(new ActionListener() {

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
						if (checker2.length() <= 6) {
							body.append("\t" + "\t" + checker1 + "\t" + "\t" + "\t" + checker2 + "\t" + "\t" + "\t" + "\s"
									+ "\s" + "\s" + "\s" + "\s" + "\s" + "\s" + "\t" + "\t" + checker3 + "\t" + "\t" + "\t"
									+ "\n");
						} else {
							body.append("\t" + "\t" + checker1 + "\t" + "\t" + " " + " " + checker2 + "\t" + "\t" + "\t" + "\t"
									+ checker3 + "\t" + "\t" + "\t" + "\n");
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
						if (checker2.length() <= 6) {
							body.append("\t" + "\t" + checker1 + "\t" + "\t" + "\t" + checker2 + "\t" + "\t" + "\t" + "\s"
									+ "\s" + "\s" + "\s" + "\s" + "\s" + "\s" + "\t" + "\t" + checker3 + "\t" + "\t" + "\t"
									+ "\n");
						} else {
							body.append("\t" + "\t" + checker1 + "\t" + "\t" + " " + " " + checker2 + "\t" + "\t" + "\t" + "\t"
									+ checker3 + "\t" + "\t" + "\t" + "\n");
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
			}

		});
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}

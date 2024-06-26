package Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Getter;
import ver1.ObjectDAO.LoginDAO;
import ver1.ObjectDAO.WriterDAO;
import ver1.ObjectDAO.WriterDAO2;
import ver1.models.WriterDTO;

@Getter
public class WriterFrame extends JFrame {

	WriterDAO writerDAO;
	WriterDAO2 writerDAO2;
	WriterDTO dto;
	WriterFrame mContext = this;

	LoginDAO loginDAO;

	CheckerFrame check;

	private JLabel frame;
	private JTextField titleField;
	private JTextArea contentField;
	private JButton facility;
	private JButton teacher;
	private JButton sumbit;
	private JButton back;

	private int id;
	private boolean titleClick = false;
	private boolean contentClick = false;

	private String category = "-1";
	private LocalDate date = LocalDate.now();

	public WriterFrame(LoginDAO loginDAO) {
		this.loginDAO = loginDAO; // LoginDAO 객체를 초기화
		initData();
		setInitLayout();
		addAction();
	}

	public WriterFrame(LoginDAO loginDAO, int id, CheckerFrame check) {
		this.loginDAO = loginDAO;
		this.id = id;
		this.check = check;
		initData2();
		setInitLayout();
		addAction2();
	}

	public void initData() {

		setTitle("New Petition");
		frame = new JLabel(new ImageIcon("img/writepage.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(frame);
		setSize(1280, 900);

		mContext.titleField = new JTextField("제목을 입력하세요");
		titleField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		titleField.setBounds(300, 75, 900, 30);
		titleField.setBackground(new Color(213, 222, 232));
		titleField.setEnabled(false);

		mContext.contentField = new JTextArea("내용을 입력하세요");
		contentField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentField.setBounds(300, 125, 900, 600);
		contentField.setBackground(new Color(213, 222, 232));
		contentField.setLineWrap(true);
		contentField.setEnabled(false);

		facility = new JButton(new ImageIcon("img/facilityButton.png"));
		facility.setBounds(0, 180, 220, 80);
		facility.setBorderPainted(false);
		facility.setBackground(new Color(255, 255, 255));

		teacher = new JButton(new ImageIcon("img/teacherButton.png"));
		teacher.setBounds(0, 280, 220, 80);
		teacher.setBorderPainted(false);
		teacher.setBackground(new Color(255, 255, 255));

		sumbit = new JButton(new ImageIcon("img/writeArticleButton.png"));
		sumbit.setBounds(950, 750, 280, 80);
		sumbit.setBorderPainted(false);
		sumbit.setBackground(new Color(255, 255, 255));

		back = new JButton(new ImageIcon("img/exitButton.png"));
		back.setBounds(0, 750, 210, 80);
		back.setBorderPainted(false);
		back.setBackground(Color.WHITE);

	}

	public void initData2() {

		setTitle("New Petition");
		frame = new JLabel(new ImageIcon("img/writepage.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(frame);
		setSize(1280, 900);

		mContext.titleField = new JTextField();
		titleField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		titleField.setBounds(300, 75, 900, 30);
		titleField.setBackground(new Color(213, 222, 232));
		titleField.setText(check.getTitleBar());

		mContext.contentField = new JTextArea();
		contentField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentField.setBounds(300, 125, 900, 600);
		contentField.setBackground(new Color(213, 222, 232));
		contentField.setLineWrap(true);
		contentField.setText(check.getContentBar());

		facility = new JButton(new ImageIcon("img/facilityButton.png"));
		facility.setBounds(0, 180, 220, 80);
		facility.setBorderPainted(false);
		facility.setBackground(new Color(255, 255, 255));

		teacher = new JButton(new ImageIcon("img/teacherButton.png"));
		teacher.setBounds(0, 280, 220, 80);
		teacher.setBorderPainted(false);
		teacher.setBackground(new Color(255, 255, 255));

		sumbit = new JButton(new ImageIcon("img/writeArticleButton.png"));
		sumbit.setBounds(950, 750, 280, 80);
		sumbit.setBorderPainted(false);
		sumbit.setBackground(new Color(255, 255, 255));

		back = new JButton(new ImageIcon("img/exitButton.png"));
		back.setBounds(0, 750, 210, 80);
		back.setBorderPainted(false);
		back.setBackground(Color.WHITE);

		titleClick = true;
		contentClick = true;
	}

	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		add(titleField);
		add(contentField);
		add(facility);
		add(teacher);
		add(sumbit);
		add(back);

		setVisible(true);
	}

	public void addAction() {
		facility.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				category = "facility";
				JOptionPane.showMessageDialog(null, "Facility");
			}

		});

		teacher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				category = "teacher";
				JOptionPane.showMessageDialog(null, "Teacher");
			}
		});

		sumbit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mContext.category.equals("-1") && !mContext.titleField.getText().equals("")
						&& !mContext.contentField.getText().equals("")) {
					// WriterDTO 객체를 생성하고 데이터를 설정합니다.
					dto = WriterDTO.builder().category(category).title(titleField.getText())
							.content(contentField.getText()).build();
					try {

						writerDAO = new WriterDAO(dto, mContext, loginDAO);
						dispose();

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (mContext.category.equals("-1")) {
					JOptionPane.showMessageDialog(null, "카테고리를 선택해주세요");
				} else {
					JOptionPane.showMessageDialog(null, "제목/내용을 입력해주세요.");
				}

			}
		});
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		titleField.addMouseListener(new MouseListener() {

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

				if (titleClick == false) {
					titleClick = true;
					titleField.setEnabled(true);
					titleField.setText("");
				} else {
				}

			}
		});

		contentField.addMouseListener(new MouseListener() {

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

				if (contentClick == false) {
					contentClick = true;
					contentField.setEnabled(true);
					contentField.setText("");
				} else {
				}

			}
		});

	}

	public void addAction2() {
		facility.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				category = "facility";
				System.out.println(category);
			}

		});

		teacher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				category = "teacher";
				System.out.println(category);
			}
		});

		sumbit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mContext.category.equals("-1") && !mContext.titleField.getText().equals("")
						&& !mContext.contentField.getText().equals("")) {
					// WriterDTO 객체를 생성하고 데이터를 설정합니다.
					dto = WriterDTO.builder().title(titleField.getText()).content(contentField.getText())
							.category(category).build();
					try {

						writerDAO2 = new WriterDAO2(dto, mContext, loginDAO, check);
						dispose();

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (mContext.category.equals("-1")) {
					JOptionPane.showMessageDialog(null, "카테고리를 선택해주세요");
				} else {
					JOptionPane.showMessageDialog(null, "제목/내용을 입력해주세요.");
				}

			}
		});
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		titleField.addMouseListener(new MouseListener() {

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

				if (titleClick == false) {
					titleClick = true;
					titleField.setEnabled(true);
					titleField.setText("");
				} else {
				}

			}
		});

		contentField.addMouseListener(new MouseListener() {

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

				if (contentClick == false) {
					contentClick = true;
					contentField.setEnabled(true);
					contentField.setText("");
				} else {
				}

			}
		});

	}

}
package Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame{
	
	private JLabel frame;
	private JButton facilityButton;
	private JButton teacherButton;
	
	public MainFrame() {
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
		facilityButton.setBackground(new Color(255,255,255));
		
		teacherButton = new JButton(new ImageIcon("img/teacherText.png"));
		teacherButton.setBounds(35, 270, 125, 40);
		teacherButton.setBorderPainted(false);
		teacherButton.setBackground(new Color(255,255,255));
	}
	
	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(facilityButton);
		
		setVisible(true);
		
	}
	public void addAction() {
		facilityButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("시설 버튼");
				
			}
		});
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.ResetBossUIListener;
import controller.ResetPlayerUIListener;
import infor.BossInfor;
import infor.PlayerInfor;
import panel.DrawingPanel;

public class BossView extends JFrame implements ActionListener {
	private JTextField textField_Answer;
	private DrawingPanel drawing;
	private Socket bossSocket;
	private BossInfor inforOfBossSend;
	private ObjectOutputStream outputStream;
	private String nameOfBoss;
	
	public BossView(String nameOfBoss) {
		this.nameOfBoss = nameOfBoss;
		init();
		try {
			bossSocket = new Socket("localhost", 1111);
			listenToServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BossAppear();
	}
	
	public void init() {
		ResetBossUIListener listen = new ResetBossUIListener(this);

		this.setTitle("Boss Room");
		this.setSize(660, 400);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel_DrawArea = new JPanel();
		panel_DrawArea.setBounds(10, 10, 400, 250);
		panel_DrawArea.setLayout(null);
		drawing = new DrawingPanel();
		drawing.setLocation(0, 0);
		drawing.setSize(400, 250);
		panel_DrawArea.add(drawing);
		getContentPane().add(panel_DrawArea);

		textField_Answer = new JTextField();
		textField_Answer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_Answer.setBounds(10, 270, 400, 42);
		getContentPane().add(textField_Answer);
		textField_Answer.setColumns(10);

		JButton button_Delete = new JButton("DELETE");
		button_Delete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_Delete.setBounds(10, 321, 189, 32);
		button_Delete.addActionListener(listen);
		button_Delete.setForeground(Color.RED);
		button_Delete.setBackground(new Color(153, 0, 0));
		button_Delete.setBorderPainted(false);
		getContentPane().add(button_Delete);

		JButton button_Push = new JButton("PUSH");
		button_Push.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_Push.setBounds(209, 322, 201, 31);
		button_Push.setBackground(new Color(0, 0, 102));
		button_Push.setForeground(new Color(51, 102, 255));
		button_Push.setBorderPainted(false);
		button_Push.addActionListener(this);
		getContentPane().add(button_Push);

		JPanel panel_Infor = new JPanel();
		panel_Infor.setBackground(new Color(0, 0, 0));
		panel_Infor.setBounds(420, 0, 226, 363);
		getContentPane().add(panel_Infor);
		panel_Infor.setLayout(null);
		
		JLabel label_Title = new JLabel("BATTLE GUESS");
		label_Title.setBackground(new Color(255, 255, 0));
		label_Title.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_Title.setForeground(new Color(50, 205, 50));
		label_Title.setHorizontalAlignment(SwingConstants.CENTER);
		label_Title.setToolTipText("");
		label_Title.setBounds(10, 10, 206, 75);
		label_Title.setOpaque(true);
		panel_Infor.add(label_Title);
		
		JLabel label_Name = new JLabel(this.nameOfBoss);
		label_Name.setBackground(Color.YELLOW);
		label_Name.setForeground(Color.RED);
		label_Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_Name.setHorizontalAlignment(SwingConstants.CENTER);
		label_Name.setBounds(22, 104, 180, 75);
		label_Name.setOpaque(true);
		panel_Infor.add(label_Name);
		
		Box verticalBox_Name = Box.createVerticalBox();
		verticalBox_Name.setBounds(10, 87, 206, 105);
		panel_Infor.add(verticalBox_Name);
		verticalBox_Name.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "NAME", TitledBorder.CENTER, TitledBorder.TOP, null, Color.GREEN));
		
		JLabel label_Score = new JLabel("1111");
		label_Score.setOpaque(true);
		label_Score.setHorizontalAlignment(SwingConstants.CENTER);
		label_Score.setForeground(Color.RED);
		label_Score.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_Score.setBackground(Color.YELLOW);
		label_Score.setBounds(22, 213, 180, 36);
		panel_Infor.add(label_Score);
		
		Box verticalBox_KeyRoom = Box.createVerticalBox();
		verticalBox_KeyRoom.setBounds(10, 196, 206, 63);
		panel_Infor.add(verticalBox_KeyRoom);
		verticalBox_KeyRoom.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "KEY ROOM", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 255, 0)));
		
		JLabel lblNewLabel = new JLabel("YOU ARE BOSS");
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 269, 206, 84);
		lblNewLabel.setOpaque(true);
		panel_Infor.add(lblNewLabel);
		
		JLabel label_Background = new JLabel("");
		label_Background.setOpaque(true);
		label_Background.setBackground(Color.YELLOW);
		label_Background.setBounds(0, 0, 646, 363);
		getContentPane().add(label_Background);	
		
		this.setVisible(true);
	}
	
	public void BossAppear() {
		try {
			inforOfBossSend = new BossInfor();
			outputStream = new ObjectOutputStream(bossSocket.getOutputStream());
			outputStream.writeObject(inforOfBossSend);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ResetAll() {
		this.drawing.clearDrawing();
		this.textField_Answer.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Đã nhấn P");
		try {
			inforOfBossSend = new BossInfor();
			inforOfBossSend.setCorrectAnswer(this.textField_Answer.getText());
			inforOfBossSend.setLines(this.drawing.getLines());

			outputStream = new ObjectOutputStream(bossSocket.getOutputStream());
			outputStream.writeObject(inforOfBossSend);
			outputStream.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void listenToServer() {
		new Thread(() -> {
				try {			
					while (true) {
						ObjectInputStream inputStream = new ObjectInputStream(bossSocket.getInputStream());
						Object receivedData = inputStream.readObject();
						if (receivedData instanceof PlayerInfor) {
							PlayerInfor player = (PlayerInfor) receivedData;
							if (player.getAnswerOfPlayer().equals(textField_Answer.getText())) {
								System.out.println("Người dùng nào đó đã đoán đúng đáp án!");
								JOptionPane.showMessageDialog(this,
										"Đã có người chơi đoán đúng đáp án",
										"CORRECT ANSWER", JOptionPane.INFORMATION_MESSAGE);
							} else {
								System.out.println("Người dùng nào đó đã đoán sai đáp án!");
							}
						} 			
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}).start();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new BossView("Mr.Bo...!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

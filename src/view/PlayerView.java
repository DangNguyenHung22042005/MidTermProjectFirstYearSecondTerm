package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.ResetPlayerUIListener;
import infor.BossInfor;
import infor.PlayerInfor;
import infor.ScoreUpdateInfor;
import panel.DrawingPanel;
import security.EncryptByMD5;
import server.Server;
import xml.CreateXMLFileForPlayer;
import xml.Player;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class PlayerView extends JFrame implements ActionListener, FocusListener {
	private JTextField textField_Answer;
	private DrawingPanel drawing;
	private Socket playerSocket;
	private PlayerInfor inforOfPlayerSend;
	private ObjectOutputStream outputStream;
	private String correctAnswerOfBossSend;
	private String nameOfPlayer;
	private int scoreOfPlayer;
	private JLabel label_Score;
	private String passwordOfPlayer;
	private ScoreUpdateInfor scoreUp10;

	public PlayerView(String nameOfPlayer, int scoreOfPlayer, String passwordOfPlayer) {
		this.nameOfPlayer = nameOfPlayer;
		this.scoreOfPlayer = scoreOfPlayer;
		this.passwordOfPlayer = passwordOfPlayer;
		init();
		try {
			playerSocket = new Socket("localhost", 2204);
			listenToServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PlayerAppear();
	}

	public void init() {
		ResetPlayerUIListener listen = new ResetPlayerUIListener(this);

		this.setTitle("Player Room");
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

		textField_Answer = new JTextField("Enter your answer...");
		textField_Answer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_Answer.setBounds(10, 270, 400, 42);
		textField_Answer.addFocusListener(this);
		textField_Answer.setColumns(10);
		getContentPane().add(textField_Answer);

		JButton button_Delete = new JButton("DELETE");
		button_Delete.setBackground(new Color(153, 0, 0));
		button_Delete.setForeground(Color.RED);
		button_Delete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_Delete.setBounds(10, 321, 189, 32);
		button_Delete.setBorderPainted(false);
		button_Delete.addActionListener(listen);
		getContentPane().add(button_Delete);

		JButton button_Guess = new JButton("GUESS");
		button_Guess.setBackground(new Color(0, 0, 102));
		button_Guess.setForeground(new Color(51, 102, 255));
		button_Guess.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_Guess.setBounds(209, 322, 201, 31);
		button_Guess.setBorderPainted(false);
		button_Guess.addActionListener(this);
		getContentPane().add(button_Guess);
		
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
		
		JLabel label_Name = new JLabel(this.nameOfPlayer);
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
		
		label_Score = new JLabel();
		label_Score.setText(Integer.toString(scoreOfPlayer));
		label_Score.setOpaque(true);
		label_Score.setHorizontalAlignment(SwingConstants.CENTER);
		label_Score.setForeground(Color.RED);
		label_Score.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_Score.setBackground(Color.YELLOW);
		label_Score.setBounds(22, 213, 180, 36);
		panel_Infor.add(label_Score);
		
		Box verticalBox_Score = Box.createVerticalBox();
		verticalBox_Score.setBounds(10, 199, 206, 60);
		panel_Infor.add(verticalBox_Score);
		verticalBox_Score.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "SCORE", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 255, 0)));
		
		JLabel lblYouArePlayer = new JLabel("YOU ARE PLAYER");
		lblYouArePlayer.setBackground(Color.YELLOW);
		lblYouArePlayer.setForeground(Color.GREEN);
		lblYouArePlayer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblYouArePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouArePlayer.setBounds(10, 269, 206, 84);
		lblYouArePlayer.setOpaque(true);
		panel_Infor.add(lblYouArePlayer);
		
		JLabel label_Background = new JLabel("");
		label_Background.setOpaque(true);
		label_Background.setBackground(Color.YELLOW);
		label_Background.setBounds(0, 0, 646, 363);
		getContentPane().add(label_Background);	
				
		this.setVisible(true);
	}

	public void PlayerAppear() {
		try {
			inforOfPlayerSend = new PlayerInfor();
			outputStream = new ObjectOutputStream(playerSocket.getOutputStream());
			outputStream.writeObject(inforOfPlayerSend);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ResetAnswer() {
		this.textField_Answer.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Đã nhấn G");
		try {
			inforOfPlayerSend = new PlayerInfor();
			inforOfPlayerSend.setAnswerOfPlayer(this.textField_Answer.getText());

			outputStream = new ObjectOutputStream(playerSocket.getOutputStream());
			outputStream.writeObject(inforOfPlayerSend);
			outputStream.flush();
			if (this.textField_Answer.getText().equals(correctAnswerOfBossSend)) {
				displayCorrectResult();
				this.scoreOfPlayer += 10;
				this.label_Score.setText(Integer.toString(scoreOfPlayer));
				
				scoreUp10 = new ScoreUpdateInfor();
				scoreUp10.setName(nameOfPlayer);
				scoreUp10.setPassword(passwordOfPlayer);
				scoreUp10.setScore(scoreOfPlayer);

				outputStream = new ObjectOutputStream(playerSocket.getOutputStream());
				outputStream.writeObject(scoreUp10);
				outputStream.flush();
			} else {
				displayWrongResult();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void listenToServer() {
		new Thread(() -> {
				try {			
					while (true) {
						ObjectInputStream inputStream = new ObjectInputStream(playerSocket.getInputStream());
						Object receivedData = inputStream.readObject();
						if (receivedData instanceof BossInfor) {
							BossInfor boss = (BossInfor) receivedData;
							this.drawing.setLines(boss.getLines());
							drawing.repaint();
							this.correctAnswerOfBossSend = boss.getCorrectAnswer();
						} 			
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}).start();
	}
	
	public void focusGained(FocusEvent e) {
		if (textField_Answer.getText().equals("Enter your answer...")) {
			textField_Answer.setText("");
		}
	}

	public void focusLost(FocusEvent e) {
		if (textField_Answer.getText().isEmpty()) {
			textField_Answer.setText("Enter your answer...");
		}
	}
	
	public void displayCorrectResult() {
		JOptionPane.showMessageDialog(this,
				"Xin chúc mừng, đáp án của bạn hoàn toàn chính xác! Bạn được thêm 10 điểm thưởng!",
				"CORRECT ANSWER", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void displayWrongResult() {
		JOptionPane.showMessageDialog(this, "Xin chia buồn, đáp án của bạn sai mất rồi!", "WRONG ANSWER",
				JOptionPane.ERROR_MESSAGE);
	}	
}

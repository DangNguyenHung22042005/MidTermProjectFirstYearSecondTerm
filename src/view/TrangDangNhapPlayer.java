package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import infor.BossInfor;
import infor.LoginInfor;
import infor.PlayerInfor;
import infor.ServerSendBackInfor;
import security.EncryptByMD5;

public class TrangDangNhapPlayer extends JFrame implements ActionListener, MouseListener {
	JTextField textField_tenTaiKhoan;
	JPasswordField passwordField_matKhau;
	JButton button_dangNhap;
	JButton button_dangKy;
	JToggleButton toggleButton_hideAnShow;
	String nameOfPlayer;
	int scoreOfPlayer;
	String passwordOfPlayer;
	Socket loginSocket;
	private ObjectOutputStream outputStream;
	private LoginInfor inforOfLoginSend;
	private Boolean token = false;

	public TrangDangNhapPlayer() {
		try {
			loginSocket = new Socket("192.168.227.10", 2204);
			listenToServer();
			
			setTitle("Đăng nhập");
			setSize(820, 480);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			getContentPane().setLayout(null);

			JLabel label_welcome = new JLabel("Welcome to");
			label_welcome.setForeground(new Color(255, 51, 0));
			label_welcome.setFont(new Font("Arial", Font.BOLD, 20));
			label_welcome.setBounds(589, 76, 110, 13);
			getContentPane().add(label_welcome);

			URL src = TrangDangNhap.class.getResource("/image/the_last_logo.png");
			Image temp = Toolkit.getDefaultToolkit().createImage(src);
			ImageIcon back_ground = new ImageIcon(temp.getScaledInstance(362, 443, temp.SCALE_SMOOTH));
			
			JLabel lblPlayerLogin = new JLabel("PLAYER LOGIN");
			lblPlayerLogin.setForeground(Color.GREEN);
			lblPlayerLogin.setBackground(Color.YELLOW);
			lblPlayerLogin.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayerLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblPlayerLogin.setBounds(484, 341, 291, 79);
			lblPlayerLogin.setOpaque(true);
			getContentPane().add(lblPlayerLogin);

			JLabel label_image = new JLabel(back_ground);
			label_image.setBackground(new Color(255, 255, 0));
			label_image.setBounds(444, 0, 362, 443);
			getContentPane().add(label_image);

			JPanel panel_dangNhap = new JPanel();
			panel_dangNhap.setBounds(0, 0, 449, 443);
			getContentPane().add(panel_dangNhap);
			panel_dangNhap.setLayout(null);

			JLabel lblTiKhon = new JLabel("Tài khoản\r\n");
			lblTiKhon.setHorizontalAlignment(SwingConstants.CENTER);
			lblTiKhon.setBackground(new Color(0, 128, 0));
			lblTiKhon.setOpaque(true);
			lblTiKhon.setForeground(Color.GREEN);
			lblTiKhon.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTiKhon.setBounds(45, 78, 106, 34);
			panel_dangNhap.add(lblTiKhon);

			textField_tenTaiKhoan = new JTextField();
			textField_tenTaiKhoan.setFont(new Font("Arial Black", Font.PLAIN, 18));
			textField_tenTaiKhoan.setBounds(161, 77, 275, 34);
			panel_dangNhap.add(textField_tenTaiKhoan);
			textField_tenTaiKhoan.setColumns(10);

			JLabel label_matKhau = new JLabel("Mật khẩu");
			label_matKhau.setHorizontalAlignment(SwingConstants.CENTER);
			label_matKhau.setBackground(new Color(0, 128, 0));
			label_matKhau.setOpaque(true);
			label_matKhau.setForeground(Color.GREEN);
			label_matKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
			label_matKhau.setBounds(45, 163, 106, 34);
			panel_dangNhap.add(label_matKhau);

			passwordField_matKhau = new JPasswordField();
			passwordField_matKhau.setFont(new Font("Arial Black", Font.PLAIN, 18));
			passwordField_matKhau.setColumns(10);
			passwordField_matKhau.setBounds(161, 162, 186, 34);
			panel_dangNhap.add(passwordField_matKhau);

			button_dangNhap = new JButton("Đăng nhập");
			button_dangNhap.setBackground(new Color(0, 0, 255));
			button_dangNhap.setBorderPainted(false);
			button_dangNhap.setForeground(Color.CYAN);
			button_dangNhap.setFont(new Font("Arial", Font.BOLD, 20));
			button_dangNhap.setBounds(45, 247, 391, 44);
			button_dangNhap.addActionListener(this);
			panel_dangNhap.add(button_dangNhap);

			button_dangKy = new JButton("Đăng ký");
			button_dangKy.setForeground(new Color(255, 0, 0));
			button_dangKy.setFont(new Font("Arial", Font.BOLD, 20));
			button_dangKy.setBorderPainted(false);
			button_dangKy.setBackground(new Color(139, 0, 0));
			button_dangKy.setBounds(45, 329, 391, 44);
			button_dangKy.addActionListener(this);
			panel_dangNhap.add(button_dangKy);

			toggleButton_hideAnShow = new JToggleButton("Show");
			toggleButton_hideAnShow.setBackground(Color.LIGHT_GRAY);
			toggleButton_hideAnShow.setBorderPainted(false);
			toggleButton_hideAnShow.setOpaque(true);
			toggleButton_hideAnShow.setFont(new Font("Arial", Font.BOLD, 15));
			toggleButton_hideAnShow.setForeground(Color.RED);
			toggleButton_hideAnShow.setBounds(350, 162, 86, 34);
			toggleButton_hideAnShow.addMouseListener(this);
			panel_dangNhap.add(toggleButton_hideAnShow);

			JLabel label_nenTrang = new JLabel("");
			label_nenTrang.setBackground(new Color(255, 255, 255));
			label_nenTrang.setOpaque(true);
			label_nenTrang.setBounds(33, 31, 434, 383);
			panel_dangNhap.add(label_nenTrang);
			
			JLabel label_nenVang = new JLabel("");
			label_nenVang.setBackground(new Color(255, 255, 0));
			label_nenVang.setOpaque(true);
			label_nenVang.setBounds(0, 0, 457, 443);
			panel_dangNhap.add(label_nenVang);
					
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginAppear();
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Đăng ký")) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new TrangDangKyPlayer();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			String tenNguoiDungNhap = textField_tenTaiKhoan.getText();
			
			char[] matKhauNguoiDungNhap = passwordField_matKhau.getPassword();
			String matKhauNguoiDungNhapCuoiCung = new String(matKhauNguoiDungNhap);
			
			if (tenNguoiDungNhap.equals("") || matKhauNguoiDungNhapCuoiCung.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					inforOfLoginSend = new LoginInfor();
					inforOfLoginSend.setNameOfPlayer(tenNguoiDungNhap);
					inforOfLoginSend.setPasswordOfPlayer(matKhauNguoiDungNhapCuoiCung);
					outputStream = new ObjectOutputStream(loginSocket.getOutputStream());
					outputStream.writeObject(inforOfLoginSend);
					outputStream.flush();
					Thread.sleep(1000);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (token) {
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						new PlayerView(tenNguoiDungNhap, scoreOfPlayer, matKhauNguoiDungNhapCuoiCung);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Sai tên tài khoản hoặc mật khẩu!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		if (toggleButton_hideAnShow.isSelected()) {
			passwordField_matKhau.setEchoChar((char) 0);
			toggleButton_hideAnShow.setText("Hide");
		} else {
			passwordField_matKhau.setEchoChar('*');
			toggleButton_hideAnShow.setText("Show");
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
	
	public void	LoginAppear() {
		try {
			inforOfLoginSend = new LoginInfor();
			outputStream = new ObjectOutputStream(loginSocket.getOutputStream());
			outputStream.writeObject(inforOfLoginSend);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listenToServer() {
		new Thread(() -> {
				try {			
					while (true) {
						ObjectInputStream inputStream = new ObjectInputStream(loginSocket.getInputStream());
						Object receivedData = inputStream.readObject();
						if (receivedData instanceof ServerSendBackInfor) {
							ServerSendBackInfor serverInforBack = (ServerSendBackInfor) receivedData;
							if (serverInforBack.getToken() == true) {
								scoreOfPlayer = serverInforBack.getScoreOfPlayer();
								token = true;
							} else {
								token = false;
								System.out.println("Token error, cannot log in now!");
							}						
						} 			
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}).start();
	}
}

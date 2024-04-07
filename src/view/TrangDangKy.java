package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class TrangDangKy extends JFrame implements ActionListener, MouseListener {
	JTextField textField_tenTaiKhoan;
	JPasswordField passwordField_matKhau;
	JPasswordField passwordField_xacNhanMatKhau;
	JToggleButton toggleButton_hideAnShow;
	JRadioButton radioButton_dieuKhoan;
	JButton button_dangKy;

	Connection con;
	Statement stm;

	public TrangDangKy() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String url = "jdbc:sqlserver://HUNG\\SQLEXPRESS:1433;databaseName=TKCN";
			String userName = "sa";
			String password = "123456789";

			con = DriverManager.getConnection(url, userName, password);
			stm = con.createStatement();
			con.setAutoCommit(false);
			this.setTitle("Đăng ký");
			this.setSize(510, 607);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);

			URL src = TrangDangNhap.class.getResource("/image/the_last_logo.png");
			Image temp = Toolkit.getDefaultToolkit().createImage(src);
			ImageIcon back_ground = new ImageIcon(temp.getScaledInstance(496, 124, temp.SCALE_SMOOTH));

			JLabel label_image = new JLabel(back_ground);
			label_image.setBounds(0, 0, 496, 80);
			getContentPane().add(label_image);

			JPanel panel_main = new JPanel();
			panel_main.setBounds(0, 78, 496, 435);
			getContentPane().add(panel_main);
			panel_main.setLayout(null);

			JLabel label_taiKhoan = new JLabel("Tài khoản\r\n");
			label_taiKhoan.setOpaque(true);
			label_taiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
			label_taiKhoan.setForeground(Color.GREEN);
			label_taiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
			label_taiKhoan.setBackground(new Color(0, 128, 0));
			label_taiKhoan.setBounds(42, 54, 106, 34);
			panel_main.add(label_taiKhoan);

			JLabel label_matKhau = new JLabel("Mật khẩu");
			label_matKhau.setOpaque(true);
			label_matKhau.setHorizontalAlignment(SwingConstants.CENTER);
			label_matKhau.setForeground(Color.GREEN);
			label_matKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
			label_matKhau.setBackground(new Color(0, 128, 0));
			label_matKhau.setBounds(42, 124, 106, 34);
			panel_main.add(label_matKhau);

			JLabel label_xacNhanMatKhau = new JLabel("Xác nhận mật khẩu");
			label_xacNhanMatKhau.setOpaque(true);
			label_xacNhanMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
			label_xacNhanMatKhau.setForeground(Color.GREEN);
			label_xacNhanMatKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
			label_xacNhanMatKhau.setBackground(new Color(0, 128, 0));
			label_xacNhanMatKhau.setBounds(42, 192, 146, 34);
			panel_main.add(label_xacNhanMatKhau);

			textField_tenTaiKhoan = new JTextField();
			textField_tenTaiKhoan.setFont(new Font("Arial Black", Font.PLAIN, 18));
			textField_tenTaiKhoan.setColumns(10);
			textField_tenTaiKhoan.setBounds(177, 53, 275, 34);
			panel_main.add(textField_tenTaiKhoan);

			passwordField_matKhau = new JPasswordField();
			passwordField_matKhau.setFont(new Font("Arial Black", Font.PLAIN, 18));
			passwordField_matKhau.setColumns(10);
			passwordField_matKhau.setBounds(177, 124, 186, 33);
			panel_main.add(passwordField_matKhau);

			passwordField_xacNhanMatKhau = new JPasswordField();
			passwordField_xacNhanMatKhau.setFont(new Font("Arial Black", Font.PLAIN, 18));
			passwordField_xacNhanMatKhau.setColumns(10);
			passwordField_xacNhanMatKhau.setBounds(212, 191, 240, 34);
			panel_main.add(passwordField_xacNhanMatKhau);

			toggleButton_hideAnShow = new JToggleButton("Show");
			toggleButton_hideAnShow.setOpaque(true);
			toggleButton_hideAnShow.setForeground(Color.RED);
			toggleButton_hideAnShow.setFont(new Font("Arial", Font.BOLD, 15));
			toggleButton_hideAnShow.setBorderPainted(false);
			toggleButton_hideAnShow.setBackground(Color.LIGHT_GRAY);
			toggleButton_hideAnShow.setBounds(373, 125, 79, 33);
			toggleButton_hideAnShow.addMouseListener(this);
			panel_main.add(toggleButton_hideAnShow);

			radioButton_dieuKhoan = new JRadioButton("Tôi đã đọc và đồng ý với điều khoản sử dụng");
			radioButton_dieuKhoan.setForeground(Color.RED);
			radioButton_dieuKhoan.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
			radioButton_dieuKhoan.setBounds(45, 270, 407, 21);
			panel_main.add(radioButton_dieuKhoan);

			button_dangKy = new JButton("Đăng ký");
			button_dangKy.setForeground(Color.CYAN);
			button_dangKy.setFont(new Font("Arial", Font.BOLD, 20));
			button_dangKy.setBorderPainted(false);
			button_dangKy.setBackground(new Color(0, 0, 205));
			button_dangKy.setBounds(42, 331, 410, 44);
			button_dangKy.addActionListener(this);
			panel_main.add(button_dangKy);

			JLabel label_nenTrang = new JLabel("");
			label_nenTrang.setOpaque(true);
			label_nenTrang.setBackground(Color.WHITE);
			label_nenTrang.setBounds(33, 10, 434, 395);
			panel_main.add(label_nenTrang);

			JLabel label_nenVang = new JLabel("");
			label_nenVang.setOpaque(true);
			label_nenVang.setBackground(Color.YELLOW);
			label_nenVang.setBounds(0, 0, 496, 435);
			panel_main.add(label_nenVang);
			
			JLabel label_Regis = new JLabel("REGISTRATION BOSS");
			label_Regis.setForeground(Color.GREEN);
			label_Regis.setBackground(Color.YELLOW);
			label_Regis.setHorizontalAlignment(SwingConstants.CENTER);
			label_Regis.setFont(new Font("Tahoma", Font.BOLD, 30));
			label_Regis.setBounds(0, 511, 496, 59);
			label_Regis.setOpaque(true);
			getContentPane().add(label_Regis);

			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
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

	public void actionPerformed(ActionEvent e) {
		InsertData();
	}

	public void InsertData() {
		try {
			String tenNguoiDungNhap = textField_tenTaiKhoan.getText();
			char[] matKhauNguoiDungNhap = passwordField_matKhau.getPassword();
			String matKhauCuoiCung = new String(matKhauNguoiDungNhap);
			char[] matKhauNguoiDungXacNhan = passwordField_xacNhanMatKhau.getPassword();
			String matKhauXacNhanCuoiCung = new String(matKhauNguoiDungXacNhan);
			if (tenNguoiDungNhap.equals("") || matKhauCuoiCung.equals("") || matKhauXacNhanCuoiCung.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (!radioButton_dieuKhoan.isSelected()) {
				JOptionPane.showMessageDialog(this, "Vui lòng đồng ý với điều khoản sử dụng để tiếp tục", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (!matKhauCuoiCung.equals(matKhauXacNhanCuoiCung)) {
					JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không trùng khớp, Vui lòng kiểm tra lại!",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					String sql = "INSERT INTO TAIKHOAN " + "VALUES (N'" + tenNguoiDungNhap + "', N'" + matKhauCuoiCung + "')";
					con.setAutoCommit(false);
					stm.executeUpdate(sql);
					con.commit();
					JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công!", "COMPLETE",
							JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tên tài khoản và mật khẩu này đã được đăng ký trước đó, Bạn có thể dùng nó để đăng nhập!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
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
}

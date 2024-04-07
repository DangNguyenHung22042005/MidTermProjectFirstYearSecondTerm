package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class Captcha1 extends JFrame implements ActionListener, MouseListener {
	JButton button_xacNhan;
	JToggleButton tglbtnImg_8;
	JToggleButton tglbtnImg_7;
	JToggleButton tglbtnImg_5;
	JToggleButton tglbtnImg_4;
	JToggleButton tglbtnImg_6;
	JToggleButton tglbtnImg_3;
	JToggleButton tglbtnImg_2;
	JToggleButton tglbtnImg_1;
	JToggleButton tglbtnImg;
	JLabel label_nenDen_1;
	JLabel label_nenDen_2;
	JLabel label_nenDen_3;
	JLabel label_nenDen;
	JLabel label_nenDen_4;
	JLabel label_nenDen_5;
	JLabel label_nenDen_6;
	JLabel label_nenDen_7;
	JLabel label_nenDen_8;
	JLabel label_nenDen_9;
	String nameOfBoss;

	public Captcha1(String nameOfBoss) {
		this.nameOfBoss = nameOfBoss;
		this.setTitle("Captcha");
		this.setSize(500, 589);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel label_tieuDe = new JLabel("Vui lòng chọn tất cả ảnh xuất hiện \"con chó\"");
		label_tieuDe.setForeground(new Color(255, 255, 255));
		label_tieuDe.setBackground(new Color(30, 144, 255));
		label_tieuDe.setOpaque(true);
		label_tieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		label_tieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		label_tieuDe.setBounds(0, 0, 486, 102);
		getContentPane().add(label_tieuDe);

		URL src1 = TrangDangNhap.class.getResource("/image/dog_images_1.jpg");
		Image temp1 = Toolkit.getDefaultToolkit().createImage(src1);
		ImageIcon dog_1 = new ImageIcon(temp1.getScaledInstance(150, 102, temp1.SCALE_SMOOTH));

		URL src2 = TrangDangNhap.class.getResource("/image/dog_images_2.jpg");
		Image temp2 = Toolkit.getDefaultToolkit().createImage(src2);
		ImageIcon dog_2 = new ImageIcon(temp2.getScaledInstance(150, 102, temp2.SCALE_SMOOTH));

		URL src3 = TrangDangNhap.class.getResource("/image/dog_images_3.jpg");
		Image temp3 = Toolkit.getDefaultToolkit().createImage(src3);
		ImageIcon dog_3 = new ImageIcon(temp3.getScaledInstance(150, 102, temp3.SCALE_SMOOTH));

		URL src4 = TrangDangNhap.class.getResource("/image/elephant_images_1.jpg");
		Image temp4 = Toolkit.getDefaultToolkit().createImage(src4);
		ImageIcon elephant_1 = new ImageIcon(temp4.getScaledInstance(150, 102, temp4.SCALE_SMOOTH));

		URL src5 = TrangDangNhap.class.getResource("/image/fish_images_1.jpg");
		Image temp5 = Toolkit.getDefaultToolkit().createImage(src5);
		ImageIcon fish_1 = new ImageIcon(temp5.getScaledInstance(150, 102, temp5.SCALE_SMOOTH));

		URL src6 = TrangDangNhap.class.getResource("/image/crocodile_images_1.jpg");
		Image temp6 = Toolkit.getDefaultToolkit().createImage(src6);
		ImageIcon crocodile_1 = new ImageIcon(temp6.getScaledInstance(150, 102, temp6.SCALE_SMOOTH));

		URL src7 = TrangDangNhap.class.getResource("/image/cat_images_1.jpg");
		Image temp7 = Toolkit.getDefaultToolkit().createImage(src7);
		ImageIcon cat_1 = new ImageIcon(temp7.getScaledInstance(150, 102, temp7.SCALE_SMOOTH));

		URL src8 = TrangDangNhap.class.getResource("/image/cat_images_2.jpg");
		Image temp8 = Toolkit.getDefaultToolkit().createImage(src8);
		ImageIcon cat_2 = new ImageIcon(temp8.getScaledInstance(150, 102, temp8.SCALE_SMOOTH));

		URL src9 = TrangDangNhap.class.getResource("/image/bird_images_1.jpg");
		Image temp9 = Toolkit.getDefaultToolkit().createImage(src9);
		ImageIcon bird_1 = new ImageIcon(temp9.getScaledInstance(150, 102, temp9.SCALE_SMOOTH));

		tglbtnImg = new JToggleButton(dog_1);
		tglbtnImg.setFont(new Font("Tahoma", Font.PLAIN, 5));
		tglbtnImg.setBounds(10, 112, 133, 102);
		tglbtnImg.addActionListener(this);
		getContentPane().add(tglbtnImg);

		tglbtnImg_1 = new JToggleButton(elephant_1);
		tglbtnImg_1.setFont(new Font("Tahoma", Font.PLAIN, 5));
		tglbtnImg_1.setBounds(176, 112, 133, 102);
		tglbtnImg_1.addActionListener(this);
		getContentPane().add(tglbtnImg_1);

		tglbtnImg_2 = new JToggleButton(fish_1);
		tglbtnImg_2.setBounds(343, 112, 133, 102);
		tglbtnImg_2.addActionListener(this);
		getContentPane().add(tglbtnImg_2);

		tglbtnImg_3 = new JToggleButton(crocodile_1);
		tglbtnImg_3.setBounds(10, 244, 133, 102);
		tglbtnImg_3.addActionListener(this);
		getContentPane().add(tglbtnImg_3);

		tglbtnImg_4 = new JToggleButton(dog_2);
		tglbtnImg_4.setBounds(176, 244, 133, 102);
		tglbtnImg_4.addActionListener(this);
		getContentPane().add(tglbtnImg_4);

		tglbtnImg_5 = new JToggleButton(cat_1);
		tglbtnImg_5.setBounds(343, 244, 133, 102);
		tglbtnImg_5.addActionListener(this);
		getContentPane().add(tglbtnImg_5);

		tglbtnImg_6 = new JToggleButton(cat_2);
		tglbtnImg_6.setBounds(10, 368, 133, 102);
		tglbtnImg_6.addActionListener(this);
		getContentPane().add(tglbtnImg_6);

		tglbtnImg_7 = new JToggleButton(bird_1);
		tglbtnImg_7.setBounds(176, 368, 133, 102);
		tglbtnImg_7.addActionListener(this);
		getContentPane().add(tglbtnImg_7);

		tglbtnImg_8 = new JToggleButton(dog_3);
		tglbtnImg_8.setBounds(343, 368, 133, 102);
		tglbtnImg_8.addActionListener(this);
		getContentPane().add(tglbtnImg_8);

		JSeparator separator_nganCach = new JSeparator();
		separator_nganCach.setBounds(0, 480, 486, 2);
		getContentPane().add(separator_nganCach);

		URL src = TrangDangNhap.class.getResource("/image/make_color_image.png");
		Image temp = Toolkit.getDefaultToolkit().createImage(src);
		ImageIcon make_color = new ImageIcon(temp.getScaledInstance(167, 40, temp.SCALE_SMOOTH));

		JLabel label_makeColor = new JLabel(make_color);
		label_makeColor.setBounds(10, 492, 167, 40);
		getContentPane().add(label_makeColor);

		button_xacNhan = new JButton("Xác nhận");
		button_xacNhan.setForeground(new Color(255, 255, 255));
		button_xacNhan.setBorderPainted(false);
		button_xacNhan.setBackground(new Color(30, 144, 255));
		button_xacNhan.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_xacNhan.setBounds(292, 492, 184, 47);
		button_xacNhan.addMouseListener(this);
		getContentPane().add(button_xacNhan);

		JLabel label_nenTrang = new JLabel("");
		label_nenTrang.setBackground(Color.WHITE);
		label_nenTrang.setOpaque(true);
		label_nenTrang.setBounds(0, 480, 486, 72);
		getContentPane().add(label_nenTrang);

		label_nenDen_1 = new JLabel("");
		label_nenDen_1.setBackground(Color.WHITE);
		label_nenDen_1.setOpaque(true);
		label_nenDen_1.setBounds(0, 101, 151, 123);
		getContentPane().add(label_nenDen_1);

		label_nenDen_2 = new JLabel("");
		label_nenDen_2.setOpaque(true);
		label_nenDen_2.setBackground(Color.WHITE);
		label_nenDen_2.setBounds(163, 101, 157, 123);
		getContentPane().add(label_nenDen_2);

		label_nenDen_3 = new JLabel("");
		label_nenDen_3.setOpaque(true);
		label_nenDen_3.setBackground(Color.WHITE);
		label_nenDen_3.setBounds(335, 101, 151, 123);
		getContentPane().add(label_nenDen_3);

		label_nenDen_4 = new JLabel("");
		label_nenDen_4.setOpaque(true);
		label_nenDen_4.setBackground(Color.WHITE);
		label_nenDen_4.setBounds(0, 235, 151, 117);
		getContentPane().add(label_nenDen_4);

		label_nenDen_5 = new JLabel("");
		label_nenDen_5.setOpaque(true);
		label_nenDen_5.setBackground(Color.WHITE);
		label_nenDen_5.setBounds(163, 235, 157, 117);
		getContentPane().add(label_nenDen_5);

		label_nenDen_6 = new JLabel("");
		label_nenDen_6.setOpaque(true);
		label_nenDen_6.setBackground(Color.WHITE);
		label_nenDen_6.setBounds(335, 235, 151, 117);
		getContentPane().add(label_nenDen_6);

		label_nenDen_7 = new JLabel("");
		label_nenDen_7.setOpaque(true);
		label_nenDen_7.setBackground(Color.WHITE);
		label_nenDen_7.setBounds(0, 359, 151, 123);
		getContentPane().add(label_nenDen_7);

		label_nenDen_8 = new JLabel("");
		label_nenDen_8.setOpaque(true);
		label_nenDen_8.setBackground(Color.WHITE);
		label_nenDen_8.setBounds(163, 359, 157, 123);
		getContentPane().add(label_nenDen_8);

		label_nenDen_9 = new JLabel("");
		label_nenDen_9.setOpaque(true);
		label_nenDen_9.setBackground(Color.WHITE);
		label_nenDen_9.setBounds(335, 359, 151, 123);
		getContentPane().add(label_nenDen_9);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (tglbtnImg.isSelected()) {
			label_nenDen_1.setBackground(Color.black);
		} else {
			label_nenDen_1.setBackground(Color.white);
		}

		if (tglbtnImg_1.isSelected()) {
			label_nenDen_2.setBackground(Color.black);
		} else {
			label_nenDen_2.setBackground(Color.white);
		}

		if (tglbtnImg_2.isSelected()) {
			label_nenDen_3.setBackground(Color.black);
		} else {
			label_nenDen_3.setBackground(Color.white);
		}

		if (tglbtnImg_3.isSelected()) {
			label_nenDen_4.setBackground(Color.black);
		} else {
			label_nenDen_4.setBackground(Color.white);
		}

		if (tglbtnImg_4.isSelected()) {
			label_nenDen_5.setBackground(Color.black);
		} else {
			label_nenDen_5.setBackground(Color.white);
		}

		if (tglbtnImg_5.isSelected()) {
			label_nenDen_6.setBackground(Color.black);
		} else {
			label_nenDen_6.setBackground(Color.white);
		}

		if (tglbtnImg_6.isSelected()) {
			label_nenDen_7.setBackground(Color.black);
		} else {
			label_nenDen_7.setBackground(Color.white);
		}

		if (tglbtnImg_7.isSelected()) {
			label_nenDen_8.setBackground(Color.black);
		} else {
			label_nenDen_8.setBackground(Color.white);
		}

		if (tglbtnImg_8.isSelected()) {
			label_nenDen_9.setBackground(Color.black);
		} else {
			label_nenDen_9.setBackground(Color.white);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (!(tglbtnImg.isSelected() || tglbtnImg_1.isSelected() || tglbtnImg_2.isSelected() || tglbtnImg_3.isSelected()
				|| tglbtnImg_4.isSelected() || tglbtnImg_5.isSelected() || tglbtnImg_6.isSelected()
				|| tglbtnImg_7.isSelected() || tglbtnImg_8.isSelected())) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn các hình ảnh phù hợp!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else if ((tglbtnImg.isSelected() && tglbtnImg_4.isSelected() && tglbtnImg_8.isSelected())
				&& !(tglbtnImg_1.isSelected() || tglbtnImg_2.isSelected() || tglbtnImg_3.isSelected()
						|| tglbtnImg_5.isSelected() || tglbtnImg_6.isSelected() || tglbtnImg_7.isSelected())) {
			JOptionPane.showMessageDialog(this, "Xác nhận thành công!", "COMPLETE",
					JOptionPane.INFORMATION_MESSAGE);
			new BossView(this.nameOfBoss);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Bạn đã lựa chọn sai, vui lòng thực hiện xác nhận kế tiếp", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new Captcha2(this.nameOfBoss);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			this.dispose();
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

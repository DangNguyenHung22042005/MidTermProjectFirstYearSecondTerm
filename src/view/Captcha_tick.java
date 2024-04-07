package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Captcha_tick extends JFrame implements ActionListener, MouseListener {
	JToggleButton toggleButton_tick;
	ImageIcon tick_icon;
	ImageIcon white_icon;
	JButton btnTipTheo;
	String nameOfBoss;
	
	public Captcha_tick(String nameOfBoss) {
		this.nameOfBoss = nameOfBoss;
		this.setTitle("Captcha");
		this.setSize(574, 191);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		URL src1 = TrangDangNhap.class.getResource("/image/captcha_icon.jpg");
		Image temp1 = Toolkit.getDefaultToolkit().createImage(src1);
		ImageIcon captcha_icon = new ImageIcon(temp1.getScaledInstance(125, 98, temp1.SCALE_SMOOTH));

		URL src2 = TrangDangNhap.class.getResource("/image/tick_icon.jpg");
		Image temp2 = Toolkit.getDefaultToolkit().createImage(src2);
		tick_icon = new ImageIcon(temp2.getScaledInstance(58, 51, temp2.SCALE_SMOOTH));
		
		URL src3 = TrangDangNhap.class.getResource("/image/white_images.jpg");
		Image temp3 = Toolkit.getDefaultToolkit().createImage(src3);
		white_icon = new ImageIcon(temp3.getScaledInstance(58, 51, temp3.SCALE_SMOOTH));
		
		JLabel label_captcha_icon = new JLabel(captcha_icon);
		label_captcha_icon.setBounds(425, 10, 125, 98);
		getContentPane().add(label_captcha_icon);
		
		toggleButton_tick = new JToggleButton("");
		toggleButton_tick.setBounds(10, 33, 58, 51);
		toggleButton_tick.addActionListener(this);
		getContentPane().add(toggleButton_tick);
		
		JLabel label_text = new JLabel("Tôi không phải là người máy");
		label_text.setFont(new Font("Arial", Font.PLAIN, 20));
		label_text.setForeground(new Color(0, 0, 0));
		label_text.setBounds(78, 33, 319, 51);
		getContentPane().add(label_text);
		
		btnTipTheo = new JButton("Tiếp theo");
		btnTipTheo.setForeground(Color.GREEN);
		btnTipTheo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnTipTheo.setBounds(10, 113, 540, 31);
		btnTipTheo.addMouseListener(this);
		btnTipTheo.setVisible(false);
		getContentPane().add(btnTipTheo);
		
		JLabel label_nenTrang = new JLabel("");
		label_nenTrang.setBackground(Color.WHITE);
		label_nenTrang.setOpaque(true);
		label_nenTrang.setBounds(0, 0, 560, 154);
		getContentPane().add(label_nenTrang);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (toggleButton_tick.isSelected()) {
			toggleButton_tick.setIcon(tick_icon);
			btnTipTheo.setVisible(true);
		} else {
			toggleButton_tick.setIcon(white_icon);
			btnTipTheo.setVisible(false);
		}
	}

	public void mouseClicked(MouseEvent e) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Captcha1(this.nameOfBoss);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		this.dispose();
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

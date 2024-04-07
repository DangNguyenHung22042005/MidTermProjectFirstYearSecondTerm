package run;

import java.sql.Connection;
import javax.swing.UIManager;

import view.BossView;
import view.TrangDangNhap;

public class StartToRunBossView {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			TrangDangNhap start = new TrangDangNhap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}






















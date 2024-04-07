package run;

import javax.swing.UIManager;
import view.TrangDangNhapPlayer;

public class StartToRunPlayerView {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			TrangDangNhapPlayer start = new TrangDangNhapPlayer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

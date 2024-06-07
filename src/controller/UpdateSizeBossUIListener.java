package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.BossView;

public class UpdateSizeBossUIListener implements ActionListener {
	BossView view;
	
	public UpdateSizeBossUIListener(BossView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.SetSize();
	}

}
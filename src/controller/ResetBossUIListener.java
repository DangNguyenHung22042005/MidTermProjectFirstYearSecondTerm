package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.BossView;
import view.PlayerView;

public class ResetBossUIListener implements ActionListener {
	BossView view;
	
	public ResetBossUIListener(BossView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.ResetAll();
	}

}

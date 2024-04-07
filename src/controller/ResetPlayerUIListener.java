package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PlayerView;

public class ResetPlayerUIListener implements ActionListener {
	PlayerView view;
	
	public ResetPlayerUIListener(PlayerView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.ResetAnswer();
	}
}

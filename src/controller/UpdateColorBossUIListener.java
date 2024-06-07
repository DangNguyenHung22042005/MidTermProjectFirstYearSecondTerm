package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import view.BossView;

public class UpdateColorBossUIListener implements ActionListener {
	BossView view;
	
	public UpdateColorBossUIListener(BossView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
        int selectedIndex = cb.getSelectedIndex();
		view.SetColor(selectedIndex);
	}

}

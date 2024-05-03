package infor;

import java.io.Serializable;

public class LoginInfor implements Serializable {
	String nameOfPlayer;
	String passwordOfPlayer;
	
	public String getNameOfPlayer() {
		return nameOfPlayer;
	}
	public void setNameOfPlayer(String nameOfPlayer) {
		this.nameOfPlayer = nameOfPlayer;
	}
	public String getPasswordOfPlayer() {
		return passwordOfPlayer;
	}
	public void setPasswordOfPlayer(String passwordOfPlayer) {
		this.passwordOfPlayer = passwordOfPlayer;
	}
}

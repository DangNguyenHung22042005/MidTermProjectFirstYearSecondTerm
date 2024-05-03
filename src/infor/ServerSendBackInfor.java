package infor;

import java.io.Serializable;

public class ServerSendBackInfor implements Serializable {
	int scoreOfPlayer;
	Boolean token = false;
	
	public int getScoreOfPlayer() {
		return scoreOfPlayer;
	}
	public void setScoreOfPlayer(int scoreOfPlayer) {
		this.scoreOfPlayer = scoreOfPlayer;
	}
	public Boolean getToken() {
		return token;
	}
	public void setToken(Boolean token) {
		this.token = token;
	}
	
}

package infor;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class BossInfor implements Serializable {
	private ArrayList<ArrayList<Point>> lines;
	private String correctAnswer;
	
	public ArrayList<ArrayList<Point>> getLines() {
		return lines;
	}
	public void setLines(ArrayList<ArrayList<Point>> lines) {
		this.lines = lines;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}

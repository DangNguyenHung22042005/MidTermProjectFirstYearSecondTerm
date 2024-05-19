package xml;

public class Player {
	private String number, name, score;

	public Player(String number, String name, String score) {
		this.number = number;
		this.name = name;
		this.score = score;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	
}

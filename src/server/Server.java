package server;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import infor.BossInfor;
import infor.LoginInfor;
import infor.PlayerInfor;
import infor.ScoreUpdateInfor;
import infor.ServerSendBackInfor;
import infor.SignupInfor;
import panel.DrawingPanel;
import security.EncryptByMD5;
import xml.CreateXMLFileForPlayer;
import xml.Player;

public class Server implements Runnable {
	private ServerSocket serverSocket;
	private Thread thread;
	private ArrayList<ArrayList<Point>> lines;
	private String correctAnswer;
	private ObjectInputStream inputStream;
	private Object receivedData;
	private BossInfor boss;
	private PlayerInfor player;
	private List<Socket> listPlayer = new ArrayList<>();
	private List<Socket> listBoss = new ArrayList<>();
	private int countBoss = 0;
	private int countPlayer = 0;
	private int countLogin = 0;
	private int countSignup = 0;
	boolean check_ten;
	boolean check_mk;
	String nameOfPlayer = "";
	String passwordOfPlayer = "";
	int scoreOfPlayer = 0;
	private ServerSendBackInfor verify;
	
	Connection con;
	Statement stm;
	ResultSet rst;

	public Server() {
		try {
			serverSocket = new ServerSocket(2204);
			
			System.out.println("Port 1111 can be connect by client!");
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String url = "jdbc:sqlserver://HUNG\\SQLEXPRESS:1433;databaseName=TKPLAYER";
			String userName = "sa";
			String password = "123456789";

			con = DriverManager.getConnection(url, userName, password);
			stm = con.createStatement();
			con.setAutoCommit(false);

			check_ten = false;
			check_mk = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				if (clientSocket != null) {
					inputStream = new ObjectInputStream(clientSocket.getInputStream());
					receivedData = inputStream.readObject();
					if (receivedData instanceof BossInfor) {
						listBoss.add(clientSocket);
						System.out.println("New boss connected!");
						++countBoss;
						System.out.println("Boss live: " + countBoss);
						System.out.println("----------------------------");
						handleBoss(clientSocket);
					} else if (receivedData instanceof PlayerInfor) {
						listPlayer.add(clientSocket);
						System.out.println("New player connected!");
						++countPlayer;
						System.out.println("Player live: " + countPlayer);
						System.out.println("----------------------------");
						handlePlayer(clientSocket);
					} else if (receivedData instanceof LoginInfor) {
						System.out.println("New Login connected!");
						++countLogin;
						System.out.println("login live: " + countLogin);
						System.out.println("----------------------------");
						handleLogin(clientSocket);
					} else if (receivedData instanceof SignupInfor) {
						System.out.println("New Login connected!");
						++countSignup;
						System.out.println("Signup live: " + countSignup);
						System.out.println("----------------------------");
						handleSignup(clientSocket);
					}
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void handlePlayer(Socket clientSocket) {
		new Thread(() -> {
			try {
				while (true) {
					ObjectInputStream inputStreamPlayer = new ObjectInputStream(clientSocket.getInputStream());
					Object receivedDataPlayer = inputStreamPlayer.readObject();
					if (receivedDataPlayer instanceof ScoreUpdateInfor) {
						ScoreUpdateInfor newScore = (ScoreUpdateInfor)receivedDataPlayer;
						UpdateScoreDatabase(newScore.getScore(), newScore.getName(), newScore.getPassword());
						
						for (Player p : CreateXMLFileForPlayer._listPlayers) {
							System.out.println("Bắt đầu kiểm tra đối tượng " + p.getName() + " để tiến hành cập nhật điểm vào file xml!");
							System.out.println(p.getName()+ "-" + newScore.getName() + " " + Integer.parseInt(p.getScore()) + "-" + (newScore.getScore() - 10));
							if ((p.getName().equals(newScore.getName())) && (Integer.parseInt(p.getScore()) == (newScore.getScore() - 10))) {
								Player newPlayer = new Player(String.valueOf(p.getNumber()), p.getName(), String.valueOf(newScore.getScore()));
								CreateXMLFileForPlayer._listPlayers.remove(p);
								CreateXMLFileForPlayer.addPlayer(newPlayer);
								CreateXMLFileForPlayer.CallXMLForPlayer();
								return;
							}
						}
					} else {
						for (Socket boss : listBoss) {
							ObjectOutputStream bossOutputStream = new ObjectOutputStream(boss.getOutputStream());
							bossOutputStream.writeObject(receivedDataPlayer);
							bossOutputStream.flush();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void handleBoss(Socket clientSocket) {
		new Thread(() -> {
			try {
				while (true) {
					ObjectInputStream inputStreamBoss = new ObjectInputStream(clientSocket.getInputStream());
					Object receivedDataBoss = inputStreamBoss.readObject();
					for (Socket player : listPlayer) {
						ObjectOutputStream playerOutputStream = new ObjectOutputStream(player.getOutputStream());
						playerOutputStream.writeObject(receivedDataBoss);
						playerOutputStream.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	private void handleLogin(Socket clientSocket) {
		new Thread(() -> {
			try {
				while (true) {
					ObjectInputStream inputStreamLogin = new ObjectInputStream(clientSocket.getInputStream());
					Object receivedDataLogin = inputStreamLogin.readObject();
					if (receivedDataLogin instanceof LoginInfor) {
						LoginInfor login = (LoginInfor) receivedDataLogin;
						nameOfPlayer = login.getNameOfPlayer();
						passwordOfPlayer = login.getPasswordOfPlayer();
						verify = new ServerSendBackInfor();
						if (check()) {
							check_ten = false;
							check_mk = false;
							verify.setToken(true);
							verify.setScoreOfPlayer(scoreOfPlayer);
							
							Player newPlayer = new Player(String.valueOf(countPlayer), nameOfPlayer, String.valueOf(scoreOfPlayer));
							CreateXMLFileForPlayer.addPlayer(newPlayer);
							CreateXMLFileForPlayer.CallXMLForPlayer();
						} 
						ObjectOutputStream loginOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
						loginOutputStream.writeObject(verify);
						loginOutputStream.flush();
						verify = new ServerSendBackInfor();
						verify.setToken(false);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	private void handleSignup(Socket clientSocket) {
		new Thread(() -> {
			try {
				while (true) {
					ObjectInputStream inputStreamLogin = new ObjectInputStream(clientSocket.getInputStream());
					Object receivedDataLogin = inputStreamLogin.readObject();
					if (receivedDataLogin instanceof SignupInfor) {
						SignupInfor signupIF = (SignupInfor) receivedDataLogin;
						String name = signupIF.getName();
						String password = signupIF.getPassword();
						CreateAccount(name, password);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	boolean check() {
		try {
			ResultSet rst = stm.executeQuery("SELECT *\r\n" + "FROM TAIKHOAN");

			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();

			String tenNguoiDungNhap = nameOfPlayer;
			
			String matKhauNguoiDungNhapCuoiCung = passwordOfPlayer;
			String matKhauSauKhiMaHoa = EncryptByMD5.encryptMD5(matKhauNguoiDungNhapCuoiCung);

			while (rst.next()) {
				for (int i = 1; i <= num_column; i++) {
					if (i == 1) {
						if (tenNguoiDungNhap.equals(rst.getString(i))) {
							check_ten = true;
						}
					}
					if (i == 2) {
						if (matKhauSauKhiMaHoa.equals(rst.getString(i))) {
							check_mk = true;
						}
					}
					if (i == 3) {
						this.scoreOfPlayer = rst.getInt(i);
					}
				}
				if (check_ten && check_mk) {
					return true;
				} else {
					check_ten = false;
					check_mk = false;
				}
			}
			rst.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	private void UpdateScoreDatabase(int score, String name, String password) {
		try {
			String sql = "UPDATE TAIKHOAN SET score = " + score + " WHERE username = '" + name + "' AND password = '" + password + "'";
			con.setAutoCommit(false);
			stm.executeUpdate(sql);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void CreateAccount(String name, String password) {
		try {
			String matKhauSauKhiMaHoa = EncryptByMD5.encryptMD5(password);
			String sql = "INSERT INTO TAIKHOAN " + "VALUES (N'" + name + "', N'" + matKhauSauKhiMaHoa + "', " + 0 + ")";
			con.setAutoCommit(false);
			stm.executeUpdate(sql);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

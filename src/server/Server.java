package server;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import infor.BossInfor;
import infor.PlayerInfor;
import panel.DrawingPanel;

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

	public Server() {
		try {
			serverSocket = new ServerSocket(1111);
			System.out.println("Port 1111 can be connect by client!");
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
					for (Socket boss : listBoss) {
						ObjectOutputStream bossOutputStream = new ObjectOutputStream(boss.getOutputStream());
						bossOutputStream.writeObject(receivedDataPlayer);
						bossOutputStream.flush();
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

	public static void main(String[] args) {
		new Server();
	}
}

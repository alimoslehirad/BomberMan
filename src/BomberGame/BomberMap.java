package BomberGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class BomberMap extends JFrame implements KeyListener {
	BomberMan[] player = new BomberMan[4];
	GameController g;
	private boolean closeFlag;
	private int well_cnt;
	private ObjectPool v = new ObjectPool();
	private Timer timer, timer1;
	private boolean initFlag;

	public BomberMap(String title, int x, int y) {
		Runnable r = new Client(this);
		closeFlag = false;
		initFlag = false;
		Thread t = new Thread(r);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, x * 50, y * 50 + 30);
		setVisible(true);
		setTitle(title);
		BomberMapMask_init(v.frameMask);
		addKeyListener(this);
		player[0] = new BomberMan("pics/player1.png", 0, 0);
		player[1] = new BomberMan("pics/player2.png", 0, 13);
		player[2] = new BomberMan("pics/player1.png", 13, 0);
		player[3] = new BomberMan("pics/player1.png", 13, 13);
		initFlag = true;
		repaint();
		timer = new Timer();
		timer.schedule(new RemindTask(), 160 * 1000);
		well_cnt = 0;
		t.start();
		g = new GameController();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("close");
				setCloseFlag();
			}

		});
	}

	private void setCloseFlag() {
		closeFlag = true;
	}

	private boolean isCloseFlag() {
		return closeFlag;
	}

	private void wellGenerator() {
		timer1 = new Timer();
		timer1.schedule(new RemindTask2(), 3 * 1000);
		int indexi = (int) (Math.random() * 13);
		int indexj = (int) (Math.random() * 13);
		v.obs[indexi][indexj] = new Well_c(v.obs[indexi][indexj].xPos, v.obs[indexi][indexj].yPos);
		well_cnt++;
		repaint();
	}

	private void BomberMapMask_init(int[][] mask) {
		try {
			v.fileName = "BomberMap.txt";
			FileReader fileReader = new FileReader(v.fileName);
			v.bufferedReader = new BufferedReader(fileReader);

		} catch (FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" +
							v.fileName + "'");
		}


		String[] strArray;
		for (int i = 0; i < 14; i++) {
			try {
				String line = v.bufferedReader.readLine();

				if (line == null) v.bufferedReader.close();
				strArray = Objects.requireNonNull(line).split(" ");
				for (int j = 0; j < 14; j++) {
					mask[i][j] = Integer.parseInt(strArray[j]);
					if (mask[i][j] == v.BoxID) {
						v.obs[i][j] = new Box_c(j * 50, i * 50 + v.y0);

					}
					if (mask[i][j] == v.wallID) {
						v.obs[i][j] = new Wall_c();
						v.obs[i][j].setPos(j * 50, i * 50 + v.y0);
					}
					if (mask[i][j] == v.Blank) {
						v.obs[i][j] = new Blank_c(j * 50, i * 50 + v.y0);


					}

				}

			} catch (FileNotFoundException ex) {
				System.out.println(
						"Unable to open file '" +
								v.fileName + "'");
			} catch (IOException ex) {
				System.out.println(
						"Error reading file '"
								+ v.fileName + "'");
			}
		}

	}

	public void paint(Graphics g) {
		if (initFlag) {
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 700, 730);
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 14; j++) {
					v.obs[i][j].draw(this, g2);
				}

			}
			for (int i = 0; i < 4; i++) {
				if (player[i].isAlive())
					player[i].draw(g2, this);
			}

		}

	}

	public void keyPressed(KeyEvent e) {
		g.keyPressedAct(Integer.toString(e.getKeyCode()), v.obs, player, this);
		repaint();
	}

	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	class RemindTask extends TimerTask {
		public void run() {
			System.out.println("Game Time's up!");
			wellGenerator();
			timer.cancel(); //Terminate the timer thread
		}
	}

	class RemindTask2 extends TimerTask {
		public void run() {
			System.out.println("Well generator Time's up!");
			if (well_cnt < 50) {
				wellGenerator();
			} else {
				timer1.cancel(); //Terminate the timer thread
			}
		}
	}

	///==========================================================================
	//=======================================================================
	class Client implements Runnable {
		Socket mSocket;
		BomberMap bomberMap;
		int port = 10005;
		String serverAddress = "192.168.1.111";

		//    String serverAddress = "127.0.0.1";
		public Client(BomberMap b) {
			bomberMap = b;
		}

		public void run() {
			while (isCloseFlag()) {
				try {
					System.out.println("connecting...");
					mSocket = new Socket(serverAddress, port);
					System.out.println("connect to server ....  " + port);
					BufferedReader input = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
					String answer = input.readLine();
					System.out.println(answer);
					g.keyPressedAct(answer, v.obs, player, bomberMap);
					repaint();
					input.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

}
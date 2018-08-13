package BomberGame;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
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
		ToClient_lissening t1 = new ToClient_lissening(this,9091,0);
		ToClient_lissening t2 = new ToClient_lissening(this,9092,1);
		ToClient_lissening t3 = new ToClient_lissening(this,9093,2);
		ToClient_lissening t4 = new ToClient_lissening(this,9094,3);
		closeFlag = false;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, x * 50, y * 50 + 30);
		setVisible(true);
		setTitle(title);
		BomberMapMask_init(v.frameMask);
		addKeyListener(this);
		player[0] = new BomberMan("pics/player1.png", 0, 0);player[0].name="P0";
		player[1] = new BomberMan("pics/player2.png", 0, 13);player[1].name="P1";
		player[2] = new BomberMan("pics/player1.png", 13, 0);player[2].name="P2";
		player[3] = new BomberMan("pics/player1.png", 13, 13);player[3].name="P3";
		timer = new Timer();
		timer.schedule(new RemindTask(),160 * 1000);
		well_cnt = 0;
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		g = new GameController();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("close");
				setCloseFlag();
			}
		});
		initFlag = true;
	}

	private void setCloseFlag() {
		closeFlag = true;
	}

	private boolean isCloseFlag() {
		return closeFlag;
	}

	private void wellGenerator(BomberMap map) {
		timer1 = new Timer();
		timer1.schedule(new RemindTask2(), 3 * 1000);
		int indexi = (int) (Math.random() * 13);
		int indexj = (int) (Math.random() * 13);
		v.obs[indexi][indexj] = new Well_c(v.obs[indexi][indexj].xPos, v.obs[indexi][indexj].yPos);
		well_cnt++;
		sendMapChange2server(indexi,indexj,v.obs[indexi][indexj].getID());
		map.mapCellUpdating(v.obs[indexi][indexj]);
	}

	private void BomberMapMask_init(int[][] mask) {
		System.out.println("BomberInit function");
	}
private MapCell[][] creatMapCell(int [][] mask){
		MapCell[][] map =new MapCell[14][14];
	for (int i = 0; i < 14; i++) {
		for (int j = 0; j < 14; j++) {
			if (mask[i][j] == v.BoxID) {
				map[i][j] = new Box_c(j * 50, i * 50 + v.y0);
			}
			if (mask[i][j] == v.wallID) {
				map[i][j] = new Wall_c();
				map[i][j].setPos(j * 50, i * 50 + v.y0);
			}
			if (mask[i][j] == v.Blank) {
				map[i][j] = new Blank_c(j * 50, i * 50 + v.y0);
			}
			if (mask[i][j] == v.BompID) {
				map[i][j] = new Bomb_c(j * 50, i * 50 + v.y0);
			}
			if (mask[i][j] == v.WellID) {
				map[i][j] = new Well_c(j * 50, i * 50 + v.y0);
			}
			if (mask[i][j] == v.flameID) {
				map[i][j] = new Flame_c(j * 50, i * 50 + v.y0);
			}
		}
	}
	return  map;
}
public void paint(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
//	v.obs = creatMapCell(bomberMapUpdating("inGame"));
	if(initFlag) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 700, 730);
		v.obs = creatMapCell(bomberMapUpdating("initialize"));
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				v.obs[i][j].draw(this, g2);
			}
		}
		initFlag=false;
	}
	else {

		v.cell.draw(this, g2);
	}

	for (int i = 0; i < 4; i++) {
		if (player[i].isAlive())
			player[i].draw(g2, this);
	}
	}
public int[][] bomberMapUpdating(String mode){
		int[][] map=new int[14][14];
	try {
		String serverAddress="127.0.0.1";
//		String serverAddress="79.175.133.67";
		int serverPort=9090;
		Socket socket = new Socket(serverAddress,serverPort);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("Map R "+mode+ " End");
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String input="";
		while(input.equals("")) {
			input = in.readLine();
		}
		System.out.println("end receiving");
		System.out.println(input);
		String[] strArray;
		strArray=input.split(" ");
		socket.close();
		out.close();
		int k=1;
		for(int i=0;i<14;i++){
			for(int j=0;j<14;j++){
				map[i][j]=Integer.parseInt(strArray[k]);
				k++;
			}
		}

	} catch (IOException ex) {
	}
	return map;
}

public boolean sendPacket2Server(String packet,int port,String serverIP){
	try {
		Socket socket = new Socket(serverIP,serverPort);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(packet);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String input="";
		while(input.equals("")) {
			input = in.readLine();
		}
		System.out.println("end receiving");
		System.out.println(input);
		String[] strArray;
		strArray=input.split(" ");
		socket.close();
		out.close();
		int k=1;
		for(int i=0;i<14;i++){
			for(int j=0;j<14;j++){
				map[i][j]=Integer.parseInt(strArray[k]);
				k++;
			}
		}

	} catch (IOException ex) {
	}

}
	public void keyPressed(KeyEvent e) {
		g.keyPressedAct(Integer.toString(e.getKeyCode()), v.obs, player, this,0);
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_S){
			initFlag=true;
			v.cell = new Blank_c(0 * 50, 1 * 50 + v.y0);
			repaint();
		}

	}


	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	public void sendMapChange2server(int indexi,int indexj,int CellID){
		try {
			String serverAddress="127.0.0.1";
//			String serverAddress="79.175.133.67";
			int serverPort=9090;
			Socket socket = new Socket(serverAddress,serverPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Map W "+ Integer.toString(indexi)+" "+Integer.toString(indexj)+" "+Integer.toString(CellID)+" " +"End");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input="";
			while(input.equals("")) {
				input = in.readLine();
			}
			System.out.println("end Map writing");
			System.out.println(input);
			socket.close();
			out.close();
		} catch (IOException ex) {
		}


	}
	class RemindTask extends TimerTask {
		public void run() {
			System.out.println("Game Time's up!");
			wellGenerator(BomberMap.this);
			timer.cancel(); //Terminate the timer thread
		}
	}

	class RemindTask2 extends TimerTask {
		public void run() {
			System.out.println("Well generator Time's up!");
			if (well_cnt < 50) {
				wellGenerator(BomberMap.this);
			} else {
				timer1.cancel(); //Terminate the timer thread
			}
		}
	}

	//=====================================================================
	public void mapCellUpdating(MapCell cell){
		v.cell=cell;
		repaint();
	}



	///==========================================================================
	//=======================================================================
	class ToClient_lissening extends Thread {
		ServerSocket mServer;
		BomberMap bomberMap;
		int port;
		int clientId;

		public ToClient_lissening(BomberMap b , int port,int clientId) {
			bomberMap = b;
			this.port=port;
			this.clientId=clientId;
		}

		public void run() {
			try {

				// create server socket!
				mServer = new ServerSocket(port);
				System.out.println("Server Created! port:" + port);
			}catch (IOException e){
				System.out.println(e.getMessage());
			}
			while (!isCloseFlag()) {
				try {
					// wait for client
					Socket socket = mServer.accept();
					System.out.println("Connected to	" + port);
					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String answer = input.readLine();
					System.out.println(answer);
					g.keyPressedAct(answer, v.obs, player, bomberMap,clientId);
					repaint();
					input.close();

				}catch (IOException ex ){
					ex.printStackTrace();
					try{mServer.close();}catch(IOException e){}
				}

			}
		}

	}

}
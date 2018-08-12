package BomberGame;
import java.io.*;
import BomberInit.BomberKeyConfig;
import java.io.FileOutputStream;
import java.net.Socket;

class GameController {
	private BomberKeyConfig keyConf = new BomberKeyConfig();

	private int findTask(String[][] key, String keyCode,  int clientId) {
		int i;boolean flag =false;

		for (i = 0; i < 5; i++) {
			if (key[clientId][i].equals(keyCode)) { flag=true; break; }
		}
		if(flag) return i;
		else  return -1;
	}

	void keyPressedAct(String KeyCode, MapCell[][] obs, BomberMan[] player, BomberMap P, int clientId) {
		int[] x = new int[2];
		int playerId=clientId;
		playersStat_update(P);
		int taskId=findTask(keyConf.keys, KeyCode, clientId);

		if (permissionToTask(taskId, obs, P,clientId)) {

			P.player[playerId].react(taskId, obs, player, P);
		}

		playerStatWrite2Server(P.player[0]);


	}

	private boolean permissionToTask( int taskId,MapCell[][] obs, BomberMap P,int clientId) {
		int UP = 0;
		int DOWN = 1;
		int LEFT = 2;
		int RIGHT = 3;
		int BOMB = 4;
		boolean permission = false;
		if (taskId == UP) {
			permission = moveUp_permission(obs, P.player[clientId]);
		} else if (taskId == DOWN) {
			permission = moveDown_permission(obs, P.player[clientId]);
		} else if (taskId == RIGHT) {
			permission = moveRight_permission(obs, P.player[clientId]);
		} else if (taskId == LEFT) {
			permission = moveLeft_permission(obs, P.player[clientId]);
		} else if (taskId == BOMB) {
			permission = bombing_permission(P.player[clientId]);

		}
		return permission;
	}

	private boolean moveRight_permission(MapCell[][] d, BomberMan m) {

		boolean permission = false;
		if (m.indexj != 13) {
			if (d[m.indexi][m.indexj + 1].isCrossPermission()) {
				permission = true;

			}
		}
		return permission;
	}

	private boolean moveLeft_permission(MapCell[][] d, BomberMan m) {
		boolean permission = false;
		if (m.indexj != 0) {
			if (d[m.indexi][m.indexj - 1].isCrossPermission()) {
				permission = true;
			}
		}

		return permission;

	}

	private boolean moveUp_permission(MapCell[][] d, BomberMan m) {
		boolean permission = false;
		if (m.indexi != 0) {
			if (d[m.indexi - 1][m.indexj].isCrossPermission()) {
				permission = true;
			}
		}

		return permission;
	}

	private boolean moveDown_permission(MapCell[][] d, BomberMan m) {
		boolean permission = false;
		if (m.indexi != 13) {
			if (d[m.indexi + 1][m.indexj].isCrossPermission()) {
				permission = true;
			}
		}

		return permission;
	}

	private boolean bombing_permission(BomberMan m) {
		boolean permission = false;
		if (m.concurrentBombing_num != 0) {
			permission = true;
		}
		return permission;
	}
	public boolean playersStat_update(BomberMap map){
		for(int i=0;i<4;i++){
			System.out.println("i==="+i);
			playerStatUpdate(map.player[i]);
		}
		return true;
	}
	public void playerStatUpdate(BomberMan p){
		try {
			String serverAddress="127.0.0.1";
			int serverPort=9090;
			Socket socket = new Socket(serverAddress,serverPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(p.name+" R End");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input="";
			while(input.equals("")) {
				input = in.readLine();
			}
			System.out.println("end recieving");
			System.out.println(input);
			String[] strArray;
			strArray=input.split(" ");
			p.indexi=Integer.parseInt(strArray[1]);
			p.indexj=Integer.parseInt(strArray[2]);
			socket.close();
			out.close();
		} catch (IOException ex) {
		}


	}


	public boolean  playerStatWrite2Server(BomberMan p){
		try {
		String serverAddress="127.0.0.1";
		int serverPort=9090;
		Socket socket = new Socket(serverAddress,serverPort);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		out.println(p.name+" W "+p.indexi+" "+p.indexj +" End");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input="";
			while(input.equals("")) {
				input = in.readLine();
			}
			System.out.println("write to database is done");
			System.out.println(input);
//			String[] strArray;
//			strArray=input.split(" ");
//			p.indexi=Integer.parseInt(strArray[1]);
//			p.indexj=Integer.parseInt(strArray[2]);
			socket.close();
			out.close();
		} catch (IOException ex) {
		}
		return true;
	}
}

package BomberGame;
import java.io.*;
import BomberInit.BomberKeyConfig;
import java.io.FileOutputStream;
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
		int taskId=findTask(keyConf.keys, KeyCode, clientId);

		if (permissionToTask(taskId, obs, P,clientId)) {

			P.player[playerId].react(taskId, obs, player, P);
		}
		writePlayerPosToTextFile(P);
	}

	private void writePlayerPosToTextFile(BomberMap P) {
		try {
			FileOutputStream fout = new FileOutputStream("BomberManStat.txt");
			String textStr = "";
			StringBuffer sb = new StringBuffer("Hello");
			textStr="";

			for (int i = 0; i < 4; i++) {
 					textStr+="P"+Integer.toString(i)+":\n" + "Xpos=" + Integer.toString( P.player[i].xPos) +"\n";
					textStr+= "Ypos=" + Integer.toString( P.player[i].yPos)+ " \n"+"indexi=" + Integer.toString( P.player[i].indexi) +" \n"+
							"indexj=" + Integer.toString( P.player[i].indexj)  +" \n" + "end"+"\n";

				}

				byte c[] = textStr.getBytes();//converting string into byte array
				fout.write(c);
				fout.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
/*		try{

			FileOutputStream fs = new FileOutputStream("ali.ser");
			ObjectOutputStream os=new ObjectOutputStream(fs);
			os.writeObject(P.player[0]);
			os.close();

		}
		catch (Exception ex){
			ex.printStackTrace();

		}*/

		System.out.println("Player stat write success...");

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
	public void playersStat_update(BomberMan[] players){
		for(int i=0;i<4;i++){

		}

	}
	public void playerStatUpdate(BomberMan p){

	}
}

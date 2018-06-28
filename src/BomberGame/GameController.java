package BomberGame;

import BomberInit.BomberKeyConfig;

 class GameController {
	private  BomberKeyConfig keyConf = new BomberKeyConfig();

	private void findTask(String[][] key, String keyCode, int[] x) {
		boolean flag = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				if (key[i][j].equals(keyCode)) {
					x[0] = i;
					x[1] = j;
					flag = true;
					break;
				}
			}
			if (flag) break;
		}

	}

	void keyPressedAct(String KeyCode, MapCell[][] obs, BomberMan[] player, BomberMap P) {
		int[] x = new int[2];
		findTask(keyConf.keys, KeyCode, x);
		if (permissionToTask(x, obs, P)) {

			P.player[x[0]].react(x[1], obs, player, P);
		}
	}

	private boolean permissionToTask(int[] x, MapCell[][] obs, BomberMap P) {
		   int UP = 0;
		   int DOWN = 1;
		   int LEFT = 2;
		   int RIGHT = 3;
		   int BOMB = 4;
		boolean permission = false;
		if (x[1] == UP) {
			permission = moveUp_permission(obs, P.player[x[0]]);
		} else if (x[1] == DOWN) {
			permission = moveDown_permission(obs, P.player[x[0]]);
		} else if (x[1] == RIGHT) {
			permission = moveRight_permission(obs, P.player[x[0]]);
		} else if (x[1] == LEFT) {
			permission = moveLeft_permission(obs, P.player[x[0]]);
		} else if (x[1] == BOMB) {
			permission = bombing_permission(P.player[x[0]]);

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

	private  boolean moveDown_permission(MapCell[][] d, BomberMan m) {
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
		if(m.concurrentBombing_num!=0){
			permission=true;
		}


		return permission;
	}
}

package BomberGame;

import BomberInit.BomberKeyConfig;

public class GameController {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int BOMB = 4;
	public final int Blank = 0;
	BomberKeyConfig keyConf = new BomberKeyConfig();


	public GameController() {

	}


	public void findTask(String[][] key, String keyCode, int[] x) {
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

	public void keyPressedAct(String KeyCode, MapCell[][] obs, BomberMan[] player, BomberMap P) {
		int[] x = new int[2];
		findTask(keyConf.keys, KeyCode, x);
		if (permitionToTask(x, obs, P)) {

			P.player[x[0]].react(x[1], obs, player, P);
		}
	}

	public boolean permitionToTask(int[] x, MapCell[][] obs, BomberMap P) {
		boolean permition = false;
		if (x[1] == UP) {
			permition = moveUp_permition(obs, P.player[x[0]]);
		} else if (x[1] == DOWN) {
			permition = moveDown_permition(obs, P.player[x[0]]);
		} else if (x[1] == RIGHT) {
			permition = moveRight_permition(obs, P.player[x[0]]);
		} else if (x[1] == LEFT) {
			permition = moveLeft_permition(obs, P.player[x[0]]);
		} else if (x[1] == BOMB) {
			permition = bombing_permition(P.player[x[0]]);

		}
		return permition;

	}

	public boolean moveRight_permition(MapCell[][] d, BomberMan m) {

		boolean permition = false;
		if (m.indexj != 13) {
			if (d[m.indexi][m.indexj + 1].isCrossPermition()) {
				permition = true;
			} else {
				permition = false;
			}
		}

		return permition;
	}


	boolean moveLeft_permition(MapCell[][] d, BomberMan m) {
		boolean permition = false;
		if (m.indexj != 0) {
			if (d[m.indexi][m.indexj - 1].isCrossPermition()) {
				permition = true;
			} else {
				permition = false;
			}
		}

		return permition;

	}

	boolean moveUp_permition(MapCell[][] d, BomberMan m) {
		boolean permition = false;
		if (m.indexi != 0) {
			if (d[m.indexi - 1][m.indexj].isCrossPermition()) {
				permition = true;
			} else {
				permition = false;
			}
		}

		return permition;
	}

	boolean moveDown_permition(MapCell[][] d, BomberMan m) {
		boolean permition = false;
		if (m.indexi != 13) {
			if (d[m.indexi + 1][m.indexj].isCrossPermition()) {
				permition = true;
			} else {
				permition = false;
			}
		}

		return permition;
	}

	boolean bombing_permition(BomberMan m) {
		boolean permition = true;


		return permition;
	}
}

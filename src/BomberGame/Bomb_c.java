package BomberGame;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb_c extends MapCell {
	private final byte BreadytToFire = 1;
	private final byte BFired = 0;
	public int indexi;
	public int indexj;

	public int bombFlame;
	private MapCell[][] obs;
	private Timer timer;
	byte state;
	private BomberMan[] player;
	private BomberMap gameMap;
	private int second;
	private int ID;

	public Bomb_c(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		crossPermission = true;
		probability = .125;
		second = 3;
		img = Toolkit.getDefaultToolkit().getImage("pics/Bomb.jpg");
		ID = 5;

	}

	 void BombTimer_start(MapCell[][] obs1, BomberMan[] player, BomberMap P) {
		timer = new Timer();
		timer.schedule(new RemindTask(), second * 1000);
		this.obs = obs1;
		this.gameMap = P;
		this.player = player;
	}

	public int getID() {
		return this.ID;
	}

	@Override
	public boolean getToFireAction() {
		return true;
	}

	public void draw(BomberMap P, Graphics2D g) {
		g.drawImage(this.img, this.xPos, this.yPos, P);
	}

	private void downFlameAction(int flamePower, MapCell[][] obs, BomberMan[] player, BomberMap map) {
		for (int k = 1; k <= flamePower; k++) {
			if (indexi < 13) {
				if (obs[indexi + k][indexj].getToFireAction()) {
					obs[indexi + k][indexj] = obs[indexi + k][indexj].content;
					map.sendMapChange2server(indexi+k,indexj,obs[indexi+k][indexj] .getID());
					map.mapCellUpdating(obs[indexi+k][indexj]);
				}
				for (int i = 0; i < 4; i++) {
					if ((player[i].indexi == (indexi + k)) && (player[i].indexj == indexj)) {
						player[i].alive = false;
					}

				}
			}
		}
	}

	private void upFlameAction(int flamePower, MapCell[][] obs, BomberMan[] player, BomberMap map) {
		for (int k = 1; k <= flamePower; k++) {
			if (indexi > 0) {
				if (obs[indexi - k][indexj].getToFireAction()) {
					obs[indexi - k][indexj] = obs[indexi - k][indexj].content;
					map.sendMapChange2server(indexi-k,indexj,obs[indexi-k][indexj] .getID());
					map.mapCellUpdating(obs[indexi-k][indexj ]);
				}
				for (int i = 0; i < 4; i++) {
					if ((player[i].indexi == (indexi - k)) && (player[i].indexj == indexj)) {
						player[i].alive = false;
					}

				}
			}
		}
	}

	private void rightFlameAction(int flamePower, MapCell[][] obs, BomberMan[] player, BomberMap map) {
		for (int k = 1; k <= flamePower; k++) {
			if (indexj < 13) {
				if (obs[indexi][indexj + k].getToFireAction()) {
					obs[indexi][indexj + k] = obs[indexi][indexj + k].content;
					map.sendMapChange2server(indexi,indexj+k,obs[indexi][indexj + k] .getID());
					map.mapCellUpdating(obs[indexi][indexj + k]);
				}
				for (int i = 0; i < 4; i++) {
					if ((player[i].indexi == indexi) && (player[i].indexj == (indexj + k))) {
						player[i].alive = false;
					}

				}
			}
		}
	}

	private void leftFlameAction(int flamePower, MapCell[][] obs, BomberMan[] player, BomberMap map) {
		for (int k = 1; k <= flamePower; k++) {
			if (indexj > 0) {
				if (obs[indexi][indexj - k].getToFireAction()) {
					obs[indexi][indexj - k] = obs[indexi][indexj - k].content;
					map.sendMapChange2server(indexi,indexj-k,obs[indexi][indexj - k] .getID());
					map.mapCellUpdating(obs[indexi][indexj - k]);
				}
				for (int i = 0; i < 4; i++) {
					if ((player[i].indexi == indexi) && (player[i].indexj == (indexj - k))) {
						player[i].alive = false;
					}

				}
			}
		}
	}

	 void bombـexplosion(MapCell[][] obs, BomberMan[] player , BomberMap map) {

		if (this.state == BreadytToFire) {
			obs[indexi][indexj] = new Blank_c(obs[indexi][indexj].xPos, obs[indexi][indexj].yPos);
			map.sendMapChange2server(indexi,indexj,obs[indexi][indexj].getID());
			map.mapCellUpdating(obs[indexi][indexj]);
			downFlameAction(bombFlame, obs, player,map);
			upFlameAction(bombFlame, obs, player,map);
			rightFlameAction(bombFlame, obs, player,map);
			leftFlameAction(bombFlame, obs, player,map);
		}

		state = BFired;

	}

	class RemindTask extends TimerTask {
		public void run() {
			System.out.println("Time's up!");
			bombـexplosion(obs, player,gameMap);
			gameMap.repaint();
			timer.cancel(); //Terminate the timer thread
		}
	}


}

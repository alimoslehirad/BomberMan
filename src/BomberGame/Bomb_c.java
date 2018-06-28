package BomberGame;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb_c extends MapCell {
	private final byte BreadytToFire = 1;
	private final byte BFired = 0;
	private int indexi;
	private int indexj;
	private int bombFlame;
	private BomberMan[] player;
	private BomberMap gameMap;
	MapCell[][] obs;
	Timer timer;
	byte state;
	private int secound;
	private int ID;

	public Bomb_c(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		crossPermition = true;
		probability = .125;
		secound = 3;
		img = Toolkit.getDefaultToolkit().getImage("pics/Bomb.jpg");
		ID = 5;

	}

	private void BombTimer_start(MapCell[][] obs1, BomberMan[] player, BomberMap P) {
		timer = new Timer();
		timer.schedule(new RemindTask(), secound * 1000);
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

	private void downFlameAction(int flamePower, MapCell[][] obs, BomberMan[] player) {
		for (int k = 1; k <= flamePower; k++) {
			if (indexi < 13) {
				if (obs[indexi + k][indexj].getToFireAction()) {
					obs[indexi + k][indexj] = obs[indexi + k][indexj].content;
				}
				for (int i = 0; i < 4; i++) {
					if ((player[i].indexi == (indexi + k)) && (player[i].indexj == indexj)) {
						player[i].alive = false;
					}

				}
			}
		}
	}

	private void upFlameAction(int flamePower, MapCell[][] obs, BomberMan[] player) {
		for (int k = 1; k <= flamePower; k++) {
			if (indexi > 0) {
				if (obs[indexi - k][indexj].getToFireAction()) {
					obs[indexi - k][indexj] = obs[indexi - k][indexj].content;

				}
				for (int i = 0; i < 4; i++) {
					if ((player[i].indexi == (indexi - k)) && (player[i].indexj == indexj)) {
						player[i].alive = false;
					}

				}
			}
		}
	}

	private void rightFlameAction(int flamePower, MapCell[][] obs, BomberMan[] player) {
		for (int k = 1; k <= flamePower; k++) {
			if (indexj < 13) {
				if (obs[indexi][indexj + k].getToFireAction()) {
					obs[indexi][indexj + k] = obs[indexi][indexj + k].content;
				}
				for (int i = 0; i < 4; i++) {
					if ((player[i].indexi == indexi) && (player[i].indexj == (indexj + k))) {
						player[i].alive = false;
					}

				}
			}
		}
	}

	private void leftFlameAction(int flamePower, MapCell[][] obs, BomberMan[] player) {
		for (int k = 1; k <= flamePower; k++) {
			if (indexj > 0) {
				if (obs[indexi][indexj - k].getToFireAction()) {
					obs[indexi][indexj - k] = obs[indexi][indexj - k].content;
				}
				for (int i = 0; i < 4; i++) {
					if ((player[i].indexi == indexi) && (player[i].indexj == (indexj - k))) {
						player[i].alive = false;
					}

				}
			}
		}
	}

	public void bombـexplosion(MapCell[][] obs, BomberMan[] player) {

		if (this.state == BreadytToFire) {
			downFlameAction(bombFlame, obs, player);
			upFlameAction(bombFlame, obs, player);
			rightFlameAction(bombFlame, obs, player);
			leftFlameAction(bombFlame, obs, player);
		}
		obs[indexi][indexj] = new Blank_c(obs[indexi][indexj].xPos, obs[indexi][indexj].yPos);
		state = BFired;


	}

	class RemindTask extends TimerTask {
		public void run() {
			System.out.println("Time's up!");
			bombـexplosion(obs, player);
			gameMap.repaint();
			timer.cancel(); //Terminate the timer thread
		}
	}


}

package BomberGame;

import java.awt.*;

public class BomberMan extends MovingObject {
	 int concurrentBombing_num;
	 int bombingFlame;
	 boolean bombingFlag;
	private Bomb_c b;
	private ObjectPool v = new ObjectPool();
	private Image img;
	public String name;
	public BomberMan(String s, int indexi, int indexj) {
		width = 50;
		height = 50;
		concurrentBombing_num = 1;
		this.indexi = indexi;
		this.indexj = indexj;
		xPos = indexi * 50;
		yPos = indexj * 50 + 30;
		bombingFlame = 1;
		alive = true;
		img = Toolkit.getDefaultToolkit().getImage(s);
		bombingFlag=false;

	}

	public boolean isAlive() {
		return alive;
	}

	public void setPosition() {
		xPos = indexj * 50;
		yPos = indexi * 50 + 30;
	}

	private void getPoint(MapCell[][] obs, BomberMap P) {
		if (obs[indexi][indexj].getID() == 9) {
			bombingFlame = 2;
		} else if (obs[indexi][indexj].getID() == 7) {
			this.alive = false;
		} else if (obs[indexi][indexj].getID() == 5) {
			concurrentBombing_num++;
		}
		obs[indexi][indexj] = new Blank_c(obs[indexi][indexj].getxPos(), obs[indexi][indexj].getyPos());
		P.sendMapChange2server(indexi,indexj,obs[indexi][indexj].getID());
		P.mapCellUpdating(obs[indexi][indexj]);
	}

	public void react(int cmd, MapCell[][] obs, BomberMan[] player, BomberMap P) {

		if (cmd == 0) {
			move_up();
			getPoint(obs, P);
			if(!bombingFlag)
				v.cell=new Blank_c((indexj) * 50, (indexi+1) * 50 + v.y0);
			P.mapCellUpdating(v.cell);
			bombingFlag=false;
		} else if (cmd == 1) {
			move_down();
			getPoint(obs, P);
			if(!bombingFlag)
				v.cell=new Blank_c((indexj) * 50, (indexi-1) * 50 + v.y0);
			P.mapCellUpdating(v.cell);
			bombingFlag=false;
		} else if (cmd == 2) {
			move_left();
			getPoint(obs, P);
			if(!bombingFlag)
				v.cell=new Blank_c((indexj+1) * 50, (indexi) * 50 + v.y0);
			P.mapCellUpdating(v.cell);
			bombingFlag=false;
		} else if (cmd == 3) {
			move_right();
			getPoint(obs, P);
			if(!bombingFlag)
				v.cell=new Blank_c((indexj-1) * 50, (indexi) * 50 + v.y0);
			P.mapCellUpdating(v.cell);
			bombingFlag=false;
		} else if (cmd == 4) {
			bombing(obs, player, P);
		}

	}

	public void bombing(MapCell[][] obs, BomberMan[] player, BomberMap P) {
		b = new Bomb_c(this.xPos, this.yPos);
		b.indexi = indexi;
		b.indexj = indexj;
		b.bombFlame = bombingFlame;
		b.state = v.BReadytToFire;
		obs[indexi][indexj] = b;
		b.BombTimer_start(obs, player, P);
		P.sendMapChange2server(indexi,indexj,obs[indexi][indexj].getID());
		v.cell=b;
		bombingFlag=true;
	}

	public void draw(Graphics2D g, BomberMap P) {
		g.drawImage(this.img, xPos, yPos, P);
	}
}

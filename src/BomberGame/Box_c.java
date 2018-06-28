package BomberGame;

import java.awt.*;

public class Box_c extends MapCell {
	public MapCell[] con;
	Bomb_c b;
	double rand;
	private int ID;

	public Box_c(int x, int y) {
		con = new MapCell[4];
		this.xPos = x;
		this.yPos = y;
		ID = 2;
		img = Toolkit.getDefaultToolkit().getImage("pics/WoodenBox.png");
		crossPermition = false;
		con[0] = new Well_c(this.xPos, this.yPos);
		con[1] = new Bomb_c(this.xPos, this.yPos);
		con[2] = new Flame_c(this.xPos, this.yPos);
		con[3] = new Blank_c(this.xPos, this.yPos);
		rand = Math.random();
//        System.out.println(rand);
		double sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += con[i].probability;
			if (rand < sum) {
				this.content = con[i];
				break;
			}
		}


	}

	@Override
	public boolean getToFireAction() {
		return true;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void draw(BomberMap P, Graphics2D g) {
		g.drawImage(this.img, xPos, yPos, P);

	}

}
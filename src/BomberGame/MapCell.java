package BomberGame;

import javax.swing.*;
import java.awt.*;

public abstract class MapCell extends JFrame {
	public double probability;
	public Integer xPos;
	public Integer yPos;
	public int width = 50;
	public int height = 50;
	public boolean crossPermition;
	public MapCell content;
	public Image img;

	public boolean isCrossPermition() {
		return crossPermition;
	}

	public abstract int getID();

	public abstract boolean getToFireAction();

	public void setPos(int x, int y) {
		this.yPos = y;
		this.xPos = x;
	}


	public Integer getyPos() {
		return yPos;
	}

	public Integer getxPos() {
		return xPos;
	}

	abstract void draw(BomberMap P, Graphics2D g);

}

package BomberGame;

import javax.swing.*;
import java.awt.*;

public abstract class MapCell extends JFrame {
	double probability;
	public Integer xPos;
	public Integer yPos;
	 boolean crossPermission;
	 MapCell content;
	 Image img;

	 boolean isCrossPermission() {
		return crossPermission;
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

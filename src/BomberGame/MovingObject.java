package BomberGame;

import javax.swing.*;
import java.awt.*;


public abstract class MovingObject extends JFrame {

	public Integer xPos;
	public Integer yPos;
	int width;
	int height;
	int indexi;
	int indexj;
	boolean alive;


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	void move_right() {

		indexj++;
		xPos = indexj * 50;
		yPos = indexi * 50 + 30;
	}

	void move_left() {
		indexj--;
		xPos = indexj * 50;
		yPos = indexi * 50 + 30;
	}

	void move_up() {
		indexi--;
		xPos = indexj * 50;
		yPos = indexi * 50 + 30;
	}

	void move_down() {
		indexi++;
		xPos = indexj * 50;
		yPos = indexi * 50 + 30;

	}

	abstract void draw(Graphics2D g, BomberMap P);

}

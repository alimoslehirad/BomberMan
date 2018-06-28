package BomberInit;

import java.awt.event.KeyEvent;

/**
 * File:         BomberKeyConfig.java

/**
 * This class keeps track of the keyboard configration for each player.
 * This class can't be instanciated.
 */
public class BomberKeyConfig {
	/** player numbers enumerations */
	public static final int P1 = 0;
	public static final int P2 = 1;
	public static final int P3 = 2;
	public static final int P4 = 3;
	/** key number enumerations */
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int BOMB = 4;
	/** the keys */
	public String[][] keys = new String[4][5];


	/**
	 * Creates the configuration file with default configurations.
	 */
	public BomberKeyConfig() {


		/** player 1 default key configurations */
		keys[P1][UP] = Integer.toString(KeyEvent.VK_UP);
		keys[P1][DOWN] = Integer.toString(KeyEvent.VK_DOWN);
		keys[P1][LEFT] = Integer.toString(KeyEvent.VK_LEFT);
		keys[P1][RIGHT] = Integer.toString(KeyEvent.VK_RIGHT);
		keys[P1][BOMB] = Integer.toString(KeyEvent.VK_ENTER);

		/** player 2 default key configurations */
		keys[P2][UP] = Integer.toString(KeyEvent.VK_W);
		keys[P2][DOWN] = Integer.toString(KeyEvent.VK_S);
		keys[P2][LEFT] = Integer.toString(KeyEvent.VK_A);
		keys[P2][RIGHT] = Integer.toString(KeyEvent.VK_D);
		keys[P2][BOMB] = Integer.toString(KeyEvent.VK_Q);

		/** player 3 default key configurations */
		keys[P3][UP] = Integer.toString(KeyEvent.VK_Y);
		keys[P3][DOWN] = Integer.toString(KeyEvent.VK_H);
		keys[P3][LEFT] = Integer.toString(KeyEvent.VK_G);
		keys[P3][RIGHT] = Integer.toString(KeyEvent.VK_J);
		keys[P3][BOMB] = Integer.toString(KeyEvent.VK_T);

		/** player 4 default key configurations */
		keys[P4][UP] = Integer.toString(KeyEvent.VK_O);
		keys[P4][DOWN] = Integer.toString(KeyEvent.VK_L);
		keys[P4][LEFT] = Integer.toString(KeyEvent.VK_K);
		keys[P4][RIGHT] = Integer.toString(KeyEvent.VK_SEMICOLON);
		keys[P4][BOMB] = Integer.toString(KeyEvent.VK_I);


	}
}
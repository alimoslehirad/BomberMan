package BomberGame;

import java.io.BufferedReader;

public class ObjectPool {
	 final int BoxID = 2;
	 final int wallID = 3;
	 final int Blank = 0;
	 final byte BReadytToFire = 1;
	 BufferedReader bufferedReader;
	 String fileName;
	int[][] frameMask;

	int y0;
	MapCell[][] obs;

	public ObjectPool() {
		frameMask = new int[14][14];

		obs = new MapCell[14][14];


		y0 = 30 ;


	}


}
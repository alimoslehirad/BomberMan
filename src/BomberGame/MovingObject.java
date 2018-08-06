package BomberGame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public abstract class MovingObject extends JFrame {

	public Integer xPos;
	public Integer yPos;
	int width;
	int height;
	int indexi;
	int indexj;
	boolean alive;
	String fileName;
	BufferedReader bufferedReader;

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


		try {
			 fileName = "BomberManStat.txt";
			FileReader fileReader = new FileReader(fileName);
			 bufferedReader = new BufferedReader(fileReader);

		} catch (FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" +
							fileName + "'");
		}
		String st="";
		String allSt="";
		while (st!=null){
			try{
				st = bufferedReader.readLine();
			}catch (Exception ex){System.out.println(ex);}
			allSt+=st;

		}
//		System.out.println(allSt);
        int i= allSt.indexOf("P0");
		int j=allSt.indexOf("end",5);
		char[] s=new char[j-i];
		allSt.getChars(i,j,s,0);

		String sm= new String(s);
		System.out.println(sm);
		i=sm.indexOf("indexi=")+7;
		j=sm.lastIndexOf(' ',i+3);
		char[] s1=new char[j-i];
		sm.getChars(i,j,s1,0);
		String indexS=new String(s1);
		int indexii=Integer.parseInt(indexS);
		System.out.println(indexii);
		indexii++;
		indexi=indexii;
		xPos = indexj * 50;
		yPos = indexi * 50 + 30;

	}

	abstract void draw(Graphics2D g, BomberMap P);

}

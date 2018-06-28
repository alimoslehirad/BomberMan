package BomberGame;
import java.awt.*;

import javax.swing.JFrame;


public abstract class MovingObject extends JFrame {

    public int width ;
    public int height;
    public int speed;
    public int indexi;
    public int indexj;
    public Integer xPos;
    public Integer yPos;
    public boolean alive;
    public Integer getxPos() {
        return xPos;
    }
    public int getxTopLeft(){
        return this.xPos;
    }
    public int getxTopRight(){
        return this.xPos+this.width;
    }
    public int getxDownLeft(){
        return this.xPos;
    }
    public int getxDownRight(){
        return this.xPos+this.width;
    }
    public int getyDownRight(){return this.yPos+this.height;}
    public int getyTopRight(){return this.yPos;}

    public  boolean colisioncheck(int x , int y ){
        return true;
    }


    public Integer getyPos() {
        return yPos;
    }

    public void setPos(int x, int y){
        this.yPos=y;
        this.xPos=x;
    }

    public int getWidth() {
        return width;
    }
    public int getStep(){
        return this.speed*this.width/2;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }



    public void move_right() {

        indexj++; xPos=indexj*50;
        yPos=indexi*50+30;
    }

    public void move_left() {
        indexj--; xPos=indexj*50;
        yPos=indexi*50+30;
    }

    public void move_up() {
        indexi--; xPos=indexj*50;
        yPos=indexi*50+30;
    }

    public void move_down() {
        indexi++; xPos=indexj*50;
        yPos=indexi*50+30;

    }

    abstract void   draw( Graphics2D g , BomberMap P);

}

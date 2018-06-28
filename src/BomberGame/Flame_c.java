package BomberGame;
import java.awt.*;
public class Flame_c extends  MapCell{
    private int ID;
    public Flame_c(int x,int y){
        this.xPos=x;
        this.yPos=y;
        ID=10;
        probability=.3;
        img = Toolkit.getDefaultToolkit().getImage("pics/flame.png");
        crossPermition=true;
    }


    @Override
    public boolean getToFireAction() {
        return false;
    }
    @Override
    public int getID() {
        return ID;
    }

    public void draw(BomberMap P , Graphics2D g){
        g.drawImage(this.img, xPos, yPos, P);

    }
}

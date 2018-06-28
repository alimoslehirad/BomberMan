package BomberGame;
import java.awt.*;

public class Wall_c extends MapCell {
    private int ID;
    public Wall_c(){
        crossPermition=false;
        ID=3;
        img=Toolkit.getDefaultToolkit().getImage("pics/wall.png");
    }
    @Override
    public  int getID(){
        return this.ID;
    }
    @Override
    public boolean getToFireAction() {
        return false;
    }



    @Override
    public void draw(BomberMap P, Graphics2D g) {
        g.drawImage(this.img, xPos, yPos, P);

    }
}
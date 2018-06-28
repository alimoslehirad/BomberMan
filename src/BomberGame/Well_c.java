package BomberGame;
import java.awt.*;
public class Well_c extends MapCell {
    private int ID;
    public boolean isCrossPermition(){
        return crossPermition;
    }
    public Well_c(int x, int y){
        this.xPos=x;
        this.yPos=y;
        probability=.075;
        crossPermition=true;
        ID=7;
        img = Toolkit.getDefaultToolkit().getImage("pics/well.png");
    }
    @Override
    public boolean getToFireAction() {
        return true;
    }
    @Override
    public  int getID(){
        return this.ID;
    }
    @Override
    public void draw(BomberMap P , Graphics2D g){
        g.drawImage(this.img, xPos, yPos, P);

    }
}

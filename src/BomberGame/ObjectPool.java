package BomberGame;
import java.io.BufferedReader;

public class ObjectPool {
    int[][] frameMask;
   // BomberMan p1;

    public  final int BoxID=2;
    public   final int wallID=3;
    public  final int BombID=1;
    public  final int BomberManID=4;
    public  final int Blank=0;
    public  final int RightKey=39;
    public  final int LeftKey=37;
    public  final int DownKey=40;
    public  final int UpKey=38;
    public  final byte BreadytToFire=1;
    public  final byte BFired=0;
    public final byte BoXNum=28;
    public final byte WallNum=49;
    public final byte BlankNum=200-(BoXNum+WallNum);
    public final byte BOX_Rappid=100;
    public final byte BOX_Empty=110;

    int H0;
    int y0;
 //  BomberMan p1;
    public  final byte BIdle=2;

    public BufferedReader bufferedReader;
    public String fileName;

    MapCell[][] obs;

    public ObjectPool() {
        frameMask = new int[14][14];
       // p1 = new BomberMan();

        obs = new MapCell[14][14];

        H0=30;
        y0=H0+0;


    }


}
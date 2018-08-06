import BomberGame.BomberMap;
import BomberInit.BomberMapMake;
public class Main {
	public static final int x = 14;
	public static final int y = 14;

	public static void main(String[] args) {

//       new BomberMapMake("BomberMan Initialize Game",x,y);
		new BomberMap("BomberMan Game", x, y);

		System.out.println("program end");

	}

}

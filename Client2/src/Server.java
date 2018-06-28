import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Server extends JFrame implements KeyListener {
	ServerSocket mServer;
	Socket socket;
	int serverPort = 9092;
	Scanner datain = new Scanner(System.in);


	public Server() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 10 * 50, 10 * 50 + 30);
		setVisible(true);
		setTitle("9092");
		addKeyListener(this);
		String command = "sdf";
		String[] strArray;
		String concatStr = "";



	}




	public static void main(String[] args) {

		new Server();


	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		try {
			String serverAddress="127.0.0.1";
			Socket socket = new Socket(serverAddress,serverPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(Integer.toString(e.getKeyCode()));
			out.close();
		} catch (IOException ex) {
		}

	}

	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

}
        
 

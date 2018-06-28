import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;

    public class Server extends JFrame implements KeyListener  {
		ServerSocket mServer;
		Socket socket;
		int serverPort = 9090;
		Scanner datain = new Scanner(System.in);


		public Server() {
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400, 200, 10 * 50, 10 * 50 + 30);
			setVisible(true);
			addKeyListener(this);
			String command = "sdf";
			String[] strArray;
			String concatStr="";

			while (!command.equals("end")) {
				try{
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String input = in.readLine();
					System.out.println(input);
				}catch (IOException e){}
				System.out.print("Enter Command:  ");

				command = datain.nextLine();

				strArray = command.split(" ");
				if (strArray[0].equals("start")) {
					serverPort = Integer.parseInt(strArray[1]);
					try {
                        // create server socket!
                        mServer = new ServerSocket(serverPort);

                        System.out.println("Server Created!");

                        // wait for client
                        Socket socket = mServer.accept();
                        // horaaaaa
                        System.out.println("Connected to	" + serverPort);
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("connect to server");
                        out.close();
                    }catch (IOException ex ){
						ex.printStackTrace();
						try{mServer.close();}catch(IOException e){}
					}

				}
				else if(strArray[0].equals("send")){
                    concatStr="";
					for(int i=1;i<strArray.length;i++){
						concatStr+=strArray[i]+" ";
					}
                    try {
                        Socket socket = mServer.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(concatStr);
                        out.close();
                    }catch (IOException ex){}
				}
				else if (strArray[0].equals("exit")) {
				    try {
                        mServer.close();
                    }catch (IOException ex){}
				}


			}



		}

		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode());
			try {
				Socket socket = mServer.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println(Integer.toString(e.getKeyCode()));
				out.close();
			}catch (IOException ex){}

		}

		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent keyEvent) {

		}



		public static void main (String[]args){

                    new Server();



			}

	}
        
 

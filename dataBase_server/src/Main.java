import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.awt.*;

import javax.swing.JFrame;

 class Main {
     ServerSocket mServer;
     Socket socket;
     int serverPort = 9090;
     public int indexi,indexj;
     public Main() {

         String command = "sdf";
         String concatStr = "";
         serverRun();
     }

     private void serverRun() {
         String[] strArray;
         while (true) {
             try {
                 //---------------------------------------------------
                 // create server socket!
//               System.out.println("start soucketing");
                 mServer = new ServerSocket(serverPort);
                 System.out.println("Server Created!");
                 // wait for client
                 Socket socket = mServer.accept();
                 // horaaaaa

                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 String input="";
                 while(input.equals("")) {
                     input = in.readLine();
                 }
                 System.out.println(input);
                 strArray=input.split(" ");

                 if(strArray[0].equals("Map")){
                     if(strArray[1].equals("R")){
                         if(strArray[2].equals("initialize")) {
                             input = mapDataExtraction("Init");
                             mapDataInit();
                         }
                         else{
                             input = mapDataExtraction("inGame");
                         }
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         out.println("map " + input + " end");
                         out.close();

                     }
                     else if(strArray[1].equals("W")){
                         writeMapToTextFile(Integer.parseInt(strArray[2]),Integer.parseInt(strArray[3]),strArray[4]);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         out.println("write done");
                         out.close();

                     }
                 }
                 else {
                     if (strArray[1].equals("R")) {
                         int[] indexij = new int[2];
                         indexij = dataExtraction(input, indexi, indexj);
                         //-----------------------------------------------------
                         System.out.println("indexi= " + indexij[0] + ",indexj= " + indexij[1]);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         out.println("P " + indexij[0] + " " + indexij[1] + " end");
                         out.close();
                     } else if (strArray[1].equals("W")) {
                         writePlayerPosToTextFile(input);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         out.println("write is done ");
                         out.close();

                     }
                 }
                 mServer.close();

             } catch (IOException e) {
             }
         }
     }

     private int[] dataExtraction(String playerID, int indexi, int indexj) {
         String fileName = "";
         BufferedReader bufferedReader;
         String st = "";
         String allSt = "";
         String[] strArray;
         int[] indexij=new int[2];
         strArray=playerID.split(" ");
         try {
             fileName = "BomberManStat.txt";
             FileReader fileReader = new FileReader(fileName);
             bufferedReader = new BufferedReader(fileReader);
             while (st != null) {
                 try {
                     st = bufferedReader.readLine();
                 } catch (Exception ex) {
                     System.out.println(ex);
                 }
                 allSt += st;

             }

         } catch (FileNotFoundException ex) {
             System.out.println(
                     "Unable to open file '" +
                             fileName + "'");
         }
         int i = allSt.indexOf(strArray[0]);
         int j = allSt.lastIndexOf("end");
         char[] s = new char[j - i];
         allSt.getChars(i, j, s, 0);
         String sm = new String(s);
//         System.out.println(sm);
         i = sm.indexOf("indexi=") + 7;
         j = sm.lastIndexOf(",", i + 3);
         String indexS = sm.substring(i,j);
         int index = Integer.parseInt(indexS);
         System.out.println(index);
         indexij[0] = index;
         //---------------------------------------
         i = sm.indexOf("indexj=") + 7;
         j = sm.lastIndexOf(",", i+3 );
         indexS = sm.substring(i, j);
         index = Integer.parseInt(indexS);
         System.out.println(index);
         indexij[1] = index;
         return indexij;
     }
//====================================================================================

     private String mapDataExtraction(String mode) {
         String fileName = "";
         BufferedReader bufferedReader;
         String st = "";
         String allSt = "";
         String[] strArray;
         int[] indexij=new int[2];

         try {
             if(mode.equals("Init")){
                 fileName = "BomberMapInit";
             }
             else {
                 fileName = "BomberMap.txt";
             }
             FileReader fileReader = new FileReader(fileName);
             bufferedReader = new BufferedReader(fileReader);
             while (st != null) {
                 try {
                     st = bufferedReader.readLine();
                 } catch (Exception ex) {
                     System.out.println(ex);
                 }
                 allSt += st;

             }

         } catch (FileNotFoundException ex) {
             System.out.println(
                     "Unable to open file '" +
                             fileName + "'");
         }

         return allSt;
     }

     private void mapDataInit() {
         String fileName = "";
         BufferedReader bufferedReader;
         String st = "";
         String allSt = "";
         try {
             fileName = "BomberMapInit";
             FileReader fileReader = new FileReader(fileName);
             bufferedReader = new BufferedReader(fileReader);
             while (st != null) {
                 try {
                     st = bufferedReader.readLine();
                 } catch (Exception ex) {
                     System.out.println(ex);
                 }
                 allSt += st+"\n";

             }
             try {
                 FileOutputStream fout = new FileOutputStream("BomberMap.txt");
                 byte c[] = allSt.getBytes();//converting string into byte array
                 fout.write(c);
             }catch (Exception e){
                 System.out.println(e);
             }


         } catch (FileNotFoundException ex) {
             System.out.println(
                     "Unable to open file '" +
                             fileName + "'");
         }


     }

     public static void main(String[] args) {

         new Main();


     }

     private void writePlayerPosToTextFile(String command2write) {
         String[] strArray;
         strArray=command2write.split(" ");
         try {
             String fileName = "BomberManStat.txt";
//           FileInputStream fin =new FileInputStream(fileName);
             String textStr = "";
             FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             textStr="";
             String st="";
             while (st != null) {
                 try {
                     st = bufferedReader.readLine();
                 } catch (Exception ex) {
                     System.out.println(ex);
                 }
                 textStr += st+"\n";
             }
             int i=textStr.indexOf(strArray[0]);
             int j=textStr.lastIndexOf("end",i+50);
             String s1 ;//= new char[j - i];
             s1=textStr.substring(i, j);
             System.out.println(s1);
             i=s1.indexOf("indexi");
             j=s1.indexOf(",",i);
             String su=s1.substring(i,j);
             String sui="indexi="+strArray[2];
             s1=s1.replace(su,sui);
             i=s1.indexOf("indexj");
             j=s1.indexOf(",",i);
             su=s1.substring(i,j);
             s1=s1.replace(su,"indexj="+strArray[3]);
             String playerStat;
             String textStr2="";
             i=textStr.indexOf(strArray[0]);
             j=textStr.lastIndexOf("end",i+50);
             playerStat=textStr.substring(i,j);
             textStr=textStr.replace(playerStat,s1);
             System.out.println("result is....\n"+textStr);
             FileOutputStream fout = new FileOutputStream(fileName);
             byte c[] = textStr.getBytes();//converting string into byte array
             fout.write(c);
             fout.close();
             try{


             }catch(Exception e){
                 System.out.println(e.getMessage());
             }
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }


         System.out.println("Player stat write success...");

     }
     ///------------------------------------------------------------------------------
     private String updatePlayerPos(String playerPos, int indexi, int indexj) {
         String fileName = "";
         BufferedReader bufferedReader;
         String st = "";
         String allSt = "";
         String[] strArray;
         int[] indexij=new int[2];
         strArray=playerPos.split(" ");
         int i = playerPos.indexOf("indexi=") + 7;
         int j = playerPos.lastIndexOf(' ', i + 3);
         char[] s1 = new char[j - i];
         String indexS = new String(s1);
         int index = Integer.parseInt(indexS);
         System.out.println(index);
         indexij[0] = index;
         //---------------------------------------
         indexS = new String(s1);
         index = Integer.parseInt(indexS);
         System.out.println(index);
         indexij[1] = index;
      return "sdf";
     }

     public void writeMapToTextFile(int indexi,int indexj,String cellId) {
         String[] strArray;
         String fileName = "BomberMap.txt";
         String textStr = "";
             try {
                 FileReader fileReader = new FileReader(fileName);
                 BufferedReader bufferedReader = new BufferedReader(fileReader);
                 for (int i = 0; i < 14; i++) {
                     String line = bufferedReader.readLine();

                     if (line == null) bufferedReader.close();
                     strArray = line.split(" ");
                     if (indexi == i) {
                         strArray[indexj] = cellId;
                         line = "";
                         for (int j = 0; j < 14; j++) {
                             line += strArray[j] + " ";
                         }
                     }
                     textStr += line + "\n";
                 }
             } catch (FileNotFoundException ex) {
                 System.out.println(
                         "Unable to open file '" +
                                 fileName + "'");
             } catch (IOException ex) {
                 System.out.println(
                         "Error reading file '"
                                 + fileName + "'");
             }

         //----------
         try {
             FileOutputStream fout = new FileOutputStream(fileName);

             byte c[] = textStr.getBytes();//converting string into byte array
             fout.write(c);
             fout.close();
             System.out.println("Map write success...");
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }


     }



 }




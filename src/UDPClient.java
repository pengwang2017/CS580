import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
	  boolean qFlag= true;
      BufferedReader inFromUser =
         new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
      while(qFlag){
      String sentence = inFromUser.readLine();
      //close client when user input is quit
      if(sentence == "quit") {break;}
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      //String modifiedSentence = new String(receivePacket.getData());
      String modifiedSentence = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
      System.out.println("FROM SERVER:" + modifiedSentence);
      
      }
      clientSocket.close();
   }
}
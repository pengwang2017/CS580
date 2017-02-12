import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;


class UDPServer
{
   public static void main(String args[]) throws Exception
      {
	   	String rootFolder = "/Users/pengwang/documents/02-cs580/ps1";
		Path wordListFile = Paths.get(rootFolder, "words.txt");
		Path wordDictionaryFile = Paths.get(rootFolder, "dictionary.txt");
		Path testResultFile = Paths.get(rootFolder, "testResult.txt");

		WordList allWords = WordList.FromFile(wordListFile);
		WordDictionary dict = WordDictionary.FromFile(wordDictionaryFile);
		Utility u = new Utility();
	   
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  //must do it this way to solve the left over problem
                  String sentence = new String( receivePacket.getData(),0,receivePacket.getLength());
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  
          		
          		if (allWords.Exist(sentence)) {
          			WordDefinition def = dict.GetDefinition(sentence);
          			if (def != null) {
          				sendData = def.GetDefinition().getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);
          				
          				System.out.println(def.GetDefinition());
          				u.writeResult(testResultFile , def.GetWord());
          				u.writeResult(testResultFile, "\n");
          				u.writeResult(testResultFile, def.GetDefinition());
          				u.writeResult(testResultFile, "\n");
          				
          			} else {
          				u.writeResult(testResultFile, "Cannot find definition to word" + sentence + "\n");
          				
          			}
          		} else {
          			u.writeResult(testResultFile, "Cannot find word " + sentence + "\n");
          		}
                  
                  
                  
               }
      }
}

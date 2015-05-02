import java.net.*;
import java.util.Random;
class PingServer {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(9876); //server will run on port #9876
        byte[] receiveData = new byte[1024];
        System.out.println("The Ping Server has Started\n");
        while(true)
            {
        		Random random = new Random();
                DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, 
                                       receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(
                                             receivePacket.getData(),
                                             0,
                                             receivePacket.getLength());
                InetAddress IPAddress =
                    receivePacket.getAddress(); //get client's IP
                int port = receivePacket.getPort(); //get client's port #
                	System.out.println("\n^^^^^^^^^^^^^^^^START^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
					 System.out.println("client's port#= " + port);
					  System.out.println("client's IP#= " +IPAddress);
					  System.out.println("Client Message= " +sentence);
					  
                String capitalizedSentence = 
                    sentence.toUpperCase();
                int rand = random.nextInt(10);
                if (rand <4){
                	System.out.println("\n--------------------------------------------------------\n");
                	System.out.println("Reply not Sent");
                	System.out.println("--------------------END------------------------\n");
                	continue;
                
                }
                byte[] sendData = capitalizedSentence.
                    getBytes();
                DatagramPacket sendPacket =
                    new DatagramPacket(sendData,
                                       sendData.length, 
                                       IPAddress, port);
                serverSocket.send(sendPacket);
                System.out.println("\n--------------------------------------------------------\n");
                System.out.println("Reply to the client sent");
                System.out.println("--------------------END-------------------------\n");
            }
    }
}

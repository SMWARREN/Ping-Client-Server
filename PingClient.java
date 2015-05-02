import java.io.*;
import java.net.*;
class PingClient {
    public static void main(String args[]) throws Exception
    {
    	@SuppressWarnings("unused")
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();//port # is assigned by OS to the client
        InetAddress IPAddress = 
            InetAddress.getByName("localhost");
        byte[] receiveData = new byte[1024];
        for(int i = 1; i<= 10; i++){
        	try{
        		long startTime = System.nanoTime()/1000;
        		String sentence = "Ping # " + i + "\n";
		        byte[] sendData = sentence.getBytes();
		        DatagramPacket sendPacket =
		            new DatagramPacket(sendData, sendData.length, 
		                               IPAddress, 9876); //data with server's IP and server's port #
		        clientSocket.send(sendPacket);
		        DatagramPacket receivePacket =
		            new DatagramPacket(receiveData,
		                               receiveData.length);
		        clientSocket.setSoTimeout(10);
		        clientSocket.receive(receivePacket);
		        // we still need to catch the exception and retry
		        String modifiedSentence =
		            new String(receivePacket.getData(),
		                       0,
		                       receivePacket.getLength());
		        long elapsedTimeNs = (System.nanoTime()/1000 - startTime);
		        System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^START^^^^^^^^^^^^^^^^^^^^^^^\n");
		        System.out.println("FROM SERVER:" +
		                           modifiedSentence);
		        System.out.println("\n" +
                        "Ping was sent  " + startTime + " microseconds ago. The " + "RTT:" +elapsedTimeNs + " microseconds" + "\n");
		        System.out.println("------------------END---------------\n");
		        } catch(SocketTimeoutException stde) {
		        	 System.out.println("\n****************************START of Request Timeout**********************\n");
		        	System.out.println("Request Timeout on Ping #" + i +"\n"); 
		        	System.out.println("------------------END---------------------------\n");
		        }
        	
        }	
		        clientSocket.close();
		    
		}
}

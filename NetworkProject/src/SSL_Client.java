import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

public class SSL_Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int serverPort = 3333;
		String serverName = "localhost";
		
		System.setProperty("javax.net.ssl.truestStore", "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\trustedcerts");
		
		try {
			SSLSocketFactory sslSocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			
			Socket socket = (Socket) sslSocketfactory.createSocket(serverName, serverPort);
			
			BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			
			BufferedReader commandPromptBufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Please enter a username : ");
			
			printWriter.println(commandPromptBufferedReader.readLine());
			
			String message = null;
			while(true) {
				System.out.println("Prease enter a message to send to server : ");
				message = commandPromptBufferedReader.readLine();
				if(message.equals("quit")) {
					socket.close();
					break;
				}
				printWriter.println(message);
				System.out.print("message reply from server : ");
				System.out.println(socketBufferedReader.readLine());
			}
			
			
			
			
			
			
			
//			DataOutputStream outputStream = new DataOutputStream(sslSocket.getOutputStream());
//			
//			DataInputStream inputStream = new DataInputStream(sslSocket.getInputStream());
//			
//			//System.out.println(inputStream.readUTF());
//			
//			while(true) {
//				System.out.println("Write a Message : ");
//				String messageToSend = System.console().readLine();
//				outputStream.writeUTF(messageToSend);
//				System.out.println(inputStream.readUTF());
//				if(messageToSend.equals("close")) {
//					outputStream.close();
//					inputStream.close();
//					sslSocket.close();
//					break;
//				}
//			}
		}catch(Exception ex) {
			System.err.println("Error Happened : " + ex.toString());
		}
	}

}

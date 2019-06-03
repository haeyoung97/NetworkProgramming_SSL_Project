import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

public class SSL_Client {
	private int serverPort;
	private String serverName;
	private String path;
	private String username;
	private boolean TF = false;
	
	private String accessMsg = null;
	
	private SSLSocketFactory sslSocketfactory;
	private Socket socket;
	private BufferedReader socketBufferedReader;
	private PrintWriter printWriter;
	private BufferedReader commandPromptBufferedReader;
	
	public SSL_Client(int serverPort, String serverName, String path, String username) {
		this.serverPort = serverPort;
		this.serverName = serverName;
		this.path = path;
		this.username = username;
	}
	
	public void SSLconnection_Client() {
		// TODO Auto-generated method stub
		
		//System.setProperty("javax.net.ssl.truestStore", "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\trustedcerts");
		System.setProperty("javax.net.ssl.truestStore", this.path);
		try {
			sslSocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			
			socket = (Socket) sslSocketfactory.createSocket(this.serverName, this.serverPort);
			
			socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			
			commandPromptBufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			accessMsg = "client socket access success!";
			System.out.println("Please enter a username : ");
			TF = true;
			
			printWriter.println(commandPromptBufferedReader.readLine());
/*			
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
*/
			
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
		}
		catch(Exception ex) {
			System.err.println("Error Happened : " + ex.toString());
			accessMsg = "Error Happened : " + ex.toString();
			TF = false;
		}
		
	}
	public void run() {
		String message = null;
		try {
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
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getAccessMessage() {
		return accessMsg;
	}
	public boolean isConnect() {
		return TF;
	}

}

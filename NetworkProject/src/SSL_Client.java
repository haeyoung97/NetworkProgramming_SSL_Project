import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;
import javax.swing.JTextField;

import RMI.HandleWordText;
import RMI.HandleWordTextImpl;

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
	
	private JTextField textField = new JTextField();
	
	public SSL_Client(int serverPort, String serverName, String path, String username) {
		this.serverPort = serverPort;
		this.serverName = serverName;
		this.path = path;
		this.username = username;
	}
	
	public void SSLconnection_Client() {
		// TODO Auto-generated method stub
		rmiSetting_Client();
		
		//System.setProperty("javax.net.ssl.truestStore", "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\trustedcerts");
		System.setProperty("javax.net.ssl.trustStore", this.path);
		System.setProperty("javax.net.ssl.trustStorePassword", "networkSSL");
		try {
			sslSocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();

			//HandleWordText he = (HandleWordText)Naming.lookup("rmi://"+serverName+"/HandleExcel");
			socket = (Socket) sslSocketfactory.createSocket(this.serverName, this.serverPort);
			
			//System.out.println("test : " + he.dataParse());
			socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("test 1");
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("test 2");
			//commandPromptBufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			accessMsg = "client socket access success!";
			//System.out.println("Please enter a username : ");
			//textField.setText(accessMsg);
			//printWriter.println(commandPromptBufferedReader.readLine());
			
			HandleWordTextImpl handleExcelImpl = new HandleWordTextImpl();
			System.out.println("test : " + handleExcelImpl.dataParse());
			TF = true;
			
			//printWriter.println(commandPromptBufferedReader.readLine());
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
			textField.setText(accessMsg);
			TF = false;
		}
		
	}
	
	public void rmiSetting_Client() {
		try {
			HandleWordText he = (HandleWordText)Naming.lookup("rmi://localhost/HandleExcel");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.print("MalformedURLException : ");
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.print("RemoteException : ");
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			System.out.print("NotBoundException : ");
			e.printStackTrace();
		}
		
	}
	
	
	public void run() {
		String message = null;
		try {
			commandPromptBufferedReader = new BufferedReader(new InputStreamReader(System.in));
			printWriter.println(commandPromptBufferedReader.readLine());
			System.out.println("run_test");
			while(true) {
				System.out.println("Prease enter a message to send to server : ");
				
				message = commandPromptBufferedReader.readLine();
				System.out.println("run_test1");
				if(message.equals("quit")) {
					socket.close();
					break;
				}
				printWriter.println(message);
				System.out.print("message reply from server : ");
				//System.out.println(socketBufferedReader.getInputStream());
				//System.out.println(socketBufferedReader.readLine());
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;

import RMI.HandleWordText;
import RMI.HandleWordTextImpl;

public class SSL_Client {
	private int serverPort;
	private String serverName;
	private String path;
	private String username;
	private boolean TF = false;
	
	private String accessMsg;
	
	private SSLSocketFactory sslSocketfactory;
	private Socket socket;

	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private BufferedReader socketBufferedReader;
	
	private HandleWordText handleWordText;
	
	public SSL_Client(int serverPort, String serverName, String path, String username) {
		this.serverPort = serverPort;
		this.serverName = serverName;
		this.path = path;
		this.username = username;
	}
	
	public void SSLconnection_Client() {
		// TODO Auto-generated method stub
		rmiSetting_Client();
		
		System.setProperty("javax.net.ssl.trustStore", this.path);
		System.setProperty("javax.net.ssl.trustStorePassword", "networkSSL");
		
		System.setProperty("javax.net.debug","ssl");
		try {
			sslSocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();

			socket = (Socket) sslSocketfactory.createSocket(this.serverName, this.serverPort);
			
			accessMsg = "[ user : " + username + " ] client socket access success!";
			
			handleWordText = new HandleWordTextImpl();
			System.out.println("test : " + handleWordText.dataParse());
			TF = true;
			
		}
		catch(Exception ex) {
			System.err.println("Error Happened : " + ex.toString());
			accessMsg = "Error Happened : " + ex.toString();
			TF = false;
			JOptionPane.showMessageDialog(null, accessMsg);
		}
		
	}
	
	public void rmiSetting_Client() {
		try {
			handleWordText = (HandleWordText)Naming.lookup("rmi://localhost/HandleWordText");
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
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String message = null;
			
			System.out.print("First line : ");
			
			while(!(message = bufferedReader.readLine()).equals("close")){
				printWriter.println(message);
				String line = socketBufferedReader.readLine();
				System.out.println("Got Back : " + line);
				System.out.print("Next Line : ");
			}
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

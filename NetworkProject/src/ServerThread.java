import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.net.ssl.SSLSocket;

public class ServerThread {
	private SSLSocket socket;
	private int port;
	private String serviceName;
	private String server;
	
	private PrintWriter printWriter;
	private BufferedReader bufferedReader;
	
	public ServerThread(SSLSocket socket, int port, String serviceName, String server){
		this.socket = socket;
		this.port = port;
		this.serviceName = serviceName;
		this.server = server;
	}
	
	public void start() {
		
		try {
			System.out.println("thread!");
			
			printWriter = new PrintWriter(socket.getOutputStream());
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("user : " + bufferedReader.readLine() + " is now connected to the server...");
			
			while(true) {
				if("quit".equals(bufferedReader.readLine())) {
					System.out.println("service close!");
					printWriter.close();
					bufferedReader.close();
					break;
				}
				System.out.println("server test");
				printWriter.write(bufferedReader.readLine() + " echo");
			}
			
		}catch(IOException e) {
			printWriter.close();
			try {
				bufferedReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

}

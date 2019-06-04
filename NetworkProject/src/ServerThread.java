import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.Naming;

import javax.net.ssl.SSLSocket;

import RMI.HandleExcel;
import RMI.HandleExcelImpl;


public class ServerThread {
	private SSLSocket socket;
	private int port;
	private String serviceName;
	private String server;
	
	public ServerThread(SSLSocket socket, int port, String serviceName, String server){
		this.socket = socket;
		this.port = port;
		this.serviceName = serviceName;
		this.server = server;
	}
	
	public void start() {
		try {
			System.out.println("thread!");
			HandleExcel handleExcel = new HandleExcelImpl();
			//Naming.rebind("rmi://" + server + ":" + port + "/" + serviceName, handleExcel);
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("user : " + bufferedReader.readLine() + " is now connected to the server...");
			while(true) {
				printWriter.println(bufferedReader.readLine() + " echo");
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}

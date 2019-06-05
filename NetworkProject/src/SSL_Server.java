import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import RMI.HandleWordTextImpl;

public class SSL_Server {
	private SSLServerSocketFactory sslServerSocketfactory;
	private SSLServerSocket sslServerSocket;
	private SSLSocket sslSocket;
	
	private int port;
	private String serviceName;
	private String server;
	
	public SSL_Server(int port, String serviceName, String server) {
		this.port = port;
		this.serviceName = serviceName;
		this.server = server;
		
		try {
			rmiSetting_Server();
			establishEnviron();
		}catch(Exception e) {
			System.out.println("Trouble : " + e);
		}
		
	}
	
	public void rmiSetting_Server() {
		HandleWordTextImpl handleExcel = null;
		try {
			handleExcel = new HandleWordTextImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.print("RemoteException_handle : ");
			e.printStackTrace();
		}
		
		//make object findable
		Registry r = null;
		try {
			r = LocateRegistry.getRegistry("localhost", 1099);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.print("RemoteException : ");
			e.printStackTrace();
		}
		try {
			r.rebind("HandleExcel", handleExcel);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			System.out.print("AccessException : ");
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.print("RemoteException : ");
			e.printStackTrace();
		}
		
	}
	
	public void establishEnviron() {
		System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\keystore\\MySSLServerKey");
		//System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\Á¤»ó¾Æ\\Desktop\\networkProject\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\keystore\\MySSLServerKey");
		System.setProperty("javax.net.ssl.keyStorePassword", "networkSSL");
		
		try {
			sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(port);
			
			System.out.println("Echo Server Started & Ready to accept Client Connection");
			
			while(true) {
				sslSocket = (SSLSocket) sslServerSocket.accept();
				//new ServerThread((SSLSocket)sslServerSocket.accept()).start();
				new ServerThread(sslSocket, port, serviceName, server).start();	
			}
		}catch(Exception ex) {
			System.err.println("Error Happend : " + ex.toString());
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length != 3) {
			System.out.println("Usage: Classname Port ServiceName Server");
			System.exit(1);
		}
		int mPort = Integer.parseInt(args[0]);
		String mServiceName = args[1];
		String mServer = args[2];
		new SSL_Server(mPort, mServiceName, mServer);	
		
	}

}

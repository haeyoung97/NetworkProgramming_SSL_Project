import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import RMI.HandleWordText;
import RMI.HandleWordTextImpl;

public class SSL_Server {
	private SSLServerSocketFactory sslServerSocketfactory;
	private SSLServerSocket sslServerSocket;
	private SSLSocket sslSocket;
	
	private int port;
	
	public SSL_Server(int port) {
		this.port = port;
		
		try {
			rmiSetting_Server();
			establishEnviron();
		}catch(Exception e) {
			System.out.println("Trouble : " + e);
		}
		
	}
	
	public void rmiSetting_Server() {
		HandleWordText handleWordText = null;
		Registry r = null;
		try {
			handleWordText = new HandleWordTextImpl();
			r = LocateRegistry.getRegistry("localhost", 1099);
			r.rebind("HandleWordText", handleWordText);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.print("RemoteException_handle : ");
			e.printStackTrace();
		}
		
	}
	
	public void establishEnviron() {
		
		//System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\keystore\\MySSLServerKey");
		System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\정상아\\Desktop\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\keystore\\MySSLServerKey");
		System.setProperty("javax.net.ssl.keyStorePassword", "networkSSL");

		System.setProperty("javax.net.debug","ssl");
		
        try {
			sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(port);
			
			System.out.println("Echo Server Started & Ready to accept Client Connection");
			int tmp = 1;
			while(true) {
				sslSocket = (SSLSocket) sslServerSocket.accept();
				System.out.println("accept! : " + tmp);
				tmp++;
				//new ServerThread(sslSocket).start();	
			}
		}catch(Exception ex) {
			System.err.println("Error Happend : " + ex.toString());
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length != 1) {
			System.out.println("Usage: Classname Port"); // 실행방법 : java SSL_Server portNumver
			System.exit(1);
		}
		int mPort = Integer.parseInt(args[0]);
		new SSL_Server(mPort);	
		
	}

}

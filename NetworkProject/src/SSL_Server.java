import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

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
		final KeyStore ks;
	    final KeyManagerFactory kmf;
	    final SSLContext sc;
		
	    String root = "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\";
	    String ksName = root + ".keystore\\MySSLServerKey";
//	    System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\.keystore\\MySSLServerKey");
//		System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\정상아\\Desktop\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\keystore\\MySSLServerKey");
//		
//	    System.setProperty("javax.net.ssl.keyStorePassword", "networkSSL");

		System.setProperty("javax.net.debug","ssl");
		//System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
		
		char keyStorePass[] = "networkSSL".toCharArray();
	    char keyPass[] = "networkSSL".toCharArray();
//		
        try {
        	ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(ksName), keyStorePass);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);
            kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, keyPass);
            
            sc = SSLContext.getInstance("TLSv1.2");
            //sc.init(kmf.getKeyManagers(), null, null);
            sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        	SSLContext.setDefault(sc);
////        	
        	
        	sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(port);
			printServerSocketInfo(sslServerSocket);
			
			sslServerSocket.setEnabledCipherSuites(new String[]{"TLS_RSA_WITH_AES_128_CBC_SHA"});
			sslServerSocket.setEnabledProtocols(new String[]{"TLSv1","TLSv1.1","TLSv1.2","SSLv2Hello"});
			
			System.out.println("Echo Server Started & Ready to accept Client Connection");
			
			while(true) {
				sslSocket = (SSLSocket) sslServerSocket.accept();
				printSocketInfo(sslSocket);
				new ServerThread(sslSocket).start();	
			}
		}catch(Exception ex) {
			System.err.println("Error Happend : " + ex.toString());
		}
	}
	
	private static void printSocketInfo(SSLSocket s) {
		System.out.println("Socket class: "+s.getClass());
		System.out.println("   Remote address = "
				+s.getInetAddress().toString());
		System.out.println("   Remote port = "+s.getPort());
		System.out.println("   Local socket address = "
				+s.getLocalSocketAddress().toString());
		System.out.println("   Local address = "
				+s.getLocalAddress().toString());
		System.out.println("   Local port = "+s.getLocalPort());
		System.out.println("   Need client authentication = "
				+s.getNeedClientAuth());
		SSLSession ss = s.getSession();
		System.out.println("   Cipher suite = "+ss.getCipherSuite());
		System.out.println("   Protocol = "+ss.getProtocol());
	}
	private static void printServerSocketInfo(SSLServerSocket s) {
		System.out.println("Server socket class: "+s.getClass());
		System.out.println("   Server address = "+s.getInetAddress().toString());
		System.out.println("   Server port = "+s.getLocalPort());
		System.out.println("   Need client authentication = "+s.getNeedClientAuth());
		System.out.println("   Want client authentication = "+s.getWantClientAuth());
		System.out.println("   Use client mode = "+s.getUseClientMode());
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

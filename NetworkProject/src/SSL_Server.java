import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class SSL_Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 3333;
		
		System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\.keystore\\MySSLServerKey");
		
		System.setProperty("javax.net.ssl.keyStorePassword", "networkSSL");
		
		try {
			SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			SSLServerSocket sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(port);
			
			
			
			System.out.println("Echo Server Started & Ready to accept Client Connection");
			
			while(true) {
				new ServerThread(sslServerSocket.accept()).start();
			}
		}catch(Exception ex) {
			System.err.println("Error Happend : " + ex.toString());
		}
	}

}

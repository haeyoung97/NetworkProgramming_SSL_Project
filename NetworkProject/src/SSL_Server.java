import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

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
			//HandleExcel handleExcel = new HandleExcelImpl();
			
			establishEnviron();
			//Naming.rebind("rmi://" + server + ":" + port + "/" + serviceName, handleExcel);
		}catch(Exception e) {
			System.out.println("Trouble : " + e);
		}
		
	}
	
	public void establishEnviron() {
		System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\bin\\keystore\\MySSLServerKey");
		//System.setProperty("javax.net.ssl.keyStorePassword", "networkSSL");
		
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

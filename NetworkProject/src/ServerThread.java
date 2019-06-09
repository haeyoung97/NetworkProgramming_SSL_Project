import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import javax.net.ssl.SSLSocket;

public class ServerThread {
	private SSLSocket socket;

	private InputStream input;
	private BufferedReader reader;
	private OutputStream output;
	private Writer writer;
	private PrintWriter outPrint;
	
	
	public ServerThread(SSLSocket socket){
		this.socket = socket;
	}
	
	public void start() {
		
		try {
			input = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
			output = socket.getOutputStream();
			
			writer = new OutputStreamWriter(output);
			
			outPrint = new PrintWriter(writer, true);
			
			String message = null;
			message = reader.readLine();
//			outPrint.println(message);
			System.out.println(message);
//			while((message = reader.readLine()) != null)
//				outPrint.println(message);
			
//			input.close();
//			reader.close();
//			output.close();
//			writer.close();
//			outPrint.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

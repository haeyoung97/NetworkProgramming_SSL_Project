import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ButtonEventListener implements ActionListener {
	private JTextField textField;
	private JPanel contentpane;
	private String openPath;
	
	private JTextField sslFile;
	private JTextField serverAdd;
	private JTextField port;
	private JTextField username;
	private JTextField accessLog;
	
	//private SSLSocketClient sslSocketClient;
	
	public ButtonEventListener(JPanel contentPane, JTextField textField) {
		this.contentpane = contentPane;
		this.textField = textField;
	}
	
	public ButtonEventListener(JPanel contentPane, JTextField sslFile, JTextField serverAdd, JTextField port, JTextField username, JTextField accessLog) {
		this.contentpane = contentPane;
		this.sslFile = sslFile;
		this.serverAdd = serverAdd;
		this.port = port;
		this.username = username;
		this.accessLog = accessLog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AbstractButton btn = (AbstractButton)e.getSource();
		String btnName = btn.getName();
		this.command(btnName);
	}
	
	private void command(String btnName) {
		switch(btnName) {
		case "Access":
			String sslfilePath = sslFile.getText();
			String strServername = serverAdd.getText();
			int serverport = Integer.parseInt(port.getText());
			String strUsername = username.getText();
			System.out.println("접속");
			
			SSL_Client client = new SSL_Client(serverport, strServername, sslfilePath, strUsername);
			client.SSLconnection_Client();
			String strAccessLog = client.getAccessMessage();
			accessLog.setText(strAccessLog);
			
			if(client.isConnect()) {
				client.run();
			}
			
			break;
			
		case "Upload":
			System.out.println("업로드");
			openFilePath ofp = new openFilePath();
			openPath = ofp.PathReturn();
			textField.setText(openPath);
			System.out.println(openPath);
			break;
		}
		
	}
	public String getOpenPath() {
		return openPath;
	}

}

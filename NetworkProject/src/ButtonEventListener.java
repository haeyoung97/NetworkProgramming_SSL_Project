import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JTextField;

public class ButtonEventListener implements ActionListener {
	private JTextField textField;
	private String openPath;
	
	private JTextField sslFile;
	private JTextField serverAdd;
	private JTextField port;
	private JTextField username;
	private boolean TF = false;
	
	public ButtonEventListener(JTextField textField) {
		this.textField = textField;
	}
	
	public ButtonEventListener(JTextField sslFile, JTextField serverAdd, JTextField port, JTextField username) {
		this.sslFile = sslFile;
		this.serverAdd = serverAdd;
		this.port = port;
		this.username = username;
	}
	public ButtonEventListener(boolean TF) {
		this.TF = TF;
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
			TF = true;
			System.out.println("command : " + TF);
			
			String sslfilePath = sslFile.getText();
			String strServername = serverAdd.getText();
			int serverport = Integer.parseInt(port.getText());
			String strUsername = username.getText();
			System.out.println("접속");
			
			// 음 아니면 접속 버튼 누르면 그냥 뭐 음 boolean 리턴값 주고 clientInterface file에서 SSL_Client 생성자를 실행해야하나,....????????
			
//			SSL_Client client = new SSL_Client(serverport, strServername, sslfilePath, strUsername);
//			client.SSLconnection_Client();
//			String strAccessLog = client.getAccessMessage();
//			System.out.println(strAccessLog);
//			System.out.println(client.isConnect());
//			if(client.isConnect()) {
//				client.run();
//			}
			new HandleWordTextWindow(serverport, strServername, sslfilePath, strUsername);
			
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
	public boolean isPressAccess() {
		return TF;
	}
}

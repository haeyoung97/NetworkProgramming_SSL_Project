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
	
	private SSLSocketClient sslSocketClient;
	
	public ButtonEventListener(JPanel contentPane, JTextField textField) {
		this.contentpane = contentPane;
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AbstractButton btn = (AbstractButton)e.getSource();
		String btnName = btn.getName();
		String data = textField.getText();
		this.command(btnName, data);
	}
	
	private void command(String btnName, String data) {
		switch(btnName) {
		case "Access":
			sslSocketClient = new SSLSocketClient(data, 9999);
			System.out.println("접속");
			System.out.println(data);
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import RMI.HandleWordText;
import RMI.HandleWordTextImpl;

public class ButtonEventListener implements ActionListener {
	private JTextField textField;
	private String openPath;
	private String wordOpenPath;
	private String wordtext;
	private String infoText;
	
	private JTextArea wordRead;
	private JTextArea infoArea;
	
	private JTextField sslFile;
	private JTextField serverAdd;
	private JTextField port;
	private JTextField username;
	private boolean TF = false;
	
	private HandleWordText handleWordText;
//	private Highlighter.HighlightPainter myHighlighter = new MyHighLightPainter(Color.YELLOW);
	
	public ButtonEventListener(JTextField textField, JTextArea wordRead, JTextArea infoArea) {
		this.textField = textField;
		this.wordRead = wordRead;
		this.infoArea = infoArea;
	}
	public ButtonEventListener(JTextField textField, JTextArea wordRead) {
		this.textField = textField;
		this.wordRead = wordRead;
	}
	public ButtonEventListener(JTextField textField) {
		this.textField = textField;
	}
	
	public ButtonEventListener(JTextField sslFile, JTextField serverAdd, JTextField port, JTextField username) {
		this.sslFile = sslFile;
		this.serverAdd = serverAdd;
		this.port = port;
		this.username = username;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AbstractButton btn = (AbstractButton)e.getSource();
		String btnName = btn.getName();
		try {
			handleWordText = new HandleWordTextImpl();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			
			new HandleWordTextWindow(serverport, strServername, sslfilePath, strUsername);
			
			break;
			
		case "Upload":
			System.out.println("업로드");
			openFilePath ofp = new openFilePath();
			openPath = ofp.PathReturn();
			if(openPath != null) {
				textField.setText(openPath);
			}
			System.out.println(openPath);
			break;
		case "wordUpload":
			System.out.println("word Upload");
			openFilePath wordOpen = new openFilePath();
			wordOpenPath = wordOpen.PathReturn();
			if(wordOpenPath != null) {
				textField.setText(wordOpenPath);
			}
			else {
				return;
			}
			try {
				//handleWordText = new HandleWordTextImpl();
				wordtext = handleWordText.dataParse(wordOpenPath);
				infoText = handleWordText.printFileInfo(wordOpenPath);
				wordRead.setText(wordtext);
				infoArea.setText(infoText);
				infoArea.append("------------------------------------------[Search History]-------------------------------------------\n");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "search":
			
			String searchWord = textField.getText();
			
			if(searchWord.equals(null)) {
				JOptionPane.showMessageDialog(null, "값을 입력하세요");
				return;
			}
			else {
				try {
					System.out.println("search : " + searchWord);
					int count = handleWordText.highlight(wordRead, searchWord);
					handleWordText.printSearchHistory(searchWord, infoArea, count);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		
	}

	public boolean isPressAccess() {
		return TF;
	}
}

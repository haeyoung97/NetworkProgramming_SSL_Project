import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

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
				infoArea.append("-----------------[Search History]-----------------");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "search":
			System.out.println("search");
			String searchWord = textField.getText();
			
			if(searchWord.equals(null)) {
				JOptionPane.showMessageDialog(null, "값을 입력하세요");
				return;
			}
			else {
				try {
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
//	public void highlight(JTextComponent textComp, String pattern) {
//		removeHighlights(textComp);
//		try {
//			Highlighter hilite = textComp.getHighlighter();
//			Document doc = textComp.getDocument();
//			String text = doc.getText(0, doc.getLength());
//		
//			int pos = 0;
//			while((pos = text.indexOf(pattern, pos)) >= 0) {
//				hilite.addHighlight(pos, pos+pattern.length(), myHighlighter);
//				pos += pattern.length();
//			}
//			
//			
//		}
//		catch (BadLocationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void removeHighlights(JTextComponent textComp){
//		Highlighter hilite = textComp.getHighlighter();
//		Highlighter.Highlight[] hilites = hilite.getHighlights();
//		for(int i = 0; i < hilites.length; i++) {
//			if(hilites[i].getPainter() instanceof MyHighLightPainter) {
//				hilite.removeHighlight(hilites[i]);
//			}
//		}
//	}
//	private class MyHighLightPainter extends DefaultHighlighter.DefaultHighlightPainter{
//
//		public MyHighLightPainter(Color color) {
//			super(color);
//			// TODO Auto-generated constructor stub
//		}
//		
//	}
	public boolean isPressAccess() {
		return TF;
	}
}

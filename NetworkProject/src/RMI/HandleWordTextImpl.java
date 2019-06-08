package RMI;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
//
//import ButtonEventListener.MyHighLightPainter;

public class HandleWordTextImpl extends UnicastRemoteObject implements HandleWordText {
	private static final long serialVersionUID = 1L;
	
	private Highlighter.HighlightPainter myHighlighter = new MyHighLightPainter(Color.YELLOW);
	
	public HandleWordTextImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String dataParse(String FilePath) throws RemoteException {
		String path = FilePath;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			StringBuffer sb = new StringBuffer();
			String temp;
			while((temp = br.readLine()) != null) {
				sb.append(temp);
				sb.append("\n"); // 단순히 한문장씩 읽어서 "\n"이 적용되지 않는다.
			}
			br.close();
			String data = sb.toString();
			return data;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "hello";	
	}
	
	public void highlight(JTextComponent textComp, String pattern) throws RemoteException{
		removeHighlights(textComp);
		try {
			Highlighter hilite = textComp.getHighlighter();
			Document doc = textComp.getDocument();
			String text = doc.getText(0, doc.getLength());
		
			int pos = 0;
			while((pos = text.indexOf(pattern, pos)) >= 0) {
				hilite.addHighlight(pos, pos+pattern.length(), myHighlighter);
				pos += pattern.length();
			}
			
			
		}
		catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeHighlights(JTextComponent textComp) throws RemoteException {
		Highlighter hilite = textComp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();
		for(int i = 0; i < hilites.length; i++) {
			if(hilites[i].getPainter() instanceof MyHighLightPainter) {
				hilite.removeHighlight(hilites[i]);
			}
		}
	}
	
	
}

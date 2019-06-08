package RMI;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.StringBuffer;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
//:
//import ButtonEventListener.MyHighLightPainter;
//import RMI.MyHighLightPainter;

public class HandleWordTextImpl extends UnicastRemoteObject implements HandleWordText {
	private static final long serialVersionUID = 1L;

	//private static final String StringBuffer = null;
	
	private Highlighter.HighlightPainter myHighlighter = new MyHighLightPainter(Color.YELLOW);

	public HandleWordTextImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String dataParse(String FilePath) throws RemoteException {
		String path = FilePath;
		String data = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			StringBuffer sb = new StringBuffer();
			String temp;
			while((temp = br.readLine()) != null) {
				sb.append(temp);
				sb.append("\n"); // 단순히 한문장씩 읽어서 "\n"이 적용되지 않는다.
			}
			br.close();
			data = sb.toString();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;	
	}
	
	public int highlight(JTextComponent textComp, String pattern) throws RemoteException{
		removeHighlights(textComp);
		int count = 0;
		try {
			Highlighter hilite = textComp.getHighlighter();
			Document doc = textComp.getDocument();
			String text = doc.getText(0, doc.getLength());
			int l = textComp.getText().indexOf(pattern);
			
			if(l == -1) {
				JOptionPane.showMessageDialog(null, "search value not found");
				return 0;
			}
			int pos = 0;
			while((pos = text.indexOf(pattern, pos)) >= 0) {
				hilite.addHighlight(pos, pos+pattern.length(), myHighlighter);
				pos += pattern.length();
				count++;
			}
			
			
		}
		catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
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
	public String printFileInfo(String FilePath) throws RemoteException{
		String path = FilePath;
		String data = null;
		File file = new File(path);
		StringBuffer sb = new StringBuffer();
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(file.lastModified()));
		sb.append("File Name : " + file.getName() + "\n");
		sb.append("File Path : " + file.getPath() + "\n");
		sb.append("File Length : " + file.length() + "\n");
		sb.append("File Modify Date : " + date + "\n\n");
		
		data = sb.toString();
		
		return data;	
	}
	public void printSearchHistory(String searchword, JTextArea textArea, int count) throws RemoteException {
		String printing = "[search history] : " + searchword + "	[count] : " + count + "\n";
		textArea.append(printing);
	}
	
	
}

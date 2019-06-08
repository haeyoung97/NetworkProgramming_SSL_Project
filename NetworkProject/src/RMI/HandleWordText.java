package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public interface HandleWordText extends Remote {
	public String dataParse(String FilePath) throws RemoteException;
	public int highlight(JTextComponent textComp, String pattern) throws RemoteException;
	public void removeHighlights(JTextComponent textComp) throws RemoteException;
	public String printFileInfo(String FilePath) throws RemoteException;
	public void printSearchHistory(String searchword, JTextArea textArea, int count) throws RemoteException;

}

package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.text.JTextComponent;

public interface HandleWordText extends Remote {
	public String dataParse(String FilePath) throws RemoteException;
	public void highlight(JTextComponent textComp, String pattern) throws RemoteException;
	public void removeHighlights(JTextComponent textComp) throws RemoteException;
	
}

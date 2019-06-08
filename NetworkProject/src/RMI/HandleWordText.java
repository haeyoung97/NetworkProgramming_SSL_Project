package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HandleWordText extends Remote {
	public String dataParse(String FilePath) throws RemoteException;
	public void loadExcel() throws RemoteException;
	
}

package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HandleWordTextImpl extends UnicastRemoteObject implements HandleWordText {
	private static final long serialVersionUID = 1L;
	
	
	public HandleWordTextImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String dataParse() throws RemoteException {
		return "hello";	
	}
	
	public void loadExcel() throws RemoteException{
		
	}
}

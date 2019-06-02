package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HandleExcelImpl extends UnicastRemoteObject implements HandleExcel {
	private static final long serialVersionUID = 1L;
	
	
	protected HandleExcelImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String dataParse() throws RemoteException {
		return null;
		
	}
}

package RMI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HandleExcelImpl extends UnicastRemoteObject implements HandleExcel {
	private static final long serialVersionUID = 1L;
	
	
	public HandleExcelImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String dataParse() throws RemoteException {
		return "hello";	
	}
	
	public void loadExcel() throws RemoteException{
		String loadFile = "C:\\Users\\haeyoung\\Documents\\GitHub\\NetworkProgramming_SSL_Project\\NetworkProject\\festival_data.xlsx";
		
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(loadFile);
	
			XSSFWorkbook  workbook = new XSSFWorkbook (fis);
			int rowindex = 0; int columnindex = 0;
		
			XSSFSheet sheet = workbook.getSheetAt(0);
			// ÇàÀÇ ¼ö
			int rows = sheet.getPhysicalNumberOfRows();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
		
		
	}
}

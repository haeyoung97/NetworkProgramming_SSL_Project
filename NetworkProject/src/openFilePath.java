import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class openFilePath {
	
	private JFileChooser fileChooser;
	private String returnOpenPath;
	private FileOutputStream fs;
	
	public openFilePath() {
		fileChooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("sslkeyfile", "ssl file");
		//fileChooser.setFileFilter(filter);
		
		fileChooser.setDialogTitle("Open");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int ret = fileChooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�.");
			return;
		}
		returnOpenPath = fileChooser.getSelectedFile().getPath();
	}
	public String PathReturn() {
		return returnOpenPath;
	}
}

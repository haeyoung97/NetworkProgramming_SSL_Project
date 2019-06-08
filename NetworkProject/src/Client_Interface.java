import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
//import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Client_Interface extends JFrame {
	
	static final int LayoutWidth = 1000;
	static final int LayoutHeight = 300;
	
	private Container contentPane;
	protected JPanel startPane = new JPanel();
	protected JPanel searchPane = new JPanel();
	
	private JTextField serverAddressField;
	private JTextField sslUploadField;
	private JTextField usernameField;
	//private JTextField isAccessField;
	private JTextField serverPortField;
	
	private JButton serverAccessBtn;
	private JButton sslUploadBtn;
	
	
	private ButtonEventListener serverAccessListener;
	private ButtonEventListener sslUploadListener;
	
	private JSplitPane jsp;
	
	public Client_Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LayoutWidth, LayoutHeight);
		contentPane = getContentPane();
		createButton();
		//createSearchPane();
		//SSL_function(isAccess);
		
		//jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, startPane, searchPane);
		//jsp.setDividerLocation(LayoutWidth/7);
		
		contentPane.add(startPane);
		//SSL_function();
		
		setVisible(true);
		
	}
	void createButton() {
		
		JPanel panel = new JPanel();
		
		sslUploadField = new JTextField(15);
		sslUploadField.setText("sslFile upload");
		sslUploadField.setSize(80, 20);
		panel.add(sslUploadField);

		sslUploadBtn = new JButton();
		sslUploadBtn.setText("업로드");
		sslUploadBtn.setName("Upload");
		sslUploadBtn.setPreferredSize(new Dimension(70, 20));
		sslUploadBtn.setBorderPainted(false);
		panel.add(sslUploadBtn);
		
		JLabel serverAddressLabel = new JLabel("server address : ");
		panel.add(serverAddressLabel);
		
		serverAddressField = new JTextField(10);
		serverAddressField.setSize(80,20);
		panel.add(serverAddressField);
		
		JLabel serverPortLabel = new JLabel("server port : ");
		panel.add(serverPortLabel);
		
		serverPortField = new JTextField(5);
		panel.add(serverPortField);
		
		JLabel usernameLabel = new JLabel("username : ");
		panel.add(usernameLabel);
		
		usernameField = new JTextField(8);
		panel.add(usernameField);
		
		serverAccessBtn = new JButton();
		serverAccessBtn.setText("접속");
		serverAccessBtn.setName("Access");
		serverAccessBtn.setPreferredSize(new Dimension(70,20));
		serverAccessBtn.setBorderPainted(false);
		panel.add(serverAccessBtn);
			
		startPane.add(panel, BorderLayout.CENTER);
	
		sslUploadListener = new ButtonEventListener(sslUploadField);
		serverAccessListener = new ButtonEventListener(sslUploadField, serverAddressField, serverPortField, usernameField);
		
		sslUploadBtn.addActionListener(sslUploadListener);
		serverAccessBtn.addActionListener(serverAccessListener);
		
	}
	
//	void createSearchPane() {
//		JPanel panel = new JPanel();
//		
//		JLabel searchLabel = new JLabel("search : ");
//		panel.add(searchLabel);
//		
//		JTextField searchField = new JTextField(20);
//		panel.add(searchField);
//		
//		JButton searchButton = new JButton();
//		searchButton.setText("search");
//		searchButton.setName("search");
//		searchButton.setBorderPainted(false);
//		panel.add(searchButton);
//		
//		searchPane.add(panel, BorderLayout.NORTH);
//				
//		// scroll Bar 수정중...
//		JTextArea searchArea = new JTextArea();
//		//searchArea.setEnabled(false);
//		searchArea.setName("searchArea");
//		searchArea.setText("textArea");
//		searchArea.setPreferredSize(new Dimension(800, 300));
//		JScrollPane scrollpane = new JScrollPane(searchArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		//scrollpane.set
//		//scrollpane.add(searchArea);
//		searchPane.add(scrollpane, BorderLayout.SOUTH);
//		
//	}
	

	public static void main(String[] args) {
		new Client_Interface();
	}

}


 
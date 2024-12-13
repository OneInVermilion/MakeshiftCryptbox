package JPanels;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.CustomPanel;
import Classes.Metadater;
import Classes.Steganographer;

import java.awt.CardLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;

public class MetadataPanel extends CustomPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textInput;
	private JTextField textOutput;
	private JTextField textFolderPath;
	
	private JTextField textCurrentTime;
	private JTextField textCreated;
	private JTextField textLastModified;
	private JTextField textLastAccessed;
	private JTextField textOwner;
	
	private JLabel lblCurrentTime;
	private JLabel lblCreated;
	private JLabel lblLastModified;
	private JLabel lblLastAccessed;
	private JLabel lblSize;
	private JLabel lblSizeTransl;
	private JLabel lblOwner;
	private JLabel lblRegularFile;
	private JLabel lblDirectory;
	private JLabel lblSymbolicLink;
	private JLabel lblOther;
	private JLabel lblConsole;
	
	private JRadioButton rdbtnRegularFile;
	private JRadioButton rdbtnDirectory;
	private JRadioButton rdbtnSymbolicLink;
	private JRadioButton rdbtnOther;
	private JButton btnSave;

	/**
	 * Create the panel.
	 */
	public MetadataPanel() {
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new MainMenuPanel());
			}
		});
		btnBack.setBounds(10, 325, 80, 30);
		add(btnBack);
		
		textInput = new JTextField();
		textInput.setToolTipText("Example.png");
		textInput.setBounds(30, 90, 250, 30);
		add(textInput);
		textInput.setColumns(10);
		
		textOutput = new JTextField();
		textOutput.setToolTipText("Example1.png");
		textOutput.setColumns(10);
		textOutput.setBounds(300, 90, 250, 30);
		add(textOutput);
		
		
		
		JLabel lblInputPath = new JLabel("Input File Name");
		lblInputPath.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputPath.setBounds(105, 70, 100, 13);
		add(lblInputPath);
		
		JLabel lblOutputPath = new JLabel("Output File Name (Only For Writing)");
		lblOutputPath.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutputPath.setBounds(305, 70, 220, 13);
		add(lblOutputPath);
		
		textFolderPath = new JTextField();
		textFolderPath.setToolTipText("C:/Users/Example/Documents");
		textFolderPath.setColumns(10);
		textFolderPath.setBounds(30, 30, 520, 30);
		add(textFolderPath);
		
		JLabel lblFolderPath = new JLabel("Folder Path");
		lblFolderPath.setHorizontalAlignment(SwingConstants.CENTER);
		lblFolderPath.setBounds(250, 10, 80, 13);
		add(lblFolderPath);
		
		textCurrentTime = new JTextField();
		textCurrentTime.setEditable(false);
		textCurrentTime.setColumns(10);
		textCurrentTime.setBounds(105, 130, 175, 20);
		add(textCurrentTime);
		
		lblCurrentTime = new JLabel("Current Time");
		lblCurrentTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentTime.setBounds(0, 130, 100, 20);
		add(lblCurrentTime);
		
		lblCreated = new JLabel("Created");
		lblCreated.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreated.setBounds(0, 150, 100, 20);
		add(lblCreated);
		
		textCreated = new JTextField();
		textCreated.setToolTipText("dd.MM.yyyy kk:mm:ss.SSS");
		textCreated.setColumns(10);
		textCreated.setBounds(105, 150, 175, 20);
		add(textCreated);
		
		textLastModified = new JTextField();
		textLastModified.setToolTipText("dd.MM.yyyy kk:mm:ss.SSS");
		textLastModified.setColumns(10);
		textLastModified.setBounds(105, 170, 175, 20);
		add(textLastModified);
		
		lblLastModified = new JLabel("Last Modified");
		lblLastModified.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastModified.setBounds(0, 170, 100, 20);
		add(lblLastModified);
		
		textLastAccessed = new JTextField();
		textLastAccessed.setToolTipText("dd.MM.yyyy kk:mm:ss.SSS");
		textLastAccessed.setColumns(10);
		textLastAccessed.setBounds(105, 190, 175, 20);
		add(textLastAccessed);
		
		lblLastAccessed = new JLabel("Last Accessed");
		lblLastAccessed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastAccessed.setBounds(0, 190, 100, 20);
		add(lblLastAccessed);
		
		lblSize = new JLabel("Size");
		lblSize.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSize.setBounds(0, 230, 100, 20);
		add(lblSize);
		
		lblSizeTransl = new JLabel("X B = X KB = X MB = X GB");
		lblSizeTransl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSizeTransl.setHorizontalAlignment(SwingConstants.LEFT);
		lblSizeTransl.setBounds(105, 231, 175, 20);
		add(lblSizeTransl);
		
		lblOwner = new JLabel("Owner");
		lblOwner.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOwner.setBounds(0, 210, 100, 20);
		add(lblOwner);
		
		textOwner = new JTextField();
		textOwner.setToolTipText("username");
		textOwner.setColumns(10);
		textOwner.setBounds(105, 210, 175, 20);
		add(textOwner);
		
		lblRegularFile = new JLabel("Regular File");
		lblRegularFile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegularFile.setBounds(0, 250, 100, 20);
		add(lblRegularFile);
		
		lblDirectory = new JLabel("Directory");
		lblDirectory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirectory.setBounds(0, 270, 100, 20);
		add(lblDirectory);
		
		lblSymbolicLink = new JLabel("Symbolic Link");
		lblSymbolicLink.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSymbolicLink.setBounds(0, 290, 100, 20);
		add(lblSymbolicLink);
		
		lblOther = new JLabel("Other");
		lblOther.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOther.setBounds(0, 310, 100, 20);
		add(lblOther);
		
		rdbtnRegularFile = new JRadioButton();
		rdbtnRegularFile.setEnabled(false);
		rdbtnRegularFile.setBounds(105, 250, 30, 20);
		add(rdbtnRegularFile);
		
		rdbtnDirectory = new JRadioButton();
		rdbtnDirectory.setEnabled(false);
		rdbtnDirectory.setBounds(105, 270, 30, 20);
		add(rdbtnDirectory);
		
		rdbtnSymbolicLink = new JRadioButton();
		rdbtnSymbolicLink.setEnabled(false);
		rdbtnSymbolicLink.setBounds(105, 290, 30, 20);
		add(rdbtnSymbolicLink);
		
		rdbtnOther = new JRadioButton();
		rdbtnOther.setEnabled(false);
		rdbtnOther.setBounds(105, 310, 30, 20);
		add(rdbtnOther);
		
		ButtonGroup btnGr = new ButtonGroup();
		btnGr.add(rdbtnRegularFile);
		btnGr.add(rdbtnDirectory);
		btnGr.add(rdbtnSymbolicLink);
		btnGr.add(rdbtnOther);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {loadFile(textFolderPath.getText() + "/" + textInput.getText());
				lblConsole.setText("<html>"
		                + "Successfully Loaded File " + textInput.getText() + "<br>"
		                + "Full Path:" + "<br>"
		                + textFolderPath.getText() + "\\" + textInput.getText()
		                + "</html>");
				}
				catch (IOException e1) {e1.printStackTrace(); lblConsole.setText("Unknown Error. Please Check All Fields For Correctness");}
			};
		});
		btnLoad.setBounds(135, 325, 70, 30);
		add(btnLoad);
		
		btnSave = new JButton("Save");
		btnSave.setToolTipText("Create Output File With Contents Of Input File And Selected Metadata");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {saveFile();
				lblConsole.setText("<html>"
		                + "Successfully Saved File " + textOutput.getText() + "<br>"
		                + "Full Path:" + "<br>"
		                + textFolderPath.getText() + "\\" + textOutput.getText()
		                + "</html>");
				}
				catch (IOException e1) {e1.printStackTrace(); lblConsole.setText("Unknown Error. Please Check All Fields For Correctness");}
			}
		});
		btnSave.setBounds(210, 325, 70, 30);
		add(btnSave);
		
		lblConsole = new JLabel("Hold Cursor Over Fields To Gain Information");
		lblConsole.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblConsole.setVerticalAlignment(SwingConstants.BOTTOM);
		lblConsole.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsole.setBounds(290, 325, 260, 30);
		add(lblConsole);

	}
	
	private void loadFile(String path) throws IOException {
		String[] data = Metadater.getData(path);
		textCurrentTime.setText(data[9]);
		textCreated.setText(data[0]);
		textLastModified.setText(data[1]);
		textLastAccessed.setText(data[2]);
		textOwner.setText(Metadater.formatUser(data[3]));
		Long sizeB = Long.parseLong(data[4]);
		lblSizeTransl.setText(sizeB + " B = " + (sizeB / 1024) + " KB = " + (sizeB / 1024 / 1024) + " MB = " + (sizeB / 1024 / 1024 / 1024) + " GB");
		rdbtnRegularFile.setSelected(Boolean.parseBoolean(data[5]));
		rdbtnDirectory.setSelected(Boolean.parseBoolean(data[6]));
		rdbtnSymbolicLink.setSelected(Boolean.parseBoolean(data[7]));
		rdbtnOther.setSelected(Boolean.parseBoolean(data[8]));
	}
	
	private void saveFile() throws IOException {
		String iPath = textFolderPath.getText() + "/" + textInput.getText();
		String oPath = textFolderPath.getText() + "/" + textOutput.getText();
		String[] data = new String[4];
		data[0] = textCreated.getText();
		data[1] = textLastModified.getText();
		data[2] = textLastAccessed.getText();
		data[3] = textOwner.getText();
		Metadater.createFile(iPath, oPath, data);
		//System.out.println("MetadataPanel.saveFile()");
	}
  }

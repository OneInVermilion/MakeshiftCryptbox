package JPanels;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.CustomPanel;
import Classes.Hexer;
import Classes.Steganographer;
import Classes.Tracker;

import java.awt.CardLayout;
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
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HexEditorPanel extends CustomPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textInput;
	private JTextField textFolderPath;
	
	private boolean showAsSymbols = false;
	private JTextField textIntSigned;
	private JTextField textIntUnsigned;
	private JTextField textBin;
	private JTextField textHex;
	private JTextField textAscii;
	private JButton btnApply;
	JLabel lblConsole;
	
	private byte[] fileBytes;
	private byte byteBeingEdited;
	private String loadedFilePath;
	private JTextField textOutput;
	

	/**
	 * Create the panel.
	 */
	public HexEditorPanel() {
		Tracker.hexEditorPanel = this;
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
		textInput.setBounds(380, 80, 170, 30);
		add(textInput);
		textInput.setColumns(10);
		
		
		
		JLabel lblInputPath = new JLabel("Input File Name");
		lblInputPath.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInputPath.setBounds(450, 67, 100, 13);
		add(lblInputPath);
		
		textFolderPath = new JTextField();
		textFolderPath.setToolTipText("C:/Users/Example/Documents");
		textFolderPath.setColumns(10);
		textFolderPath.setBounds(30, 30, 520, 30);
		add(textFolderPath);
		
		JLabel lblFolderPath = new JLabel("Folder Path");
		lblFolderPath.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFolderPath.setBounds(470, 10, 80, 13);
		add(lblFolderPath);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadHexInfoPanel();
			}
		});
		btnLoad.setBounds(470, 170, 80, 30);
		add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(textFolderPath.getText() + "/" + textOutput.getText());
			}
		});
		btnSave.setBounds(380, 170, 80, 30);
		add(btnSave);
		
		textIntSigned = new JTextField();
		textIntSigned.setEditable(false);
		textIntSigned.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String t = textIntSigned.getText();
				byte b = Hexer.getByteFromRepresentation(t, 0);
				byteBeingEdited = b;
			}
		});
		textIntSigned.setColumns(10);
		textIntSigned.setBounds(380, 225, 80, 30);
		add(textIntSigned);
		
		textIntUnsigned = new JTextField();
		textIntUnsigned.setEditable(false);
		textIntUnsigned.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String t = textIntUnsigned.getText();
				byte b = Hexer.getByteFromRepresentation(t, 1);
				byteBeingEdited = b;
			}
		});
		textIntUnsigned.setColumns(10);
		textIntUnsigned.setBounds(470, 225, 80, 30);
		add(textIntUnsigned);
		
		textBin = new JTextField();
		textBin.setEditable(false);
		textBin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String t = textBin.getText();
				byte b = Hexer.getByteFromRepresentation(t, 2);
				byteBeingEdited = b;
			}
		});
		textBin.setColumns(10);
		textBin.setBounds(380, 265, 170, 30);
		add(textBin);
		
		textHex = new JTextField();
		textHex.setEditable(false);
		textHex.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String t = textHex.getText();
				byte b = Hexer.getByteFromRepresentation(t, 3);
				byteBeingEdited = b;
			}
		});
		textHex.setColumns(10);
		textHex.setBounds(470, 306, 80, 30);
		add(textHex);
		
		textAscii = new JTextField();
		textAscii.setEditable(false);
		textAscii.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String t = textAscii.getText();
				byte b = Hexer.getByteFromRepresentation(t, 4);
				byteBeingEdited = b;
			}
		});
		textAscii.setColumns(10);
		textAscii.setBounds(380, 305, 80, 30);
		add(textAscii);
		
		JLabel lblSigned = new JLabel("signed");
		lblSigned.setHorizontalAlignment(SwingConstants.CENTER);
		lblSigned.setBounds(380, 210, 80, 15);
		add(lblSigned);
		
		JLabel lblUnsigned = new JLabel("unsigned");
		lblUnsigned.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnsigned.setBounds(470, 210, 80, 15);
		add(lblUnsigned);
		
		JLabel lblBin = new JLabel("bin");
		lblBin.setHorizontalAlignment(SwingConstants.LEFT);
		lblBin.setBounds(555, 272, 20, 15);
		add(lblBin);
		
		JLabel lblInt = new JLabel("int");
		lblInt.setHorizontalAlignment(SwingConstants.LEFT);
		lblInt.setBounds(555, 233, 20, 15);
		add(lblInt);
		
		JLabel lblHex = new JLabel("hex");
		lblHex.setHorizontalAlignment(SwingConstants.LEFT);
		lblHex.setBounds(555, 314, 25, 15);
		add(lblHex);
		
		JLabel lblAscii = new JLabel("ascii");
		lblAscii.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAscii.setBounds(325, 314, 50, 15);
		add(lblAscii);
		
		JCheckBox chckbxShowAsSymbols = new JCheckBox("Show As Symbols");
		chckbxShowAsSymbols.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//byteBeingEdited = (Byte) null;
				//Tracker.selectedByte = (Byte) null;
				//Tracker.selectedBytePosition = (Integer) null;
				
				showAsSymbols = chckbxShowAsSymbols.isSelected();
				Tracker.hexInfoPanel.clearPanel();
				Tracker.hexInfoPanel.updPanel(fileBytes, showAsSymbols);
				switchByteFieldsEditable(false);
			}
		});
		chckbxShowAsSymbols.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxShowAsSymbols.setBounds(194, 320, 125, 20);
		add(chckbxShowAsSymbols);
		
		btnApply = new JButton("Ok");
		btnApply.setEnabled(false);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updByteFields(byteBeingEdited);
				fileBytes[Tracker.selectedBytePosition] = byteBeingEdited;
				updHexInfoPanel();
			}
		});
		btnApply.setBounds(490, 340, 60, 20);
		add(btnApply);
		
		lblConsole = new JLabel("");
		lblConsole.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblConsole.setVerticalAlignment(SwingConstants.BOTTOM);
		lblConsole.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsole.setBounds(100, 325, 380, 30);
		add(lblConsole);
		
		textOutput = new JTextField();
		textOutput.setToolTipText("Example.png");
		textOutput.setColumns(10);
		textOutput.setBounds(380, 130, 170, 30);
		add(textOutput);
		
		JLabel lblOutputPath = new JLabel("Output File Name");
		lblOutputPath.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOutputPath.setBounds(450, 117, 100, 13);
		add(lblOutputPath);

	}
	
	private void loadHexInfoPanel() {
		fileBytes = null;
		try {
			//fileBytes = Hexer.getBinaryData("C:/Users/ermak/Documents/fileProgrammingTests/test.png");
			loadedFilePath = textFolderPath.getText() + "/" + textInput.getText();
			fileBytes = Hexer.getBinaryData(loadedFilePath);
			Tracker.hexInfoPanel.clearPanel();
			Tracker.hexInfoPanel.updPanel(fileBytes, showAsSymbols);
			lblConsole.setText("Loaded " + textInput.getText() + " Successfully");
		}
		catch (IOException e1) {e1.printStackTrace(); lblConsole.setText("Loading Failed");}
	}
	
	private void saveFile(String name) {
		try {
			Hexer.createFile(name, fileBytes);
			lblConsole.setText("Saved " + name + " Successfully");
		}
		catch (IOException e) {e.printStackTrace(); lblConsole.setText("Saving Failed");}
	}
	
	private void updHexInfoPanel() {
		Tracker.hexInfoPanel.clearPanel();
		Tracker.hexInfoPanel.updPanel(fileBytes, showAsSymbols);
	}
	
	public void updByteFields(byte b) {
		textIntSigned.setText(Hexer.getByteRepresentations(b)[0]);
		textIntUnsigned.setText(Hexer.getByteRepresentations(b)[1]);
		textBin.setText(Hexer.getByteRepresentations(b)[2]);
		textHex.setText(Hexer.getByteRepresentations(b)[3]);
		textAscii.setText(Hexer.getByteRepresentations(b)[4]);
		switchByteFieldsEditable(true);
		//if (showAsSymbols) {Tracker.currentBytePanel.setText(Hexer.getByteRepresentations(b)[4]);}
		//else {Tracker.currentBytePanel.setText(Hexer.getByteRepresentations(b)[3]);}
		
	}
	
	private void switchByteFieldsEditable(boolean state) {
		if (state) {
			textIntSigned.setEditable(true);
			textIntUnsigned.setEditable(true);
			textBin.setEditable(true);
			textAscii.setEditable(true);
			textHex.setEditable(true);
			btnApply.setEnabled(true);
		}
		else {
			textIntSigned.setEditable(false);
			textIntUnsigned.setEditable(false);
			textBin.setEditable(false);
			textAscii.setEditable(false);
			textHex.setEditable(false);
			btnApply.setEnabled(false);
			textIntSigned.setText("");
			textIntUnsigned.setText("");
			textBin.setText("");
			textAscii.setText("");
			textHex.setText("");
		}
	}
 }

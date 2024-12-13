package JPanels;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.CustomPanel;
import Classes.Hexer;
import Classes.Steganographer;
import Classes.Tracker;

import java.awt.CardLayout;

import javax.swing.AbstractButton;
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
import javax.swing.JProgressBar;

public class HexEditorPanel2 extends CustomPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textInput;
	private JTextField textFolderPath;
	
	private JTextField textIntSigned;
	private JTextField textIntUnsigned;
	private JTextField textBin;
	private JTextField textHex;
	private JTextField textAscii;
	JLabel lblConsole;
	JLabel lblOutputPath;
	
	private JLabel lblLoadText;
	private JCheckBox chckbxLoadTextInfo;
	private boolean useViewer;
	
	private String loadedFilePath;
	private JTextField textOutput;
	private JTextField textLocation;
	

	/**
	 * Create the panel.
	 */
	public HexEditorPanel2() {
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
		textFolderPath.setBounds(20, 30, 530, 30);
		add(textFolderPath);
		
		JLabel lblFolderPath = new JLabel("Folder Path");
		lblFolderPath.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFolderPath.setBounds(470, 10, 80, 13);
		add(lblFolderPath);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switchByteFieldsEditable(false);
					loadHexInfoPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String t = textIntSigned.getText();
					byte b = Hexer.getByteFromRepresentation(t, 0);
					applyByteChange(Integer.parseInt(textLocation.getText(), 16), b);
				}
			}
		});
		textIntSigned.setColumns(10);
		textIntSigned.setBounds(380, 225, 80, 30);
		add(textIntSigned);
		
		textIntUnsigned = new JTextField();
		textIntUnsigned.setEditable(false);
		textIntUnsigned.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String t = textIntUnsigned.getText();
					byte b = Hexer.getByteFromRepresentation(t, 1);
					applyByteChange(Integer.parseInt(textLocation.getText(), 16), b);
				}
			}
		});
		textIntUnsigned.setColumns(10);
		textIntUnsigned.setBounds(470, 225, 80, 30);
		add(textIntUnsigned);
		
		textBin = new JTextField();
		textBin.setEditable(false);
		textBin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String t = textBin.getText();
					byte b = Hexer.getByteFromRepresentation(t, 2);
					applyByteChange(Integer.parseInt(textLocation.getText(), 16), b);
				}
			}
		});
		textBin.setColumns(10);
		textBin.setBounds(380, 265, 170, 30);
		add(textBin);
		
		textHex = new JTextField();
		textHex.setEditable(false);
		textHex.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String t = textHex.getText();
					byte b = Hexer.getByteFromRepresentation(t, 3);
					applyByteChange(Integer.parseInt(textLocation.getText(), 16), b);
				}
			}
		});
		textHex.setColumns(10);
		textHex.setBounds(470, 306, 80, 30);
		add(textHex);
		
		textAscii = new JTextField();
		textAscii.setEditable(false);
		textAscii.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String t = textAscii.getText();
					byte b = Hexer.getByteFromRepresentation(t, 4);
					applyByteChange(Integer.parseInt(textLocation.getText(), 16), b);
				}
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
		
		lblConsole = new JLabel("");
		lblConsole.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblConsole.setVerticalAlignment(SwingConstants.BOTTOM);
		lblConsole.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsole.setBounds(100, 335, 380, 20);
		add(lblConsole);
		
		textOutput = new JTextField();
		textOutput.setToolTipText("Example1.png");
		textOutput.setColumns(10);
		textOutput.setBounds(380, 130, 170, 30);
		add(textOutput);
		
		lblOutputPath = new JLabel("Output File Name");
		lblOutputPath.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOutputPath.setBounds(450, 117, 100, 13);
		add(lblOutputPath);
		
		textLocation = new JTextField();
		textLocation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					changeTextLocation(0);
					//Tracker.hexInfoPanel.findByteInInfoString(Integer.parseInt(textLocation.getText()));
				}
			}
		});
		textLocation.setText("0");
		textLocation.setColumns(10);
		textLocation.setBounds(185, 305, 80, 30);
		add(textLocation);
		
		JButton btnLocLeft = new JButton("<");
		btnLocLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeTextLocation(-1);
			}
		});
		btnLocLeft.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLocLeft.setBounds(135, 305, 50, 30);
		add(btnLocLeft);
		
		JButton btnLocRight = new JButton(">");
		btnLocRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeTextLocation(1);
			}
		});
		btnLocRight.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLocRight.setBounds(265, 305, 50, 30);
		add(btnLocRight);
		
		chckbxLoadTextInfo = new JCheckBox("");
		chckbxLoadTextInfo.setSelected(true);
		chckbxLoadTextInfo.setBounds(555, 175, 20, 20);
		add(chckbxLoadTextInfo);
		
		lblLoadText = new JLabel("<html>"
                + "Load" + "<br>"
                + "In" + "<br>"
                + "Byte" + "<br>"
                + "Viewer" + "<br>"
                + "</html>");
		lblLoadText.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblLoadText.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadText.setBounds(550, 130, 35, 43);
		add(lblLoadText);
	}
	
	//chanes the text representation of byte location
	//aka + or - the amount from integer number, represented as a string
	private void changeTextLocation(int amount) {
		try {
			String loc = textLocation.getText();
			int locInt = Integer.parseInt(loc, 16);
			textLocation.setText(Integer.toString(locInt + amount, 16));
			updByteFields(fileBytes[locInt + amount]);
		}
		catch (Exception e1) {
			lblConsole.setText("Byte Location Does Not Exist");
			switchByteFieldsEditable(false);
		}
		
	}
	
	private void applyByteChange(int location, byte val) {
		try {
			fileBytes[location] = val;
			String hexVal = Hexer.getByteRepresentations(val)[3];
			if (useViewer) {Tracker.hexInfoPanel.changeByteInInfoString(hexVal, location);}
			lblConsole.setText("Changed Byte Successfully");
		}
		catch (Exception e1) {
			lblConsole.setText("Incorrect Byte Value");
		}
		textIntSigned.setText(Hexer.getByteRepresentations(val)[0]);
		textIntUnsigned.setText(Hexer.getByteRepresentations(val)[1]);
		textBin.setText(Hexer.getByteRepresentations(val)[2]);
		textHex.setText(Hexer.getByteRepresentations(val)[3]);
		textAscii.setText(Hexer.getByteRepresentations(val)[4]);
	}
	
	private void loadHexInfoPanel() throws IOException {
		useViewer = chckbxLoadTextInfo.isSelected();
		//fileBytes = Hexer.getBinaryData("C:/Users/ermak/Documents/fileProgrammingTests/bs.png");
		loadedFilePath = textFolderPath.getText() + "/" + textInput.getText();
		fileBytes = Hexer.getBinaryData(loadedFilePath);
		Tracker.hexInfoPanel.clearPanel();
		if (useViewer) {Tracker.hexInfoPanel.updPanel(fileBytes);}
		lblConsole.setText("Loaded " + textInput.getText() + " Successfully. Last Byte: " + Integer.toHexString(fileBytes.length - 1));
	}
	
	private void saveFile(String name) {
		try {
			Hexer.createFile(name, fileBytes);
			lblConsole.setText("Saved " + name + " Successfully");
		}
		catch (IOException e) {e.printStackTrace(); lblConsole.setText("Saving Failed");}
	}
	
	public void updByteFields(byte b) {
		textIntSigned.setText(Hexer.getByteRepresentations(b)[0]);
		textIntUnsigned.setText(Hexer.getByteRepresentations(b)[1]);
		textBin.setText(Hexer.getByteRepresentations(b)[2]);
		textHex.setText(Hexer.getByteRepresentations(b)[3]);
		textAscii.setText(Hexer.getByteRepresentations(b)[4]);
		switchByteFieldsEditable(true);
	}
	
	private void switchByteFieldsEditable(boolean state) {
		if (state) {
			textIntSigned.setEditable(true);
			textIntUnsigned.setEditable(true);
			textBin.setEditable(true);
			textAscii.setEditable(true);
			textHex.setEditable(true);
		}
		else {
			textIntSigned.setEditable(false);
			textIntUnsigned.setEditable(false);
			textBin.setEditable(false);
			textAscii.setEditable(false);
			textHex.setEditable(false);
			textIntSigned.setText("");
			textIntUnsigned.setText("");
			textBin.setText("");
			textAscii.setText("");
			textHex.setText("");
		}
	}
 }

package JPanels;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.CustomPanel;
import Classes.Steganographer;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Text2ImagePanel extends CustomPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textInput;
	private JTextField textOutput;
	private JTextField textMessage;
	private JTextField textPixelStart;
	private JTextField textPixelStep;
	private JTextField textEncodedBits;
	private JTextField textFolderPath;
	private JCheckBox bxR;
	private JCheckBox bxG;
	private JCheckBox bxB;
	private JCheckBox bxA;
	private JLabel lblPixelsCount;
	
	private int bits_needed = 0;
	private int bits_available = 0;

	/**
	 * Create the panel.
	 */
	public Text2ImagePanel() {
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
		textInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updPixelCounter();
			}
		});
		textInput.setBounds(30, 90, 250, 30);
		add(textInput);
		textInput.setColumns(10);
		
		textOutput = new JTextField();
		textOutput.setToolTipText("Example1.png");
		textOutput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updPixelCounter();
			}
		});
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
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(250, 130, 80, 13);
		add(lblMessage);
		
		JCheckBox bxRead = new JCheckBox("Read");
		bxRead.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bxRead.setBounds(100, 190, 50, 30);
		add(bxRead);
		
		JLabel lblPixelStart = new JLabel("Pixel Start");
		lblPixelStart.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblPixelStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblPixelStart.setBounds(160, 190, 60, 10);
		add(lblPixelStart);
		
		JLabel lblPixelStep = new JLabel("Pixel Step");
		lblPixelStep.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblPixelStep.setHorizontalAlignment(SwingConstants.CENTER);
		lblPixelStep.setBounds(230, 190, 60, 10);
		add(lblPixelStep);
		
		JLabel lblEncodedBits = new JLabel("Enc Bits/Val");
		lblEncodedBits.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncodedBits.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEncodedBits.setBounds(290, 190, 80, 10);
		add(lblEncodedBits);
		
		JLabel lblInvolvedValues = new JLabel("Involved Values: RGBA");
		lblInvolvedValues.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblInvolvedValues.setHorizontalAlignment(SwingConstants.LEFT);
		lblInvolvedValues.setBounds(370, 200, 120, 13);
		add(lblInvolvedValues);
		
		bxG = new JCheckBox("");
		bxG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updPixelCounter();
			}
		});
		bxG.setSelected(true);
		bxG.setBounds(490, 195, 20, 20);
		add(bxG);
		
		bxB = new JCheckBox("");
		bxB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updPixelCounter();
			}
		});
		bxB.setSelected(true);
		bxB.setBounds(510, 195, 20, 20);
		add(bxB);
		
		bxR = new JCheckBox("");
		bxR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updPixelCounter();
			}
		});
		bxR.setSelected(true);
		bxR.setBounds(470, 195, 20, 20);
		add(bxR);
		
		bxA = new JCheckBox("");
		bxA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updPixelCounter();
			}
		});
		bxA.setBounds(530, 195, 20, 20);
		add(bxA);
		
		JLabel lblPixelsNeeded = new JLabel("Bits Needed / Available");
		lblPixelsNeeded.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblPixelsNeeded.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPixelsNeeded.setBounds(410, 120, 140, 10);
		add(lblPixelsNeeded);
		
		lblPixelsCount = new JLabel("0 / 0");
		lblPixelsCount.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPixelsCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPixelsCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPixelsCount.setBounds(340, 130, 210, 20);
		add(lblPixelsCount);
		
		JLabel lblConsole = new JLabel("Hold Cursor Over Fields To Gain Information");
		lblConsole.setVerticalAlignment(SwingConstants.BOTTOM);
		lblConsole.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsole.setBounds(30, 226, 520, 88);
		add(lblConsole);
		
		textFolderPath = new JTextField();
		textFolderPath.setToolTipText("C:/Users/Example/Documents");
		textFolderPath.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updPixelCounter();
			}
		});
		textFolderPath.setColumns(10);
		textFolderPath.setBounds(30, 30, 520, 30);
		add(textFolderPath);
		
		JLabel lblFolderPath = new JLabel("Folder Path");
		lblFolderPath.setHorizontalAlignment(SwingConstants.CENTER);
		lblFolderPath.setBounds(250, 10, 80, 13);
		add(lblFolderPath);
		
		textPixelStart = new JTextField();
		textPixelStart.setToolTipText("<html>"
                + "How Many Pixels Are Skipped Before Starting To Alter Them" + "<br>"
                + "From Left To Right, Then From Top To Bottom"
                + "</html>");
		textPixelStart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Integer.parseInt(textPixelStart.getText()) < 0) {textPixelStart.setText("0");}
				updPixelCounter();
			}
		});
		textPixelStart.setText("0");
		textPixelStart.setColumns(10);
		textPixelStart.setBounds(160, 205, 60, 20);
		add(textPixelStart);
		
		textPixelStep = new JTextField();
		textPixelStep.setToolTipText("<html>"
                + "How Much Of A Step Is Done When Switching Pixels" + "<br>"
                + "e.g. 1 Is The Next Pixel, 2 Skips Every Other Pixel"
                + "</html>");
		textPixelStep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Integer.parseInt(textPixelStep.getText()) < 1) {textPixelStep.setText("1");}
				updPixelCounter();
			}
		});
		textPixelStep.setText("1");
		textPixelStep.setColumns(10);
		textPixelStep.setBounds(230, 205, 60, 20);
		add(textPixelStep);
		
		textEncodedBits = new JTextField();
		textEncodedBits.setToolTipText("<html>"
                + "How Many Bits Per R/G/B/A Value Byte Are Encoded, From LSB To MSB" + "<br>"
                + "e.g. 3 Results In xxxxxXXX xxxxxXXX xxxxxXXX xxxxxXXX, where bits x are untouched and X convey hidden message"
                + "</html>");
		textEncodedBits.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Integer.parseInt(textEncodedBits.getText()) < 1) {textEncodedBits.setText("1");}
				else if (Integer.parseInt(textEncodedBits.getText()) > 8) {textEncodedBits.setText("8");}
				updPixelCounter();
			}
		});
		textEncodedBits.setText("1");
		textEncodedBits.setColumns(10);
		textEncodedBits.setBounds(300, 205, 60, 20);
		add(textEncodedBits);
		
		textMessage = new JTextField();
		textMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updPixelCounter();
			}
		});
		textMessage.setColumns(10);
		textMessage.setBounds(30, 150, 520, 30);
		add(textMessage);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage img = Steganographer.fileToImg(textFolderPath.getText() + "/" + textInput.getText());
				if (img == null) {lblConsole.setText("Error With Reading Folder Path Or Input File");}
				String message = textMessage.getText();
				int set1 = Integer.parseInt(textPixelStart.getText());
				int set2 = Integer.parseInt(textPixelStep.getText());
				int set3 = Integer.parseInt(textEncodedBits.getText());
				boolean set4 = bxR.isSelected();
				boolean set5 = bxG.isSelected();
				boolean set6 = bxB.isSelected();
				boolean set7 = bxA.isSelected();
				
				if (!bxRead.isSelected()) {
					if (textOutput.getText().length() < 3) {lblConsole.setText("Please Check Output File Name Correctness");}
					String format = textOutput.getText().substring(textOutput.getText().length() - 3, textOutput.getText().length());
					
					int correctness = Steganographer.getSettingsCorrectness(img.getWidth(), img.getHeight(), message, set1, set2, set3, set4, set5, set6, set7, format);
					if (correctness != 0) {lblConsole.setText(Steganographer.interpretCorrectness(correctness));}
					else {
						String filePath = textFolderPath.getText() + "/" + textOutput.getText();
						message = Steganographer.insertSizeSymbol(message);
						img = Steganographer.lsbHide(img, message, set1, set2, set3, set4, set5, set6, set7);
						boolean check = Steganographer.imgToFile(img, filePath, format);
						if (!check) {lblConsole.setText("Error With Reading Output Path");}
						else if (filePath.split("/").length == 1) {lblConsole.setText("Please Write Full Path");}
						else {
							//lblConsole.setText("Successfully Created File " + textOutput.getText() + "\nFull Path:\nblabla");
							lblConsole.setText("<html>"
					                + "Successfully Created File " + textOutput.getText() + "<br>"
					                + "Full Path:" + "<br>"
					                + filePath
					                + "</html>");
							}
					}
				}
				else {
					try {
						int msgLen = Steganographer.getMessageSize(img, set1, set2, set3, set4, set5, set6, set7);
						String decode = Steganographer.lsbRead(img, msgLen, set1, set2, set3, set4, set5, set6, set7);
						decode = decode.substring(1);
						textMessage.setText(decode);
						lblConsole.setText("Successfully Read Message");
					}
					catch (Exception ex) {lblConsole.setText("Hidden Message Not Found");}
				}
			}
		});
		btnRun.setBounds(30, 190, 60, 30);
		add(btnRun);

	}
	
	private void updPixelVars(int w, int h, String text, int s1, int s2, int s3, boolean s4, boolean s5, boolean s6, boolean s7) {
		try {
			int[] pix = Steganographer.getMessageFitness(w, h, text, s1, s2, s3, s4, s5, s6, s7);
			bits_needed = pix[0];
			bits_available = pix[1];
		}
		catch (Exception ex) {}
	}
	
	private void updPixelCounter() {
		BufferedImage img = Steganographer.fileToImg(textFolderPath.getText() + "/" + textInput.getText());
		try {
			updPixelVars(img.getWidth(), img.getHeight(), textMessage.getText(),
					Integer.parseInt(textPixelStart.getText()),
					Integer.parseInt(textPixelStep.getText()),
					Integer.parseInt(textEncodedBits.getText()),
					bxR.isSelected(), bxG.isSelected(), bxB.isSelected(), bxA.isSelected());
			lblPixelsCount.setText(bits_needed + " / " + bits_available);
			if (bits_needed <= bits_available) {lblPixelsCount.setForeground(Color.GREEN);}
			else {lblPixelsCount.setForeground(Color.RED);}
		}
		catch (Exception ex) {
			bits_needed = 0;
			bits_available = 0;
			lblPixelsCount.setText(bits_needed + " / " + bits_available);
			lblPixelsCount.setForeground(Color.BLACK);
		}
	}
 }

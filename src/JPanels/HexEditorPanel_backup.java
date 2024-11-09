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

public class HexEditorPanel_backup extends CustomPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textInput;
	private JTextField textFolderPath;
	
	private int bits_needed = 0;
	private int bits_available = 0;
	private JTextField textIntSigned;
	private JTextField textIntUnsigned;
	private JTextField textBin;
	private JTextField textHex;

	/**
	 * Create the panel.
	 */
	public HexEditorPanel_backup() {
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
		textInput.setBounds(380, 90, 170, 30);
		add(textInput);
		textInput.setColumns(10);
		
		
		
		JLabel lblInputPath = new JLabel("Input File Name");
		lblInputPath.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInputPath.setBounds(450, 70, 100, 13);
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
		btnLoad.setBounds(470, 130, 80, 30);
		add(btnLoad);
		
		JButton btnSave1 = new JButton("Save");
		btnSave1.setBounds(380, 130, 80, 30);
		add(btnSave1);
		
		JButton btnSave2 = new JButton("Save As New File");
		btnSave2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave2.setBounds(380, 170, 170, 30);
		add(btnSave2);
		
		textIntSigned = new JTextField();
		textIntSigned.setToolTipText("Example.png");
		textIntSigned.setColumns(10);
		textIntSigned.setBounds(380, 245, 80, 30);
		add(textIntSigned);
		
		textIntUnsigned = new JTextField();
		textIntUnsigned.setToolTipText("Example.png");
		textIntUnsigned.setColumns(10);
		textIntUnsigned.setBounds(470, 245, 80, 30);
		add(textIntUnsigned);
		
		textBin = new JTextField();
		textBin.setToolTipText("Example.png");
		textBin.setColumns(10);
		textBin.setBounds(380, 285, 170, 30);
		add(textBin);
		
		textHex = new JTextField();
		textHex.setToolTipText("Example.png");
		textHex.setColumns(10);
		textHex.setBounds(470, 326, 80, 30);
		add(textHex);
		
		JLabel lblSigned = new JLabel("signed");
		lblSigned.setHorizontalAlignment(SwingConstants.CENTER);
		lblSigned.setBounds(380, 230, 80, 15);
		add(lblSigned);
		
		JLabel lblUnsigned = new JLabel("unsigned");
		lblUnsigned.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnsigned.setBounds(470, 230, 80, 15);
		add(lblUnsigned);
		
		JLabel lblBin = new JLabel("bin");
		lblBin.setHorizontalAlignment(SwingConstants.CENTER);
		lblBin.setBounds(550, 292, 20, 15);
		add(lblBin);
		
		JLabel lblInt = new JLabel("int");
		lblInt.setHorizontalAlignment(SwingConstants.CENTER);
		lblInt.setBounds(550, 253, 20, 15);
		add(lblInt);
		
		JLabel lblHex = new JLabel("hex");
		lblHex.setHorizontalAlignment(SwingConstants.CENTER);
		lblHex.setBounds(550, 334, 20, 15);
		add(lblHex);

	}
 }

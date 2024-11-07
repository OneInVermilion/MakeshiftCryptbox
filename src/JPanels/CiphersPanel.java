package JPanels;

import javax.swing.JPanel;

import Classes.Cipherer;
import Classes.CustomPanel;
import Classes.MainConsole;

import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class CiphersPanel extends CustomPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textInput;
	private JTextField textOutput;
	private JTextField textCeasarKey;
	private JTextField textVigenereKey;
	private JTextField textCustomMap;
	private JTextField textVernamKey;

	/**
	 * Create the panel.
	 */
	public CiphersPanel() {
		setLayout(null);
		
		JLabel lblConsole = new JLabel("Hold Cursor Over Buttons To Gain Information");
		lblConsole.setVerticalAlignment(SwingConstants.BOTTOM);
		lblConsole.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsole.setBounds(30, 226, 520, 88);
		add(lblConsole);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new MainMenuPanel());
			}
		});
		btnBack.setBounds(10, 325, 80, 30);
		add(btnBack);
		
		JLabel lblInputCounter = new JLabel("0");
		lblInputCounter.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputCounter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInputCounter.setBounds(541, 16, 40, 15);
		add(lblInputCounter);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblInput.setBounds(0, 16, 50, 15);
		add(lblInput);
		
		JLabel lblOutputCounter = new JLabel("0");
		lblOutputCounter.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutputCounter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOutputCounter.setBounds(541, 58, 40, 15);
		add(lblOutputCounter);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(0, 56, 50, 15);
		add(lblOutput);
		
		JLabel lblVigenereCounter = new JLabel("0");
		lblVigenereCounter.setHorizontalAlignment(SwingConstants.CENTER);
		lblVigenereCounter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVigenereCounter.setBounds(541, 139, 40, 15);
		add(lblVigenereCounter);
		
		JLabel lblCustomCounter = new JLabel("0");
		lblCustomCounter.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomCounter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustomCounter.setBounds(541, 179, 40, 15);
		add(lblCustomCounter);
		
		JLabel lblVernamCounter = new JLabel("0");
		lblVernamCounter.setHorizontalAlignment(SwingConstants.CENTER);
		lblVernamCounter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVernamCounter.setBounds(541, 219, 40, 15);
		add(lblVernamCounter);
		
		JCheckBox chckbxVigenere = new JCheckBox("Decode");
		chckbxVigenere.setBounds(130, 130, 70, 30);
		add(chckbxVigenere);
		
		JCheckBox chckbxCustomMap = new JCheckBox("Decode");
		chckbxCustomMap.setBounds(130, 170, 70, 30);
		add(chckbxCustomMap);
		
		JCheckBox chckbxBase64 = new JCheckBox("Decode");
		chckbxBase64.setBounds(130, 250, 70, 30);
		add(chckbxBase64);
		
		textInput = new JTextField();
		textInput.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				lblInputCounter.setText(Integer.toString(textInput.getText().length()));
			}
		});
		textInput.setBounds(60, 10, 480, 30);
		add(textInput);
		textInput.setColumns(10);
		
		textOutput = new JTextField();
		textOutput.setEditable(false);
		textOutput.setColumns(10);
		textOutput.setBounds(60, 50, 480, 30);
		add(textOutput);
		
		JButton btnCeasar = new JButton("Ceasar");
		btnCeasar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Cipherer.isANumber(textCeasarKey.getText())) {
					textOutput.setText(Cipherer.Ceasar(textInput.getText(), Integer.parseInt(textCeasarKey.getText())));
				}
				else {
					textOutput.setText("Key Invalid");
				}
				lblOutputCounter.setText(Integer.toString(textOutput.getText().length()));
			}
		});
		btnCeasar.setToolTipText("<html>"
                + "Enter value to shift letters" + "<br>"
                + "Key 1: A -> B, B -> C" + "<br>"
                + "Key -2: C -> A, Z -> B"
                + "</html>");
		btnCeasar.setBounds(10, 90, 110, 30);
		add(btnCeasar);
		
		JButton btnVigenere = new JButton("Vigenère");
		btnVigenere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(lblInputCounter.getText()) == Integer.parseInt(lblVigenereCounter.getText())) { 
					textOutput.setText(Cipherer.Vigenere(textInput.getText(), textVigenereKey.getText(), !chckbxVigenere.isSelected()));
				}
				else {
					textOutput.setText("Equal Lengths of Input and Key Required");
				}
				lblOutputCounter.setText(Integer.toString(textOutput.getText().length()));
			}
		});
		btnVigenere.setToolTipText("<html>"
                + "Enter key of equal length to input" + "<br>"
                + "Letters are shifted based on key's letters" + "<br>"
                + "Key \'abz\': 1st letter shifted 1, 2nd letter shifted 2, 3rd letter shifted 26" + "<br>"
                + "Upper/Lower case doens't matter"
                + "</html>");
		btnVigenere.setBounds(10, 130, 110, 30);
		add(btnVigenere);
		
		JButton btnAtbash = new JButton("Atbash");
		btnAtbash.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnAtbash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCustomMap.setText("ZYXWVUTSRQPONMLKJIHGFEDCBA");
				lblCustomCounter.setText(Integer.toString(textCustomMap.getText().length()));
			}
		});
		btnAtbash.setToolTipText("<html>"
                + "Switches letters with their symmetric ones in the alphabet" + "<br>"
                + "A -> Z, B -> Y, etc"
                + "</html>");
		btnAtbash.setBounds(210, 170, 70, 30);
		add(btnAtbash);
		
		JButton btnCustomMap = new JButton("Cutsom Map");
		btnCustomMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Cipherer.CheckMapValidity(textCustomMap.getText())) {
					textOutput.setText(Cipherer.Keymap(textInput.getText(), textCustomMap.getText(), !chckbxCustomMap.isSelected()));
				}
				else {
					textOutput.setText("Map Invalid");
				}
				lblOutputCounter.setText(Integer.toString(textOutput.getText().length()));
			}
		});
		btnCustomMap.setToolTipText("<html>"
                + "Enter 26 non-repeating letters" + "<br>"
                + "A swapped with 1st letter, B swapped with 2nd letter, etc" + "<br>"
                + "Key \'zyx...cba\' is Atbash" + "<br>"
                + "Upper/Lower case doens't matter"
                + "</html>");
		btnCustomMap.setBounds(10, 170, 110, 30);
		add(btnCustomMap);
		
		JButton btnVernam = new JButton("Vernam");
		btnVernam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Cipherer.CheckSymbolsValidity(textInput.getText()) && Cipherer.CheckSymbolsValidity(textVernamKey.getText()))) {
					textOutput.setText("This Cipher Supports only English Letters, Numbers 0-9, Symbols + and /");
				}
				else if (!(Integer.parseInt(lblInputCounter.getText()) == Integer.parseInt(lblVernamCounter.getText()))) { 
					textOutput.setText("Equal Lengths of Input and Key Required");
				}
				else {
					textOutput.setText(Cipherer.Vernam(textInput.getText(), textVernamKey.getText()));
				}
				lblOutputCounter.setText(Integer.toString(textOutput.getText().length()));
			}
		});
		btnVernam.setToolTipText("<html>"
                + "Enter key of equal length to input" + "<br>"
                + "Input and Key are performed bitwise XOR operation to produce an output" + "<br>"
                + "Each symbol is 6 bits - 64 characters are supported" + "<br>"
                + "Supported: Upper- and lowercase english letters, numbers 0-9, symbols + and /" + "<br>"
                + "</html>");
		btnVernam.setBounds(10, 210, 110, 30);
		add(btnVernam);
		
		JButton btnBase64 = new JButton("Base64");
		btnBase64.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!chckbxBase64.isSelected()) {
					textOutput.setText(Cipherer.Base64Enc(textInput.getText()));
				}
				else {
					try {
						textOutput.setText(Cipherer.Base64Dec(textInput.getText()));
					}
					catch (Exception ex) {
						textOutput.setText("Input Invalid");
					}
				}
				lblOutputCounter.setText(Integer.toString(textOutput.getText().length()));
			}
		});
		btnBase64.setToolTipText("<html>"
                + "Symbols are transformed into binary octets" + "<br>"
                + "Then, the octets are regrouped into sextets" + "<br>"
                + "Consequentially, the output is 33.333% longer"
                + "</html>");
		btnBase64.setBounds(10, 250, 110, 30);
		add(btnBase64);
		
		textCeasarKey = new JTextField();
		textCeasarKey.setColumns(10);
		textCeasarKey.setBounds(130, 90, 70, 30);
		add(textCeasarKey);
		
		textVigenereKey = new JTextField();
		textVigenereKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblVigenereCounter.setText(Integer.toString(textVigenereKey.getText().length()));
			}
		});
		textVigenereKey.setColumns(10);
		textVigenereKey.setBounds(210, 130, 330, 30);
		add(textVigenereKey);
		
		textCustomMap = new JTextField();
		textCustomMap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblCustomCounter.setText(Integer.toString(textCustomMap.getText().length()));
			}
		});
		textCustomMap.setColumns(10);
		textCustomMap.setBounds(290, 170, 250, 30);
		add(textCustomMap);
		
		textVernamKey = new JTextField();
		textVernamKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblVernamCounter.setText(Integer.toString(textVernamKey.getText().length()));
			}
		});
		textVernamKey.setColumns(10);
		textVernamKey.setBounds(130, 210, 410, 30);
		add(textVernamKey);
		
		JButton btnMoveOI = new JButton("Move Output to Input");
		btnMoveOI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textInput.setText(textOutput.getText());
				textOutput.setText("");
				lblInputCounter.setText(Integer.toString(textInput.getText().length()));
				lblOutputCounter.setText(Integer.toString(textOutput.getText().length()));
			}
		});
		btnMoveOI.setToolTipText("");
		btnMoveOI.setBounds(375, 90, 165, 30);
		add(btnMoveOI);
		
		
		
		
		
		

	}
}

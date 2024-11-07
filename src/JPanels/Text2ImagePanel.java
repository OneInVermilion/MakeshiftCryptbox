package JPanels;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.CustomPanel;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;

public class Text2ImagePanel extends CustomPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public Text2ImagePanel() {
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new SteganographyPanel());
			}
		});
		btnBack.setBounds(10, 325, 80, 30);
		add(btnBack);
		
		textField = new JTextField();
		textField.setBounds(30, 50, 250, 30);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(300, 50, 250, 30);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(30, 110, 520, 30);
		add(textField_2);
		
		JLabel lblNewLabel = new JLabel("Input Path");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(115, 20, 80, 13);
		add(lblNewLabel);
		
		JLabel lblOutputPath = new JLabel("Output Path");
		lblOutputPath.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutputPath.setBounds(385, 10, 80, 13);
		add(lblOutputPath);
		
		JLabel lblOnlyForWriting = new JLabel("Only Used For Writing");
		lblOnlyForWriting.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlyForWriting.setBounds(350, 30, 150, 13);
		add(lblOnlyForWriting);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(250, 90, 80, 13);
		add(lblMessage);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(30, 285, 520, 30);
		add(textField_3);
		
		JButton btnNewButton = new JButton("LSB");
		btnNewButton.setBounds(30, 150, 60, 30);
		add(btnNewButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Read");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckbxNewCheckBox.setBounds(100, 150, 50, 30);
		add(chckbxNewCheckBox);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(160, 165, 60, 20);
		add(textField_4);
		
		JLabel lblPixelStart = new JLabel("Pixel Start");
		lblPixelStart.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblPixelStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblPixelStart.setBounds(160, 150, 60, 10);
		add(lblPixelStart);
		
		JLabel lblPixelStep = new JLabel("Pixel Step");
		lblPixelStep.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblPixelStep.setHorizontalAlignment(SwingConstants.CENTER);
		lblPixelStep.setBounds(230, 150, 60, 10);
		add(lblPixelStep);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(230, 165, 60, 20);
		add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(300, 165, 60, 20);
		add(textField_6);
		
		JLabel lblEncodedPixels = new JLabel("Enc Bits/Val");
		lblEncodedPixels.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncodedPixels.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEncodedPixels.setBounds(290, 150, 80, 10);
		add(lblEncodedPixels);
		
		JLabel lblNewLabel_1 = new JLabel("Involved Values: RGBA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(370, 160, 120, 13);
		add(lblNewLabel_1);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setBounds(490, 155, 20, 20);
		add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1.setBounds(510, 155, 20, 20);
		add(chckbxNewCheckBox_1_1);
		
		JCheckBox chckbxNewCheckBox_1_2 = new JCheckBox("");
		chckbxNewCheckBox_1_2.setBounds(470, 155, 20, 20);
		add(chckbxNewCheckBox_1_2);
		
		JCheckBox chckbxNewCheckBox_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1_1.setBounds(530, 155, 20, 20);
		add(chckbxNewCheckBox_1_1_1);
		
		JLabel lblPixelsNeeded = new JLabel("Pixels Needed / Available");
		lblPixelsNeeded.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblPixelsNeeded.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPixelsNeeded.setBounds(410, 90, 140, 10);
		add(lblPixelsNeeded);
		
		JLabel lblXY = new JLabel("x / y");
		lblXY.setForeground(new Color(0, 255, 0));
		lblXY.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXY.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblXY.setBounds(410, 100, 140, 10);
		add(lblXY);

	}
}

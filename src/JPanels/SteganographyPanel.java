package JPanels;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.CustomPanel;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SteganographyPanel extends CustomPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SteganographyPanel() {
		setLayout(null);
		
		JButton btnT2T = new JButton("Text to Text");
		btnT2T.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnT2T.setBounds(170, 112, 120, 60);
		add(btnT2T);
		
		JButton btnT2I = new JButton("Text to Image LSB");
		btnT2I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new Text2ImagePanel());
			}
		});
		btnT2I.setBounds(290, 112, 120, 60);
		add(btnT2I);
		
		JButton btnT2A = new JButton("Text to Audio");
		btnT2A.setBounds(170, 172, 120, 60);
		add(btnT2A);
		
		JButton btnI2I = new JButton("Image to Image");
		btnI2I.setBounds(290, 172, 120, 60);
		add(btnI2I);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new MainMenuPanel());
			}
		});
		btnBack.setBounds(10, 325, 80, 30);
		add(btnBack);

	}
}

package JPanels;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.CustomPanel;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends CustomPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		setLayout(null);
		
		JButton btnSteganography = new JButton("Steganography");
		btnSteganography.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new Text2ImagePanel());
			}
		});
		btnSteganography.setBounds(170, 112, 120, 60);
		add(btnSteganography);
		
		JButton btnCiphers = new JButton("Ciphers");
		btnCiphers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new CiphersPanel());
			}
		});
		btnCiphers.setBounds(290, 112, 120, 60);
		add(btnCiphers);
		
		JButton btnMetadata = new JButton("Metadata");
		btnMetadata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new MetadataPanel());
			}
		});
		btnMetadata.setBounds(170, 172, 120, 60);
		add(btnMetadata);
		
		JButton btnHexEditor = new JButton("Hex Editor");
		btnHexEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSwitchPanel(new HexEditorPanel2(), new HexInfoPanel2().scroll);
			}
		});
		btnHexEditor.setBounds(290, 172, 120, 60);
		add(btnHexEditor);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(240, 275, 100, 30);
		add(btnExit);

	}
}

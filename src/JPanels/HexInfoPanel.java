package JPanels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.CustomPanel;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;

public class HexInfoPanel extends CustomPanel {

	private static final long serialVersionUID = 1L;

	public JScrollPane scroll;
	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	int scale = 3;
	
	public HexInfoPanel() {
		setBackground(new Color(255, 255, 255));
		scroll = new JScrollPane(this);
		gbc.insets = new Insets(scale, scale, scale, scale);
		setLayout(gridBagLayout);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		fillPanel();
	}
	
	public void updPanel(byte[] bytes) {
		int row_size = 16;
		for (int i = 0; i < bytes.length; i++) {
			JLabel byte_here = new JLabel(Byte.toString(bytes[i]));
			gbc.gridx = i % row_size;
			gbc.gridy = i / row_size;
			add(byte_here, gbc);
		}
	}
	
	private void fillPanel() {
		int row_size = 16;
		for (int i = 0; i < 200; i++) {
			String n = Integer.toString(i%100);
			if (n.length() == 1) {n = "0" + n;}
			JLabel byte_here = new JLabel(n);
			gbc.gridx = i % row_size;
			gbc.gridy = i / row_size;
			add(byte_here, gbc);
		}
	}
}

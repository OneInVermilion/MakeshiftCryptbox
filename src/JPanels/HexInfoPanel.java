package JPanels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.CustomMouseListener;
import Classes.CustomPanel;
import Classes.Hexer;
import Classes.Tracker;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
	public ArrayList bytesList = new ArrayList();
	
	public HexInfoPanel() {
		setBackground(new Color(255, 255, 255));
		scroll = new JScrollPane(this);
		gbc.insets = new Insets(scale, scale, scale, scale);
		setLayout(gridBagLayout);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//fillPanel();
		
		Tracker.hexInfoPanel = this;
	}
	
	public void clearPanel() {
		for (int i = 0; i < bytesList.size(); i++) {
			remove((JLabel) bytesList.get(i));
			revalidate();
			repaint();
		}
	}
	
	public void updPanel(byte[] bytes, boolean showAsSymbols) {
		int row_size = 16;
		for (int i = 0; i < bytes.length; i++) {
			//JLabel byte_here = new JLabel(Byte.toString(bytes[i]));
			JLabel byte_here;
			if (showAsSymbols) {byte_here = new JLabel(Hexer.getByteRepresentations(bytes[i])[4]);}
			else {byte_here = new JLabel(Hexer.getByteRepresentations(bytes[i])[3]);}
			//byte_here.addMouseListener(new CustomMouseListener() {mouseClicked(e, byte_here);}});
			byte_here.addMouseListener(new CustomMouseListener(bytes[i], byte_here, i));
			gbc.gridx = i % row_size;
			gbc.gridy = i / row_size;
			add(byte_here, gbc);
			bytesList.add(byte_here);
			revalidate();
			repaint();
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
			bytesList.add(byte_here);
			revalidate();
			repaint();
		}
	}
}

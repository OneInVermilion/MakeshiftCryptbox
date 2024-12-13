package JPanels;

import javax.swing.JLabel;

import Classes.CustomMouseListener;
import Classes.CustomPanel;
import Classes.Hexer;
import Classes.Tracker;
import Classes.ByteLoaderThread;

import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class HexInfoPanel2 extends CustomPanel {

	private static final long serialVersionUID = 1L;

	public JScrollPane scroll;
	JTextArea hexTextArea;
	private String output;
	
	public HexInfoPanel2() {
		setBackground(new Color(255, 255, 255));
		scroll = new JScrollPane(this);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		hexTextArea = new JTextArea();
		scroll.setViewportView(hexTextArea);
		hexTextArea.setFont(new Font("Courier New", Font.PLAIN, 9));
		hexTextArea.setText("");
		
		Tracker.hexInfoPanel = this;
		
		revalidate();
		repaint();
	}
	
	public void run() {}
	
	private String iToLine(int i) {
		String s = Integer.toHexString(i);
		while (s.length() < 7) {s = "0" + s;}
		s = "\n" + s + "X ";
		return s;
	}
	
	public void updPanel(byte[] b) {
		String hex = " X  -->  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f";
		for (int i = 0; i <= b.length / 16; i += 1) {
			hex += iToLine(i);
			for (int j = 0; j < 16; j++) {
				int location = i * 16 + j;
				if (b.length - 1 < location) {break;}
				hex += Hexer.getByteRepresentations(b[location])[3];// + " ";
				if (j != 15) {hex += " ";}
			}
		}
		output = hex;
		hexTextArea.setText(output);
	}
	
	//find to later change byte in out string representation
	private int findByteInInfoString(int location) {
		location += 35;
		int true_loc = Hexer.findNthChar(output, ' ', location + 1) + 1;
		//System.out.println(true_loc + "|||" + output.charAt(true_loc) + "|" + output.charAt(true_loc + 1));
		return true_loc;
	}
	
	public void changeByteInInfoString(String hex, int location) {
		location = findByteInInfoString(location);
		output = output.substring(0, location) + hex + output.substring(location + 2);
		hexTextArea.setText(output);
	}
	
	public void clearPanel() {
		hexTextArea.setText("");
	}
}

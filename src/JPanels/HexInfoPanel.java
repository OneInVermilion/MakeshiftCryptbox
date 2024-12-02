package JPanels;

import javax.swing.JLabel;

import Classes.CustomMouseListener;
import Classes.CustomPanel;
import Classes.Hexer;
import Classes.Tracker;
import Classes.byteLoaderThread;

import java.awt.Insets;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

public class HexInfoPanel extends CustomPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	public JScrollPane scroll;
	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	int scale = 3;
	JLabel byte_here;
	
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
		if (bytesList != null) {
			for (int i = 0; i < bytesList.length; i++) {
				remove((JLabel) bytesList[i]);
				revalidate();
				repaint();
			}
		}
	}
	
	//LINEAR IMPLEMENTATION - IMPOSSIBLY BAD LOAD TIMES FOR BIGGER IMAGES
	/*public void updPanel(byte[] bytes, boolean showAsSymbols) {
		int row_size = 16;
		for (int i = 0; i < bytes.length; i++) {
			JLabel byte_here; //IS DEFINED IN THE BEGINNING OF CLASS FOR PARALLEL SECTION
			if (showAsSymbols) {byte_here = new JLabel(Hexer.getByteRepresentations(bytes[i])[4]);}
			else {byte_here = new JLabel(Hexer.getByteRepresentations(bytes[i])[3]);}
			byte_here.addMouseListener(new CustomMouseListener(bytes[i], byte_here, i));
			gbc.gridx = i % row_size;
			gbc.gridy = i / row_size;
			add(byte_here, gbc);
			bytesList.add(byte_here);
		}
		revalidate();
		repaint();
	}*/
	
	//PARALLEL IMPLEMENTATION
	public void updPanel(byte[] bytes, boolean showAsSymbols) {
		int num_threads = 4;
		bytesList = new JLabel[bytes.length];
		byteLoaderThread[] threads = new byteLoaderThread[num_threads];
		for (int i = 0; i < num_threads; i++) {
			byteLoaderThread thr = new byteLoaderThread(this, bytes, showAsSymbols, i, num_threads);
			threads[i] = thr;
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			while (threads[i].isAlive()) {}
		}
		
		int row_size = 16;
		for (int i = 0; i < bytesList.length; i++) {
			gbc.gridx = i % row_size;
			gbc.gridy = i / row_size;
			add(bytesList[i], gbc);
		}
		revalidate();
		repaint();
	}
	
	public void run() {
		
	}
	
	public void synchPanel(String[] s) {
		for (int i = 0; i < bytesList.length; i++) {
			((JLabel) bytesList[i]).setText(s[i]);
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
			bytesList[i] = byte_here;
			revalidate();
			repaint();
		}
	}
}

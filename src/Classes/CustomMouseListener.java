package Classes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import JPanels.HexEditorPanel;

public class CustomMouseListener implements MouseListener {

	private byte b;
	private JLabel jp;
	private int bpos;
	
	public CustomMouseListener (byte b, JLabel jp, int bpos) {
		this.b = b;
		this.jp = jp;
		this.bpos = bpos;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Tracker.selectedByte = b;
		Tracker.currentBytePanel = jp;
		Tracker.hexEditorPanel.updByteFields(Tracker.selectedByte);
		Tracker.selectedBytePosition = bpos;
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}

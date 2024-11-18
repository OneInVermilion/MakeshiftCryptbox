package Classes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import JPanels.HexEditorPanel;

public class CustomMouseListener implements MouseListener {

	private JLabel jp;
	private int bpos;
	
	public CustomMouseListener (JLabel jp, int bpos) {
		this.jp = jp;
		this.bpos = bpos;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Tracker.currentBytePanel = jp;
		Tracker.selectedBytePosition = bpos;
		Tracker.hexEditorPanel.updByteFields(Tracker.hexEditorPanel.fileBytes[bpos]);
	}



	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

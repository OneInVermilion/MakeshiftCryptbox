package Classes;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import JFrames.MainFrame;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel me;
	
	public CustomPanel() {
		me = this;
	}
	
	public void requestSwitchPanel(JPanel pan) {
		MainFrame parent = (MainFrame) SwingUtilities.getWindowAncestor(me);
			parent.switchPanel(pan);
	}
}